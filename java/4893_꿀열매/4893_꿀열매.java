// Problem: 꿀열매
// Number: 4893
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {
    static int N, M;
    static char[][] map;

    static final int INF = 1_000_000_000;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new char[N][M];

        Point start = null;
        Point exit = null;
        List<Point> honeys = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String line = scanner.next();
            for (int j = 0; j < M; j++) {
                char ch = line.charAt(j);
                map[i][j] = ch;
                if (ch == 'A') start = new Point(i, j);
                else if (ch == 'B') exit = new Point(i, j);
                else if (ch == 'G') honeys.add(new Point(i, j));
            }
        }

        // A에서 모든 칸까지의 최소 칸 수
        int[][] distFromA = bfs(start);
        // B에서 모든 칸까지의 최소 칸 수
        int[][] distFromB = bfs(exit);

        int distAB = distFromA[exit.r][exit.c];
        // 꿀을 전혀 먹지 않는 경우: 하루 1칸
        int answer = distAB; // 최소 일수 후보

        // 꿀을 먹는 경우들 시도
        for (Point g : honeys) {
            int dAG = distFromA[g.r][g.c];
            int dGB = distFromB[g.r][g.c];

            if (dAG == INF || dGB == INF) continue;

            int totalSteps = dAG + dGB;

            // dAG칸은 하루 1칸, dGB칸은 하루 2칸(올림)
            // 꿀을 먹은 날은 "먹으러 가는 칸"도 이동에 포함하므로
            // A→G까지는 1칸/일, G→B는 2칸/일로 나누어 일수 계산
            int daysBeforeHoney = dAG;
            int daysAfterHoney = (dGB + 1) / 2; // 2칸/일 → 올림 나눗셈

            int days = daysBeforeHoney + daysAfterHoney;
            if (days < answer) {
                answer = days;
            }
        }

        System.out.println(answer);
    }

    // 벽(#)을 제외한 칸들에 대해 BFS로 최소 칸 수 계산
    static int[][] bfs(Point start) {
        int[][] dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], INF);
        }

        Queue<Point> q = new ArrayDeque<>();
        dist[start.r][start.c] = 0;
        q.offer(start);

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            int d = dist[r][c];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (map[nr][nc] == '#') continue; // 벽은 못 감

                if (dist[nr][nc] > d + 1) {
                    dist[nr][nc] = d + 1;
                    q.offer(new Point(nr, nc));
                }
            }
        }

        return dist;
    }
}