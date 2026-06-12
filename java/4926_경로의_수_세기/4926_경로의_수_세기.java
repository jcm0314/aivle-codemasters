// Problem: 경로의 수 세기
// Number: 4926
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {

    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // 방의 수
        int k = scanner.nextInt(); // 통로의 수

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < k; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            // 양방향 통로
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // 1단계: BFS로 최단 거리 구하기
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new ArrayDeque<>();
        dist[1] = 0;
        q.offer(1);

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph.get(cur)) {
                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1;
                    q.offer(next);
                }
            }
        }

        // 2단계: 최단 경로 수 세기 (DP)
        long[] ways = new long[n + 1];
        ways[1] = 1;

        // dist 기준으로 정점들을 정렬
        Integer[] nodes = new Integer[n];
        for (int i = 0; i < n; i++) nodes[i] = i + 1;
        Arrays.sort(nodes, (a, b) -> Integer.compare(dist[a], dist[b]));

        for (int u : nodes) {
            if (dist[u] == -1) continue; // 도달 불가 (문제 조건상 없지만 안전용)
            for (int v : graph.get(u)) {
                // 최단 거리 방향으로만 이동
                if (dist[v] == dist[u] + 1) {
                    ways[v] = (ways[v] + ways[u]) % MOD;
                }
            }
        }

        System.out.println(ways[n] % MOD);
    }
}