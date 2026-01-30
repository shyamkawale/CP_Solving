package codeforces.problems.B2188_Seats;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B2188_Seats {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/problems/B2188_Seats/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/problems/B2188_Seats/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        B2188_Seats solver = new B2188_Seats();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            String seats = in.next();

            solver.solve(n, seats);
        }

        out.flush();
    }

    private void solve(int n, String seats) {
        int idx = 0;
        int cnt = 0;

        for (int i=0; i<n; i++) {
            if(seats.charAt(i) == '1') cnt++;
        }

        while(idx < n) {
            char seat = seats.charAt(idx);

            if(seat == '1') {
                idx = idx + 2;
            }
            else {
                if(idx+1 >= n) {
                    cnt++;
                    break;
                }

                char nextSeat = seats.charAt(idx+1);
                if(nextSeat == '1') {
                    idx = idx + 3;
                }
                else {
                    if(idx+2 >= n) {
                        cnt++;
                        break;
                    }

                    char nextNextSeat = seats.charAt(idx+2);
                    if(nextNextSeat == '1') {
                        idx = idx + 4;
                    }
                    else {
                        idx = idx + 3;
                    }

                    cnt++;
                }
            }
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
