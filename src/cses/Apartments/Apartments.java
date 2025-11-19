package cses.Apartments;

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

public class Apartments {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Apartments/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Apartments/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        Apartments solver = new Apartments();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();

            int[] applicants = new int[n];
            for (int i = 0; i < n; i++) {
                applicants[i] = in.nextInt();
            }
            int[] apartments = new int[m];
            for (int i = 0; i < m; i++) {
                apartments[i] = in.nextInt();
            }
            solver.solve(n, m, k, applicants, apartments);
        }

        out.flush();
    }

    private void solve(int n, int m, int k, int[] applicants, int[] apartments) {
        Arrays.sort(applicants);
        Arrays.sort(apartments);

        int cnt = 0;
        int applicantIdx = 0;
        int apartmentIdx = 0;

        while(applicantIdx < n && apartmentIdx < m) {

            if(applicants[applicantIdx] < apartments[apartmentIdx] - k) {
                applicantIdx++;
            }
            else if(Math.abs(applicants[applicantIdx]-apartments[apartmentIdx]) <= k) {
                cnt++;
                applicantIdx++;
                apartmentIdx++;
            }
            else if(applicants[applicantIdx] > apartments[apartmentIdx] + k) {
                apartmentIdx++;
            } 
        }

        // for(int i=0; i<m; i++) {
        //     for(int j=applicantIdx; j<n; j++) {
        //         if(Math.abs(apartments[i]-applicants[j]) <= k) {
        //             cnt++;
        //             applicantIdx = j+1;
        //             break;
        //         }
        //         else if(apartments[i] < applicants[j]) {
        //             break;
        //         }
        //     }
        // }

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
