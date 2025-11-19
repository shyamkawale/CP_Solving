package codeforces.C2112_Coloring_Game;

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

public class C2112_Coloring_Game {

    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/C2112_Coloring_Game/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/C2112_Coloring_Game/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        C2112_Coloring_Game solver = new C2112_Coloring_Game();

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
        int cnt = 0;
        Arrays.sort(nums);
        int last = nums[n-1];
        for(int i=0; i<n-2; i++) {
            int a = nums[i];
            for(int j=i+1; j<n-1; j++) {
                int b = nums[j];

                int left = j+1;
                int right = n-1;
                int k = j;

                while(left <= right) {
                    int mid = left + (right-left)/2;

                    int c = nums[mid];

                    if(a+b>c && a+b+c>last) {
                        k = mid;
                        left = mid+1;
                    }
                    else {
                        right = mid-1;
                    }
                }

                cnt = cnt + (k-j);
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
