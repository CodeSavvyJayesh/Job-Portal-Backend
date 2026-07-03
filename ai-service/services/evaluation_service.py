#in this particular file all the business logic of the evaluation request and response dto will take place 
# we have to think about how can we write about it ? 
# inside the service class of springboot we had to use those @service layer annotation and all here we are not supposed to use those 
# its an obvious we have to import that google.genai here 
# we have to make sure we are importing the json as our response will be in form of that only 


import json
import google.generativeai as genai

from dto.evaluation_response import EvaluationResponse


class EvaluationService:

    def __init__(self, model):
        self.model = model    # we have used this as it stroes the gemini model so we can use it in every fun 


    def evaluate_answer(self, question: str, answer: str):  # here question + answer will be store  and prompt engineering will take place 

        prompt = f"""
You are a Senior Software Engineering Interviewer.

Evaluate the following interview answer professionally.

Interview Question:
{question}

Candidate Answer:
{answer}

Evaluate the answer based on:

1. Technical Accuracy
2. Communication Skills
3. Confidence
4. Completeness
5. Practical Understanding

Instructions:

- Give a score out of 10.
- Mention the strengths.
- Mention the weaknesses.
- Provide an ideal answer.
- Be constructive and encouraging.
- Return ONLY valid JSON.

Example Response:

{{
    "score": 8,
    "strengths": [
        "Good explanation",
        "Clear communication"
    ],
    "weaknesses": [
        "Need more practical examples",
        "Missed one important concept"
    ],
    "ideal_answer": "Dependency Injection is a design pattern in which..."
}}
"""

        response = self.model.generate_content(prompt)

        print("====GEMINI RESPONSE====")
        print(response.text)
        print("=======================")
        data = json.loads(response.text)

        return EvaluationResponse(
            score = data["score"],
            strengths = data["strengths"],
            weaknesses = data["weaknesses"],
            ideal_answer = data["ideal_answer"]
        )

       
