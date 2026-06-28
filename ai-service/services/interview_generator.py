# this consist of the business logic of the project for every api end points 
# right now main.py is contains prompt and all everything which is making it messy 
# the solution over that is main.py will basically call the service layer... then gemini... then interview response 
# basically excactly like a controller -> service -> repo 
# our main.py is acting like a controller layer where are actually writing the api code 
import google.generativeai as genai
from dto.interview_response import InterviewResponse

class InterviewGenerator:
    def __init__(self,model):
        self.model = model

    def start_interview(self,resume_text: str):

        # here we also supposed to write the prompt not in the main.py file 

        prompt = f"""
You are a senior software enginnering interviewer.
Analyze the following resume.

Resume:
{resume_text}

Perform the following tasks:

1. Extract the candidate's full name.
2. Generate a professional greeting.
3. Generate exactly 15 interview questions.

Rules:

- First 2 questions should be introductory.
- Next questions should be based on skills.
- Then ask project related questions.
- If intership exists, ask interships questions.
- Finish with HR questions.

Return ONLY JSON.

Example:
{{
"candidate_name" : "John Doe",
"greeting": "Hello John! Welcome to your AI interview.",

"questions":[
"...",
"...",
"..."
]
}}
"""
    response = self.model.generate_content(prompt)

    return response.text
