// Problem: 나이트 자리 바꾸기
// Number: 4873
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {

    static final int N = 3; // 3x3 체스판

    // 나이트 이동 8방향
    static int[] dr = {-1, 1, -2, -2, 2, 2, -1, 1};
    static int[] dc = {2, 2, 1, -1, -1, 1, -2, -2};

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        char[][] board = new char[N][N];

        for (int i = 0; i < N; i++) {
            String line = scanner.next();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        String start = encode(board);
        String target = makeTarget(board);

        boolean possible = bfs(start, target);

        System.out.println(possible ? "possible" : "impossible");
    }

    // 보드를 문자열로 인코딩 (길이 9)
    static String encode(char[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    // 문자열을 보드로 디코딩
    static char[][] decode(String s) {
        char[][] board = new char[N][N];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = s.charAt(idx++);
            }
        }
        return board;
    }

    // 현재 보드에서 흰(1)과 검(2)의 위치를 서로 바꾼 목표 상태 만들기
    static String makeTarget(char[][] board) {
        char[][] target = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char c = board[i][j];
                if (c == '1') target[i][j] = '2';
                else if (c == '2') target[i][j] = '1';
                else target[i][j] = c; // 0은 그대로
            }
        }
        return encode(target);
    }

    // 상태 BFS로 start에서 target 도달 가능 여부 확인
    static boolean bfs(String start, String target) {
        if (start.equals(target)) return true;

        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        q.offer(start);
        visited.add(start);

        while (!q.isEmpty()) {
            String cur = q.poll();

            if (cur.equals(target)) return true;

            char[][] board = decode(cur);

            // 모든 나이트에 대해 이동 시도
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    char piece = board[r][c];
                    if (piece == '1' || piece == '2') {
                        for (int k = 0; k < 8; k++) {
                            int nr = r + dr[k];
                            int nc = c + dc[k];

                            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                            if (board[nr][nc] != '0') continue; // 빈칸만 가능

                            // (r,c) 나이트를 (nr,nc)로 이동한 새 상태 생성
                            char[][] nextBoard = copyBoard(board);
                            nextBoard[nr][nc] = piece;
                            nextBoard[r][c] = '0';

                            String next = encode(nextBoard);
                            if (!visited.contains(next)) {
                                visited.add(next);
                                q.offer(next);
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    static char[][] copyBoard(char[][] src) {
        char[][] dst = new char[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(src[i], 0, dst[i], 0, N);
        }
        return dst;
    }
}