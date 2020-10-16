from tkinter import Tk,Radiobutton, Button,Label,StringVar,IntVar,Entry

class TipCalculator():
    def __init__(self):
        window = Tk()
        window.title("Tip Calculator App")
        window.configure(background="khaki")
        window.geometry("500x400")
        window.resizable(width=False,height=False)

        self.meal_cost = StringVar()
        self.tip_percent = IntVar()
        self.tip = StringVar()
        self.total_cost = StringVar()

        tip_per = Label(window,text="Tip Percentages",bg="purple",fg="white",width=14,height=2)
        tip_per.grid(column=0,row=0,padx=25,pady=25)

        bill_amt = Label(window,text="Bill Amount",bg="black",fg="white",width=14,height=2)
        bill_amt.grid(column=1,row=0,padx=25,pady=25)

        bill_amt_entry = Entry(window,textvariable=self.meal_cost,width=14)
        bill_amt_entry.grid(column=2,row=0)

        five_per = Radiobutton(window,text=" 5 %",variable=self.tip_percent,value=5)
        five_per.grid(column=0,row=1,padx=5,pady=5)
        ten_per = Radiobutton(window,text="10 %",variable=self.tip_percent,value=10)
        ten_per.grid(column=0,row=2,padx=5,pady=5)
        fifteen_per = Radiobutton(window,text="15 %",variable=self.tip_percent,value=15)
        fifteen_per.grid(column=0,row=3,padx=5,pady=5)
        twenty_per = Radiobutton(window,text="20 %",variable=self.tip_percent,value=20)
        twenty_per.grid(column=0,row=4,padx=5,pady=5)
        twentyfive_per = Radiobutton(window,text="25 %",variable=self.tip_percent,value=25)
        twentyfive_per.grid(column=0,row=5,padx=5,pady=5)

        tip_amt = Label(window,text="Tip Amount",bg="black",fg="white",width=14,height=2)
        tip_amt.grid(column=1,row=2)
        tip_amt_generated = Entry(window,textvariable=self.tip,width=14)
        tip_amt_generated.grid(column=2,row=2)

        bill_total = Label(window,text="Bill Total",bg="black",fg="white",width=14,height=2)
        bill_total.grid(column=1,row=5)
        bill_total_entry = Entry(window,textvariable=self.total_cost,width=14)
        bill_total_entry.grid(column=2,row=5)

        cal_btn = Button(window,text="Calculate",bg="navy",fg="white",width=14,height=2,command=self.calculate)
        cal_btn.grid(column=1,row=8,padx=25,pady=25)
        clear_btn = Button(window,text="Clear",bg="navy",fg="white",width=14,height=2,command=self.clear)
        clear_btn.grid(column=2,row=8)

        window.mainloop()

    def calculate(self):
        pre_tip = float(self.meal_cost.get())
        per = self.tip_percent.get() /100
        tip_amt_generated = pre_tip * per
        self.tip.set(tip_amt_generated)

        final_bill = pre_tip + tip_amt_generated
        self.total_cost.set(final_bill)

    def clear(self):
        self.total_cost.set("")
        self.meal_cost.set("")
        self.tip.set("")

TipCalculator()
