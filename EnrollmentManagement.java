import java.util.*;

public class EnrollmentManagement {
    // Simulated DB
    public static final ArrayList<User> users = new ArrayList<>();
    public static final ArrayList<CourseModel> courses = new ArrayList<>();

    static {
        // seed one admin
        String adminId = Utils.generateId("USR");
        users.add(new User(adminId, "Admin", "admin@example.com", "0000000000", "admin123", "admin"));
        // seed some courses
        courses.add(new CourseModel(Utils.generateId("CRS"), "Java Basics", "8 weeks"));
        courses.add(new CourseModel(Utils.generateId("CRS"), "Data Structures", "10 weeks"));
    }

    // Registration
    public static User register(String username, String email, String phone, String password, String userType) throws DuplicateEmailException {
        if (!userType.equals("admin") && !userType.equals("student"))
            throw new IllegalArgumentException("userType must be 'admin' or 'student'");
        if (findUserByEmail(email) != null) {
            throw new DuplicateEmailException(email);
        }
        User u = new User(Utils.generateId("USR"), username, email, phone, password, userType);
        users.add(u);
        return u;
    }

    // Login
    public static User login(LoginModel creds) throws AuthenticationException {
        User u = findUserByEmail(creds.getEmail());
        if (u != null && u.getPassword().equals(creds.getPassword())) return u;
        throw new AuthenticationException();
    }

    // Enrollment
    public static void enrollCourse(User student, String courseId) throws CourseNotFoundException, AlreadyEnrolledException, AppException {
        if (student == null || !"student".equals(student.getUserType())) throw new AppException("Only students can enroll");
        CourseModel c = findCourseById(courseId);
        if (c == null) throw new CourseNotFoundException(courseId);
        for (CourseModel ec : student.getCourses()) {
            if (ec.getCourseId().equals(courseId)) {
                throw new AlreadyEnrolledException(courseId);
            }
        }
        student.getCourses().add(c);
    }

    public static void removeCourse(User student, String courseId) throws NotEnrolledException, AppException {
        if (student == null || !"student".equals(student.getUserType())) throw new AppException("Only students can remove enrollments");
        Iterator<CourseModel> it = student.getCourses().iterator();
        while (it.hasNext()) {
            CourseModel c = it.next();
            if (c.getCourseId().equals(courseId)) {
                it.remove();
                return;
            }
        }
        throw new NotEnrolledException(courseId);
    }

    // helpers
    public static User findUserByEmail(String email) {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) return u;
        }
        return null;
    }

    public static User findUserById(String userId) {
        for (User u : users) {
            if (u.getUserId().equals(userId)) return u;
        }
        return null;
    }

    public static CourseModel findCourseById(String courseId) {
        for (CourseModel c : courses) {
            if (c.getCourseId().equals(courseId)) return c;
        }
        return null;
    }
}
