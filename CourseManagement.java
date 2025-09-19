import java.util.*;

public class CourseManagement {
    public static CourseModel addCourse(String name, String duration) {
        String id = Utils.generateId("CRS");
        CourseModel c = new CourseModel(id, name, duration);
        EnrollmentManagement.courses.add(c);
        return c;
    }

    public static void deleteCourseById(String courseId) throws CourseNotFoundException {
        Iterator<CourseModel> it = EnrollmentManagement.courses.iterator();
        while (it.hasNext()) {
            CourseModel c = it.next();
            if (c.getCourseId().equals(courseId)) {
                it.remove();
                // also remove from students' enrollments
                for (User u : EnrollmentManagement.users) {
                    u.getCourses().removeIf(ec -> ec.getCourseId().equals(courseId));
                }
                return;
            }
        }
        throw new CourseNotFoundException(courseId);
    }

    public static void listCourses() {
        System.out.println("-- Courses --");
        for (CourseModel c : EnrollmentManagement.courses) {
            System.out.println(c.getCourseId() + " | " + c.getCourseName() + " | " + c.getCourseDuration());
        }
    }
}
