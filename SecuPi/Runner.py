# Name: 'Runner.py'
#
# Purpose: to execute all processes sequentially.
#
# Description:
# The module contains code that combines all the sub-processes and then runs them in correct order
# to finally produce the desired result.
#
#
# This code was developed by Kaivalya Vishwakumar Deshpande.
# Uploaded first on 23rd June, 2020.
# This was done with a motive to extend my PBL(Project Based Learning Course, FE: Semester: 2) Project.


from Mail_Handle import CreateMail
import Camera


def run():
    Camera.Capture()

    with open('Visitors/' + Camera.image_file_name, 'rb') as img_obj:
        mail = CreateMail(img_obj)

    mail.send()
