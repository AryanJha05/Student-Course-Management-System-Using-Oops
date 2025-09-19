import java.util.*;

public class UserManagement {
    public static User addStudent(String username, String email, String phone, String password) throws DuplicateEmailException {
        return EnrollmentManagement.register(username, email, phone, password, "student");
    }

    public static void deleteStudentByEmail(String email) throws UserNotFoundException {
        Iterator<User> it = EnrollmentManagement.users.iterator();
        while (it.hasNext()) {
            User u = it.next();
            if (u.getEmail().equalsIgnoreCase(email) && "student".equals(u.getUserType())) {
                it.remove();
                return;
            }
        }
        throw new UserNotFoundException(email);
    }

    public static void listStudents() {
        System.out.println("-- Students --");
        for (User u : EnrollmentManagement.users) {
            if ("student".equals(u.getUserType())) {
                System.out.println(u.getUsername() + " | " + u.getEmail());
            }
        }
    }
}
