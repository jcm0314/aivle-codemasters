// Problem: 동네 한 바퀴
// Number: 4870
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {

    static int N, M;
    static List<Integer>[] graph;
    static boolean[] visited;
    static boolean canReturn;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        M = scanner.nextInt();

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int s = scanner.nextInt();
            int e = scanner.nextInt();
            graph[s].add(e);
        }

        visited = new boolean[N + 1];
        canReturn = false;

        visited[1] = true;
        dfs(1, 0); // 현재 노드 1, 아직 다른 구역은 방문 전(depth=0)

        System.out.println(canReturn ? "YES" : "NO");
    }

    // cur: 현재 노드, depth: 지금까지 거친 "1번 이외의" 노드 수
    static void dfs(int cur, int depth) {
        if (canReturn) return; // 이미 가능함을 찾았으면 더 볼 필요 없음

        for (int next : graph[cur]) {
            if (next == 1) {
                // 1번으로 돌아가는 간선
                if (depth >= 1) {
                    canReturn = true;
                    return;
                }
                // depth == 0 이면 1 -> 1 즉시 복귀(산책 불인정)이지만
                // 문제에서 s != e 라 했으므로 사실상 안 들어옴
            } else {
                if (!visited[next]) {
                    visited[next] = true;
                    dfs(next, depth + 1);
                    if (canReturn) return;
                }
            }
        }
    }
}