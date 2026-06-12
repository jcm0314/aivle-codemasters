// Problem: 넘치는 자신감
// Number: 4891
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {
    static final long MOD = 1_000_000_007L;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        long[] w = new long[N + 1];
        long[] p = new long[N + 1];

        // 1번 선수
        w[1] = scanner.nextLong();
        p[1] = scanner.nextLong();

        long multiplierW = scanner.nextLong();
        long multiplierP = scanner.nextLong();

        int K = scanner.nextInt();

        // 갱신 정보 저장: u1 -> (u2, u3)
        Map<Integer, long[]> updates = new HashMap<>();
        for (int i = 0; i < K; i++) {
            int u1 = scanner.nextInt();
            long u2 = scanner.nextLong();
            long u3 = scanner.nextLong();
            updates.put(u1, new long[]{u2, u3});
        }

        // 2번 ~ N번 선수 w[i], p[i] 계산
        for (int i = 2; i <= N; i++) {
            w[i] = (w[i - 1] * multiplierW) % MOD;
            p[i] = (p[i - 1] * multiplierP) % MOD;

            // i에 해당하는 갱신 정보가 있으면 multiplier 교체
            if (updates.containsKey(i)) {
                long[] up = updates.get(i);
                multiplierW = up[0];
                multiplierP = up[1];
            }
        }

        // 몸무게 배열 복사해서 정렬
        long[] weights = new long[N];
        for (int i = 1; i <= N; i++) {
            weights[i - 1] = w[i];
        }
        Arrays.sort(weights);

        long answer = 0L;

        for (int i = 1; i <= N; i++) {
            long threshold = w[i] - p[i]; // w[j] < threshold

            // 이분 탐색: weights에서 threshold 미만 개수
            int count = lowerBound(weights, threshold);

            // 자기 자신이 포함되는지 확인
            if (w[i] < threshold) {
                count--; // 본인 제외
            }

            answer += count;
            if (answer >= MOD) answer %= MOD;
        }

        answer %= MOD;
        System.out.println(answer);
    }

    // 처음으로 arr[pos] >= value 가 되는 위치 pos 리턴
    // 즉, value 미만인 원소의 개수 = pos
    static int lowerBound(long[] arr, long value) {
        int left = 0;
        int right = arr.length; // [left, right)

        while (left < right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] >= value) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}