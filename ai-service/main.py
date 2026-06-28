from fastapi import FastAPI, HTTPException
from fastapi import UploadFile, File 
from fastapi.middleware.cors import CORSMiddleware
from utils.resume_parser import extract_resume_parser
import tempfile
from pydantic import BaseModel
from dotenv import load_dotenv
import google.generativeai as genai
import os

# here we have to make the use of corsMiddleware in order to connect the frontend with the backend 

# in this main.py we have to make sure that should have above api:
# 1. /upload-resume
# 2. /start-interview
# 3. /evaluate-interview/answer 
# 4. /finish-interview   so basically only 4 endpoints are necessary 
# Load environment variables
load_dotenv()

# Configure Gemini
genai.configure(
    api_key=os.getenv("GEMINI_API_KEY")
)

# Gemini Model
model = genai.GenerativeModel(
    "models/gemini-2.5-flash"
)

# FastAPI App
app = FastAPI(
    title="AI Interview Copilot",
    description="Generate interview questions from resume",
    version="1.0"
)

# cors configuration 
app.add_middleware(
    CORSMiddleware,
    allow_origins=[
        "http://localhost:5173"
    ],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)
# DTO
class ResumeRequest(BaseModel):
    resume_text: str


# Home API
@app.get("/")
def home():
    return "This is ai interview copilot page "

# we have to create an api that will upload the resume 
@app.post("/upload-resume")
async def upload_resume(file:UploadFile=File(...)):
    try:
        # we have to use the try catch block in every single function in order to catch the errror 
        with tempfile.NamedTemporaryFile(delete=False, suffix=".pdf") as temp_file:
            content = await file.read()  # write a pdf to temp file 

            temp_file.write(content)

            temp_path = temp_file.name

            # extract the resume text 
            resume_text = extract_resume_parser(temp_path)

            return {
                "resume_text": resume_text
            }

    except Exception as e:
                raise HTTPException(
                    status_code = 500,
                    detail = str(e)
                )


# Generate Questions API 
# we have already created the busines logic of this particular api in the services layer 
# also we have return the data transfer object in the request,response format 
"""
@app.post("/start-interview")
def generate_questions(request: ResumeRequest):

    try:

        prompt = f
        You are a senior technical interviewer.

        Analyze the following resume carefully.

        Resume:
        {request.resume_text}

        Generate:
        1. Five technical interview questions.
        2. Questions should be relevant to the candidate's skills.
        3. Do not provide answers.
        4. Return only the questions.
        

        response = model.generate_content(prompt)

        return {
            "questions": response.text
        }

    except Exception as e:
        raise HTTPException(
            status_code=500,
            detail=str(e)
        )
"""

