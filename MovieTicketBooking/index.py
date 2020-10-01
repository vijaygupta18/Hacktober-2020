from tkinter import *
from tkinter import messagebox
from tkinter.ttk import Combobox
import mysql.connector as connector
import pandas
import datetime as dt
import random

window = Tk()
window.geometry('1360x786')

myconnection = connector.connect(host='localhost', user='root', password='root', database='movieticket')
mycursor = myconnection.cursor()

main_heading = Label(window, text='THE CINEMA', font=('arial 28 bold underline'))
main_heading.pack()

select_city = Label(window, text='select city :', font=('arial', 20))
select_city.place(x=150, y=100)

select_city_list = ['Indore', 'Bhopal', 'Delhi', 'Mumbai', 'Banglore']
select_city_combo = Combobox(window, values=select_city_list)

select_city_combo.set('Indore')
select_city_combo.place(x=300, y=110)

canvas = Canvas(window, width=1360, height=1, bg='black')
canvas.place(x=0, y=150)

image1 = PhotoImage(file='terminator.png')
image2 = PhotoImage(file='zombieland.png')
image3 = PhotoImage(file='war.png')
card_image = PhotoImage(file='cards.png')


def display_movies():
    def movie1_details():
        movie1 = Toplevel()
        movie1.geometry('800x550+190+10')
        movie1.title('Zombieland Double Tap')
        topframe = Frame(movie1, width=800, height=50)

        select_language = Label(topframe, text='Select Language :', font=('arial', 10))
        select_language.place(x=400, y=20)

        i = StringVar()
        eng_radio = Radiobutton(topframe, text='English', value='English', variable=i)
        eng_radio.place(x=520, y=20)

        hin_radio = Radiobutton(topframe, text='Hindi', value='Hindi', variable=i)
        hin_radio.place(x=600, y=20)

        i.set('English')

        def back_click():
            movie1.destroy()

        back_button = Button(topframe, text='BACK', command=back_click)
        back_button.place(x=50, y=20)
        topframe.pack()

        frame = Frame(movie1, width=800, height=450)
        frame.pack()

        movie1_desc_tag = Label(frame, text='Movie Description:', font=('arial', 12))
        movie1_desc_tag.place(x=10, y=75)
        movie1_desc = Label(frame,
                            text='Zombieland Double Tap | Director: Ruben Fleischer | Stars: Jesse Eisenberg, Zoey Deutch, Woody Harrelson | duration: 1:33',
                            font=('arial', 10))
        movie1_desc.place(x=10, y=100)

        def show_timing():
            global i1
            i1 = StringVar()
            timing_radio1 = Radiobutton(frame, text='8:30', value='8:30', variable=i1)
            timing_radio1.place(x=425, y=200)

            timing_radio2 = Radiobutton(frame, text='16:40', value='16:40', variable=i1)
            timing_radio2.place(x=425, y=250)

            timing_radio3 = Radiobutton(frame, text='22:10', value='22:00', variable=i1)
            timing_radio3.place(x=425, y=300)

            i1.set('8:30')

        show_timings = Button(frame, text='Show Timings', command=show_timing)
        show_timings.place(x=425, y=150)

        def seats_window():
            movie1_seats = Toplevel()
            movie1_seats.geometry('800x550+190+10')
            movie1_seats.title('Seats')

            def loaddata():
                myconnection = connector.connect(host="localhost", user="root", passwd="root", database="movieticket")
                mycursor = myconnection.cursor()
                mycursor.execute('select * from seats1')
                result = mycursor.fetchall()
                df = pandas.DataFrame(result)
                textdata.insert(INSERT, df)
                textdata.config(state='disabled')

            textdata_label = Label(movie1_seats, text='Availability of seats :', font='arial 10 bold')
            textdata_label.place(x=475, y=2)

            load_data = Button(movie1_seats, text='Load data', command=loaddata)
            load_data.place(x=625, y=2)

            textdata = Text(movie1_seats, width=30, height=8)
            textdata.place(x=450, y=50)

            screen_label = Label(movie1_seats, text='SCREEN')
            screen_label.place(x=135, y=180)

            label = Label(movie1_seats, text='Make Your Selection:', font='arial 12 underline')
            label.place(x=75, y=100)

            silver_label = Label(movie1_seats, text='SILVER (Rs.300/seat)')
            silver_label.place(x=600, y=250)

            gold_label = Label(movie1_seats, text='GOLD (Rs.350/seat)')
            gold_label.place(x=600, y=345)

            platinum_label = Label(movie1_seats, text='PLATINUM (Rs.400/seat)')
            platinum_label.place(x=600, y=410)

            canvas = Canvas(movie1_seats, width=1360, height=1, bg='black')
            canvas.place(x=0, y=305)

            canvas = Canvas(movie1_seats, width=1360, height=1, bg='black')
            canvas.place(x=0, y=395)

            cost = []

            def a1():
                a1.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'A1 ')
                a1['state'] = DISABLED
                mycursor.execute("update seats1 set A = 'NA' where seat=1")
                myconnection.commit()

            def a2():
                a2.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'A2 ')
                a2['state'] = DISABLED
                mycursor.execute("update seats1 set A = 'NA' where seat=2")
                myconnection.commit()

            def a3():
                a3.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'A3 ')
                a3['state'] = DISABLED
                mycursor.execute("update seats1 set A = 'NA' where seat=3")
                myconnection.commit()

            def a4():
                a4.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'A4 ')
                a4['state'] = DISABLED
                mycursor.execute("update seats1 set A = 'NA' where seat=4")
                myconnection.commit()

            def a5():
                a5.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'A5 ')
                a5['state'] = DISABLED
                mycursor.execute("update seats1 set A = 'NA' where seat=5")
                myconnection.commit()

            def b1():
                b1.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'B1 ')
                b1['state'] = DISABLED
                mycursor.execute("update seats1 set B = 'NA' where seat=1")
                myconnection.commit()

            def b2():
                b2.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'B2 ')
                b2['state'] = DISABLED
                mycursor.execute("update seats1 set B = 'NA' where seat=2")
                myconnection.commit()

            def b3():
                b3.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'B3 ')
                b3['state'] = DISABLED
                mycursor.execute("update seats1 set B = 'NA' where seat=3")
                myconnection.commit()

            def b4():
                b4.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'B4 ')
                b4['state'] = DISABLED
                mycursor.execute("update seats1 set B = 'NA' where seat=4")
                myconnection.commit()

            def b5():
                b5.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'B5 ')
                b5['state'] = DISABLED
                mycursor.execute("update seats1 set B = 'NA' where seat=5")
                myconnection.commit()

            def c1():
                c1.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'C1 ')
                c1['state'] = DISABLED
                mycursor.execute("update seats1 set C = 'NA' where seat=1")
                myconnection.commit()

            def c2():
                c2.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'C2 ')
                c2['state'] = DISABLED
                mycursor.execute("update seats1 set C = 'NA' where seat=2")
                myconnection.commit()

            def c3():
                c3.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'C3 ')
                c3['state'] = DISABLED
                mycursor.execute("update seats1 set C = 'NA' where seat=3")
                myconnection.commit()

            def c4():
                c4.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'C4 ')
                c4['state'] = DISABLED
                mycursor.execute("update seats1 set C = 'NA' where seat=4")
                myconnection.commit()

            def c5():
                c5.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'C5 ')
                c5['state'] = DISABLED
                mycursor.execute("update seats1 set C = 'NA' where seat=5")
                myconnection.commit()

            def d1():
                d1.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'D1 ')
                d1['state'] = DISABLED
                mycursor.execute("update seats1 set D = 'NA' where seat=1")
                myconnection.commit()

            def d2():
                d2.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'D2 ')
                d2['state'] = DISABLED
                mycursor.execute("update seats1 set D = 'NA' where seat=2")
                myconnection.commit()

            def d3():
                d3.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'D3 ')
                d3['state'] = DISABLED
                mycursor.execute("update seats1 set D = 'NA' where seat=3")
                myconnection.commit()

            def d4():
                d4.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'D4 ')
                d4['state'] = DISABLED
                mycursor.execute("update seats1 set D = 'NA' where seat=4")
                myconnection.commit()

            def d5():
                d5.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'D5 ')
                d5['state'] = DISABLED
                mycursor.execute("update seats1 set D = 'NA' where seat=5")
                myconnection.commit()

            def e1():
                e1.configure(bg='light grey')
                cost.append(400)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'E1 ')
                e1['state'] = DISABLED
                mycursor.execute("update seats1 set E = 'NA' where seat=1")
                myconnection.commit()

            def e2():
                e2.configure(bg='light grey')
                cost.append(400)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'E2 ')
                e2['state'] = DISABLED
                mycursor.execute("update seats1 set E = 'NA' where seat=2")
                myconnection.commit()

            def e3():
                e3.configure(bg='light grey')
                cost.append(400)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'E3 ')
                e3['state'] = DISABLED
                mycursor.execute("update seats1 set E = 'NA' where seat=3")
                myconnection.commit()

            def e4():
                e4.configure(bg='light grey')
                cost.append(400)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'E4 ')
                e4['state'] = DISABLED
                mycursor.execute("update seats1 set E = 'NA' where seat=4")
                myconnection.commit()

            def e5():
                e5.configure(bg='light grey')
                cost.append(400)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'E5 ')
                e5['state'] = DISABLED
                mycursor.execute("update seats1 set E = 'NA' where seat=5")
                myconnection.commit()

            a1 = Button(movie1_seats, text='A1', command=a1)
            a1.place(x=50, y=225)
            a2 = Button(movie1_seats, text='A2', command=a2)
            a2.place(x=95, y=225)
            a3 = Button(movie1_seats, text='A3', command=a3)
            a3.place(x=140, y=225)
            a4 = Button(movie1_seats, text='A4', command=a4)
            a4.place(x=185, y=225)
            a5 = Button(movie1_seats, text='A5', command=a5)
            a5.place(x=230, y=225)
            b1 = Button(movie1_seats, text='B1', command=b1)
            b1.place(x=50, y=270)
            b2 = Button(movie1_seats, text='B2', command=b2)
            b2.place(x=95, y=270)
            b3 = Button(movie1_seats, text='B3', command=b3)
            b3.place(x=140, y=270)
            b4 = Button(movie1_seats, text='B4', command=b4)
            b4.place(x=185, y=270)
            b5 = Button(movie1_seats, text='B5', command=b5)
            b5.place(x=230, y=270)
            c1 = Button(movie1_seats, text='C1', command=c1)
            c1.place(x=50, y=315)
            c2 = Button(movie1_seats, text='C2', command=c2)
            c2.place(x=95, y=315)
            c3 = Button(movie1_seats, text='C3', command=c3)
            c3.place(x=140, y=315)
            c4 = Button(movie1_seats, text='C4', command=c4)
            c4.place(x=185, y=315)
            c5 = Button(movie1_seats, text='C5', command=c5)
            c5.place(x=230, y=315)
            d1 = Button(movie1_seats, text='D1', command=d1)
            d1.place(x=50, y=360)
            d2 = Button(movie1_seats, text='D2', command=d2)
            d2.place(x=95, y=360)
            d3 = Button(movie1_seats, text='D3', command=d3)
            d3.place(x=140, y=360)
            d4 = Button(movie1_seats, text='D4', command=d4)
            d4.place(x=185, y=360)
            d5 = Button(movie1_seats, text='D5', command=d5)
            d5.place(x=230, y=360)
            e1 = Button(movie1_seats, text='E1', command=e1)
            e1.place(x=50, y=405)
            e2 = Button(movie1_seats, text='E2', command=e2)
            e2.place(x=95, y=405)
            e3 = Button(movie1_seats, text='E3', command=e3)
            e3.place(x=140, y=405)
            e4 = Button(movie1_seats, text='E4', command=e4)
            e4.place(x=185, y=405)
            e5 = Button(movie1_seats, text='E5', command=e5)
            e5.place(x=230, y=405)

            textvar = IntVar()

            total_cost_label = Label(movie1_seats, text='Total cost:', font='arial 10 bold')
            total_cost_label.place(x=25, y=500)

            total_cost = Entry(movie1_seats, textvariable=textvar)
            total_cost.place(x=100, y=500)

            seats_label = Label(movie1_seats, text='seats:', font='arial 10 bold')
            seats_label.place(x=360, y=480)

            seats = Text(movie1_seats, width=30, height=3, wrap=WORD)
            seats.place(x=400, y=480)

            def back_click():
                movie1_seats.destroy()

            back_button = Button(movie1_seats, text='BACK', command=back_click)
            back_button.place(x=50, y=20)

            def reset():
                movie1_seats.destroy()
                seats_window()
                mycursor.execute("update seats1 set A ='A', B='A',C='A',D='A', E='A'")
                myconnection.commit()

            reset_button = Button(movie1_seats, text='RESET', command=reset)
            reset_button.place(x=100, y=20)

            def payment():
                movie1_payment = Toplevel()
                movie1_payment.geometry('600x450+190+40')
                movie1_payment.title('Payment')

                card_label = Label(movie1_payment, image=card_image)
                card_label.place(x=325, y=150)

                payment_label = Label(movie1_payment, text='PAYMENT', font=('arial', 20))
                payment_label.place(x=250, y=20)

                name_label = Label(movie1_payment, text='Enter Name :', font=('arial', 12))
                name_label.place(x=30, y=110)

                name_entry = Entry(movie1_payment)
                name_entry.place(x=175, y=115)

                phone_label = Label(movie1_payment, text='Phone Number :', font=('arial', 12))
                phone_label.place(x=30, y=155)

                phone_entry = Entry(movie1_payment)
                phone_entry.place(x=175, y=160)

                cardholder_label = Label(movie1_payment, text='Card Holder Name :', font=('arial', 12))
                cardholder_label.place(x=30, y=200)

                cardholder_entry = Entry(movie1_payment)
                cardholder_entry.place(x=175, y=200)

                cardno_label = Label(movie1_payment, text='Card Number :', font=('arial', 12))
                cardno_label.place(x=30, y=245)

                cardno_entry = Entry(movie1_payment)
                cardno_entry.place(x=175, y=245)

                def pay():
                    reciept_window = Toplevel()
                    reciept_window.geometry('600x600+190+10')
                    reciept_window.title('RECIEPT')

                    name_var = StringVar()
                    name_fetch = name_entry.get()
                    name_var.set(name_fetch)

                    phone_var = StringVar()
                    phone_fetch = phone_entry.get()
                    phone_var.set(phone_fetch)

                    lan_var = StringVar()
                    lan_fetch = i.get()
                    lan_var.set(lan_fetch)

                    timing_var = StringVar()
                    timing_fetch = i1.get()
                    timing_var.set(timing_fetch)

                    amount_var = StringVar()
                    amount_fetch = total_cost.get()
                    amount_var.set(amount_fetch)

                    city_var = StringVar()
                    city_fetch = select_city_combo.get()
                    city_var.set(city_fetch)

                    name_receipt_label = Label(reciept_window, text='Name :', font=('arial', 12))
                    name_receipt_label.place(x=20, y=50)

                    bookingID_receipt_label = Label(reciept_window, text='Booking ID :', font=('arial', 12))
                    bookingID_receipt_label.place(x=20, y=100)

                    date_receipt_label = Label(reciept_window, text='Date :', font=('arial', 12))
                    date_receipt_label.place(x=20, y=150)

                    phone_receipt_label = Label(reciept_window, text='Phone Number :', font=('arial', 12))
                    phone_receipt_label.place(x=20, y=200)

                    seatno_receipt_label = Label(reciept_window, text='Seat Number :', font=('arial', 12))
                    seatno_receipt_label.place(x=20, y=250)

                    movie_receipt_label = Label(reciept_window, text='Movie :', font=('arial', 12))
                    movie_receipt_label.place(x=20, y=315)

                    lan_receipt_label = Label(reciept_window, text='Language :', font=('arial', 12))
                    lan_receipt_label.place(x=20, y=360)

                    timing_receipt_label = Label(reciept_window, text='Timing :', font=('arial', 12))
                    timing_receipt_label.place(x=20, y=410)

                    amount_receipt_label = Label(reciept_window, text='Amount :', font=('arial', 12))
                    amount_receipt_label.place(x=20, y=460)

                    city_receipt_label = Label(reciept_window, text='City :', font=('arial', 12))
                    city_receipt_label.place(x=20, y=510)

                    name_receipt_entry = Entry(reciept_window, textvariable=name_var)
                    name_receipt_entry.place(x=140, y=55)

                    bookingID_receipt_entry = Entry(reciept_window)
                    bookingID_receipt_entry.place(x=140, y=105)
                    ID = random.randint(1, 11)
                    bookingID_receipt_entry.insert(INSERT, ID)

                    date_receipt_entry = Entry(reciept_window)
                    date_receipt_entry.place(x=140, y=155)
                    date = dt.date.today()
                    date_receipt_entry.insert(INSERT, date)

                    phone_receipt_entry = Entry(reciept_window, textvariable=phone_var)
                    phone_receipt_entry.place(x=140, y=205)

                    seatno_receipt_entry = Text(reciept_window, width=30, height=3)
                    seatno_receipt_entry.place(x=140, y=255)
                    seatno_receipt_entry.insert(INSERT, seats.get(0.1, END))

                    movie_receipt_entry = Entry(reciept_window, width=20)
                    movie_receipt_entry.place(x=140, y=320)
                    movie_receipt_entry.insert(INSERT, 'Zombieland')

                    lan_receipt_entry = Entry(reciept_window, textvariable=lan_var)
                    lan_receipt_entry.place(x=140, y=365)

                    timing_receipt_entry = Entry(reciept_window, textvariable=timing_var)
                    timing_receipt_entry.place(x=140, y=415)

                    amount_receipt_entry = Entry(reciept_window, textvariable=amount_var)
                    amount_receipt_entry.place(x=140, y=465)

                    city_receipt_entry = Entry(reciept_window, textvariable=city_var)
                    city_receipt_entry.place(x=140, y=515)

                    exit_button = Button(reciept_window, text='EXIT', command=reciept_window.destroy)
                    exit_button.place(x=400, y=400)

                    payqry = "insert into payment values({},'{}','{}','{}','{}','{}',{},'{}')".format(
                        bookingID_receipt_entry.get(),
                        date_receipt_entry.get(),
                        name_receipt_entry.get(),
                        phone_receipt_entry.get(),
                        seatno_receipt_entry.get(0.1, END),
                        movie_receipt_entry.get(),
                        amount_receipt_entry.get(),
                        city_receipt_entry.get())

                    mycursor.execute(payqry)
                    myconnection.commit()

                    timingqry = "insert into timings values({},'{}','{}','{}')".format(bookingID_receipt_entry.get(),
                                                                                       timing_receipt_entry.get(),
                                                                                       movie_receipt_entry.get(),
                                                                                       lan_receipt_entry.get())
                    mycursor.execute(timingqry)
                    myconnection.commit()

                pay_button = Button(movie1_payment, text='PAY', command=pay)
                pay_button.place(x=310, y=350)

                def back_clicked():
                    movie1_payment.destroy()

                back_button = Button(movie1_payment, text='BACK', command=back_clicked)
                back_button.place(x=250, y=350)

            next_button = Button(movie1_seats, text='NEXT', command=payment, font=('arial', 8))
            next_button.place(x=700, y=470)

        next_button = Button(frame, text='NEXT', command=seats_window, font=('arial', 8))
        next_button.place(x=650, y=350)

    # ------------------------------------------------------------------------------------------------

    def movie2_details():
        movie2 = Toplevel()
        movie2.geometry('800x550+190+10')
        movie2.title('Terminator: Dark Fate')
        topframe = Frame(movie2, width=800, height=50)

        select_language = Label(topframe, text='Select Language :', font=('arial', 10))
        select_language.place(x=400, y=20)

        i = StringVar()
        eng_radio = Radiobutton(topframe, text='English', value='English', variable=i)
        eng_radio.place(x=520, y=20)

        hin_radio = Radiobutton(topframe, text='Hindi', value='Hindi', variable=i)
        hin_radio.place(x=600, y=20)

        i.set('English')

        def back_click():
            movie2.destroy()

        back_button = Button(topframe, text='BACK', command=back_click)
        back_button.place(x=50, y=20)
        topframe.pack()

        frame = Frame(movie2, width=800, height=450)
        frame.pack()

        movie2_desc_tag = Label(frame, text='Movie Description:', font=('arial', 12))
        movie2_desc_tag.place(x=10, y=75)

        movie2_desc = Label(frame,
                            text='Terminator: Dark Fate | Director: Tim Miller | Stars: Linda Hamilton, Arnold Schwarzenegger, Mackenzie Davis | Duration:1:49',
                            font=('arial', 10))
        movie2_desc.place(x=10, y=100)

        def show_timing():
            global i1
            i1 = StringVar()
            timing_radio1 = Radiobutton(frame, text='11:30', value='11:30', variable=i1)
            timing_radio1.place(x=425, y=200)

            timing_radio2 = Radiobutton(frame, text='21:15', value='21:15', variable=i1)
            timing_radio2.place(x=425, y=250)

            i1.set('11:30')
        show_timings = Button(frame, text='Show Timings', command=show_timing)
        show_timings.place(x=425, y=150)

        def seats_window():
            movie1_seats = Toplevel()
            movie1_seats.geometry('800x550+190+10')
            movie1_seats.title('Seats')

            def loaddata():
                myconnection = connector.connect(host="localhost", user="root", passwd="root", database="movieticket")
                mycursor = myconnection.cursor()
                mycursor.execute('select * from seats2')
                result = mycursor.fetchall()
                df = pandas.DataFrame(result)
                textdata.insert(INSERT, df)
                textdata.config(state='disabled')

            textdata_label = Label(movie1_seats, text='Availability of seats :', font='arial 10 bold')
            textdata_label.place(x=475, y=2)

            load_data = Button(movie1_seats, text='Load data', command=loaddata)
            load_data.place(x=625, y=2)

            textdata = Text(movie1_seats, width=30, height=8)
            textdata.place(x=450, y=50)

            screen_label = Label(movie1_seats, text='SCREEN')
            screen_label.place(x=135, y=180)

            label = Label(movie1_seats, text='Make Your Selection:', font='arial 12 underline')
            label.place(x=75, y=100)

            silver_label = Label(movie1_seats, text='SILVER (Rs.300/seat)')
            silver_label.place(x=600, y=250)

            gold_label = Label(movie1_seats, text='GOLD (Rs.350/seat)')
            gold_label.place(x=600, y=345)

            platinum_label = Label(movie1_seats, text='PLATINUM (Rs.400/seat)')
            platinum_label.place(x=600, y=410)

            canvas = Canvas(movie1_seats, width=1360, height=1, bg='black')
            canvas.place(x=0, y=305)

            canvas = Canvas(movie1_seats, width=1360, height=1, bg='black')
            canvas.place(x=0, y=395)

            cost = []

            def a1():
                a1.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'A1 ')
                a1['state'] = DISABLED
                mycursor.execute("update seats2 set A = 'NA' where seat=1")
                myconnection.commit()

            def a2():
                a2.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'A2 ')
                a2['state'] = DISABLED
                mycursor.execute("update seats2 set A = 'NA' where seat=2")
                myconnection.commit()

            def a3():
                a3.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'A3 ')
                a3['state'] = DISABLED
                mycursor.execute("update seats2 set A = 'NA' where seat=3")
                myconnection.commit()

            def a4():
                a4.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'A4 ')
                a4['state'] = DISABLED
                mycursor.execute("update seats2 set A = 'NA' where seat=4")
                myconnection.commit()

            def a5():
                a5.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'A5 ')
                a5['state'] = DISABLED
                mycursor.execute("update seats2 set A = 'NA' where seat=5")
                myconnection.commit()

            def b1():
                b1.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'B1 ')
                b1['state'] = DISABLED
                mycursor.execute("update seats2 set B = 'NA' where seat=1")
                myconnection.commit()

            def b2():
                b2.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'B2 ')
                b2['state'] = DISABLED
                mycursor.execute("update seats2 set B = 'NA' where seat=2")
                myconnection.commit()

            def b3():
                b3.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'B3 ')
                b3['state'] = DISABLED
                mycursor.execute("update seats2 set B = 'NA' where seat=3")
                myconnection.commit()

            def b4():
                b4.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'B4 ')
                b4['state'] = DISABLED
                mycursor.execute("update seats2 set B = 'NA' where seat=4")
                myconnection.commit()

            def b5():
                b5.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'B5 ')
                b5['state'] = DISABLED
                mycursor.execute("update seats2 set B = 'NA' where seat=5")
                myconnection.commit()

            def c1():
                c1.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'C1 ')
                c1['state'] = DISABLED
                mycursor.execute("update seats2 set C = 'NA' where seat=1")
                myconnection.commit()

            def c2():
                c2.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'C2 ')
                c2['state'] = DISABLED
                mycursor.execute("update seats2 set C = 'NA' where seat=2")
                myconnection.commit()

            def c3():
                c3.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'C3 ')
                c3['state'] = DISABLED
                mycursor.execute("update seats2 set C = 'NA' where seat=3")
                myconnection.commit()

            def c4():
                c4.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'C4 ')
                c4['state'] = DISABLED
                mycursor.execute("update seats2 set C = 'NA' where seat=4")
                myconnection.commit()

            def c5():
                c5.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'C5 ')
                c5['state'] = DISABLED
                mycursor.execute("update seats2 set C = 'NA' where seat=5")
                myconnection.commit()

            def d1():
                d1.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'D1 ')
                d1['state'] = DISABLED
                mycursor.execute("update seats2 set D = 'NA' where seat=1")
                myconnection.commit()

            def d2():
                d2.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'D2 ')
                d2['state'] = DISABLED
                mycursor.execute("update seats2 set D = 'NA' where seat=2")
                myconnection.commit()

            def d3():
                d3.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'D3 ')
                d3['state'] = DISABLED
                mycursor.execute("update seats2 set D = 'NA' where seat=3")
                myconnection.commit()

            def d4():
                d4.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'D4 ')
                d4['state'] = DISABLED
                mycursor.execute("update seats2 set D = 'NA' where seat=4")
                myconnection.commit()

            def d5():
                d5.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'D5 ')
                d5['state'] = DISABLED
                mycursor.execute("update seats2 set D = 'NA' where seat=5")
                myconnection.commit()

            def e1():
                e1.configure(bg='light grey')
                cost.append(400)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'E1 ')
                e1['state'] = DISABLED
                mycursor.execute("update seats2 set E = 'NA' where seat=1")
                myconnection.commit()

            def e2():
                e2.configure(bg='light grey')
                cost.append(400)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'E2 ')
                e2['state'] = DISABLED
                mycursor.execute("update seats2 set E = 'NA' where seat=2")
                myconnection.commit()

            def e3():
                e3.configure(bg='light grey')
                cost.append(400)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'E3 ')
                e3['state'] = DISABLED
                mycursor.execute("update seats2 set E = 'NA' where seat=3")
                myconnection.commit()

            def e4():
                e4.configure(bg='light grey')
                cost.append(400)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'E4 ')
                e4['state'] = DISABLED
                mycursor.execute("update seats2 set E = 'NA' where seat=4")
                myconnection.commit()

            def e5():
                e5.configure(bg='light grey')
                cost.append(400)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'E5 ')
                e5['state'] = DISABLED
                mycursor.execute("update seats2 set E = 'NA' where seat=5")
                myconnection.commit()

            a1 = Button(movie1_seats, text='A1', command=a1)
            a1.place(x=50, y=225)
            a2 = Button(movie1_seats, text='A2', command=a2)
            a2.place(x=95, y=225)
            a3 = Button(movie1_seats, text='A3', command=a3)
            a3.place(x=140, y=225)
            a4 = Button(movie1_seats, text='A4', command=a4)
            a4.place(x=185, y=225)
            a5 = Button(movie1_seats, text='A5', command=a5)
            a5.place(x=230, y=225)
            b1 = Button(movie1_seats, text='B1', command=b1)
            b1.place(x=50, y=270)
            b2 = Button(movie1_seats, text='B2', command=b2)
            b2.place(x=95, y=270)
            b3 = Button(movie1_seats, text='B3', command=b3)
            b3.place(x=140, y=270)
            b4 = Button(movie1_seats, text='B4', command=b4)
            b4.place(x=185, y=270)
            b5 = Button(movie1_seats, text='B5', command=b5)
            b5.place(x=230, y=270)
            c1 = Button(movie1_seats, text='C1', command=c1)
            c1.place(x=50, y=315)
            c2 = Button(movie1_seats, text='C2', command=c2)
            c2.place(x=95, y=315)
            c3 = Button(movie1_seats, text='C3', command=c3)
            c3.place(x=140, y=315)
            c4 = Button(movie1_seats, text='C4', command=c4)
            c4.place(x=185, y=315)
            c5 = Button(movie1_seats, text='C5', command=c5)
            c5.place(x=230, y=315)
            d1 = Button(movie1_seats, text='D1', command=d1)
            d1.place(x=50, y=360)
            d2 = Button(movie1_seats, text='D2', command=d2)
            d2.place(x=95, y=360)
            d3 = Button(movie1_seats, text='D3', command=d3)
            d3.place(x=140, y=360)
            d4 = Button(movie1_seats, text='D4', command=d4)
            d4.place(x=185, y=360)
            d5 = Button(movie1_seats, text='D5', command=d5)
            d5.place(x=230, y=360)
            e1 = Button(movie1_seats, text='E1', command=e1)
            e1.place(x=50, y=405)
            e2 = Button(movie1_seats, text='E2', command=e2)
            e2.place(x=95, y=405)
            e3 = Button(movie1_seats, text='E3', command=e3)
            e3.place(x=140, y=405)
            e4 = Button(movie1_seats, text='E4', command=e4)
            e4.place(x=185, y=405)
            e5 = Button(movie1_seats, text='E5', command=e5)
            e5.place(x=230, y=405)

            textvar = IntVar()

            total_cost_label = Label(movie1_seats, text='Total cost:', font='arial 10 bold')
            total_cost_label.place(x=25, y=500)

            total_cost = Entry(movie1_seats, textvariable=textvar)
            total_cost.place(x=100, y=500)

            seats_label = Label(movie1_seats, text='seats:', font='arial 10 bold')
            seats_label.place(x=360, y=480)

            seats = Text(movie1_seats, width=30, height=3, wrap=WORD)
            seats.place(x=400, y=480)

            def back_click():
                movie1_seats.destroy()

            back_button = Button(movie1_seats, text='BACK', command=back_click)
            back_button.place(x=50, y=20)

            def reset():
                movie1_seats.destroy()
                seats_window()
                mycursor.execute("update seats2 set A ='A', B='A',C='A',D='A', E='A'")
                myconnection.commit()

            reset_button = Button(movie1_seats, text='RESET', command=reset)
            reset_button.place(x=100, y=20)

            def payment():
                movie1_payment = Toplevel()
                movie1_payment.geometry('600x450+190+40')
                movie1_payment.title('Payment')

                card_label = Label(movie1_payment, image=card_image)
                card_label.place(x=325, y=150)

                payment_label = Label(movie1_payment, text='PAYMENT', font=('arial', 20))
                payment_label.place(x=250, y=20)

                name_label = Label(movie1_payment, text='Enter Name :', font=('arial', 12))
                name_label.place(x=30, y=110)

                name_entry = Entry(movie1_payment)
                name_entry.place(x=175, y=115)

                phone_label = Label(movie1_payment, text='Phone Number :', font=('arial', 12))
                phone_label.place(x=30, y=155)

                phone_entry = Entry(movie1_payment)
                phone_entry.place(x=175, y=160)

                cardholder_label = Label(movie1_payment, text='Card Holder Name :', font=('arial', 12))
                cardholder_label.place(x=30, y=200)

                cardholder_entry = Entry(movie1_payment)
                cardholder_entry.place(x=175, y=200)

                cardno_label = Label(movie1_payment, text='Card Number :', font=('arial', 12))
                cardno_label.place(x=30, y=245)

                cardno_entry = Entry(movie1_payment)
                cardno_entry.place(x=175, y=245)

                def pay():
                    reciept_window = Toplevel()
                    reciept_window.geometry('600x600+190+10')
                    reciept_window.title('RECIEPT')

                    name_var = StringVar()
                    name_fetch = name_entry.get()
                    name_var.set(name_fetch)

                    phone_var = StringVar()
                    phone_fetch = phone_entry.get()
                    phone_var.set(phone_fetch)

                    lan_var = StringVar()
                    lan_fetch = i.get()
                    lan_var.set(lan_fetch)

                    timing_var = StringVar()
                    timing_fetch = i1.get()
                    timing_var.set(timing_fetch)

                    amount_var = StringVar()
                    amount_fetch = total_cost.get()
                    amount_var.set(amount_fetch)

                    city_var = StringVar()
                    city_fetch = select_city_combo.get()
                    city_var.set(city_fetch)

                    name_receipt_label = Label(reciept_window, text='Name :', font=('arial', 12))
                    name_receipt_label.place(x=20, y=50)

                    bookingID_receipt_label = Label(reciept_window, text='Booking ID :', font=('arial', 12))
                    bookingID_receipt_label.place(x=20, y=100)

                    date_receipt_label = Label(reciept_window, text='Date :', font=('arial', 12))
                    date_receipt_label.place(x=20, y=150)

                    phone_receipt_label = Label(reciept_window, text='Phone Number :', font=('arial', 12))
                    phone_receipt_label.place(x=20, y=200)

                    seatno_receipt_label = Label(reciept_window, text='Seat Number :', font=('arial', 12))
                    seatno_receipt_label.place(x=20, y=250)

                    movie_receipt_label = Label(reciept_window, text='Movie :', font=('arial', 12))
                    movie_receipt_label.place(x=20, y=315)

                    lan_receipt_label = Label(reciept_window, text='Language :', font=('arial', 12))
                    lan_receipt_label.place(x=20, y=360)

                    timing_receipt_label = Label(reciept_window, text='Timing :', font=('arial', 12))
                    timing_receipt_label.place(x=20, y=410)

                    amount_receipt_label = Label(reciept_window, text='Amount :', font=('arial', 12))
                    amount_receipt_label.place(x=20, y=460)

                    city_receipt_label = Label(reciept_window, text='City :', font=('arial', 12))
                    city_receipt_label.place(x=20, y=510)

                    name_receipt_entry = Entry(reciept_window, textvariable=name_var)
                    name_receipt_entry.place(x=140, y=55)

                    bookingID_receipt_entry = Entry(reciept_window)
                    bookingID_receipt_entry.place(x=140, y=105)
                    ID = random.randint(1, 101)
                    bookingID_receipt_entry.insert(INSERT, ID)

                    date_receipt_entry = Entry(reciept_window)
                    date_receipt_entry.place(x=140, y=155)
                    date = dt.date.today()
                    date_receipt_entry.insert(INSERT, date)

                    phone_receipt_entry = Entry(reciept_window, textvariable=phone_var)
                    phone_receipt_entry.place(x=140, y=205)

                    seatno_receipt_entry = Text(reciept_window, width=30, height=3)
                    seatno_receipt_entry.place(x=140, y=255)
                    seatno_receipt_entry.insert(INSERT, seats.get(0.1, END))

                    movie_receipt_entry = Entry(reciept_window, width=20)
                    movie_receipt_entry.place(x=140, y=320)
                    movie_receipt_entry.insert(INSERT, 'Terminator')

                    lan_receipt_entry = Entry(reciept_window, textvariable=lan_var)
                    lan_receipt_entry.place(x=140, y=365)

                    timing_receipt_entry = Entry(reciept_window, textvariable=timing_var)
                    timing_receipt_entry.place(x=140, y=415)

                    amount_receipt_entry = Entry(reciept_window, textvariable=amount_var)
                    amount_receipt_entry.place(x=140, y=465)

                    city_receipt_entry = Entry(reciept_window, textvariable=city_var)
                    city_receipt_entry.place(x=140, y=515)

                    exit_button = Button(reciept_window, text='EXIT', command=reciept_window.destroy)
                    exit_button.place(x=400, y=400)

                    payqry = "insert into payment values({},'{}','{}','{}','{}','{}',{},'{}')".format(
                        bookingID_receipt_entry.get(),
                        date_receipt_entry.get(),
                        name_receipt_entry.get(),
                        phone_receipt_entry.get(),
                        seatno_receipt_entry.get(0.1, END),
                        movie_receipt_entry.get(),
                        amount_receipt_entry.get(),
                        city_receipt_entry.get())

                    mycursor.execute(payqry)
                    myconnection.commit()

                    timingqry = "insert into timings values({},'{}','{}','{}')".format(bookingID_receipt_entry.get(),
                                                                                       timing_receipt_entry.get(),
                                                                                       movie_receipt_entry.get(),
                                                                                       lan_receipt_entry.get())
                    mycursor.execute(timingqry)
                    myconnection.commit()

                pay_button = Button(movie1_payment, text='PAY', command=pay)
                pay_button.place(x=310, y=350)

                def back_clicked():
                    movie1_payment.destroy()

                back_button = Button(movie1_payment, text='BACK', command=back_clicked)
                back_button.place(x=250, y=350)

            next_button = Button(movie1_seats, text='NEXT', command=payment, font=('arial', 8))
            next_button.place(x=700, y=470)

        next_button = Button(frame, text='NEXT', command=seats_window, font=('arial', 8))
        next_button.place(x=650, y=350)

    # --------------------------------------------------------------------------------------------------

    def movie3_details():
        movie3 = Toplevel()
        movie3.geometry('800x550+190+10')
        movie3.title('War')
        topframe = Frame(movie3, width=800, height=50)

        select_language = Label(topframe, text='Select Language :', font=('arial', 10))
        select_language.place(x=400, y=20)

        i = StringVar()
        eng_radio = Radiobutton(topframe, text='English', value='English', variable=i)
        eng_radio.place(x=520, y=20)

        hin_radio = Radiobutton(topframe, text='Hindi', value='Hindi', variable=i)
        hin_radio.place(x=600, y=20)

        i.set('English')

        def back_click():
            movie3.destroy()

        back_button = Button(topframe, text='BACK', command=back_click)
        back_button.place(x=50, y=20)
        topframe.pack()

        frame = Frame(movie3, width=800, height=450)
        frame.pack()

        movie3_desc_tag = Label(frame, text='Movie Description:', font=('arial', 12))
        movie3_desc_tag.place(x=10, y=75)

        movie3_desc = Label(frame,
                            text='War | Director: Siddharth Anand | Stars: Hrithik Roshan, Tiger Shroff, Vaani Kapoor | Duration: 2:23',
                            font=('arial', 10))
        movie3_desc.place(x=10, y=100)

        def show_timing():
            global i1
            i1 = StringVar()
            timing_radio1 = Radiobutton(frame, text='9:00', value='9:00', variable=i1)
            timing_radio1.place(x=425, y=200)

            timing_radio2 = Radiobutton(frame, text='16:20', value='16:20', variable=i1)
            timing_radio2.place(x=425, y=250)

            timing_radio3 = Radiobutton(frame, text='22:10', value='22:00', variable=i1)
            timing_radio3.place(x=425, y=300)

            i1.set('9:00')
        show_timings = Button(frame, text='Show Timings', command=show_timing)
        show_timings.place(x=425, y=150)

        def seats_window():
            movie1_seats = Toplevel()
            movie1_seats.geometry('800x550+190+10')
            movie1_seats.title('Seats')

            def loaddata():
                myconnection = connector.connect(host="localhost", user="root", passwd="root", database="movieticket")
                mycursor = myconnection.cursor()
                mycursor.execute('select * from seats3')
                result = mycursor.fetchall()
                df = pandas.DataFrame(result)
                textdata.insert(INSERT, df)
                textdata.config(state='disabled')

            textdata_label = Label(movie1_seats, text='Availability of seats :', font='arial 10 bold')
            textdata_label.place(x=475, y=2)

            load_data = Button(movie1_seats, text='Load data', command=loaddata)
            load_data.place(x=625, y=2)

            textdata = Text(movie1_seats, width=30, height=8)
            textdata.place(x=450, y=50)

            screen_label = Label(movie1_seats, text='SCREEN')
            screen_label.place(x=135, y=180)

            label = Label(movie1_seats, text='Make Your Selection:', font='arial 12 underline')
            label.place(x=75, y=100)

            silver_label = Label(movie1_seats, text='SILVER (Rs.300/seat)')
            silver_label.place(x=600, y=250)

            gold_label = Label(movie1_seats, text='GOLD (Rs.350/seat)')
            gold_label.place(x=600, y=345)

            platinum_label = Label(movie1_seats, text='PLATINUM (Rs.400/seat)')
            platinum_label.place(x=600, y=410)

            canvas = Canvas(movie1_seats, width=1360, height=1, bg='black')
            canvas.place(x=0, y=305)

            canvas = Canvas(movie1_seats, width=1360, height=1, bg='black')
            canvas.place(x=0, y=395)

            cost = []

            def a1():
                a1.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'A1 ')
                a1['state'] = DISABLED
                mycursor.execute("update seats3 set A = 'NA' where seat=1")
                myconnection.commit()

            def a2():
                a2.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'A2 ')
                a2['state'] = DISABLED
                mycursor.execute("update seats3 set A = 'NA' where seat=2")
                myconnection.commit()

            def a3():
                a3.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'A3 ')
                a3['state'] = DISABLED
                mycursor.execute("update seats3 set A = 'NA' where seat=3")
                myconnection.commit()

            def a4():
                a4.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'A4 ')
                a4['state'] = DISABLED
                mycursor.execute("update seats3 set A = 'NA' where seat=4")
                myconnection.commit()

            def a5():
                a5.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'A5 ')
                a5['state'] = DISABLED
                mycursor.execute("update seats3 set A = 'NA' where seat=5")
                myconnection.commit()

            def b1():
                b1.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'B1 ')
                b1['state'] = DISABLED
                mycursor.execute("update seats3 set B = 'NA' where seat=1")
                myconnection.commit()

            def b2():
                b2.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'B2 ')
                b2['state'] = DISABLED
                mycursor.execute("update seats3 set B = 'NA' where seat=2")
                myconnection.commit()

            def b3():
                b3.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'B3 ')
                b3['state'] = DISABLED
                mycursor.execute("update seats3 set B = 'NA' where seat=3")
                myconnection.commit()

            def b4():
                b4.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'B4 ')
                b4['state'] = DISABLED
                mycursor.execute("update seats3 set B = 'NA' where seat=4")
                myconnection.commit()

            def b5():
                b5.configure(bg='light grey')
                cost.append(300)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'B5 ')
                b5['state'] = DISABLED
                mycursor.execute("update seats3 set B = 'NA' where seat=5")
                myconnection.commit()

            def c1():
                c1.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'C1 ')
                c1['state'] = DISABLED
                mycursor.execute("update seats3 set C = 'NA' where seat=1")
                myconnection.commit()

            def c2():
                c2.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'C2 ')
                c2['state'] = DISABLED
                mycursor.execute("update seats3 set C = 'NA' where seat=2")
                myconnection.commit()

            def c3():
                c3.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'C3 ')
                c3['state'] = DISABLED
                mycursor.execute("update seats3 set C = 'NA' where seat=3")
                myconnection.commit()

            def c4():
                c4.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'C4 ')
                c4['state'] = DISABLED
                mycursor.execute("update seats3 set C = 'NA' where seat=4")
                myconnection.commit()

            def c5():
                c5.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'C5 ')
                c5['state'] = DISABLED
                mycursor.execute("update seats3 set C = 'NA' where seat=5")
                myconnection.commit()

            def d1():
                d1.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'D1 ')
                d1['state'] = DISABLED
                mycursor.execute("update seats3 set D = 'NA' where seat=1")
                myconnection.commit()

            def d2():
                d2.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'D2 ')
                d2['state'] = DISABLED
                mycursor.execute("update seats3 set D = 'NA' where seat=2")
                myconnection.commit()

            def d3():
                d3.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'D3 ')
                d3['state'] = DISABLED
                mycursor.execute("update seats3 set D = 'NA' where seat=3")
                myconnection.commit()

            def d4():
                d4.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'D4 ')
                d4['state'] = DISABLED
                mycursor.execute("update seats3 set D = 'NA' where seat=4")
                myconnection.commit()

            def d5():
                d5.configure(bg='light grey')
                cost.append(350)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'D5 ')
                d5['state'] = DISABLED
                mycursor.execute("update seats3 set D = 'NA' where seat=5")
                myconnection.commit()

            def e1():
                e1.configure(bg='light grey')
                cost.append(400)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'E1 ')
                e1['state'] = DISABLED
                mycursor.execute("update seats3 set E = 'NA' where seat=1")
                myconnection.commit()

            def e2():
                e2.configure(bg='light grey')
                cost.append(400)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'E2 ')
                e2['state'] = DISABLED
                mycursor.execute("update seats3 set E = 'NA' where seat=2")
                myconnection.commit()

            def e3():
                e3.configure(bg='light grey')
                cost.append(400)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'E3 ')
                e3['state'] = DISABLED
                mycursor.execute("update seats3 set E = 'NA' where seat=3")
                myconnection.commit()

            def e4():
                e4.configure(bg='light grey')
                cost.append(400)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'E4 ')
                e4['state'] = DISABLED
                mycursor.execute("update seats3 set E = 'NA' where seat=4")
                myconnection.commit()

            def e5():
                e5.configure(bg='light grey')
                cost.append(400)
                textvar.set((sum(cost)))
                seats.insert(INSERT, 'E5 ')
                e5['state'] = DISABLED
                mycursor.execute("update seats3 set E = 'NA' where seat=5")
                myconnection.commit()

            a1 = Button(movie1_seats, text='A1', command=a1)
            a1.place(x=50, y=225)
            a2 = Button(movie1_seats, text='A2', command=a2)
            a2.place(x=95, y=225)
            a3 = Button(movie1_seats, text='A3', command=a3)
            a3.place(x=140, y=225)
            a4 = Button(movie1_seats, text='A4', command=a4)
            a4.place(x=185, y=225)
            a5 = Button(movie1_seats, text='A5', command=a5)
            a5.place(x=230, y=225)
            b1 = Button(movie1_seats, text='B1', command=b1)
            b1.place(x=50, y=270)
            b2 = Button(movie1_seats, text='B2', command=b2)
            b2.place(x=95, y=270)
            b3 = Button(movie1_seats, text='B3', command=b3)
            b3.place(x=140, y=270)
            b4 = Button(movie1_seats, text='B4', command=b4)
            b4.place(x=185, y=270)
            b5 = Button(movie1_seats, text='B5', command=b5)
            b5.place(x=230, y=270)
            c1 = Button(movie1_seats, text='C1', command=c1)
            c1.place(x=50, y=315)
            c2 = Button(movie1_seats, text='C2', command=c2)
            c2.place(x=95, y=315)
            c3 = Button(movie1_seats, text='C3', command=c3)
            c3.place(x=140, y=315)
            c4 = Button(movie1_seats, text='C4', command=c4)
            c4.place(x=185, y=315)
            c5 = Button(movie1_seats, text='C5', command=c5)
            c5.place(x=230, y=315)
            d1 = Button(movie1_seats, text='D1', command=d1)
            d1.place(x=50, y=360)
            d2 = Button(movie1_seats, text='D2', command=d2)
            d2.place(x=95, y=360)
            d3 = Button(movie1_seats, text='D3', command=d3)
            d3.place(x=140, y=360)
            d4 = Button(movie1_seats, text='D4', command=d4)
            d4.place(x=185, y=360)
            d5 = Button(movie1_seats, text='D5', command=d5)
            d5.place(x=230, y=360)
            e1 = Button(movie1_seats, text='E1', command=e1)
            e1.place(x=50, y=405)
            e2 = Button(movie1_seats, text='E2', command=e2)
            e2.place(x=95, y=405)
            e3 = Button(movie1_seats, text='E3', command=e3)
            e3.place(x=140, y=405)
            e4 = Button(movie1_seats, text='E4', command=e4)
            e4.place(x=185, y=405)
            e5 = Button(movie1_seats, text='E5', command=e5)
            e5.place(x=230, y=405)

            textvar = IntVar()

            total_cost_label = Label(movie1_seats, text='Total cost:', font='arial 10 bold')
            total_cost_label.place(x=25, y=500)

            total_cost = Entry(movie1_seats, textvariable=textvar)
            total_cost.place(x=100, y=500)

            seats_label = Label(movie1_seats, text='seats:', font='arial 10 bold')
            seats_label.place(x=360, y=480)

            seats = Text(movie1_seats, width=30, height=3, wrap=WORD)
            seats.place(x=400, y=480)

            def back_click():
                movie1_seats.destroy()

            back_button = Button(movie1_seats, text='BACK', command=back_click)
            back_button.place(x=50, y=20)

            def reset():
                movie1_seats.destroy()
                seats_window()
                mycursor.execute("update seats3 set A ='A', B='A',C='A',D='A', E='A'")
                myconnection.commit()

            reset_button = Button(movie1_seats, text='RESET', command=reset)
            reset_button.place(x=100, y=20)

            def payment():
                movie1_payment = Toplevel()
                movie1_payment.geometry('600x450+190+40')
                movie1_payment.title('Payment')

                card_label = Label(movie1_payment, image=card_image)
                card_label.place(x=325, y=150)

                payment_label = Label(movie1_payment, text='PAYMENT', font=('arial', 20))
                payment_label.place(x=250, y=20)

                name_label = Label(movie1_payment, text='Enter Name :', font=('arial', 12))
                name_label.place(x=30, y=110)

                name_entry = Entry(movie1_payment)
                name_entry.place(x=175, y=115)

                phone_label = Label(movie1_payment, text='Phone Number :', font=('arial', 12))
                phone_label.place(x=30, y=155)

                phone_entry = Entry(movie1_payment)
                phone_entry.place(x=175, y=160)

                cardholder_label = Label(movie1_payment, text='Card Holder Name :', font=('arial', 12))
                cardholder_label.place(x=30, y=200)

                cardholder_entry = Entry(movie1_payment)
                cardholder_entry.place(x=175, y=200)

                cardno_label = Label(movie1_payment, text='Card Number :', font=('arial', 12))
                cardno_label.place(x=30, y=245)

                cardno_entry = Entry(movie1_payment)
                cardno_entry.place(x=175, y=245)

                def pay():
                    reciept_window = Toplevel()
                    reciept_window.geometry('600x600+190+10')
                    reciept_window.title('RECIEPT')

                    name_var = StringVar()
                    name_fetch = name_entry.get()
                    name_var.set(name_fetch)

                    phone_var = StringVar()
                    phone_fetch = phone_entry.get()
                    phone_var.set(phone_fetch)

                    lan_var = StringVar()
                    lan_fetch = i.get()
                    lan_var.set(lan_fetch)

                    timing_var = StringVar()
                    timing_fetch = i1.get()
                    timing_var.set(timing_fetch)

                    amount_var = StringVar()
                    amount_fetch = total_cost.get()
                    amount_var.set(amount_fetch)

                    city_var = StringVar()
                    city_fetch = select_city_combo.get()
                    city_var.set(city_fetch)

                    name_receipt_label = Label(reciept_window, text='Name :', font=('arial', 12))
                    name_receipt_label.place(x=20, y=50)

                    bookingID_receipt_label = Label(reciept_window, text='Booking ID :', font=('arial', 12))
                    bookingID_receipt_label.place(x=20, y=100)

                    date_receipt_label = Label(reciept_window, text='Date :', font=('arial', 12))
                    date_receipt_label.place(x=20, y=150)

                    phone_receipt_label = Label(reciept_window, text='Phone Number :', font=('arial', 12))
                    phone_receipt_label.place(x=20, y=200)

                    seatno_receipt_label = Label(reciept_window, text='Seat Number :', font=('arial', 12))
                    seatno_receipt_label.place(x=20, y=250)

                    movie_receipt_label = Label(reciept_window, text='Movie :', font=('arial', 12))
                    movie_receipt_label.place(x=20, y=315)

                    lan_receipt_label = Label(reciept_window, text='Language :', font=('arial', 12))
                    lan_receipt_label.place(x=20, y=360)

                    timing_receipt_label = Label(reciept_window, text='Timing :', font=('arial', 12))
                    timing_receipt_label.place(x=20, y=410)

                    amount_receipt_label = Label(reciept_window, text='Amount :', font=('arial', 12))
                    amount_receipt_label.place(x=20, y=460)

                    city_receipt_label = Label(reciept_window, text='City :', font=('arial', 12))
                    city_receipt_label.place(x=20, y=510)

                    name_receipt_entry = Entry(reciept_window, textvariable=name_var)
                    name_receipt_entry.place(x=140, y=55)

                    bookingID_receipt_entry = Entry(reciept_window)
                    bookingID_receipt_entry.place(x=140, y=105)
                    ID = random.randint(1, 101)
                    bookingID_receipt_entry.insert(INSERT, ID)

                    date_receipt_entry = Entry(reciept_window)
                    date_receipt_entry.place(x=140, y=155)
                    myconnection = connector.connect(host="localhost", user="root", passwd="root",
                                                     database="movieticket")
                    mycursor = myconnection.cursor()
                    qry = 'select curdate()'
                    mycursor.execute(qry)
                    date = mycursor.fetchall()
                    date_receipt_entry.insert(INSERT, date)

                    phone_receipt_entry = Entry(reciept_window, textvariable=phone_var)
                    phone_receipt_entry.place(x=140, y=205)

                    seatno_receipt_entry = Text(reciept_window, width=30, height=3)
                    seatno_receipt_entry.place(x=140, y=255)
                    seatno_receipt_entry.insert(INSERT, seats.get(0.1, END))

                    movie_receipt_entry = Entry(reciept_window, width=20)
                    movie_receipt_entry.place(x=140, y=320)
                    movie_receipt_entry.insert(INSERT, 'War')

                    lan_receipt_entry = Entry(reciept_window, textvariable=lan_var)
                    lan_receipt_entry.place(x=140, y=365)

                    timing_receipt_entry = Entry(reciept_window, textvariable=timing_var)
                    timing_receipt_entry.place(x=140, y=415)

                    amount_receipt_entry = Entry(reciept_window, textvariable=amount_var)
                    amount_receipt_entry.place(x=140, y=465)

                    city_receipt_entry = Entry(reciept_window, textvariable=city_var)
                    city_receipt_entry.place(x=140, y=515)

                    exit_button = Button(reciept_window, text='EXIT', command=reciept_window.destroy)
                    exit_button.place(x=400, y=400)

                    payqry = "insert into payment values({},'{}','{}','{}','{}','{}',{},'{}')".format(
                        bookingID_receipt_entry.get(),
                        date_receipt_entry.get(),
                        name_receipt_entry.get(),
                        phone_receipt_entry.get(),
                        seatno_receipt_entry.get(0.1, END),
                        movie_receipt_entry.get(),
                        amount_receipt_entry.get(),
                        city_receipt_entry.get())

                    mycursor.execute(payqry)
                    myconnection.commit()

                    timingqry = "insert into timings values({},'{}','{}','{}')".format(bookingID_receipt_entry.get(),
                                                                                       timing_receipt_entry.get(),
                                                                                       movie_receipt_entry.get(),
                                                                                       lan_receipt_entry.get())
                    mycursor.execute(timingqry)
                    myconnection.commit()

                pay_button = Button(movie1_payment, text='PAY', command=pay)
                pay_button.place(x=310, y=350)

                def back_clicked():
                    movie1_payment.destroy()

                back_button = Button(movie1_payment, text='BACK', command=back_clicked)
                back_button.place(x=250, y=350)

            next_button = Button(movie1_seats, text='NEXT', command=payment, font=('arial', 8))
            next_button.place(x=700, y=470)

        next_button = Button(frame, text='NEXT', command=seats_window, font=('arial', 8))
        next_button.place(x=650, y=350)

    # --------------------------------------------------------------------------------------------------
    city_confirmation = messagebox.askyesno('confirmation', 'are you sure you want the above selected city?')
    if city_confirmation:
        image_labell = Label(window, image=image1)
        image_labell.place(x=75, y=260)

        image_label2 = Label(window, image=image2)
        image_label2.place(x=475, y=260)

        image_label3 = Label(window, image=image3)
        image_label3.place(x=875, y=260)

        book_tickets_button1 = Button(window, text='TERMINATOR', command=movie2_details)
        book_tickets_button1.place(x=135, y=600)

        book_tickets_button2 = Button(window, text='ZOMBIELAND', command=movie1_details)
        book_tickets_button2.place(x=540, y=600)

        book_tickets_button3 = Button(window, text='WAR', command=movie3_details)
        book_tickets_button3.place(x=1020, y=455)


show_movies = Button(window, text='Show Movies', command=display_movies)
show_movies.place(x=600, y=200)

window.mainloop()