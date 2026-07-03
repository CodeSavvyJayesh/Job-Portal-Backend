# here in the response dto we have to make sure that our response will be generated from the gemini api itself 

from pydantic import BaseModel
from typing import List

class EvaluationResponse(BaseModel):
    # now thing what we have to write here ? we have to call the gemini api or what exactly ? 
    # its only job is to received the request it's like a container , it doesnt evaulate anything, it wont call gemini
    # basically it will store only question + answer 
    # here question and answer both will store on the basis of question and on the basis of user answer it will evaluate and then it will send the evaluation response 
    # basically in response it will show score, strengths, weakness, and better answer 
    
    score : int 
    strengths : List[str]
    weaknesses : List[str]
    ideal_answer : str

    # here we have created dto sucessfully 
    # now we have to think about the business logic that will take place inside the service folder 
    # there all business logic and prompt engineering will take place, call gemini, 

    # now we have to think carefully 
    # our feebaack card is just static for now .... we have to think about the dynamic nature of it ! 
