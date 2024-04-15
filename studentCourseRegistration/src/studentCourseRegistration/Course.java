package studentCourseRegistration;

// Import necessary packages
import java.util.ArrayList;
import java.util.List;

// Define Course class
public class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private List<Student> registeredStudents;

    // Constructor to initialize
    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }
    
    // Getters and Setters
    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setCourseCode(String courseCode) {
        if (courseCode == null || courseCode.isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be null or empty.");
        }
        this.courseCode = courseCode;
    }

    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative.");
        }
        this.capacity = capacity;
    }

    // Method to register a Student
    public void registerStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        if (registeredStudents.size() >= capacity) {
            throw new IllegalStateException("Course is already full.");
        }
        if (registeredStudents.contains(student)) {
            throw new IllegalStateException("Student is already registered for this course.");
        }
        registeredStudents.add(student);
    }

    // Method to drop a Student
    public void dropStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        if (!registeredStudents.contains(student)) {
            throw new IllegalStateException("Student is not registered for this course.");
        }
        registeredStudents.remove(student);
    }
    
    // Override toString method to provide a string representation of the course
    @Override
    public String toString() {
        return "Course Code: " + courseCode +
                "\nTitle: " + title +
                "\nDescription: " + description +
                "\nCapacity: " + capacity +
                "\nSchedule: " + schedule;
    }
}
