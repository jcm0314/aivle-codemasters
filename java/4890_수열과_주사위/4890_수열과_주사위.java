// Problem: 수열과 주사위
// Number: 4890
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
        int[] a = new int[N + 1]; // 1-based index

        for (int i = 1; i <= N; i++) {
            a[i] = scanner.nextInt();
        }

        // 주사위 3개의 합 T(3~18)에 대한 경우의 수
        int[] cnt = new int[19];
        cnt[3] = 1;
        cnt[4] = 3;
        cnt[5] = 6;
        cnt[6] = 10;
        cnt[7] = 15;
        cnt[8] = 21;
        cnt[9] = 25;
        cnt[10] = 27;
        cnt[11] = 27;
        cnt[12] = 25;
        cnt[13] = 21;
        cnt[14] = 15;
        cnt[15] = 10;
        cnt[16] = 6;
        cnt[17] = 3;
        cnt[18] = 1;

        long bestValue = Long.MIN_VALUE;
        List<Integer> bestKs = new ArrayList<>();

        // K는 1 이상 N까지 (K+T가 N을 넘어가면 -100 처리)
        for (int K = 1; K <= N; K++) {
            long sum = 0;

            for (int T = 3; T <= 18; T++) {
                int score;
                int idx = K + T;
                if (idx > N) {
                    score = -100;
                } else {
                    score = a[idx];
                }
                sum += (long) cnt[T] * score;
            }

            if (sum > bestValue) {
                bestValue = sum;
                bestKs.clear();
                bestKs.add(K);
            } else if (sum == bestValue) {
                bestKs.add(K);
            }
        }

        // 첫째 줄: 최대 기댓값 * 216
        System.out.println(bestValue);

        // 둘째 줄: 오름차순 K들 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bestKs.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(bestKs.get(i));
        }
        System.out.println(sb.toString());
    }
}