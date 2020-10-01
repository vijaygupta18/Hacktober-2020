## Creating functions for bionary search.
def binary(li,n,x):  ## Here We Just Given a name to fucntion.
    li.sort ## here Sorting is very important otherwise, the program can make errors or give wrong outputs.
    s=0  ## From here the basics logic get started.
    e=len(li)-1
    while s<=e:
        m=(s+e)//2
        if li[m]==x:
            return m
        elif x>li[m]:
            s=m+1
        elif x<li[m]:
            e=m-1
    return -1 ## here The Logic Ends. Now We take Inputs from User.
N=int(input())
li=[int(x) for x in input().split()]
t=int(input())
for i in  range(t):
    x=int(input())
    ans = binary(li,n,x) ## we Are Calling This Fucntion after the inputs are done.
    print(ans) ## if different variables are used then make sure to put in fucntion while Calling it.