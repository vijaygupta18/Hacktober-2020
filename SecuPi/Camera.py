# Name: 'Camera.py'
#
# Purpose: to capture and store an image, programmatically.
#
# Description:
# The module contains code that captures and stores an image to the 'Visitors' directory.
#
#
# This code was developed by Kaivalya Vishwakumar Deshpande.
# Uploaded first on 23rd June, 2020.
# This was done with a motive to extend my PBL(Project Based Learning Course, FE: Semester: 2) Project.


from essential_imports import *

image_file_name = ''
reference = image_file_name


# Comparing the ids of 'reference' and 'image_file_name' might be used to check for
# external interference with the timestamped image file name. This is, however, not yet implemented.


def _get_name():
    global image_file_name
    image_file_name = datetime.now().strftime('%d%m%Y_%H%M%S') + '.jpg'

    # Timestamped images are produced.


def Capture():
    _get_name()
    with picamera.PiCamera() as camera:
        camera.resolution = (1280, 720)
        camera.capture('/home/pi/Documents/SecuPi/Visitors/' + image_file_name)
