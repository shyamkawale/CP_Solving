package cses.Ferris_Wheel;

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

public class FerrisWheel {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Ferris_Wheel/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Ferris_Wheel/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        FerrisWheel solver = new FerrisWheel();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int maxWt = in.nextInt();
            int[] weights = new int[n];
            for(int i=0; i<n; i++) {
                weights[i] = in.nextInt();
            }
            solver.solve(n, maxWt, weights);
        }

        out.flush();
    }

    private void solve(int n, int maxWt, int[] weights) {
        Arrays.sort(weights);

        int left = 0;
        int right = n-1;
        int ans = 0;

        while(left <= right) {
            if(weights[left] + weights[right] <= maxWt) {
                left++;
                right--;
            }
            else {
                right--;
            }
            ans++;
        }

        out.println(ans);
    }

    // private void solve(int n, int maxWt, int[] weights) {
    //     Arrays.sort(weights);

    //     int[] added = new int[n];
    //     int ans = 0;

    //     for(int i=n-1; i>=0; i--) {
    //         if(added[i] == 1) continue;
    //         int jans = -1;
    //         for(int j=i-1; j>=0; j--) {
    //             if(added[j] == 1) continue;

    //             if(weights[i] + weights[j] <= maxWt) {
    //                 jans = j;
    //                 break;
    //             }
    //         }

    //         ans++;
    //         added[i] = 1;
    //         if(jans != -1) added[jans] = 1;
    //     }

    //     out.println(ans);
    // }

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
