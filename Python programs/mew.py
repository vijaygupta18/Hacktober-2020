# cook your dish here

t=int(input())

import math

for i in range(t):

    x,y=map(int,input().split())

    a=math.gcd(x,y)

    print((x//a)*(y//a))
