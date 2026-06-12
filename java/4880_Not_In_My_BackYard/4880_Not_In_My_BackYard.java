// Problem: Not In My BackYard
// Number: 4880
// Solved: 2026. 6. 12.
// Language: Java

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int K = scanner.nextInt();

        int[] houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = scanner.nextInt();
        }

        // 모든 집 좌표를 후보 위치로 사용
        int bestPos = houses[0];
        int bestScore = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            int pos = houses[i]; // 소각장 위치 후보
            int score = 0;

            for (int j = 0; j < N; j++) {
                int d = Math.abs(pos - houses[j]);
                if (d <= K) {
                    score += d;
                } else {
                    score -= d;
                }
            }

            if (score > bestScore) {
                bestScore = score;
                bestPos = pos;
            } else if (score == bestScore && pos < bestPos) {
                // 동일한 만족도면 좌표가 작은 위치를 선택
                bestPos = pos;
            }
        }

        System.out.println(bestPos);
    }
}