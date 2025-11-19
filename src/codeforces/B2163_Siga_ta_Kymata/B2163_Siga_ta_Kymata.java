package codeforces.B2163_Siga_ta_Kymata;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B2163_Siga_ta_Kymata {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/B2163_Siga_ta_Kymata/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/B2163_Siga_ta_Kymata/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        B2163_Siga_ta_Kymata solver = new B2163_Siga_ta_Kymata();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
            }
            String bin = in.next();

            solver.solve(n, nums, bin);
        }

        out.flush();
    }

    private void solve(int n, int[] nums, String bin) {
        int[] min = new int[n];
        int[] max = new int[n];

        min[0] = 0;
        for(int i=1; i<n; i++) {
            if(nums[i] < nums[min[i-1]]) {
                min[i] = i;
            }
            else {
                min[i] = min[i-1];
            }
        }

        max[n-1] = n-1;
        for(int i=n-2; i>=0; i--) {
            if(nums[i] > nums[max[i+1]]) {
                max[i] = i;
            }
            else {
                max[i] = max[i+1];
            }
        }

        boolean flag = true;
        List<int[]> res = new ArrayList<>();
        for(int i=0; i<n; i++) {
            if(bin.charAt(i) == '0') continue;

            if(res.size() >= 5) {
                flag = false;
                break;
            }

            if(min[i] < i && i < max[i] && nums[min[i]] < nums[i] && nums[i] < nums[max[i]]) {
                res.add(new int[] {min[i]+1, max[i]+1});
            }
            else {
                flag = false;
                break;
            }
        }

        if(flag) {
            out.println(res.size());
            for(int i=0; i<res.size(); i++) {
                out.println(res.get(i)[0] + " " + res.get(i)[1]);
            }
        }
        else {
            out.println(-1);
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
