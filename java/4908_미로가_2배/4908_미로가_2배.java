// Problem: 미로가 2배
// Number: 4908
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {

    static int H1, W1, H2, W2;
    static char[][] map1, map2;

    static int s1r, s1c, e1r, e1c;
    static int s2r, s2c, e2r, e2c;

    // 상, 하, 좌, 우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // 첫 번째 미로
        H1 = scanner.nextInt();
        W1 = scanner.nextInt();
        map1 = new char[H1][W1];

        for (int i = 0; i < H1; i++) {
            String line = scanner.next();
            for (int j = 0; j < W1; j++) {
                char ch = line.charAt(j);
                map1[i][j] = ch;
                if (ch == 'S') {
                    s1r = i; s1c = j;
                } else if (ch == 'E') {
                    e1r = i; e1c = j;
                }
            }
        }

        // 두 번째 미로
        H2 = scanner.nextInt();
        W2 = scanner.nextInt();
        map2 = new char[H2][W2];

        for (int i = 0; i < H2; i++) {
            String line = scanner.next();
            for (int j = 0; j < W2; j++) {
                char ch = line.charAt(j);
                map2[i][j] = ch;
                if (ch == 'S') {
                    s2r = i; s2c = j;
                } else if (ch == 'E') {
                    e2r = i; e2c = j;
                }
            }
        }

        int answer = bfs();
        System.out.println(answer);
    }

    static int bfs() {
        // visited[r1][c1][r2][c2]
        boolean[][][][] visited =
            new boolean[H1][W1][H2][W2];

        Queue<State> q = new ArrayDeque<>();
        visited[s1r][s1c][s2r][s2c] = true;
        q.offer(new State(s1r, s1c, s2r, s2c, 0));

        while (!q.isEmpty()) {
            State cur = q.poll();

            // 두 미로 모두 도착점이면 종료
            if (cur.r1 == e1r && cur.c1 == e1c &&
                cur.r2 == e2r && cur.c2 == e2c) {
                return cur.dist;
            }

            for (int d = 0; d < 4; d++) {
                int nr1 = cur.r1 + dr[d];
                int nc1 = cur.c1 + dc[d];
                int nr2 = cur.r2 + dr[d];
                int nc2 = cur.c2 + dc[d];

                // 첫 번째 미로 이동
                if (!inRange1(nr1, nc1) || map1[nr1][nc1] == '#') {
                    // 벽이거나 밖이면 이동하지 않음
                    nr1 = cur.r1;
                    nc1 = cur.c1;
                }

                // 두 번째 미로 이동
                if (!inRange2(nr2, nc2) || map2[nr2][nc2] == '#') {
                    nr2 = cur.r2;
                    nc2 = cur.c2;
                }

                if (!visited[nr1][nc1][nr2][nc2]) {
                    visited[nr1][nc1][nr2][nc2] = true;
                    q.offer(new State(nr1, nc1, nr2, nc2, cur.dist + 1));
                }
            }
        }

        // 도달 불가
        return -1;
    }

    static boolean inRange1(int r, int c) {
        return r >= 0 && r < H1 && c >= 0 && c < W1;
    }

    static boolean inRange2(int r, int c) {
        return r >= 0 && r < H2 && c >= 0 && c < W2;
    }

    static class State {
        int r1, c1, r2, c2, dist;
        State(int r1, int c1, int r2, int c2, int dist) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.dist = dist;
        }
    }
}