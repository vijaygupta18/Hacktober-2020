import requests
from bs4 import BeautifulSoup
import smtplib
import time

URL='https://www.amazon.in/Fossil-Collider-Hybrid-Smartwatch-Black/dp/B07SY4V5QQ?pf_rd_r=KM27HBWA0FZCBPMPVFC0&pf_rd_p=21db392f-09da-469c-8ab1-930474575afc&pd_rd_r=6f6342d5-bdbd-47e5-a392-2b249c97f6b2&pd_rd_w=tD7zZ&pd_rd_wg=ejVBx&ref_=pd_gw_ci_mcx_mr_hp_d'

headers ={"User-Agent":'Your user agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:81.0) Gecko/20100101 Firefox/81.0'}

def check_price(): 
      page = requests.get(URL,headers=headers)

      soup = BeautifulSoup(page.content,'html.parser')
      
      title = soup.find(id="productTitle").get_text()
      price = soup.find(id="priceblock_saleprice").get_text()
      converted_price = float(price[0:5])

      if(converted_price <  2000):
       send_mail()

      print(converted_price)
      print(title.strip())

      if(converted_price > 2000):
       send_mail()

def send_email():
    server = smtplib .SMTP('smtp.gmail.com', 587)
    server.ehlo()
    server.starttls()
    server.ehlo()

    server.login('181210017@nitdelhi.ac.in', 'cfpszmfijlmiejib')
    subject = 'Price fell Down!!!'
    body = 'check it out on Amazon https://www.amazon.in/Fossil-Collider-Hybrid-Smartwatch-Black/dp/B07SY4V5QQ?pf_rd_r=KM27HBWA0FZCBPMPVFC0&pf_rd_p=21db392f-09da-469c-8ab1-930474575afc&pd_rd_r=6f6342d5-bdbd-47e5-a392-2b249c97f6b2&pd_rd_w=tD7zZ&pd_rd_wg=ejVBx&ref_=pd_gw_ci_mcx_mr_hp_d'
    msg = f"Subject: {subject}\n\n{body}"
    server.sendmail('181210017@nitdelhi.ac.in','chakshush2307@gmail.com',msg)

    print('Email Sent!')

    server.quit()

while(True):
    check_price()
    time.sleep(60 * 60)