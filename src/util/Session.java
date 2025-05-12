package util;

public class Session {
    private static String currentUsername;

    // Lấy username hiện tại
    public static String getCurrentUsername() {
        return currentUsername;
    }

    // Lưu username hiện tại
    public static void setCurrentUsername(String username) {
        currentUsername = username;
    }
    public static void clear() {
        currentUsername = null;
    }

    public static boolean isLoggedIn() {
        return currentUsername != null;
    }
}
