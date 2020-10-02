import tkinter as tk
import pyautogui as gui
from PIL import ImageGrab
import pyperclip
import sys

gui.PAUSE=1.5
gui.FAILSAFE = True



color_hex = None

def shutd(event):
    global root
    #   unbinding all shortcuts
    root.unbind("<Control-d>")
    root.unbind("<FocusOut>")
    root.unbind("<FocusIn>")
    root.unbind("<Control-c>")
    root.destroy()
    sys.exit(1)
    return "break"

def copy(event):
    pyperclip.copy(color_hex)

def out_f(event):
    app.attributes('-alpha', 0.3)   #   make transperant while not in focus

def in_f(event):
    app.attributes('-alpha', 1.0)   #   make opaque while in focus

def rgb2hex(r,g,b):
    return "#{:02x}{:02x}{:02x}".format(r,g,b)

# the root level window
root = tk.Tk()

root.attributes('-alpha', 0.0)  #    100% transperency
root.lift()
root.attributes('-topmost', True)
root.geometry('1x1')
root.focus_force()

#   binding shortcuts
root.bind("<Control-d>", shutd)
root.bind("<FocusOut>", out_f)
root.bind("<FocusIn>", in_f)
root.bind("<Control-c>", copy)

#   the main floating application
app = tk.Toplevel(root, relief='solid', borderwidth = 3, bg = '#ffffff')
app.attributes('-topmost', True)
app.overrideredirect(True)

#   widgets to display information
pos_lab = tk.Label(app, font=("Consolas", 12), bg = '#ffffff')
pos_lab.pack()
col_code_lab = tk.Label(app, font=("Consolas", 12), bg = '#ffffff')
col_code_lab.pack()
col_but = tk.Button(app, width=4)
col_but.pack()

screen_width = root.winfo_screenwidth()
screen_height = root.winfo_screenheight()
# print(screen_height, screen_width)

run_first = True
tk_h, tk_w = 0, 0

if __name__ == "__main__":
    try:
        while True: #   runs indefinately
            x, y = gui.position()   #   get mouse location

            #   for reducing image processing size, 1x1 screenshot under the mouse location is grabbed
            image = gui.screenshot(region=(x,y, 1, 1))
            color = image.getpixel((0, 0))  #   1x1 image has only one pixel
            color_hex = rgb2hex(*color)

            #   to restrict the hover window from overflowing the screen boundaries
            winX, winY = x+30, y+30
            if winX + tk_w >= screen_width:
                winX -= (tk_w + 30)
            if winY + tk_h >= screen_height:
                winY -= (tk_h + 30)

            #   cursor position
            pos_lab['text'] = f'({x}, {y})'
            pos_lab.update()

            #   pixel color in hex
            col_code_lab['text'] = color_hex
            col_code_lab.update()

            #   color vizualization
            col_but['bg'] = color_hex
            col_but.update()

            # move the app with the cursor movement
            app.geometry(f'+{winX}+{winY}')
            app.update()

            if run_first:
                tk_w = app.winfo_width()
                tk_h = app.winfo_height()
                run_first = False

        # root.mainloop()

    except Exception as e:  #   all exceptions are reached here
        # print('The following exception was raised!\n' + str(e))
        sys.exit(1)
