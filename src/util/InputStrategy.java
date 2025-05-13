package util;

import java.util.Date;

public interface InputStrategy {
    String getString(String msg);
    int getInt(String msg);
    double getDouble(String msg);
    Date getDate(String msg);
}

