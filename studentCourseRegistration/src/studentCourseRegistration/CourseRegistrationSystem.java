package studentCourseRegistration;

// Import necessary packages
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Define Course Registration System class
public class CourseRegistrationSystem {
    private Map<String, Course> courses;
    private Map<String, Student> students;
    private static final int DEFAULT_MAX_COURSE_LIMIT = 5; // Default max course limit
    Scanner scanner = new Scanner(System.in);// Scanner object for user input

    // Constructor initialization
    public CourseRegistrationSystem() {
        courses = new HashMap<>();
        students = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    // Method to add a student
    public void addStudent() {
        System.out.println("\nEnter Student Details:-");
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine();
        System.out.print("Enter Email Address: ");
        String email = scanner.nextLine().toLowerCase(); //Convert email to lower case
        System.out.print("Enter Student Number: ");
        String studentNumber = scanner.nextLine();

        // Check if a student with the same details already exists
        for (Student existingStudent : students.values()) {
            if (existingStudent.getName().equals(name) &&
                existingStudent.getDob().equals(dob) &&
                existingStudent.getEmail().equals(email) &&
                existingStudent.getStudentNumber().equals(studentNumber)) {
                System.out.println("Student with the same details already exists.");
                return;
            }
        }

        String studentID = generateUniqueStudentID(name);// Calling generate unique student ID method
        Student student = new Student(studentID, name, dob, email, studentNumber, DEFAULT_MAX_COURSE_LIMIT);

        students.put(studentID, student);

        System.out.println("\nStudent added successfully.");
        System.out.println("Your Student ID is: " + studentID);
        System.out.println("You can select upto 5 courses.");

        try {
            createStudentFile(studentID);
        } catch (IOException e) {
            System.err.println("An error occurred while creating the student file: " + e.getMessage());
        }
    }

    // Generate unique student ID
    private String generateUniqueStudentID(String name) {
        String[] nameParts = name.trim().split("\\s+");
        StringBuilder studentIDBuilder = new StringBuilder();
        
        // Use the first four characters of the student's name
        for (String part : nameParts) {
            studentIDBuilder.append(part.substring(0, Math.min(part.length(), 4)).toUpperCase());
        }
        
        // Generate a random four-digit number
        int randomNum = (int) (Math.random() * 9000) + 1000;
        studentIDBuilder.append(randomNum);
        return studentIDBuilder.toString();
    }
    
    // Method to display available courses
    public void displayCourses() {
        System.out.println("\nAvailable Courses:");
        System.out.println("------------------");
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            System.out.println("Please Try again later!!");
        } else {
            for (Course course : courses.values()) {
                System.out.println(course.toString());
                System.out.println();
            }
        }
    }

    // Method to add Course
    public void addCourse(String courseCode, String title, String description, int capacity, String schedule) {
        Course course = new Course(courseCode, title, description, capacity, schedule);
        courses.put(courseCode, course);
    }
    
    // Method to register a course for a Student
    public void registerCourse(String studentID, String courseCode) {
        // Convert studentID and courseCode to upper case for case insensitivity
        studentID = studentID.toUpperCase();
        courseCode = courseCode.toUpperCase();

        // Check if the student exists
        Student student = students.get(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        // Check if the course exists
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        // Check if the student is already registered for this course
        if (student.getRegisteredCourses().contains(courseCode)) {
            System.out.println("Student is already registered for this course.");
            return;
        }

        // Check if the student has reached the maximum course registration limit
        if (student.getRegisteredCourses().size() >= student.getMaxCourseLimit()) {
            System.out.println("Student has reached the maximum course registration limit.");
            return;
        }

        // Register the course for the student
        student.registerCourse(courseCode);
        System.out.println("Course registered successfully for student ID " + studentID + ".");
    }

    public void dropCourse(String studentID, String courseCode) {
        // Convert studentID and courseCode to upper case for case insensitivity
        studentID = studentID.toUpperCase();
        courseCode = courseCode.toUpperCase();

        // Check if the student exists
        Student student = students.get(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        // Check if the course exists
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        // Check if the student is registered for this course
        if (!student.getRegisteredCourses().contains(courseCode)) {
            System.out.println("Student is not registered for this course.");
            return;
        }

        // Drop the course for the student
        student.dropCourse(courseCode);
        System.out.println("Course dropped successfully for student ID " + studentID + ".");
    }

    // Method to Display Student Details
    public void displayStudentDetails(String studentID) {
        // Convert studentID to upper case
        String uppercaseStudentID = studentID.toUpperCase();
        
        Student student = students.get(uppercaseStudentID);
        if (student == null) {
            System.out.println("\nStudent not found.");
            return;
        }

        System.out.println("\nStudent Details:");
        System.out.println("\nStudent ID: " + student.getStudentID());
        System.out.println("Name: " + student.getName());
        System.out.println("Date of Birth: " + student.getDob());
        System.out.println("Email Address: " + student.getEmail());
        
        // Display Registered Courses of the student else NIL
        System.out.println("\nRegistered Courses:");
        System.out.println("-------------------");
        
        if (student.getRegisteredCourses().isEmpty()) {
            System.out.println("NIL");
        } else {
            for (String courseCode : student.getRegisteredCourses()) {
                Course course = courses.get(courseCode);
                if (course != null) {
                    System.out.println("- " + course.getTitle() + " (" + course.getCourseCode() + ")");
                } else {
                    System.out.println("NIL");
                }
            }
        }
        
        // Display Dropped Courses of the Student else NIL
        System.out.println("\nDropped Courses:");
        System.out.println("----------------");
        
        if (student.getDroppedCourses().isEmpty()) {
            System.out.println("NIL");
        } else {
            for (String courseCode : student.getDroppedCourses()) {
                Course course = courses.get(courseCode);
                if (course != null) {
                    System.out.println("- " + course.getTitle() + " (" + course.getCourseCode() + ")");
                } else {
                    System.out.println("NIL");
                }
            }
        }
    }

    // Method to create a Student File
    private void createStudentFile(String studentID) throws IOException {
        File studentDirectory = new File("student_data/" + studentID);//Creating a directory if it doesn't exist 
        if (!studentDirectory.exists()) {
            if (!studentDirectory.mkdirs()) {
                throw new IOException("Failed to create student directory.");
            }
        }

        File studentFile = new File(studentDirectory, "student.txt");//Creating a Student File inside the directory
        if (!studentFile.createNewFile()) {
            throw new IOException("Failed to create student file.");
        }
        System.out.println("Student file created successfully.");
    }

    // Method to display the main menu
    public void displayMenu() {
    	System.out.println("\n    -----------------------------------");
        System.out.println("    | Welcome to Course Registration. |");
        System.out.println("    -----------------------------------");
        System.out.println("                Menu:-             ");
        System.out.println("------------------------------------------");
        System.out.println("| Choice |         Description           |");
        System.out.println("------------------------------------------");
        System.out.println("|   1.   |         Add Student           |");
        System.out.println("|   2.   |    Display Available Courses  |");
        System.out.println("|   3.   |      Register for a Course    |");
        System.out.println("|   4.   |         Drop a Course         |");
        System.out.println("|   5.   |     Display Student Details   |");
        System.out.println("|   6.   |            Exit               |");
        System.out.println("------------------------------------------");
        System.out.print("\n     Enter your choice: ");
    }

    // Switch case to execute corresponding functionality based on user choice
    public void processChoice(int choice) {
        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                displayCourses();
                break;
            case 3:
                System.out.print("Enter Student ID: ");
                String regStudentID = scanner.nextLine();
                System.out.print("Enter the Course Code you want to register for: ");
                String regCourseCode = scanner.nextLine();
                registerCourse(regStudentID, regCourseCode);
                break;
            case 4:
                System.out.print("Enter Student ID: ");
                String dropStudentID = scanner.nextLine();
                System.out.print("Enter the Course Code you want to drop: ");
                String dropCourseCode = scanner.nextLine();
                dropCourse(dropStudentID, dropCourseCode);
                break;
            case 5:
                System.out.print("Enter Student ID: ");
                String displayStudentID = scanner.nextLine();
                displayStudentDetails(displayStudentID);
                break;
            case 6:
                System.out.println("Have a great day!!!");
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("\nInvalid Choice.");
        }
    }

    // Main method to run a program
    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();
        
        // Add courses
        system.addCourse("CSE101", "Introduction to Computer Science", "Fundamentals of computer science", 50, "Mon-Wed-Fri 10:00 AM");
        system.addCourse("JAVA101", "Java Programming", "Introduction to Java programming language", 30, "Mon-Wed-Fri 11:00 AM");
        system.addCourse("SQL101", "Introduction to SQL", "Fundamentals of Structured Query Language", 35, "Tue-Thu 10:00 AM");
        system.addCourse("HTML101", "HTML Fundamentals", "Introduction to HTML", 25, "Mon-Wed 2:00 PM");
        system.addCourse("CSS101", "CSS Fundamentals", "Introduction to Cascading Style Sheets", 25, "Tue-Thu 2:00 PM");
        system.addCourse("R101", "Introduction to R", "Introduction to R programming language", 20, "Mon-Wed 3:00 PM");
        system.addCourse("C#101", "C# Programming", "Introduction to C# programming language", 30, "Tue-Thu 3:00 PM");
        system.addCourse("PowerBI101", "Introduction to Power BI", "Introduction to data visualization with Power BI", 25, "Mon-Wed-Fri 4:00 PM");
        system.addCourse("DW101", "Data Warehousing", "Fundamentals of Data Warehousing", 20, "Tue-Thu 4:00 PM");
        system.addCourse("Cloud101", "Introduction to Cloud Computing", "Fundamentals of Cloud Computing", 30, "Mon-Wed 5:00 PM");
        
        // Display the menu continuously until the user choice to exit
        while (true) {
            try {
                system.displayMenu();
                int choice = system.scanner.nextInt();
                system.scanner.nextLine(); 
                system.processChoice(choice);
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a valid choice (1-6).");
                system.scanner.next(); // Clear the invalid input from the scanner
            }
        }
    }
}
