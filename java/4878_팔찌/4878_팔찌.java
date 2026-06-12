// Problem: 팔찌
// Number: 4878
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        String s1 = scanner.nextLine().trim();
        String s2 = scanner.nextLine().trim();

        // 길이가 다르면 바로 NO
        if (s1.length() != s2.length()) {
            System.out.println("NO");
            return;
        }

        // s1을 두 번 이어붙인 문자열에 s2가 포함되면 회전으로 같음
        String doubled = s1 + s1;

        if (doubled.contains(s2)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}