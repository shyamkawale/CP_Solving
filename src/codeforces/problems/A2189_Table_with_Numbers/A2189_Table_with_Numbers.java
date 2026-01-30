package codeforces.problems.A2189_Table_with_Numbers;

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

public class A2189_Table_with_Numbers {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/problems/A2189_Table_with_Numbers/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/problems/A2189_Table_with_Numbers/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        A2189_Table_with_Numbers solver = new A2189_Table_with_Numbers();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();

            int row = in.nextInt();
            int col = in.nextInt();

            int[] a = new int[n];
            for(int i=0; i<n; i++) {
                a[i] = in.nextInt();
            }          

            solver.solve(n, row, col, a);
        }

        out.flush();
    }

    private void solve(int n, int row, int col, int[] nums) {
        Arrays.sort(nums);

        int left = 0;
        int right = n-1;

        int maxLimit = Math.max(row, col);
        int minLimit = Math.min(row, col);
        int ans = 0;

        while(left < right) {
            int maxi = Math.max(nums[left], nums[right]);
            int mini = Math.min(nums[left], nums[right]);

            if(maxi < 1 || maxLimit < maxi) {
                right--;
            }
            else if(mini < 1 || minLimit < mini) {
                left++;
            }
            else {
                left++;
                right--;
                ans++;
            }
        }

        out.println(ans);
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
