// Problem: 부분행렬
// Number: 4875
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
        int M = scanner.nextInt();
        int X = scanner.nextInt();

        int[][] a = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                a[i][j] = scanner.nextInt();
            }
        }

        boolean possible = false;

        // 행 선택 마스크 (공집합 제외)
        for (int rowMask = 1; rowMask < (1 << N); rowMask++) {
            // 열 선택 마스크 (공집합 제외)
            for (int colMask = 1; colMask < (1 << M); colMask++) {

                int sum = 0;

                for (int i = 0; i < N; i++) {
                    if (((rowMask >> i) & 1) == 0) continue; // i행 사용 안 함

                    for (int j = 0; j < M; j++) {
                        if (((colMask >> j) & 1) == 0) continue; // j열 사용 안 함
                        sum += a[i][j];
                    }
                }

                if (sum == X) {
                    possible = true;
                    break;
                }
            }
            if (possible) break;
        }

        System.out.println(possible ? "YES" : "NO");
    }
}