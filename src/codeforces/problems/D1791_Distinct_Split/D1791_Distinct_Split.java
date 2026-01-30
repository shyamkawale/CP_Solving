package codeforces.problems.D1791_Distinct_Split;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class D1791_Distinct_Split {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/problems/D1791_Distinct_Split/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/problems/D1791_Distinct_Split/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        D1791_Distinct_Split solver = new D1791_Distinct_Split();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            String s = in.next();
            solver.solve(n, s);
        }

        out.flush();
    }

    private void solve(int n, String s) {
        int[] freqArr1 = new int[26];
        int[] freqArr2 = new int[26];
        int uniqueChar1 = 0;
        int uniqueChar2 = 0;
        for(int i=0; i<n; i++) {
            if(freqArr2[s.charAt(i)-'a'] == 0) {
                uniqueChar2++;
            }
            freqArr2[s.charAt(i)-'a']++;
        }
        
        int maxf = Integer.MIN_VALUE;
        for(int i=0; i<n-1; i++) {
            int ch = s.charAt(i);

            if(freqArr1[ch-'a'] == 0) {
                uniqueChar1++;
            }
            freqArr1[ch-'a']++;

            freqArr2[ch-'a']--;
            if(freqArr2[ch-'a'] == 0) {
                uniqueChar2--;
            }

            maxf = Math.max(maxf, uniqueChar1+uniqueChar2);
        }

        out.println(maxf);
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            br = new BufferedReader(new InputStreamReader(stream));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
