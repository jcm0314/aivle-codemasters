// Problem: 메타버스 토끼
// Number: 4889
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {

    static int N, M;
    static char[][] grid;
    static int[][] dist;

    // 상, 하, 좌, 우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        M = scanner.nextInt();
        grid = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = scanner.next();
            for (int j = 0; j < M; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        int answer = bfs();
        System.out.println(answer);
    }

    static int bfs() {
        dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }

        // 시작 위치 (0,0)이 벽이면 바로 -1
        if (grid[0][0] == '#' || grid[N - 1][M - 1] == '#') {
            return -1;
        }

        Queue<int[]> q = new ArrayDeque<>();
        dist[0][0] = 0;
        q.offer(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int curDist = dist[r][c];

            if (r == N - 1 && c == M - 1) {
                return curDist;
            }

            // 4방향으로 토끼 이동 시뮬레이션
            for (int dir = 0; dir < 4; dir++) {
                int nr1 = r + dr[dir];
                int nc1 = c + dc[dir];

                // 첫 번째 칸: 범위 체크 및 벽 여부
                if (!inRange(nr1, nc1) || grid[nr1][nc1] == '#') {
                    continue; // 이 방향으론 이동 불가
                }

                // 기본적으로 첫 번째 칸까지는 간다
                int nr = nr1;
                int nc = nc1;

                // 두 번째 칸 시도
                int nr2 = nr1 + dr[dir];
                int nc2 = nc1 + dc[dir];

                if (inRange(nr2, nc2) && grid[nr2][nc2] == '.') {
                    // 둘째 칸도 비어있으면 반드시 이동
                    nr = nr2;
                    nc = nc2;
                }

                // (nr, nc)가 이번 1분 이동 후 위치
                if (dist[nr][nc] == -1) {
                    dist[nr][nc] = curDist + 1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        // 도달 불가
        return -1;
    }

    static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}