# Course Management System (Console, Java)

A simple console-based Course Management System implemented in plain Java using ArrayList as an in-memory database. Everything is in a single package (no package declarations). It includes basic admin and student flows with login/registration and course enrollment features.

## Features

- Single-package Java project (no `package` statements)
- In-memory storage using `ArrayList`
- Seeded admin account for quick start
- Admin:
  - Add/Delete students
  - Add/Delete courses
  - List students and courses
  - View own details
- Student:
  - Registration and Login
  - Enroll in courses
  - Remove enrolled courses
  - List all courses and own courses
  - View own details
- Basic custom exceptions and try/catch handling

## File Structure

- `main.java` — Entry point (`MainApp`) and console menus (admin/student)
- `User.java` — User model (student/admin) + enrolled courses list
- `CourseModel.java` — Course model
- `LoginModel.java` — Login model
- `Utils.java` — Helpers: ID generation, input prompts, numeric input, pause
- `EnrollmentManagement.java` — In-memory DB; registration, login, enroll/remove, lookups
- `UserManagement.java` — Admin operations on students
- `CourseManagement.java` — Admin operations on courses
- `AppException.java` — Base exception
- `DuplicateEmailException.java` — Thrown on duplicate email registration
- `AuthenticationException.java` — Thrown on invalid email/password
- `UserNotFoundException.java` — Thrown when deleting a non-existent student
- `CourseNotFoundException.java` — Thrown when deleting a non-existent course
- `AlreadyEnrolledException.java` — Thrown when enrolling twice in same course
- `NotEnrolledException.java` — Thrown when removing a course not enrolled

## Prerequisites

- Java JDK 17+ recommended (works with Java 11+). Ensure `javac` and `java` are in PATH.

---
You should see the main menu with options for Admin Login, Student Login, and Student Registration.

## Default Admin Credentials

- Email: `admin@example.com`
- Password: `admin123`

These are seeded in `EnrollmentManagement.java` (static initializer). You can change them there.

## Usage Guide

1) Admin Login
- Add/Delete Students
- Add/Delete Courses
- List Students
- List Courses
- View My Details

2) Student Registration
- Create a student account (must be unique email). Handled by `EnrollmentManagement.register(...)`.

3) Student Login
- Enroll in Course: Pick a Course ID from the list
- Remove Enrolled Course: Remove by Course ID
- List All Courses / My Courses
- View My Details

## Exceptions and Handling

The app uses custom exceptions for clean error handling, caught in `main.java` and displayed as friendly messages:
- `DuplicateEmailException` — During registration/add student when email already exists
- `AuthenticationException` — On invalid login
- `UserNotFoundException` — When deleting a student that doesn't exist
- `CourseNotFoundException` — When deleting a course that doesn't exist
- `AlreadyEnrolledException` — When enrolling in an already-enrolled course
- `NotEnrolledException` — When removing a course not in the student's list

## Tips & Troubleshooting

- Make sure to run the class `MainApp` (inside `main.java`) when launching.
- If you change filenames or class names, keep the public class name matched to the file when using packages. This project uses the default package for simplicity.
- If `javac`/`java` are not recognized, install the JDK and restart your terminal.
- When copying Course IDs, ensure no extra spaces are included.

## Extending

- Persist data to a file (JSON/CSV) for state across runs
- Add validation (email/phone formats), perhaps with a `ValidationException`
- Add search/filter and course descriptions
- Add role management and more granular permissions

Enjoy building and learning from this minimal, console-based system!

