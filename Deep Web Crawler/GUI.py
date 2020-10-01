import tkinter as tk
import Crawl

class Application(tk.Frame):
    def __init__(self, master=None):
        super().__init__(master)
        self.pack()
        self.create_widgets()

    def create_widgets(self):

        self.insert = tk.Entry(self)
        self.insert.pack(side="top")
        self.insert.focus_set()

        self.hi_there = tk.Button(self)
        self.hi_there["text"] = "Click Here"
        self.hi_there["command"] = self.say_hi
        self.hi_there.pack(side="top")

        self.quit = tk.Button(self, text="QUIT", fg="red",
                              command=root.destroy)
        self.quit.pack(side="bottom")

    def say_hi(self):
        #pass
        print("Executed")
        while 1:
            Crawl.Main(self.insert.get())


root = tk.Tk()
app = Application(master=root)
app.mainloop()