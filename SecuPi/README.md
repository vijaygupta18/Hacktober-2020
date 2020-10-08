# SecuPi
A small scale implementation of Trigger-Based Home Security

# Required Hardware

* The Raspberry Pi 3B/B+ or newer; and
* A Camera module for the Pi

# Installing Secupi
#### Make sure you have Python 3 installed on your Pi, and an active Telegram Bot

* Clone this Repository onto the Pi
* Install the dependencies. You can easily find those you are missing from <code>essential_imports.py</code>
* Inside <code>Secupi.py</code>, add your bot's API Key and an emergency password, within the spaces indicated
* Within <code>Mail_Handle.py</code>, add your E-mail adderss. You may even choose to give your Pi it's own address. :)
* Congratulations! The Program is now all installed!

# Running Secupi
#### Make sure that your Pi is connected to the same network as your Phone. This crude mechanic will be removed in newer versions!

* With the LXTerminal open in your local repository run the command: <code>python3 Secupi.py</code>
* Hit the bot with the following message: <code>/get</code>
* If you see a series of messages, finally ending with 'Your Command has been Processed! Check your Mailbox!,' you are all set!

## Enjoy!