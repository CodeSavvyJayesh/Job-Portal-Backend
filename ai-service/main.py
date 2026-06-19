#this is where we will write all our ai layer code 
from fastapi import FastAPI
app = FastAPI()

@app.get("/")
def home():
    return{
        "message": "AI interview copilot running"
    }