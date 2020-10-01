from flask import Flask, flash, request, redirect, url_for ,render_template, jsonify
import json
import requests
from bs4 import BeautifulSoup

app = Flask(__name__)
@app.route('/')
def index():
    return get_statewise_data()

@app.route('/api/statewise')
def get_statewise_data():
    url = "https://www.mygov.in/corona-data/covid19-statewise-status/"
    page = requests.get(url)
    soup = BeautifulSoup(page.content, 'html.parser')
    div = soup.find_all('div', class_='field-item even')
    l = ["Andaman and Nicobar","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Dadra and Nagar Haveli and Daman and Diu","Delhi","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand","Karnataka","Kerala","Ladakh","Lakshadweep","Maharashtra","Manipur","Meghalaya","Mizoram","Madhya Pradesh","Nagaland","Odisha","Puducherry","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal"]
    dataset = {}
    for i in l:
        for j in range(len(div)):
            if i == div[j].text:
                temp = {
                    "TOTAL CONFIRMED":div[j+1].text,
                    "CURED/ DISCHARGED/ MIGRATED":div[j+2].text,
                    "DEATH":div[j+3].text
                    }
                dataset[div[j].text] = temp
    if dataset:
        return jsonify(dataset)
    else:
        return "Unable to proccess data please contact Developer wwww.github.com/sahiljena"