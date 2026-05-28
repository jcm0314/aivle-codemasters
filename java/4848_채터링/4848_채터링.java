// Problem: 채터링
// Number: 4848
// Solved: 2026. 5. 28.
// Language: Java

// don't place package name. 
import java.io.*;
import java.util.*; 

// don't change 'Main' class name and  'public' accessor. 

public class Main {
    public static void main(String[] args) throws IOException { 

        Scanner scanner = new Scanner(System.in); 
        int N = scanner.nextInt(); 
        int K = scanner.nextInt(); 
        String s = scanner.next();
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);
            for (int j = 0; j < K; j++) {
                sb.append(c);
            }
        }

        System.out.println(sb.toString());
    }
}