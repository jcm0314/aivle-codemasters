// Problem: 두 컨테이너
// Number: 4939
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // 창고 수
        int M = scanner.nextInt(); // 도로 수

        int S1 = scanner.nextInt();
        int D1 = scanner.nextInt();
        int S2 = scanner.nextInt();
        int D2 = scanner.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // dist[pos1][pos2] = 최소 이동 횟수, -1이면 미방문
        int[][] dist = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dist[i], -1);
        }

        Queue<int[]> q = new ArrayDeque<>();
        dist[S1][S2] = 0;
        q.offer(new int[]{S1, S2});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int p1 = cur[0];
            int p2 = cur[1];
            int d = dist[p1][p2];

            // 이미 목표 상태면 더 짧은 경로는 BFS 상 불가능하지만,
            // 그대로 계속 돌려도 괜찮고, 여기서 끊어도 됨.
            // if (p1 == D1 && p2 == D2) break;

            // 1번 컨테이너 이동
            for (int next1 : graph.get(p1)) {
                // 한 창고에는 하나의 컨테이너만 존재 가능
                if (next1 == p2) continue;

                if (dist[next1][p2] == -1) {
                    dist[next1][p2] = d + 1;
                    q.offer(new int[]{next1, p2});
                }
            }

            // 2번 컨테이너 이동
            for (int next2 : graph.get(p2)) {
                if (next2 == p1) continue;

                if (dist[p1][next2] == -1) {
                    dist[p1][next2] = d + 1;
                    q.offer(new int[]{p1, next2});
                }
            }
        }

        System.out.println(dist[D1][D2]);
    }
}