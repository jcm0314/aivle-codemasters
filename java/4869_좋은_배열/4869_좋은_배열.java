// Problem: 좋은 배열
// Number: 4869
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int len = 2 * N;

        int[] a = new int[len + 1]; // 1-based

        for (int i = 1; i <= len; i++) {
            a[i] = scanner.nextInt();
        }

        // 각 값 v(1..N)에 대해 등장 위치 두 개 저장
        int[] first = new int[N + 1];
        int[] second = new int[N + 1];

        for (int i = 1; i <= len; i++) {
            int v = a[i];
            if (first[v] == 0) {
                first[v] = i;
            } else {
                second[v] = i;
            }
        }

        boolean good = true;

        // 모든 값 쌍 (x, y)에 대해 구간 교차 여부 검사
        for (int x = 1; x <= N && good; x++) {
            for (int y = x + 1; y <= N; y++) {
                int ax1 = first[x], ax2 = second[x];
                int ay1 = first[y], ay2 = second[y];

                // x 구간: [ax1, ax2], y 구간: [ay1, ay2]
                // 교차 조건: ax1 < ay1 < ax2 < ay2 또는 ay1 < ax1 < ay2 < ax2
                boolean cross1 = (ax1 < ay1 && ay1 < ax2 && ax2 < ay2);
                boolean cross2 = (ay1 < ax1 && ax1 < ay2 && ay2 < ax2);

                if (cross1 || cross2) {
                    good = false;
                    break;
                }
            }
        }

        System.out.println(good ? "YES" : "NO");
    }
}