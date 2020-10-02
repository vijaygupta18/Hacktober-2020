#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Feb 23 10:53:44 2020

@author: kaydee
"""
import tkinter
import re


def func():
    
    sp = re.compile('[!@#$%^&*-+=\|;:/?._,<>]')
    a = r"[a-z]"
    n = r"[0-9]"
    
    s = r"{}".format(st.get())
    if len(s)==0 : 
        res.set(">>Invalid! Password can\'t be NULL!") 
        can.configure(bg = "red")
    elif len(s) < 8:
        can.configure(bg = "red")
        res.set(">>Invalid! Your password is {} character shorter.".format(8-len(s)))
    else: 
        sp_ = re.search(sp,s)
        a_ = re.search(a,s)
        n_ = re.findall(n,s)
        print(sp_)#debug
        print(a_)
        print(n_)
        if re.search(a,s) and len(n_)>=2 and re.search(sp,s):
             res.set(">>Accepted!") 
             can.configure(bg = "green")
             print("ok")
        elif re.search(a,s) == None:
            res.set(">>Invalid! Missing lower case alphabet!") 
            can.configure(bg = "red")
            
        elif len(n_)<2:
            res.set(">>Invalid! Missing {} numerical character!".format(2-len(n_))) 
            can.configure(bg = "red")
            
        else:
            res.set(">>Invalid! Missing special symbol!") 
            can.configure(bg = "red")
            
            
        
    print(s)#DEBUG

validator = tkinter.Tk()
validator.geometry("450x300")

validator.title('KayDee\'s password validator')
can = tkinter.Canvas(validator, height=90, width=350)
can.place(relx = 0.115, rely = 0.555)
validator.resizable(False,False)
st = tkinter.StringVar()
res = tkinter.StringVar()
tkinter.Label(validator,text = "\u2022 Your password must be atleast 8 character long.").place(relx = 0,rely = 0)
tkinter.Label(validator,text = "\u2022 Your password shall contain atleast:").place(relx = 0,rely = 0.1)
tkinter.Label(validator,text = "~One lower case alphabet").place(relx = 0.1,rely = 0.2)
tkinter.Label(validator,text = "~Two Numbers").place(relx = 0.1,rely = 0.3)
tkinter.Label(validator,text = "~One special symbol\n").place(relx = 0.1,rely = 0.4)

val = tkinter.Entry(validator,textvariable = st, width=28)
val.place(relx = 0.25, rely = 0.6)
button = tkinter.Button(validator, text='Validate!',width=25, command = lambda:[func()])
button.place(relx = 0.25, rely = 0.71)
con = tkinter.Canvas(validator, height=30, width=450,bg="white").place(relx=0,rely=0.9)
tkinter.Label(validator,text = "",textvariable = res,anchor="center",bg="white").place(relx = 0,rely = 0.92)

validator.mainloop()

