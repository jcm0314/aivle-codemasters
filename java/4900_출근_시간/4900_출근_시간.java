// Problem: 출근 시간
// Number: 4900
// Solved: 2026. 6. 12.
// Language: Java

import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {

    static class Edge {
        int to;
        int w;
        Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // 도시 수
        int M = scanner.nextInt(); // 도로 수
        int K = scanner.nextInt(); // 자녀 학교 도시 번호

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            graph.get(u).add(new Edge(v, w));
        }

        // 1 -> K까지 최단거리
        int[] distFrom1 = dijkstra(graph, N, 1);
        // K -> N까지 최단거리
        int[] distFromK = dijkstra(graph, N, K);

        int result = distFrom1[K] + distFromK[N];

        System.out.println(result);
    }

    private static int[] dijkstra(List<List<Edge>> graph, int N, int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        // [0] : node, [1] : distance
        pq.offer(new int[]{start, 0});

        boolean[] visited = new boolean[N + 1];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int now = cur[0];
            int d = cur[1];

            if (visited[now]) continue;
            visited[now] = true;

            for (Edge e : graph.get(now)) {
                int next = e.to;
                int nd = d + e.w;
                if (nd < dist[next]) {
                    dist[next] = nd;
                    pq.offer(new int[]{next, nd});
                }
            }
        }

        return dist;
    }
}