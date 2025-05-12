package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputHelper {
    private static final Scanner sc = new Scanner(System.in);

    public static String getString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    public static int getInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Vui lòng nhập số nguyên hợp lệ!");
            }
        }
    }

    public static double getDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Vui lòng nhập số thực hợp lệ!");
            }
        }
    }

    public static Date getDate(String msg) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        while (true) {
            try {
                System.out.print(msg);
                return sdf.parse(sc.nextLine());
            } catch (ParseException e) {
                System.out.println("Ngày không hợp lệ. Vui lòng nhập đúng định dạng dd/MM/yyyy!");
            }
        }
    }
}
