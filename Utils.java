import java.util.*;

public class Utils {
    private static final Random RAND = new Random();

    public static String generateId(String prefix) {
        long ts = System.currentTimeMillis();
        int rand = 1000 + RAND.nextInt(9000);
        return prefix + "-" + ts + "-" + rand;
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static String prompt(Scanner sc, String label) {
        System.out.print(label);
        return sc.nextLine().trim();
    }

    public static int promptInt(Scanner sc, String label, int min, int max) {
        while (true) {
            System.out.print(label);
            String line = sc.nextLine().trim();
            try {
                int v = Integer.parseInt(line);
                if (v < min || v > max) {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.") ;
            }
        }
    }

    public static void pause(Scanner sc) {
        System.out.println("Press Enter to continue...");
        sc.nextLine();
    }
}
