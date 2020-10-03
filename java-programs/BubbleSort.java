import java.util.*;
class BubbleSort
{
    void bubsort(int arr[])
    {
        int n = arr.length;
        int i,j,k;
        int min_index;
        
        for(i = 0;i<n-1;i++)
        {   
            for(j=0;j<n-i-1;j++)
            {
                if(arr[j]>arr[j+1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }          
        }
        System.out.println("The sorted array is:-");
        for(i=0;i<n;i++)
        {
            System.out.println(arr[i]);
        }
    }
}
class Prog3
{
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int arr[];
        int n;
        System.out.println("Enter number of elements in array:-");
        n = sc.nextInt();
        arr = new int[n];
        for(int i = 0;i<n;i++)
        {
            System.out.println("Enter element "+i);
            arr[i]= sc.nextInt();
        }
        BubbleSort b = new BubbleSort();
        b.bubsort(arr);
    }
}