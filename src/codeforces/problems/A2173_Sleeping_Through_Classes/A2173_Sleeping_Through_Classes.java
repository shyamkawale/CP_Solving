package codeforces.problems.A2173_Sleeping_Through_Classes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class A2173_Sleeping_Through_Classes {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/problems/A2173_Sleeping_Through_Classes/input.txt")
                : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/problems/A2173_Sleeping_Through_Classes/output.txt")
                : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        A2173_Sleeping_Through_Classes solver = new A2173_Sleeping_Through_Classes();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            String str = in.next();

            solver.solve(n, k, str);
        }

        out.flush();
    }

    private void solve(int n, int k, String str) {
        int i=0;
        int cnt = 0;
        int shouldBeAwakeTill = -1;
        while(i < n) {
            if(str.charAt(i) == '1') {
                shouldBeAwakeTill = i + k;
            }
            else if(shouldBeAwakeTill < i && str.charAt(i) == '0'){
                cnt++;
            }
            i++;
        }

        out.println(cnt);
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
