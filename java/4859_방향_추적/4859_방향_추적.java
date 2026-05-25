// Problem: 방향 추적
// Number: 4859
// Solved: 2026. 5. 25.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // 이동한 위치 개수

        int[] ys = new int[N];
        int[] xs = new int[N];

        for (int i = 0; i < N; i++) {
            ys[i] = scanner.nextInt(); // y 좌표
            xs[i] = scanner.nextInt(); // x 좌표
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N - 1; i++) {
            int y1 = ys[i];
            int x1 = xs[i];
            int y2 = ys[i + 1];
            int x2 = xs[i + 1];

            int dir = 0;
            int dist = 0;

            if (y1 == y2) { // 가로(동/서) 이동
                if (x2 > x1) {        // 동쪽
                    dir = 2;
                    dist = x2 - x1;
                } else {              // 서쪽
                    dir = 4;
                    dist = x1 - x2;
                }
            } else if (x1 == x2) { // 세로(북/남) 이동
                if (y2 > y1) {        // 북쪽
                    dir = 1;
                    dist = y2 - y1;
                } else {              // 남쪽
                    dir = 3;
                    dist = y1 - y2;
                }
            }

            sb.append(dir).append(" ").append(dist).append("\n");
        }

        System.out.print(sb.toString());
    }
}