import java.util.Scanner;
public class CharPattern1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		//String str = s.nextLine();
		//char ch = 'A';
		int i = 1;
		while (i<=n) {
			int j = 1;
			while (j<=n) {
				System.out.print((char)('A' +j-1));
				j = j+1;
			}
			
			System.out.println();
			i++;
		}

	}

}
