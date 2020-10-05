from flask import Flask,render_template
import connect_to_database
import print_from_table
import hashlib
import model
import sys
def list():
	conn=connect_to_database.connect()
	rows=model.fetch_everything_from_table(conn,'data')
	connect_to_database.close(conn)
	return render_template('test.html',rows = rows)


def copy_data_from_src_to_dest(src,dest):
	conn=connect_to_database.connect()
	model.delete_everything_table(conn,dest)
	param=model.fetch_a_b_c_from_table(conn,src)
	model.insert_param_into_table(conn,dest,param)
	connect_to_database.close(conn)


def update():
	conn=connect_to_database.connect()
	rows=model.fetch_everything_from_table(conn,'data2')
	connect_to_database.close(conn)
	return render_template('test.html',rows = rows)

def hash(id):
	m=hashlib.md5()
	m.update('gdkjssaklhd14252e8967bvsvvc')
	m.update(str(id))
	return m.hexdigest()


def check_id(id):
	if int(hash(id),27)%11 == 0:
		return True
	else:
		return False	


def watermark(table_name):
	conn=connect_to_database.connect()
	count=0
	params=[]
	#print "Watermarked Tuples"
	for i in range(1,10000):
		if check_id(i) == True:
			row = model.fetch_only_a_b_c_from_table(conn,table_name,i)
			#model.update_only_a_b_c_in_table(conn,table_name,i,1,1,1)
			a_old,b_old,c_old=row[0],row[1],row[2]
			a8_old,b8_old=a_old%256,b_old%256
			a_mod,b_mod=a_old-a8_old,b_old-b8_old

			lbefore=(a8_old+b8_old)/2
			hbefore=a8_old - b8_old

			hdash=2*hbefore+1

			g=(hdash+1)/2
			f=hdash/2
			
			anew8,bnew8=lbefore+g,lbefore-f
			if ((anew8>=0 and anew8<256) and (bnew8>=0 and bnew8<256)):
				a_new=a_mod+anew8
				b_new=b_mod+bnew8
				c_new=c_old|10
				#print i,a_new,b_new,c_new
				params.append((a_new,b_new,c_new,i))
				count+=1
				#print i

	model.update_all_a_b_c_in_table(conn,table_name,params)			

	print "Total rows Updated %d"%count
	connect_to_database.close(conn)	


def reverse_watermark(table_name):
	conn=connect_to_database.connect()
	count=0
	params=[]
	#print "Reverse Watermarked Tuples"
	for i in range(1,10000):
		if check_id(i) == True:
			row = model.fetch_only_a_b_c_from_table(conn,table_name,i)
			#model.update_only_a_b_c_in_table(conn,table_name,i,1,1,1)
			a_old,b_old,c_old=row[0],row[1],row[2]
			a8_old,b8_old=a_old%256,b_old%256
			a_mod,b_mod=a_old-a8_old,b_old-b8_old

			lbefore=(a8_old+b8_old)/2
			hbefore=a8_old - b8_old

			hdash=hbefore/2

			g=(hdash+1)/2
			f=hdash/2

			anew8,bnew8=lbefore+g,lbefore-f
			if ((c_old&10)==10):
				a_new=a_mod+anew8
				b_new=b_mod+bnew8
				temp=sys.maxint
				c_new=c_old&(temp^10)

				#print i,a_new,b_new,c_new
				params.append((a_new,b_new,c_new,i))
				count+=1
				#print i

	model.update_all_a_b_c_in_table(conn,table_name,params)			

	print "Total rows Updated %d"%count
	connect_to_database.close(conn)		


def count_similarity(list1,list2):
	count=0;
	#print list1
	#print list2
	for i in range(0,9999):
		if (int(list1[i][0])==int(list2[i][0]))&(int(list1[i][1])==int(list2[i][1]))&(int(list1[i][2])==int(list2[i][2])):
			count+=1
			#print i+1

	per=(float(count)/9999)*100
	ans=9999-count
	print "Total tuples not matched %d "%(ans)
	return per		

def compare_tables(src,dest):
	conn=connect_to_database.connect()
	list1=model.fetch_a_b_c_from_table(conn,src)
	list2=model.fetch_a_b_c_from_table(conn,dest)
	ans = count_similarity(list1,list2)
	print ans

if __name__ == '__main__':
	while True:
		text=int(raw_input('''Enter 1 for refershing table_2
Enter 2 for watermarking table_2
Enter 3 for comparing original and watermarked table
Enter 4 for extracting watermark\n'''))
		if text == 1:
			copy_data_from_src_to_dest('data','data2')
		elif text==2:
			watermark('data2')
		elif text==3:
			compare_tables('data','data2')
		elif text==4:
			reverse_watermark('data2')	
		else:
			break		

	
