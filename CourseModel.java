public class CourseModel {
    private String courseId;
    private String courseName;
    private String courseDuration; // e.g., "10 weeks"

    public CourseModel() {}

    public CourseModel(String courseId, String courseName, String courseDuration) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDuration = courseDuration;
    }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getCourseDuration() { return courseDuration; }
    public void setCourseDuration(String courseDuration) { this.courseDuration = courseDuration; }

    @Override
    public String toString() {
        return courseName + " (" + courseId + ", " + courseDuration + ")";
    }
}
