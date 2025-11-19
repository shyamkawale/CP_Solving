package cses.Concert_Tickets;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ConcertTickets {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Concert_Tickets/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Concert_Tickets/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        ConcertTickets solver = new ConcertTickets();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] ticketPrice = new int[n];
            for(int i=0; i<n; i++) {
                ticketPrice[i] = in.nextInt();
            }
            int[] custMaxPrice = new int[m];
            for(int i=0; i<m; i++) {
                custMaxPrice[i] = in.nextInt();
            }
            solver.solve(n, m, ticketPrice, custMaxPrice);
        }

        out.flush();
    }

    private void solve(int n, int m, int[] ticketPrice, int[] custMaxPrice) {
        Arrays.sort(ticketPrice);
        boolean[] booked = new boolean[n];

        for(int i=0; i<m; i++) {
            int jans = -1;
            for(int j=0; j<n; j++) {
                if(ticketPrice[j] > custMaxPrice[i]) break;
                if(booked[j]) continue;

                jans = j;
            }

            if(jans != -1) {
                booked[jans] = true;
                out.println(ticketPrice[jans]);
            }
            else {
                out.println(-1);
            }

        }
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
