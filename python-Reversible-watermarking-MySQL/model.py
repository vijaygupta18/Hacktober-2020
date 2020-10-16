import connect_to_database
import generate_data

def delete_everything_table(conn,table_name):
	curr=conn.cursor()
	sql="TRUNCATE %s"%(table_name)
	try:
		curr.execute(sql)
		conn.commit()
		print "data deleted sucessfully"
	except:
		conn.rollback()
		print 'data not deleted'


def insert_into_table(conn,table_name):
	params=generate_data.generate_parameters()
	t_sql="INSERT INTO %s (a, b, c) "%(table_name)
	sql=t_sql+"VALUES (%s, %s, %s)"
	curr=conn.cursor()
	try:
		curr.executemany(sql,params)
		conn.commit()
		print "inserted_sucessfully"
	except:
		conn.rollback()
		print "data not inserted"	
		
def insert_param_into_table(conn,table_name,rows):
	conn=connect_to_database.connect()
	#rows=fetch_a_b_c_from_table(conn,'data')
	#rows=fetch_everything_from_table(conn,'data')
	#for x in rows:
		#print x[0],x[1],x[2],'\n'
	table_name=str(table_name)
	t_sql="INSERT INTO %s (a, b, c) "%(table_name)
	sql=t_sql+"VALUES (%s, %s, %s)"
	curr=conn.cursor()
	try:
		curr.executemany(sql,rows)
		conn.commit()
		print "inserted_sucessfully"
	except:
		conn.rollback()
		print "data not inserted"	



		



def create_table(conn,table_name):
	curr=conn.cursor()
	sql='''
	CREATE TABLE %s(
	ID int NOT NULL AUTO_INCREMENT,
	a int NOT NULL,
	b int NOT NULL,
	c int NOT NULL,
	PRIMARY KEY(ID))
	'''%(table_name)

	try:
		curr.execute(sql)
		conn.commit()
		print "table created"
	except:
		conn.rollback()
		print "table not created"


def fetch_everything_from_table(conn,table_name):
	curr=conn.cursor()
	curr.execute("SELECT * FROM %s"%(table_name))
	rows=curr.fetchall()
	return rows

def fetch_a_b_c_from_table(conn,table_name):
	curr=conn.cursor()
	curr.execute("SELECT a,b,c FROM %s"%(table_name))
	rows=curr.fetchall()
	return rows

def fetch_only_a_b_c_from_table(conn,table_name,id):
	curr=conn.cursor()
	try:
		curr.execute("SELECT a,b,c FROM %s WHERE ID = %d"%(table_name,id))
		row=curr.fetchone()
	except:
		print "Search not sucessfull"	
	return row

def update_only_a_b_c_in_table(conn,table_name,id,a,b,c):
	curr=conn.cursor()
	table_name=str(table_name)
	id=int(id)
	a=int(a)
	b=int(b)
	c=int(c)
	try:
		curr.execute("UPDATE %s SET a = %d,b = %d,c = %d WHERE ID = %d"%(table_name,a,b,c,id))
		conn.commit()
	except:
		conn.rollback()	

def update_all_a_b_c_in_table(conn,table_name,params):
	curr=conn.cursor()
	table_name=str(table_name)
	sql="UPDATE %s SET "%(table_name)
	sql=sql + "a = %s,b =%s,c = %s WHERE ID = %s"
	curr.executemany(sql,params)
	conn.commit()
	print "Updated sucessfully"		

if __name__ == '__main__':
	x=int(raw_input('''Enter 1 for deleting every row from table
Enter 2 for inserting rows\n'''))
	y=str(raw_input("Insert table name\n"))
	conn=connect_to_database.connect()
	if x == 1:	
		delete_everything_table(conn,y)	
	else:
		insert_into_table(conn,y)	

	connect_to_database.close(conn)




	