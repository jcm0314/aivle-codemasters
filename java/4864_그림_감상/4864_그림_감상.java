// Problem: 그림 감상
// Number: 4864
// Solved: 2026. 5. 19.
// Language: Java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        char[][] board = new char[4][4]; // 맵
        
        // 44 맵에 OX체크
        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < 4; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        
        // 불가능으로 초기화
        boolean possible = false;
        
        // cntX가 4일 경우는 아무데나 붓칠해도 되니깐 ㅇㅋ
        // cntX가 3일 경우 그 곳에 붓칠하면 되니깐 ㅇㅋ
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int cntX = 0;
                
                if (board[i][j] == 'X') cntX++;
                if (board[i+1][j] == 'X') cntX++;
                if (board[i][j+1] == 'X') cntX++;
                if (board[i+1][j+1] == 'X') cntX++;
                
                if (cntX >= 3) {
                    possible = true;
                }
                
            }
        }
        System.out.println(possible ? "yes" : "no");
    }
}