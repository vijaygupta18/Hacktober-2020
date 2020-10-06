from flask import Flask,render_template,request
from algo import *

app = Flask(__name__)

@app.route('/')
def index():
    return render_template("index.html")

@app.route('/about')
def about():
    return render_template('about.html')

@app.route('/app',methods=["POST","GET"])
def conveterapp():
    if request.method=="POST":
        if request.form['board']=='MAKAUT,West Bengal':
            value=makaut_convert(request.form['marks'])
        elif request.form['board']=='CBSE Board':
            value=cbse_convert(request.form['marks'])
        elif request.form['board']=='ICSE Board':
            value=cbse_convert(request.form['marks'])
        else:
            value=cbse_convert(request.form['marks'])

        return render_template('result.html',value=value,board=request.form['board'],grade=request.form['marks'])
    
    return render_template('app.html')

@app.route('/contact')
def contact():
    return render_template('contact.html')



if __name__=="__main__":
    app.run(debug=True)
