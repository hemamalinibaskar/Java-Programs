package javaprograms;

public class HashcodeEx {
	public static void main(String[] args) {
		Student s1 = new Student(01,"Maya");
		Student s2 = new Student(01,"Maya");
		
		int a = s1.hashCode();
		int b = s2.hashCode();
		
		System.out.println("Hashcode of a: "+a);
		System.out.println("Hashcode of b: "+b);
		
		System.out.println("Hashcode returns "+s1.equals(s2));
		
		
	}

}
