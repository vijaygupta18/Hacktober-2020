
def Sum(Arr):
    SumArray = 0
    for num in range(len(Arr)):
        SumArray+=Arr[num]
    return SumArray
  
def main():
    Arr = list(map(int,input("Enter the numbers: ").split()))
    SumArray = Sum(Arr)
    print("Sum of the Array: ",SumArray)
main()