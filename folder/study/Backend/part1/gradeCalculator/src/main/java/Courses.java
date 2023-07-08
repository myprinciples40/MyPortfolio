import java.util.List;

// First-level collections Courses class
// Classes with multiple class information in a list
// This has the advantage of only needing to change this class when a modification occurs.
public class Courses {
    private final List<Course> courses;

    public Courses(List<Course> courses) {
        this.courses = courses;
    }

    public double multiplyCreditAndCourseGrade() {
        // Refactored the code below
        // Logic to add all (credits × course GPA) together
        return courses.stream()
                .mapToDouble(Course::multiplyCreditAndCourseGrade)
                .sum();
    }

    // Logic to add up all enrolled credits
    public int calculateTotalCompletedCredit() {
        return courses.stream()
                .mapToInt(Course::getCredit)
                .sum();
    }
}
