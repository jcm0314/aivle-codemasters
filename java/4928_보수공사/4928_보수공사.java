// Problem: 보수공사
// Number: 4928
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // 도로 크기 N x N
        int K = scanner.nextInt(); // K번 비를 맞으면 부식
        int M = scanner.nextInt(); // 관리 일수

        // 2D 차분 배열 (1-indexed 사용, 여유로 N+2까지)
        int[][] diff = new int[N + 2][N + 2];

        for (int day = 0; day < M; day++) {
            int X = scanner.nextInt();
            int Y = scanner.nextInt();
            int Z = scanner.nextInt();
            int W = scanner.nextInt();

            // 직사각형 (X,Y) ~ (Z,W)에 +1
            diff[X][Y] += 1;
            diff[X][W + 1] -= 1;
            diff[Z + 1][Y] -= 1;
            diff[Z + 1][W + 1] += 1;
        }

        // 2D prefix sum으로 각 칸의 비 횟수 계산
        // 1) 행 방향 누적
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N + 1; j++) {
                diff[i][j] += diff[i][j - 1];
            }
        }

        // 2) 열 방향 누적
        for (int j = 1; j <= N; j++) {
            for (int i = 1; i <= N + 1; i++) {
                diff[i][j] += diff[i - 1][j];
            }
        }

        long answer = 0;

        // 각 칸별 비 횟수 / K 의 합
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int rainCount = diff[i][j];
                if (rainCount >= K) {
                    answer += rainCount / K;
                }
            }
        }

        System.out.println(answer);
    }
}