public class CourseNotFoundException extends AppException {
    public CourseNotFoundException(String courseId) { super("Course not found: " + courseId); }
}
