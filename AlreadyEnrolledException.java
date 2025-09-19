public class AlreadyEnrolledException extends AppException {
    public AlreadyEnrolledException(String courseId) { super("Already enrolled in course: " + courseId); }
}
