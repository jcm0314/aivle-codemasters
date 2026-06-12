// Problem: Trailing Zero - 2
// Number: 4905
// Solved: 2026. 6. 12.
// Language: Java

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();  // 필요한 trailing zero 개수

        // 이분 탐색 범위 설정
        int left = 0;
        int right = 100000; // n <= 1000에 충분히 큰 상한

        int answer = right;

        while (left <= right) {
            int mid = (left + right) / 2;
            int tz = trailingZeros(mid);

            if (tz >= n) {
                // 조건을 만족하므로 일단 후보로 저장하고 더 작은 값 탐색
                answer = mid;
                right = mid - 1;
            } else {
                // 아직 trailing zero가 부족하므로 더 큰 쪽 탐색
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    // x! 의 trailing zero 개수 (5의 지수 합)
    static int trailingZeros(int x) {
        int count = 0;
        int div = 5;

        while (div <= x) {
            count += x / div;
            div *= 5;
        }

        return count;
    }
}