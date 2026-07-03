from fastapi import FastAPI, HTTPException, UploadFile, File
from fastapi.middleware.cors import CORSMiddleware
from dotenv import load_dotenv
import google.generativeai as genai
import tempfile
import os

from dto.interview_request import InterviewRequest
from services.interview_generator import InterviewGenerator
from utils.resume_parser import extract_resume_parser

from dto.evaluation_request import EvaluationRequest
from services.evaluation_service import EvaluationService

# ==========================
# Load Environment Variables
# ==========================

load_dotenv()

# ==========================
# Configure Gemini
# ==========================

genai.configure(
    api_key=os.getenv("GEMINI_API_KEY")
)

model = genai.GenerativeModel(
    "models/gemini-2.5-flash"
)

# ==========================
# Create Service Objects
# ==========================

interview_generator = InterviewGenerator(model)

# here we have to create the evaluation_service 
evalation_service = EvaluationService(model)




app = FastAPI(
    title="AI Interview Copilot",
    description="AI Powered Interview System",
    version="2.0"
)

# ==========================
# CORS Configuration
# ==========================

app.add_middleware(
    CORSMiddleware,
    allow_origins=[
        "http://localhost:5173"
    ],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# ==========================
# Home API
# ==========================

@app.get("/")
def home():
    return {
        "message": "AI Interview Copilot Running Successfully"
    }

# ==========================
# Upload Resume API
# ==========================

@app.post("/upload-resume")
async def upload_resume(file: UploadFile = File(...)):

    try:

        with tempfile.NamedTemporaryFile(delete=False, suffix=".pdf") as temp_file:

            content = await file.read()

            temp_file.write(content)

            temp_path = temp_file.name

        resume_text = extract_resume_parser(temp_path)

        return {
            "resume_text": resume_text
        }

    except Exception as e:

        raise HTTPException(
            status_code=500,
            detail=str(e)
        )

# ==========================
# Start Interview API
# ==========================

@app.post("/start-interview")
def start_interview(request: InterviewRequest):

    try:

        return interview_generator.start_interview(
            request.resume_text
        )

    except Exception as e:

        raise HTTPException(
            status_code=500,
            detail=str(e)
        )

# ==========================
# Evaluate Answer API
# (Coming Soon)
# ==========================

# @app.post("/evaluate-answer")
# here we have to basically call that particular api 
# its an obvious that we have to use the try catch block 
# inside try block we have to write the logic 
# else it should throw an error 
# and finally block will basically make sure that we are going further 
@app.post("/evaluate-answer")
def evaluate_answer(request: EvaluationRequest):
    try:
        return evalation_service.evaluate_answer(
            request.question,
            request.answer
        )
    except Exception as e:
        raise HTTPException(
            status_code = 500,
            detail=str(e)
        )


# @app.post("/finish-interview")