from tkinter import Tk, Button, Label,DoubleVar,Entry

window = Tk()
window.title("Height Conversion App")
window.configure(background="mediumslateblue")
window.geometry("400x400")
window.resizable(width=False,height=False)

def convert():
    val = float(feet_entry.get())
    cm = val * 30.48
    cm_value.set("%.2f" %cm)

def clear():
    feet_value.set("")
    cm_value.set("")


feet_lbl = Label(window,text="Feet",bg="gold",fg="black",width=16)
feet_lbl.grid(column=0,row=0,padx=40,pady=40)

feet_value = DoubleVar()
feet_entry = Entry(window,textvariable=feet_value,width=16)
feet_entry.grid(column=1,row=0,pady=20)
feet_entry.delete(0,'end')

cm_lbl = Label(window,text="Centimeter",bg="gold",fg="black",width=16)
cm_lbl.grid(column=0,row=1,padx=20,pady=20)

cm_value = DoubleVar()
cm_entry = Entry(window,textvariable=cm_value,width=16)
cm_entry.grid(column=1,row=1,pady=20)
cm_entry.delete(0,'end')

convert_btn = Button(window,text="Convert",bg="darkgreen",fg="white",width=16,command=convert)
convert_btn.grid(column=0,row=3)

clear_btn = Button(window,text="Clear",bg="black",fg="white",width=16,command=clear)
clear_btn.grid(column=1,row=3,padx=20)


window.mainloop()
