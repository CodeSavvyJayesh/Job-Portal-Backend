# in this file we are supposed to learn what should be response for the request provided in the request dto
# basically the response should be extraction of content of resume and then 

from pydantic import BaseModel
from typing import List

class InterviewResponse(BaseModel):
    candidate_name:str
    greeting:str
    questions: List[str]