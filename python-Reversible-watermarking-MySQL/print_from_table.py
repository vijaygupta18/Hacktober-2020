import connect_to_database
def print_everything_from_table(conn,table_name):
	cursor=conn.cursor()
	cursor.execute("SELECT * FROM %s"%(table_name))
	results = cursor.fetchall()
	for row in results :
		print "ID = ",row[0]
		print "a = ",row[1]
		print "b = ",row[2]
		print "c = ",row[3],"\n"

def fetch_everything_from_table(conn,table_name):
	curr=conn.cursor()
	curr.execute("SELECT * FROM %s"%(table_name))
	rows=curr.fetchall()
	return rows

if __name__ == '__main__':
	text=raw_input("Enter the table name from which data needs to be printed\n")
	conn = connect_to_database.connect()
	print_everything_from_table(conn,text)
	conn.close()
