public class TestEnv {
    public static void main(String[] args) {
        // Print environment variables to check if they are set
        System.out.println("DB_URL: " + System.getenv("DB_URL"));
        System.out.println("DB_USER: " + System.getenv("DB_USER"));
        System.out.println("DB_PASSWORD: " + System.getenv("DB_PASSWORD"));
    }

}
