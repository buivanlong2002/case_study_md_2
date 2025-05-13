package util;

import java.util.Date;

public class InputHelper {
    private static InputStrategy strategy = new ConsoleInputStrategy();  // mặc định

    public static void setStrategy(InputStrategy newStrategy) {
        strategy = newStrategy;
    }

    public static String getString(String msg) {
        return strategy.getString(msg);
    }

    public static int getInt(String msg) {
        return strategy.getInt(msg);
    }

    public static double getDouble(String msg) {
        return strategy.getDouble(msg);
    }

    public static Date getDate(String msg) {
        return strategy.getDate(msg);
    }
}
