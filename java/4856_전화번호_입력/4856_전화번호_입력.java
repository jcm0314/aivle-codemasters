// Problem: 전화번호 입력
// Number: 4856
// Solved: 2026. 5. 28.
// Language: Java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static boolean isValidPhone(String s) {
        // 길이 체크
        if (s.length() != 13) {
            return false;
        }

        // 앞 3글자 "010"인지
        if (!s.substring(0, 3).equals("010")) {
            return false;
        }

        // '-' 위치 체크
        if (s.charAt(3) != '-' || s.charAt(8) != '-') {
            return false;
        }

        // 숫자 부분 체크: 4~7, 9~12
        for (int i = 4; i <= 7; i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }

        for (int i = 9; i <= 12; i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();

        if (isValidPhone(s)) {
            System.out.println("valid");
        } else {
            System.out.println("invalid");
        }
    }
}