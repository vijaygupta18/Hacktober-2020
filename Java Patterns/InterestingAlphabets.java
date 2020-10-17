import java.util.Scanner;
public class InterestingAlphabets {
	public static void main(String[] args) {
		//Your code goes here
        
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int i =1;
        char ch = (char)(65+n);
        while(i<=n){
            int j = i;
            while (j>0){
                System.out.print((char)(ch-j));
                j--;
            }
            System.out.println();
        	i++;
            
        }
        
	}
}
