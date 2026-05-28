// Problem: 야바위
// Number: 4860
// Solved: 2026. 5. 28.
// Language: Java

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // 컵 개수
        int M = scanner.nextInt(); // 섞은 횟수

        int[] pos = new int[N + 1]; // 1-based index 사용

        // 초기 상태 위치 i에 컵 i
        for (int i = 1; i <= N; i++) {
            pos[i] = i;
        }

        // 스왑 정보 적용
        for (int i = 0; i < M; i++) {
            int A = scanner.nextInt();
            int B = scanner.nextInt();

            int temp = pos[A];
            pos[A] = pos[B];
            pos[B] = temp;
        }

        int K = scanner.nextInt(); // 처음 공이 들어 있던 컵 번호

        // 컵 K가 최종적으로 있는 위치 찾기
        int answerPos = -1;
        for (int i = 1; i <= N; i++) {
            if (pos[i] == K) {
                answerPos = i;
                break;
            }
        }

        System.out.println(answerPos);
    }
}