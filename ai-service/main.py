from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from dotenv import load_dotenv
import google.generativeai as genai
import os

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

# DTO
class ResumeRequest(BaseModel):
    resume_text: str


# Home API
@app.get("/")
def home():
    return {
        "message": "AI Interview Copilot Running Successfully"
    }


# Generate Questions API
@app.post("/generate-questions")
def generate_questions(request: ResumeRequest):

    try:

        prompt = f"""
        You are a senior technical interviewer.

        Analyze the following resume carefully.

        Resume:
        {request.resume_text}

        Generate:
        1. Five technical interview questions.
        2. Questions should be relevant to the candidate's skills.
        3. Do not provide answers.
        4. Return only the questions.
        """

        response = model.generate_content(prompt)

        return {
            "questions": response.text
        }

    except Exception as e:
        raise HTTPException(
            status_code=500,
            detail=str(e)
        )