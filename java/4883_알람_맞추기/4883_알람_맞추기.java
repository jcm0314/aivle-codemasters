// Problem: 알람 맞추기
// Number: 4883
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        String time = scanner.next(); // "HH:MM"
        int N = scanner.nextInt();

        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(3, 5));

        int start = hour * 60 + minute;

        // N번째 알람까지의 총 경과 시간 (분)
        long diff = (long)(N - 1) * N / 2; // (N-1)*N/2

        long total = start + diff;
        total %= 24L * 60L; // 하루(1440분) 순환

        int finalHour = (int)(total / 60);
        int finalMinute = (int)(total % 60);

        System.out.printf("%02d:%02d%n", finalHour, finalMinute);
    }
}