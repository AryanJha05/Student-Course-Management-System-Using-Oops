public class UserNotFoundException extends AppException {
    public UserNotFoundException(String idOrEmail) { super("User not found: " + idOrEmail); }
}
