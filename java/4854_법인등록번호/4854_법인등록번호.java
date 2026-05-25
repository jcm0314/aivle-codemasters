// Problem: 법인등록번호
// Number: 4854
// Solved: 2026. 5. 25.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String office = sc.next();   // 등기관서별 분류번호 4자리
        String serial = sc.next();   // 일련번호 6자리
        String chkStr = sc.next();   // 오류검색번호 1자리
        int givenCheck = chkStr.charAt(0) - '0';

        // 각 법인 종류별 후보 범위
        int[][] ranges = {
                {11, 15}, // 상업
                {21, 22}, // 민법
                {31, 51}, // 특수
                {81, 86}, // 외국
                {71, 71}  // 기타
        };

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int start = ranges[i][0];
            int end = ranges[i][1];

            boolean possible = false;

            for (int type = start; type <= end; type++) {
                String typeStr = String.format("%02d", type);

                // 앞 12자리 구성
                String twelve = office + typeStr + serial; // 길이 12

                int A = 0; // 홀수번째 자리 합
                int B = 0; // 짝수번째 자리 합

                for (int idx = 0; idx < 12; idx++) {
                    int digit = twelve.charAt(idx) - '0';
                    int pos = idx + 1; // 1~12번째 자리

                    if (pos % 2 == 1) {
                        A += digit;
                    } else {
                        B += digit;
                    }
                }

                int R = (2 * B + A) % 10;
                int check = 10 - R;
                if (check == 10) check = 0;

                if (check == givenCheck) {
                    possible = true;
                    break; // 이 범위 안에서 하나라도 가능하면 O
                }
            }

            result.append(possible ? 'O' : 'X');
        }

        System.out.println(result.toString());
    }
}