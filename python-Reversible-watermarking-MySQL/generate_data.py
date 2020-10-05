import random
import sys
def generate_parameters():#unset 2nd and 4th bit
	params=[]
	for i in range(1,10000):
		a=random.randint(1,10000)
		b=random.randint(1,10000)
		c=random.randint(1,10000)
		temp=sys.maxint
		c=c&(temp^2)#unsetting 2nd bit
		c=c&(temp^8)#unsetting 4th bit
		params.append((a,b,c))

	return params


def print_paramters(row):
	for x in row:
		print x[0],x[1],x[2]

def check_bit(num):
	if (((num>>1)&1) & ((num>>3)&1)):
		return True;
	else:
		return False;

def check_bit_unset(row):
	flag=0
	for x in row:
		if check_bit(x[2]) == True:
			print x[2]
			flag=1

	if flag == 0:
		print "No numbers found" 		

if __name__ == '__main__':
		params=generate_parameters()
		print_paramters(params)	
		check_bit_unset(params)	
