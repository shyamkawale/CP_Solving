package codeforces.problems.B2189_The_Curse_of_the_Frog;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B2189_The_Curse_of_the_Frog {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/problems/B2189_The_Curse_of_the_Frog/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/problems/B2189_The_Curse_of_the_Frog/output.txt")
                : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        B2189_The_Curse_of_the_Frog solver = new B2189_The_Curse_of_the_Frog();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            long target = in.nextLong();
            long[][] nums = new long[n][3];
            for (int i = 0; i < n; i++) {
                nums[i][0] = in.nextLong();
                nums[i][1] = in.nextLong();
                nums[i][2] = in.nextLong();
            }

            solver.solve(n, target, nums);
        }

        out.flush();
    }

    private void solve(int n, long target, long[][] nums) {
        long currDist = 0;
        long maxProfit = Long.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            long freeJumpsCount = nums[i][1] - 1;
            currDist += freeJumpsCount * nums[i][0];

            long profit = (nums[i][0] * nums[i][1]) - nums[i][2];
            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }

        if(currDist >= target) {
            out.println(0);
        }
        else {
            if(maxProfit <= 0) {
                out.println(-1);
            }
            else {
                long remDist = target - currDist;
                long rollbacks = (remDist + maxProfit - 1) / maxProfit;
                out.println(rollbacks);
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
