// Problem: 블로그
// Number: 4882
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
        int K = scanner.nextInt();

        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }

        // 첫 K일 합
        int currSum = 0;
        for (int i = 0; i < K; i++) {
            currSum += A[i];
        }

        int maxSum = currSum;
        int bestStart = 0; // 0-based index

        // 슬라이딩 윈도우
        for (int start = 1; start + K - 1 < N; start++) {
            currSum = currSum - A[start - 1] + A[start + K - 1];
            if (currSum > maxSum) {
                maxSum = currSum;
                bestStart = start;
            }
        }

        // 문제는 1-based 날 번호를 요구
        System.out.println(bestStart + 1);
    }
}