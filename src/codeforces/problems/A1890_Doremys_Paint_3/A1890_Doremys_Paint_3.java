package codeforces.problems.A1890_Doremys_Paint_3;

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

public class A1890_Doremys_Paint_3 {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/problems/A1890_Doremys_Paint_3/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/problems/A1890_Doremys_Paint_3/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        A1890_Doremys_Paint_3 solver = new A1890_Doremys_Paint_3();

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
        Arrays.sort(nums);

        if(nums[0] == nums[n-1]) {
            out.println("YES");
        }
        else if(n%2 == 0) {
            if(nums[0] == nums[n/2-1] && nums[n/2-1] != nums[n/2] && nums[n/2] == nums[n-1]) {
                out.println("YES");
            }
            else {
                out.println("NO");
            }
        }
        else {
            if(nums[0] == nums[n/2-1] && nums[n/2-1] != nums[n/2] && nums[n/2] == nums[n-1]) {
                out.println("YES");
            }
            else if(nums[0] == nums[n/2] && nums[n/2] != nums[n/2+1] && nums[n/2+1] == nums[n-1]) {
                out.println("YES");
            }
            else {
                out.println("NO");
            }
        }
        return;
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
