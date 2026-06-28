"""
This DTO represents the request sent by the frontend
when the user clicks the "Start Interview" button.

Flow:

React
    ↓
POST /start-interview
    ↓
InterviewRequest
"""

from pydantic import BaseModel


class InterviewRequest(BaseModel):
    """
    Request DTO for starting an AI interview.

    Attributes:
        resume_text (str): The complete extracted resume text.
    """

    resume_text: str