// Problem: 전자 시계
// Number: 4913
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        String t = scanner.next();  // "HH:MM"
        int k = scanner.nextInt();  // 주기 (분)

        int hour = Integer.parseInt(t.substring(0, 2));
        int minute = Integer.parseInt(t.substring(3, 5));
        int time = hour * 60 + minute;

        boolean[] visited = new boolean[1440];
        int count = 0;

        while (!visited[time]) {
            visited[time] = true;

            int h = time / 60;
            int m = time % 60;
            String s = String.format("%02d:%02d", h, m);

            // 회문 시각인지 검사
            if (s.charAt(0) == s.charAt(4) && s.charAt(1) == s.charAt(3)) {
                count++;
            }

            time = (time + k) % 1440;
        }

        System.out.println(count);
    }
}