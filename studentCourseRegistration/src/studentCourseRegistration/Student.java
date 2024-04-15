package studentCourseRegistration;

// Import necessary Packages
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Define Student class
public class Student {
    private String studentID;
    private String name;
    private String dob; // Date of birth
    private String email;
    private String studentNumber;
    private static int maxCourseLimit; // Static field for maximum course limit
    private List<String> registeredCourses;
    private List<String> droppedCourses;

    // Constructor to initialize
    public Student(String studentID, String name, String dob, String email, String studentNumber, int maxCourseLimit) {
        this.studentID = studentID;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.studentNumber = studentNumber;
        Student.maxCourseLimit = maxCourseLimit; // Assigning to static field
        this.registeredCourses = new ArrayList<>();
        this.droppedCourses = new ArrayList<>();
    }
    
    //Getters and Setters
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public static int getMaxCourseLimit() {
        return maxCourseLimit;
    }

    public static void setMaxCourseLimit(int limit) {
        maxCourseLimit = limit;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public List<String> getDroppedCourses() {
        return droppedCourses;
    }

    // Method to Register Course
    public void registerCourse(String courseCode) {
        if (registeredCourses.size() >= maxCourseLimit) {
            System.out.println("Cannot register for more courses. Maximum limit reached.");
            return;
        }
        registeredCourses.add(courseCode);
    }

    // Method to Drop Course
    public void dropCourse(String courseCode) {
        if (registeredCourses.contains(courseCode)) {
            registeredCourses.remove(courseCode);
            droppedCourses.add(courseCode);
        } else {
            System.out.println("Course not registered for this student.");
        }
    }

    // Method to save Registered Courses
    public void saveRegisteredCourses() {
        saveCourses(registeredCourses, "registered_courses");
    }

    // Method to save Dropped Courses
    public void saveDroppedCourses() {
        saveCourses(droppedCourses, "dropped_courses");
    }

    // Method to save Courses to file 
    private void saveCourses(List<String> courses, String folderName) {
        try {
            File folder = new File("student_activities/" + studentID + "/" + folderName);
            folder.mkdirs(); // Create folders if they don't exist

            for (String course : courses) {
                File courseFile = new File(folder, course + ".txt");
                courseFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
