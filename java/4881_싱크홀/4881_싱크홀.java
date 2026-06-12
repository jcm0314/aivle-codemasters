// Problem: 싱크홀
// Number: 4881
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

        int[] pos = new int[N];
        for (int i = 0; i < N; i++) {
            pos[i] = scanner.nextInt();
        }

        Arrays.sort(pos);

        int count = 0;
        int coverEnd = Integer.MIN_VALUE; // 현재까지 덮인 구간의 오른쪽 끝 (미만은 모두 덮임)

        for (int i = 0; i < N; i++) {
            if (pos[i] < coverEnd) {
                // 이미 기존 널빤지에 덮여 있음
                continue;
            }
            // 새로운 널빤지 하나 필요
            count++;
            coverEnd = pos[i] + K; // [pos[i], pos[i]+K) 구간 덮임
        }

        System.out.println(count);
    }
}