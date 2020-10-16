# cook your dish here

for x in range(int(input())):

    a,b,c,d=map(int,input().split())

    if(a<c and b==d):

        print('right')

    elif(a>c and b==d):

        print('left')

    elif(a==c and b<d):

        print('up')

    elif(a==c and b>d):

        print('down')

    else:

        print('sad')
