import MySQLdb as mysql
def connect():
	conn=mysql.connect('localhost','ankit','ankit@123','test')
	return conn


def close(conn):
	conn.close()
