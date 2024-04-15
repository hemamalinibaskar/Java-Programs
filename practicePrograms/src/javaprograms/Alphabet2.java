package javaprograms;
import java.util.*;

public class Alphabet2 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the alphabet from A to Z or a to z: ");
		char Alphabet = sc.next().charAt(0);
		
		switch(Alphabet) {
			case 'A' | 'a':
				System.out.println(Alphabet+" is a vowel");
				break;
			case 'E' | 'e':
				System.out.println(Alphabet+" is a vowel");
				break;
			case 'I' | 'i':
				System.out.println(Alphabet+" is a vowel");
				break;
			case 'O' | 'o':
				System.out.println(Alphabet+" is a vowel");
				break;
			case 'U' | 'u':
				System.out.println(Alphabet+" is a vowel");
				break;
			default:
				System.out.println(Alphabet+" is a consonant");
			
		
	}
	}

}
