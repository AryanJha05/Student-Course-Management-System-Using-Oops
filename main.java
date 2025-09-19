import java.util.*;

class MainApp {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("===== Course Management System =====");
            System.out.println("1. Admin Login");
            System.out.println("2. Student Login");
            System.out.println("3. Student Registration");
            System.out.println("0. Exit");
            int choice = Utils.promptInt(sc, "Choose: ", 0, 3);
            switch (choice) {
                case 1 -> adminLogin();
                case 2 -> studentLogin();
                case 3 -> studentRegistration();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
            }
        }
    }

    private static void adminLogin() {
        System.out.println("-- Admin Login --");
        String email = Utils.prompt(sc, "Email: ");
        String password = Utils.prompt(sc, "Password: ");
        try {
            User u = EnrollmentManagement.login(new LoginModel(email, password));
            if (!"admin".equals(u.getUserType())) {
                System.out.println("Invalid admin credentials.");
                Utils.pause(sc);
                return;
            }
            adminMenu(u);
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            Utils.pause(sc);
        }
    }

    private static void studentLogin() {
        System.out.println("-- Student Login --");
        String email = Utils.prompt(sc, "Email: ");
        String password = Utils.prompt(sc, "Password: ");
        try {
            User u = EnrollmentManagement.login(new LoginModel(email, password));
            if (!"student".equals(u.getUserType())) {
                System.out.println("Invalid student credentials.");
                Utils.pause(sc);
                return;
            }
            studentMenu(u);
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            Utils.pause(sc);
        }
    }

    private static void studentRegistration() {
        System.out.println("-- Student Registration --");
        String username = Utils.prompt(sc, "Name: ");
        String email = Utils.prompt(sc, "Email: ");
        String phone = Utils.prompt(sc, "Phone: ");
        String password = Utils.prompt(sc, "Password: ");
        try {
            EnrollmentManagement.register(username, email, phone, password, "student");
            System.out.println("Registered successfully. You can now log in.");
        } catch (DuplicateEmailException e) {
            System.out.println(e.getMessage());
        }
        Utils.pause(sc);
    }

    private static void adminMenu(User admin) {
        while (true) {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. List Students");
            System.out.println("4. Add Course");
            System.out.println("5. Delete Course");
            System.out.println("6. List Courses");
            System.out.println("7. View My Details");
            System.out.println("0. Logout");
            int choice = Utils.promptInt(sc, "Choose: ", 0, 7);
            switch (choice) {
                case 1 -> {
                    String name = Utils.prompt(sc, "Student Name: ");
                    String email = Utils.prompt(sc, "Email: ");
                    String phone = Utils.prompt(sc, "Phone: ");
                    String pass = Utils.prompt(sc, "Password: ");
                    try {
                        User u = UserManagement.addStudent(name, email, phone, pass);
                        System.out.println("Student added: " + u.getUsername());
                    } catch (DuplicateEmailException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> {
                    String email = Utils.prompt(sc, "Email of student to delete: ");
                    try {
                        UserManagement.deleteStudentByEmail(email);
                        System.out.println("Student deleted.");
                    } catch (UserNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> UserManagement.listStudents();
                case 4 -> {
                    String name = Utils.prompt(sc, "Course Name: ");
                    String duration = Utils.prompt(sc, "Duration (e.g., 8 weeks): ");
                    CourseModel c = CourseManagement.addCourse(name, duration);
                    System.out.println("Added: " + c);
                }
                case 5 -> {
                    String id = Utils.prompt(sc, "Course ID to delete: ");
                    try {
                        CourseManagement.deleteCourseById(id);
                        System.out.println("Course deleted.");
                    } catch (CourseNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 6 -> CourseManagement.listCourses();
                case 7 -> printUserDetails(admin);
                case 0 -> {
                    return;
                }
            }
            Utils.pause(sc);
        }
    }

    private static void studentMenu(User student) {
        while (true) {
            System.out.println("\n===== Student Menu =====");
            System.out.println("1. Enroll in Course");
            System.out.println("2. Remove Enrolled Course");
            System.out.println("3. List All Courses");
            System.out.println("4. List My Courses");
            System.out.println("5. View My Details");
            System.out.println("0. Logout");
            int choice = Utils.promptInt(sc, "Choose: ", 0, 5);
            switch (choice) {
                case 1 -> {
                    CourseManagement.listCourses();
                    String id = Utils.prompt(sc, "Enter Course ID to enroll: ");
                    try {
                        EnrollmentManagement.enrollCourse(student, id);
                        System.out.println("Enrolled successfully.");
                    } catch (AppException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> {
                    listMyCourses(student);
                    String id = Utils.prompt(sc, "Enter Course ID to remove: ");
                    try {
                        EnrollmentManagement.removeCourse(student, id);
                        System.out.println("Removed successfully.");
                    } catch (AppException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> CourseManagement.listCourses();
                case 4 -> listMyCourses(student);
                case 5 -> printUserDetails(student);
                case 0 -> {
                    return;
                }
            }
            Utils.pause(sc);
        }
    }

    private static void listMyCourses(User student) {
        System.out.println("-- My Courses --");
        if (student.getCourses().isEmpty()) {
            System.out.println("No courses enrolled yet.");
            return;
        }
        for (CourseModel c : student.getCourses()) {
            System.out.println(c.getCourseId() + " | " + c.getCourseName() + " | " + c.getCourseDuration());
        }
    }

    private static void printUserDetails(User u) {
        System.out.println("-- My Details --");
        System.out.println("Name      : " + u.getUsername());
        System.out.println("Email     : " + u.getEmail());
        System.out.println("Phone     : " + u.getPhone());
        System.out.println("User ID   : " + u.getUserId());
        System.out.println("User Type : " + u.getUserType());
        System.out.println("Enrolled  : " + (u.getCourses() == null ? 0 : u.getCourses().size()) + " course(s)");
        if (u.getCourses() != null && !u.getCourses().isEmpty()) {
            for (CourseModel c : u.getCourses()) {
                System.out.println("  - " + c.getCourseName() + " (" + c.getCourseId() + ")");
            }
        }
    }
}
