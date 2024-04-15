package javaprograms;
import java.util.*;

public class Calc {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter 1st Number: ");
		double num1 = sc.nextDouble();
		
		System.out.println("Enter 2nd Number: ");
		double num2 = sc.nextDouble();
		
		System.out.println("Enter operation '+', '-', '*', '/', '%'");
		char operation = sc.next().charAt(0);
		
		switch(operation) {
		case '+':
			System.out.println(num1 + "+" + num2+ "="+ (num1+num2));
			break;
		case '-':
			System.out.println(num1 + "-" + num2+ "="+ (num1-num2));
			break;
		case '*':
			System.out.println(num1 + "*" + num2+ "="+ (num1*num2));
			break;
		case '/':
			System.out.println(num1 + "/" + num2+ "="+ (num1/num2));
			break;
		case '%':
			System.out.println(num1 + "%" + num2+ "="+ (num1%num2));
			break;
		default:
			System.out.println("Enter correct Operation");
		}
	}

}
