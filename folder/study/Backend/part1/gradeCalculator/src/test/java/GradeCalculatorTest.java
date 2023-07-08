import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/** Implemented with TDD
 * Requirements.
 *  * Calculate grade point average = sum of (number of credits×course GPA) / total number of credits taken
 *  * Convert to number in GradeCalculator.getGradeToNumber() because 3 * A+ is not allowed
 *  * Implement based on MVC pattern (Model-View-Controller).
 *  * Use a first class collection
 *
 *  Author: Jinhwan Kim (Jin)
 *  Date created: 2023-06-25
 *  Modification Date:
 */
public class GradeCalculatorTest {
    // 1. Credit Calculator Domain Object: Completed Courses (Object-Oriented Programming, Data Structure, Chinese Conversation), Credit Calculator
    // 2. Abstraction of dynamic objects into static types for domain modeling (below)
    // 3. Completed courses: Object-Oriented Programming, Data Structure, Chinese --> Subject (Course)

    /**
     * Key point!!!!
     */
    // 4. Assign appropriate responsibilities (below) - Designing collaboration
    // Request to calculate grade point average by passing in completed courses
    // ---> Credit Calculator ---> Sum of (number of credits×course GPA) ---> Request Subject (Course) (another object)
    // ---> Total number of credits enrolled ---> Request Subject (Course) (another object)


    // After quickly passing the test code, populate the GradeCalculator class with the content inside the calculateGrade() method.
    @DisplayName("Calculate the grade point average.")
    @Test
    void calculateGradeTest() {
        List<Course> courses = List.of(new Course("OOP", 3, "A+"),
            new Course("Data Structure", 3, "A+"));

        // Passing Courses Completed When the Gradebook is Generated (Previous)
//        GradeCalculator gradeCalculator = new GradeCalculator(courses);
        // It shouldn't be much different than the code above. (No big deal).
        GradeCalculator gradeCalculator = new GradeCalculator(new Courses(courses));
        // Have the grade calculator deliver grades (Request a grade calculation)
        double gradeResult = gradeCalculator.calculateGrade();

        assertThat(gradeResult).isEqualTo(4.5);
    }
}
