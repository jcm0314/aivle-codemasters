// Problem: 가지치기
// Number: 4937
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {

    static class Edge {
        int u, v;
        int w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) return false;

            if (rank[ra] < rank[rb]) {
                parent[ra] = rb;
            } else if (rank[ra] > rank[rb]) {
                parent[rb] = ra;
            } else {
                parent[rb] = ra;
                rank[ra]++;
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // 정점 수
        int M = scanner.nextInt(); // 간선 수

        int[] w = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            w[i] = scanner.nextInt();
        }

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int weight = w[a] + w[b]; // 간선 가중치 = 양 끝 정점 아름다움 합
            edges.add(new Edge(a, b, weight));
        }

        // 가중치 기준 내림차순 정렬 (최대 스패닝 트리)
        edges.sort((e1, e2) -> Integer.compare(e2.w, e1.w));

        DSU dsu = new DSU(N);

        long totalBeauty = 0;
        int edgeCount = 0;

        for (Edge e : edges) {
            if (dsu.union(e.u, e.v)) {
                totalBeauty += e.w;
                edgeCount++;
                if (edgeCount == N - 1) break;
            }
        }

        System.out.println(totalBeauty);
    }
}