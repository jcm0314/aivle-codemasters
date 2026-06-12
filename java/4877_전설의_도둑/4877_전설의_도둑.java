// Problem: 전설의 도둑
// Number: 4877
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {

    static final int MAX = 100000;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int K = scanner.nextInt(); // 현민 위치
        int N = scanner.nextInt(); // 재우 위치

        // 같은 위치면 워프 0번
        if (K == N) {
            System.out.println(0);
            return;
        }

        int[] dist = new int[MAX + 1];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(K);
        dist[K] = 0;

        while (!q.isEmpty()) {
            int x = q.poll();
            int d = dist[x];

            // 세 가지 이동
            int[] nexts = {x + 3, x - 1, x * 2};

            for (int nx : nexts) {
                if (nx < 0 || nx > MAX) continue;
                if (dist[nx] != -1) continue;

                dist[nx] = d + 1;
                if (nx == N) {
                    System.out.println(dist[nx]);
                    return;
                }
                q.offer(nx);
            }
        }

        // 이 문제 범위에서는 항상 도달 가능하지만,
        // 혹시 모를 경우를 대비해 출력
        System.out.println(dist[N]);
    }
}