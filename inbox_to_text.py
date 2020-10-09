import email
import imaplib
from email.header import decode_header
import quopri

EMAIL = 'mail@example.com'
PASSWORD = 'Password'
SERVER = 'imap.server.com'


def get_mails():
    mail = imaplib.IMAP4_SSL(SERVER, 993)
    mail.login(EMAIL, PASSWORD)

    mail.select('inbox')

    status, data = mail.search(None, 'ALL')

    mail_ids = []

    for block in data:
        mail_ids += block.split()

    for i in mail_ids:
        status, data = mail.fetch(i, '(RFC822)')
        for response_part in data:
            if isinstance(response_part, tuple):
                message = email.message_from_bytes(response_part[1])
                mail_subject = quopri.decodestring(message.get("Subject")).decode()
                # for part in decode_header(mail_subject):
                #     decoded = str(*part)
                # mail_subject = decoded
                mail_from = message.get("From")
                mail_to = message.get("To")
                if message.is_multipart():
                    mail_content = ''
                    for part in message.walk():
                        content_type = part.get_content_type()
                        content_disposition = str(part.get("Content-Disposition"))
                        if content_type == 'text/plain':
                            charset = part.get_content_charset()
                            mail_content += part.get_payload(decode=True).decode(encoding=charset, errors="ignore")
                else:
                    charset = part.get_content_charset()
                    mail_content += part.get_payload(decode=True).decode(encoding=charset, errors="ignore")
                print(f'From: {mail_from}')
                print(f'To: {mail_to}')
                print(f'Subject: {mail_subject}')
                print(f'Content: {mail_content}')
                print('======================================================')
                create_file(mail_subject, mail_content)
        # delete mails
        # mail.store(i, '+FLAGS', '\\Deleted')
    # mail.expunge()
    mail.close()
    mail.logout()

def create_file(subject, content):
    f = open(subject + '.html','w')
    message = content
    f.write(message)
    f.close()

get_mails()
