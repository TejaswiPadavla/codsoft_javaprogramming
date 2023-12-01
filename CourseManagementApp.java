import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private List<String> registeredStudents;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public List<String> getRegisteredStudents() {
        return registeredStudents;
    }

    public boolean registerStudent(String studentId) {
        if (registeredStudents.size() < capacity) {
            registeredStudents.add(studentId);
            return true;
        } else {
            System.out.println("Course is full. Registration failed.");
            return false;
        }
    }

    public boolean dropStudent(String studentId) {
        if (registeredStudents.contains(studentId)) {
            registeredStudents.remove(studentId);
            return true;
        } else {
            System.out.println("Student is not registered for this course.");
            return false;
        }
    }
}

class Student {
    private String studentId;
    private String name;
    private List<String> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(String courseCode) {
        registeredCourses.add(courseCode);
    }

    public void dropCourse(String courseCode) {
        registeredCourses.remove(courseCode);
    }
}

class CourseManagementSystem {
    private List<Course> courses;
    private List<Student> students;

    public CourseManagementSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayCourseListing() {
        System.out.println("Course Listing:");
        for (Course course : courses) {
            System.out.println("Course Code: " + course.getCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Capacity: " + course.getCapacity());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("Available Slots: " + (course.getCapacity() - course.getRegisteredStudents().size()));
            System.out.println("------------------------------");
        }
    }

    public void registerStudentForCourse(String studentId, String courseCode) {
        Student student = getStudentById(studentId);
        Course course = getCourseByCode(courseCode);

        if (student != null && course != null) {
            if (course.registerStudent(studentId)) {
                student.registerCourse(courseCode);
                System.out.println("Registration successful for student: " + student.getName());
            }
        }
    }

    public void dropStudentFromCourse(String studentId, String courseCode) {
        Student student = getStudentById(studentId);
        Course course = getCourseByCode(courseCode);

        if (student != null && course != null) {
            if (course.dropStudent(studentId)) {
                student.dropCourse(courseCode);
                System.out.println("Course dropped successfully for student: " + student.getName());
            }
        }
    }

    private Student getStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        System.out.println("Student not found with ID: " + studentId);
        return null;
    }

    private Course getCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                return course;
            }
        }
        System.out.println("Course not found with code: " + courseCode);
        return null;
    }
}

public class CourseManagementApp {
    public static void main(String[] args) {
        // Creating courses
        Course course1 = new Course("CSCI101", "Introduction to Computer Science", "Fundamental concepts of computer science", 30, "MWF 10:00 AM - 11:00 AM");
        Course course2 = new Course("MATH202", "Calculus II", "Advanced calculus topics", 25, "TTH 2:00 PM - 3:30 PM");

        // Creating students
        Student student1 = new Student("S101", "John Doe");
        Student student2 = new Student("S102", "Jane Smith");

        // Creating the course management system
        CourseManagementSystem cms = new CourseManagementSystem();

        // Adding courses and students to the system
        cms.addCourse(course1);
        cms.addCourse(course2);

        cms.addStudent(student1);
        cms.addStudent(student2);

        // Displaying course listing
        cms.displayCourseListing();

        // Registering students for courses
        cms.registerStudentForCourse("S101", "CSCI101");
        cms.registerStudentForCourse("S102", "MATH202");

        // Displaying course listing after registration
        cms.displayCourseListing();

        // Dropping students from courses
        cms.dropStudentFromCourse("S101", "CSCI101");

        // Displaying course listing after dropping
        cms.displayCourseListing();
    }
}