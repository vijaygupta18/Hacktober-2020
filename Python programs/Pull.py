# cook your dish here

# cook your dish here

t=int(input())

for x in range(t):

    n=int(input())

    s=n

    a=0

    while(n>0):

        r=n%10

        a=a*10+r

        n=n//10

    if(a%10!=0 and a==s):

        print("wins")

    else:

        print('losses')

        
