package codeforces.problems.C2188_Restricted_Sorting;

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

public class C2188_Restricted_Sorting {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/problems/C2188_Restricted_Sorting/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/problems/C2188_Restricted_Sorting/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        C2188_Restricted_Sorting solver = new C2188_Restricted_Sorting();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
            }

            solver.solve(n, nums);
        }

        out.flush();
    }

    private void solve(int n, int[] nums) {
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);

        boolean alreadySorted = true;
        for(int i=0; i<n; i++) {
            if(nums[i] != sortedNums[i]) {
                alreadySorted = false;
                break;
            }
        }

        if(alreadySorted) {
            out.println(-1);
            return;
        }

        int globalMin = sortedNums[0];
        int globalMax = sortedNums[n-1];
        int k = Integer.MAX_VALUE;
        for(int i=0; i<n; i++) {
            if(nums[i] == sortedNums[i]) continue;

            k = Math.min(k, Math.max(nums[i]-globalMin, globalMax-nums[i]));
        }

        out.println(k);
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
