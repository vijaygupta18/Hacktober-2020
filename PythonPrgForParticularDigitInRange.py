m=int(input())
n=int(input())
l1=[]
for i in range(m,n+1):
    l=[]
    for k in str(i):
        if k=='1' or k=='4' or k=='9':
            l.append(0)
        else:
            l.append(1)
    if sum(l)==0:
        l1.append(i)
print(len(l1))
    
