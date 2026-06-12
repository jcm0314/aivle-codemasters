// Problem: 격자판 칠하기
// Number: 4894
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {
    static int N, M;
    static int[][] board;   // 각 칸의 색(0,1,2)
    static long answer;     // 경우의 수

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        M = scanner.nextInt();

        board = new int[N][M];
        answer = 0;

        dfs(0, 0);

        System.out.println(answer);
    }

    // (r, c) 위치부터 끝까지 칠하는 DFS
    static void dfs(int r, int c) {
        // 한 행이 끝나면 다음 행으로
        if (c == M) {
            r++;
            c = 0;
        }

        // 모든 칸을 다 채웠다면 경우의 수 1 증가
        if (r == N) {
            answer++;
            return;
        }

        // 현재 칸 (r,c)에 3가지 색(0,1,2)을 시도
        for (int color = 0; color < 3; color++) {
            if (canColor(r, c, color)) {
                board[r][c] = color;
                dfs(r, c + 1);
                // 되돌리기(사실 안 해도 다시 덮어쓰지만 명시적으로 적어도 됨)
                // board[r][c] = -1;
            }
        }
    }

    // (r,c)에 color를 칠할 수 있는지 검사
    static boolean canColor(int r, int c, int color) {
        // 위쪽 칸과 같은 색이면 안 됨
        if (r - 1 >= 0 && board[r - 1][c] == color) {
            return false;
        }
        // 왼쪽 칸과 같은 색이면 안 됨
        if (c - 1 >= 0 && board[r][c - 1] == color) {
            return false;
        }
        return true;
    }
}