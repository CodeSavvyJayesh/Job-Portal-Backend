# in this file the resume parsing should take place ! 
# the important keywords from every section should be fetched 
# here we can install a pdf parser  : PyMuPDF (fitz) -> this is very fast , better text extraction, widely used in production 

# here we have resume.pdf -> extract text -> return string 


import fitz    # its a module provided by pymudpdf to allow open pdf, read page, extract text 
def extract_resume_parser(file_path: str):    # here the input will be the pdf and output would be complete resume text 
    document = fitz.open(file_path)   # this will open the pdf

    text = ""    # initially our extracted text would be 
    for page in document :    # suppose our resume having 2 pages then it will use the looping page1,page2 
        text += page.get_text()     # extract page and appends it   eg: name : jayesh, skills: java, react, springboot 
    
    document.close()    # we have to close the pdfs

    return text   # atlast we have to return the text which will later use for the question genera
