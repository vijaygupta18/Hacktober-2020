# Name: 'Secupi.py'
#
# Purpose: to configure the Trigger.
#
# Description:
# The module contains code that acts as a Trigger, whenever a predefined state change* is detected.
#
#
# *Predefined state change:
# A state change, defined in a program, to which that program must respond.
# Specifically, for this project, ideally the state change of the doorbell from open to closed
# must trigger necessary actions.
# Currently, the trigger is only a Request from the User through Telegram.
# This needs to be dealt with in future.
#
# This code was developed by Kaivalya Vishwakumar Deshpande.
# Uploaded first on 23rd June, 2020.
# This was done with a motive to extend my PBL(Project Based Learning Course, FE: Semester: 2) Project.


from essential_imports import *
import Runner

_Auth_Token = 'Your_Auth_Token'  # Requires more Security

_emer = '/<Your Emergency Shutdown passcode>'  # Requires more Security


def _stop_and_shutdown():  # Requires more Security
    call('sudo shutdown -h now', shell=True)


def handle(msg):
    chat_id = msg['chat']['id']
    command = msg['text']

    print('Received:')
    print(command)

    # The following if statement contains the Triggering Condition. The block will thus contain
    # necessary actions.
    if command == '/get':
        _bot.sendMessage(chat_id, str('Processing Your Request...'))

        Runner.run()  # triggering the main sequence here

        _bot.sendMessage(chat_id, str('Your Command has been Processed! Check your Mailbox!'))

    # Currently, to avoid a security breach, I have set up this crude method (elif block) that would
    # shut the Pi down as a whole. It requires a passcode, defined as _emer that is known only
    # to the user.
    # Despite searching for and trying to develop snippets to stop the telebot thread, all my
    # attempts were unsuccessful.
    # Recast this method.

    elif command == _emer:  # Requires more Security
        _bot.sendMessage(chat_id, str('Halting the Code'))
        _stop_and_shutdown()


# Establishing Connection with the Bot:

_bot = telepot.Bot(_Auth_Token)  # Requires more Security

MessageLoop(_bot, handle).run_as_thread()  # Requires more Security
print('Listening.....')

while 1:
    sleep(10)
