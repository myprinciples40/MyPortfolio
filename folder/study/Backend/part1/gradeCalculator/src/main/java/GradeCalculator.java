import java.util.List;

public class GradeCalculator {
    // Using first-class collections
    private final Courses courses;

    public GradeCalculator(List<Course> courses) {
        this.courses = new Courses(courses);
    }

    // Methods for modifying the GradeCalculatorTest test class
    public GradeCalculator(Courses courses) {
        this.courses = courses;
    }
    /**
     * Key point!!!!
     */
    //  4. Assign appropriate responsibilities (below) - Designing collaboration
    public double calculateGrade() {
        // By setting Courses, we can delegate this value to a first-class collection.
        // The sum of (number of credits × course GPA)
        double totalMultipliedCreditAndCourseGrade = courses.multiplyCreditAndCourseGrade();
        // Total number of credits enrolled
        int totalCompletedCredit = courses.calculateTotalCompletedCredit();

        return totalMultipliedCreditAndCourseGrade / totalCompletedCredit;
    }
}
