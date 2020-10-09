from tkinter import *
"""
from chatterbot import Chatbot
from chatterbot.trainers import ListTrainer
import pyttsx3


engine=pyttsx3.init()
#voices will be go in voices variable
voices=engine.getProperty('voices')
engine.setProperty('voice',voices[1].id)


#convo list
bot = Chatbot("My bot")
convo = ['Hola',
         'Como estas?',
         'Donde vives?'
         'Qual idioma hablas?',
         'Que comistes hoy?',
         'Tienes una mascota'
         ]

#Train the bot
trainer=ListTrainer(bot)

trainer.train(convo)




def askbot():
    query=questionfield.get()
    #question got stored in a query
    answer=bot.get_response(query)
    #give answer to question
    textarea.insert(END, 'TU: '+ query+ '\n\n')
    textarea.insert(END, 'ROBOT: ' + str(answer)+ '\n\n')
    #say the answer
    engine.say(answer)
    engine.runAndWait()
    
    questionfield.delete(0,END)
    textarea.yview(END)
"""   
#-------------------------------
#UI
root = Tk()
root.geometry('500x570+100+30')
root.resizable(0,0)
root.title("La Platica")
root.config(bg='rosybrown2')


pic = PhotoImage(file='pic.png')

picture_Label = Label(root, image=pic, bg='rosybrown2')
picture_Label.pack(pady=5)

center_Frame = Frame(root)
center_Frame.pack()

scrollbar = Scrollbar(center_Frame)
scrollbar.pack(side=RIGHT, fill=Y)

textarea=Text(center_Frame,font=('times new roman',20,'bold'),width=80, height=10, yscrollcommand=scrollbar.set)
textarea.pack(side=LEFT, fill=BOTH)

#paded now versus in the begining so it won't be at the top
questionField = Entry(root, font=('verdana', 25, 'bold'))
questionField.pack(fill=X, pady=15)

askimage = PhotoImage(file='ask.png')
#note we want a hand cursor to hover over the ask button with  an active background color
askButton = Button(root, image=askimage, bd=0, bg= 'rosybrown2', activebackground = 'rosybrown2', cursor= 'hand2')
askButton.pack()

"""
def enter_function(event):
    askButton.invoke()

    
root.bind('<Return>', enter_function)


root.mainloop()
"""
