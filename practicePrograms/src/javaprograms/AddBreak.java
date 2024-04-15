package javaprograms;
import java.util.*;

public class AddBreak {
	public static void main(String[] args) {
		double num, sum=0.0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number here, To stop the calculation enter 0 or less than 0: ");
		
		while(true) {
			num = sc.nextDouble();
			if(num<=0)
				break;
			sum = sum+num;
			
		}
		System.out.println("Total value: "+sum);
	}
}
