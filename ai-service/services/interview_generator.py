"""
Interview Generator Service

Responsibilities:
1. Accept extracted resume text.
2. Create AI prompt.
3. Call Gemini API.
4. Parse Gemini JSON response.
5. Return InterviewResponse DTO.
"""

import json
import google.generativeai as genai

from dto.interview_response import InterviewResponse


class InterviewGenerator:

    def __init__(self, model):
        self.model = model

    def start_interview(self, resume_text: str) -> InterviewResponse:

        prompt = f"""
You are an experienced Senior Software Engineering Interviewer.

Analyze the following resume carefully.

Resume:

{resume_text}

Your tasks are:

1. Extract the candidate's full name.

2. Generate a professional greeting.

3. Generate EXACTLY 15 interview questions.

Interview Structure:

Round 1 (Introduction)
- 2 Questions

Round 2 (Technical Skills)
- 5 Questions

Round 3 (Projects)
- 5 Questions

Round 4 (Internship / Experience)
- 1 Question
(If internship is unavailable, ask another project question.)

Round 5 (HR)
- 2 Questions

Rules:

• Questions must be personalized.

• Never ask generic DSA questions unless mentioned in resume.

• Questions should be based on:
  - Skills
  - Projects
  - Internship
  - Technologies
  - Achievements

Return ONLY valid JSON.

Example:

{{
    "candidate_name": "John Doe",

    "greeting":
    "Hello John! Welcome to your AI Interview. I have carefully analyzed your resume. Let's begin.",

    "questions": [

        "Tell me about yourself.",

        "Walk me through your resume.",

        "Explain Dependency Injection.",

        "What is JWT Authentication?",

        "Explain your Job Portal project.",

        "... remaining questions ..."
    ]
}}

Do NOT return markdown.

Do NOT return ```json.

Return ONLY JSON.
"""

        response = self.model.generate_content(prompt)

        data = json.loads(response.text)

        return InterviewResponse(

            candidate_name=data["candidate_name"],

            greeting=data["greeting"],

            questions=data["questions"]

        )