// Problem: 직각삼각형
// Number: 4868
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

        int[] count = new int[N + 1];

        // a <= b 로 두고 탐색
        for (int a = 1; a <= N; a++) {
            for (int b = a; b <= N; b++) {
                int sumSq = a * a + b * b;
                int c = (int) Math.sqrt(sumSq);

                if (c * c != sumSq) continue; // c가 정수가 아님

                int P = a + b + c;
                if (P > N) continue;

                count[P]++;
            }
        }

        int bestP = 0;
        int bestCnt = 0;

        for (int P = 1; P <= N; P++) {
            if (count[P] > bestCnt) {
                bestCnt = count[P];
                bestP = P;
            }
        }

        System.out.println(bestP + " " + bestCnt);
    }
}