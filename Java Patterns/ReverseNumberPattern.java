import java.util.Scanner;
public class ReverseNumberPattern {


	public static void main(String[] args) {
		
		/* Your class should be named Solution.
	 	* Read input as specified in the question.
	 	* Print output as specified in the question.
		*/
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int i = 1;
        int val;
        while (i<=n){
            int j = 1;
            val = i;
            while (j<=i){
                System.out.print(val);
                val--;
                j = j+1;
            }
            System.out.println();
            i++;
        }

		
	}

}

