// Problem: 주사위 굴리기
// Number: 4906
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()); // 초기 X (동쪽으로)
        int y = Integer.parseInt(st.nextToken()); // 초기 Y (남쪽으로)

        // dice[0..5] = {E, S, W, N, T, B}
        int[] dice = new int[6];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        int N = Integer.parseInt(br.readLine().trim());
        int[] cmds = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cmds[i] = Integer.parseInt(st.nextToken());
        }

        int[][] board = new int[H][W];

        // 시작 위치에도 바닥 숫자 찍기
        board[y][x] = dice[5]; // bottom

        for (int c = 0; c < N; c++) {
            int dir = cmds[c];
            int nx = x;
            int ny = y;

            if (dir == 1) {          // 동쪽
                nx = x + 1;
            } else if (dir == 2) {   // 남쪽
                ny = y + 1;
            } else if (dir == 3) {   // 서쪽
                nx = x - 1;
            } else if (dir == 4) {   // 북쪽
                ny = y - 1;
            }

            // 경계 체크: 밖이면 위치는 그대로 (nx, ny 무시)
            boolean out = (nx < 0 || nx >= W || ny < 0 || ny >= H);
            if (!out) {
                x = nx;
                y = ny;
            }

            // 방향에 따라 주사위 굴리기 (제자리에서 굴리는 것도 포함)
            rollDice(dice, dir);

            // 현재 위치에 바닥 숫자 찍기
            board[y][x] = dice[5]; // bottom
        }

        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < H; row++) {
            for (int col = 0; col < W; col++) {
                if (col > 0) sb.append(' ');
                sb.append(board[row][col]);
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }

    // dice[0..5] = {E, S, W, N, T, B}
    static void rollDice(int[] d, int dir) {
        int[] nd = new int[6];
        if (dir == 1) { // 동쪽
            nd[0] = d[4]; // E <= T
            nd[2] = d[5]; // W <= B
            nd[4] = d[2]; // T <= W
            nd[5] = d[0]; // B <= E
            nd[1] = d[1]; // S
            nd[3] = d[3]; // N
        } else if (dir == 3) { // 서쪽
            nd[0] = d[5]; // E <= B
            nd[2] = d[4]; // W <= T
            nd[4] = d[0]; // T <= E
            nd[5] = d[2]; // B <= W
            nd[1] = d[1];
            nd[3] = d[3];
        } else if (dir == 2) { // 남쪽
            nd[1] = d[4]; // S <= T
            nd[3] = d[5]; // N <= B
            nd[4] = d[3]; // T <= N
            nd[5] = d[1]; // B <= S
            nd[0] = d[0]; // E
            nd[2] = d[2]; // W
        } else if (dir == 4) { // 북쪽
            nd[1] = d[5]; // S <= B
            nd[3] = d[4]; // N <= T
            nd[4] = d[1]; // T <= S
            nd[5] = d[3]; // B <= N
            nd[0] = d[0];
            nd[2] = d[2];
        } else {
            // 잘못된 방향은 변화 없음
            System.arraycopy(d, 0, nd, 0, 6);
        }
        System.arraycopy(nd, 0, d, 0, 6);
    }
}