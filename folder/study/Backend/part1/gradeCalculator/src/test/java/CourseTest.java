import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

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

// Classes that test for completed courses
public class CourseTest {
    @DisplayName("Create a subject (course).")
    @Test
    void createTest() {
        assertThatCode(() -> new Course("OOP", 3, "A+"))
                .doesNotThrowAnyException();
    }
}
