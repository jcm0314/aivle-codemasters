// Problem: 기차와 파리
// Number: 4849
// Solved: 2026. 5. 25.
// Language: Java

// don't place package name. 
 
import java.io.*; 
import java.util.*; 
 
// don't change 'Main' class name and  'public' accessor. 
public class Main { 
    public static void main(String[] args) throws IOException { 
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        StringTokenizer st = new StringTokenizer(br.readLine()); 
 
        long X = Long.parseLong(st.nextToken()); 
        long Y = Long.parseLong(st.nextToken()); 
        long Z = Long.parseLong(st.nextToken()); 
 
        // 충돌까지 걸리는 시간  
        double t = (double) X / (2.0 * Y); 
 
        // 파리가 이동한 거리  
        double distance = Z * t; 
 
        // 소수점 버리기 
        System.out.println((long) distance); 
    } 
}