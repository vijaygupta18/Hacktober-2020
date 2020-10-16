from flask import Flask
import controller
app=Flask(__name__)

@app.route('/list')
def list():
	return controller.list()

@app.route('/update')
def update():
	return controller.update()


	
if __name__ == '__main__':
	app.run(debug = True)

