// Problem: 음식 배달
// Number: 4897
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {

    static int N, K;
    static int[] xs, ys;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        K = scanner.nextInt();

        xs = new int[N];
        ys = new int[N];

        for (int i = 0; i < N; i++) {
            xs[i] = scanner.nextInt();
            ys[i] = scanner.nextInt();
        }

        // 집 인덱스 0..N-1에서 K개를 뽑는 조합
        boolean[] chosen = new boolean[N];
        combine(0, 0, chosen);

        System.out.println(answer);
    }

    // 조합: idx번째 집을 볼 차례, cnt개 선택 완료
    static void combine(int idx, int cnt, boolean[] chosen) {
        if (cnt == K) {
            // 선택된 집들의 인덱스를 리스트로 모아서 순열 탐색
            int[] selected = new int[K];
            int p = 0;
            for (int i = 0; i < N; i++) {
                if (chosen[i]) selected[p++] = i;
            }
            permute(selected, 0);
            return;
        }
        if (idx == N) return;

        // 선택
        chosen[idx] = true;
        combine(idx + 1, cnt + 1, chosen);

        // 선택 안 함
        chosen[idx] = false;
        combine(idx + 1, cnt, chosen);
    }

    // selected 배열에 담긴 집 인덱스들의 순열을 모두 시도
    static void permute(int[] selected, int depth) {
        if (depth == selected.length) {
            // 이 순열에 대한 왕복 시간 계산
            int total = 0;

            // A(1,1)에서 첫 집까지
            int first = selected[0];
            total += dist(1, 1, xs[first], ys[first]);

            // 집들 사이
            for (int i = 0; i < selected.length - 1; i++) {
                int u = selected[i];
                int v = selected[i + 1];
                total += dist(xs[u], ys[u], xs[v], ys[v]);
            }

            // 마지막 집에서 A로
            int last = selected[selected.length - 1];
            total += dist(xs[last], ys[last], 1, 1);

            if (total < answer) {
                answer = total;
            }
            return;
        }

        for (int i = depth; i < selected.length; i++) {
            swap(selected, depth, i);
            permute(selected, depth + 1);
            swap(selected, depth, i);
        }
    }

    static int dist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}