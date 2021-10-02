import java.util.Scanner;

public class ReplaceVowelsWithNumbers{

public static void main(String...arg){

    //Initialize the string and enter the string value from the Console

    String s=new Scanner(System.in).

    nextLine(); 

    //Displaying of original and converted strings

    //Replacing of all vowels using replaceAll() function and regex for each pair of vowels (lower/upper) in 5 steps (because we have 5 pair of vowels)

    System.out.printf((s.isEmpty()||s.trim().length()==0?"There is no any input!":"The original string: %s%nThe converted string: %s"),s,s.replaceAll ("a|A", "4").replaceAll("e|E","3"). replaceAll ("i|I","1").replaceAll("o|O","0"). replaceAll("u|U","7"));         

    }

}
