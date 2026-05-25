// Problem: 삼색 잉크
// Number: 4853
// Solved: 2026. 5. 25.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String weekStr = sc.next();   // 1월 1일 요일 (SUN, MON, ...)
        int N = sc.nextInt();         // 공휴일 개수

        // 월별 말일 (윤년 아님)
        int[] monthDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        // 공휴일 표시: 0-based 인덱스 사용 (month-1, day-1)
        boolean[][] isHoliday = new boolean[12][31];

        for (int i = 0; i < N; i++) {
            int a = sc.nextInt(); // a월
            int b = sc.nextInt(); // b일
            isHoliday[a - 1][b - 1] = true;
        }

        // 요일을 0~6 정수로 매핑
        int startDay = toWeekIndex(weekStr); // 0: SUN, ..., 6: SAT

        // counts[digit][color]  color: 0=red, 1=blue, 2=black
        int[][] counts = new int[10][3];

        int curDay = startDay; // 1월 1일의 요일

        for (int month = 0; month < 12; month++) {
            for (int day = 1; day <= monthDays[month]; day++) {
                // 색 결정
                int color = getColor(curDay, isHoliday[month][day - 1]);
                // day 숫자를 문자열로 바꿔서 각 자리수 카운트
                String s = Integer.toString(day);
                for (int i = 0; i < s.length(); i++) {
                    int d = s.charAt(i) - '0';
                    counts[d][color]++;
                }
                // 다음 날짜로
                curDay = (curDay + 1) % 7;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int d = 0; d <= 9; d++) {
            sb.append(counts[d][0]).append(" ")
              .append(counts[d][1]).append(" ")
              .append(counts[d][2]).append("\n");
        }

        System.out.print(sb.toString());
    }

    // 요일 문자열을 인덱스로 변환
    private static int toWeekIndex(String w) {
        switch (w) {
            case "SUN": return 0;
            case "MON": return 1;
            case "TUE": return 2;
            case "WED": return 3;
            case "THU": return 4;
            case "FRI": return 5;
            case "SAT": return 6;
        }
        return 0; // 문제 조건상 여기 오지 않음
    }

    // 색상 결정: 0=red, 1=blue, 2=black
    private static int getColor(int dayOfWeek, boolean isHoliday) {
        // dayOfWeek: 0=SUN, 1=MON, ..., 6=SAT
        if (dayOfWeek == 0 || isHoliday) {
            // 일요일이거나, 공휴일이면 빨간색
            return 0; // red
        } else if (dayOfWeek == 6) {
            // 공휴일이 아닌 토요일은 파란색
            return 1; // blue
        } else {
            // 나머지 평일은 검은색
            return 2; // black
        }
    }
}