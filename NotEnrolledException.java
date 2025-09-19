public class NotEnrolledException extends AppException {
    public NotEnrolledException(String courseId) { super("Not enrolled in course: " + courseId); }
}
