public class DuplicateEmailException extends AppException {
    public DuplicateEmailException(String email) { super("Email already registered: " + email); }
}
