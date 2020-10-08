# Name: 'Mail_Handle.py'
#
# Purpose: to define a template for ease in creating and sending emails.
#
# Description:
# The module contains code that defines a class 'CreateMail' to enhance code readability in the
# Runner module.
# The class below creates a secure connection with Gmail’s SMTP server, using the SMTP_SSL() of
# smtplib to initiate a TLS-encrypted connection (that is offered by Gmail).
# The default context of ssl validates the server host name, its CA certificates, and thus optimizes
# the security of the connection, as per Python's Security considerations.
# Further, the class also defines a send() method that brings together all the attachments and transfers
# the mail to the receiver's mail-box.
#
# This code was developed by Kaivalya Vishwakumar Deshpande.
# Uploaded first on 23rd June, 2020.
# This was done with a motive to extend my PBL(Project Based Learning Course, FE: Semester: 2) Project.

from essential_imports import *


class CreateMail:
    _sender = _receiver = "your_email_id@gmail.com"  # I've kept the Sending and Receiving ends as the same.
    # so, feel free to change that

    _entry = getpass.getpass()  # Would be better if this tightened 

    _message = MIMEMultipart()
    _message['Subject'] = 'Response to Your Image Request'
    _message['From'] = _sender
    _message['To'] = _receiver

    with open('embed.html', 'r') as html_file:
        html = html_file.read()
    html_file_to_embed = MIMEText(html, 'html')

    context = ssl.create_default_context()

    def __init__(self, img_file_obj):
        self.image_file = img_file_obj
        self.image_filename = img_file_obj.name

        CreateMail._get_image(self)

    def _get_image(self):
        img_data = self.image_file.read()
        image_to_attach = MIMEImage(img_data, name=self.image_filename)

        setattr(CreateMail, 'image_to_attach', image_to_attach)

    @staticmethod
    def _attach():
        CreateMail._message.attach(
            CreateMail.html_file_to_embed
        )
        CreateMail._message.attach(
            CreateMail.image_to_attach
        )

    @staticmethod
    def send():
        CreateMail._attach()

        with smtplib.SMTP_SSL('smtp.gmail.com', 465, context=CreateMail.context) as server:
            try:
                server.login(CreateMail._sender, CreateMail._entry)
                server.sendmail(
                    CreateMail._sender, CreateMail._receiver, CreateMail._message.as_string()
                )
                print('Mail Sent Successfully!')
            except smtplib.SMTPException:
                print("Some SMTPException has been Raised ...: ", end='')
                print(Exception)
