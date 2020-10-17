import java.util.Scanner;
public class Lec4_SquarePattern {


	public static void main(String[] args) {
		
		/* Your class should be named Solution.
	 	* Read input as specified in the question.
	 	* Print output as specified in the question.
        
		*/
        
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int i = 1;
        while (i<=n){
            int j = 1;
            while (j<=n){
                System.out.print(n);
                j = j + 1;
                
            }
            System.out.println();
            i++;
        }

		
	}

}

