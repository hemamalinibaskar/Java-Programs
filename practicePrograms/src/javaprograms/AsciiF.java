package javaprograms;
import java.util.*;

public class AsciiF {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the letter: ");
		char ch1 = sc.next().charAt(0);
		int Asciivalue = ch1;
		
		System.out.println("ASCII value of "+ch1+" is "+Asciivalue);
		
		
		
	}

}
