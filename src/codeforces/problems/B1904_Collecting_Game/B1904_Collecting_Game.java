package codeforces.problems.B1904_Collecting_Game;

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

public class B1904_Collecting_Game {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/problems/B1904_Collecting_Game/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/problems/B1904_Collecting_Game/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        B1904_Collecting_Game solver = new B1904_Collecting_Game();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            long[] nums = new long[n];
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextLong();
            }

            solver.solve(n, nums);
        }

        out.flush();
    }

    private void solve(int n, long[] nums) {
        long arr[][] = new long[n][2];
        for(int i=0; i<n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> Long.compare(a[0], b[0]));
        // 20, 5, 1, 4, 2
        // 0, 1, 2, 3, 4
        // 1, 2, 4, 5, 20

        long[] ps = new long[n+1];
        ps[0] = 0;
        for(int i=0; i<n; i++) {
            ps[i+1] = ps[i] + arr[i][0];
        }

        int res[] = new int[n];
        int last = n-1;

        res[(int) arr[n - 1][1]] = n-1;
        for(int i=n-2; i>=0; i--) {
            if(ps[i+1] < arr[i+1][0]) {
                last = i;
            }
            // if ps[i+1] >= arr[i+1][0] then this can also collect all from last
            // becoz this can collect arr[i+1] and then all after that (as we already know what we can collect from i+1)
            res[(int)arr[i][1]] = last;
        }

        for(int i=0; i<n; i++) {
            out.print(res[i] + " ");
        }
        out.println();
    }


    // private void solve(int n, long[] nums) {
    //     for(int i=0; i<n; i++) {
    //         Queue<Long> pq = new PriorityQueue<>();
    //         for(int j=0; j<n; j++) {
    //             pq.offer(nums[j]);
    //         }
    //         int rem = getRem(nums[i], pq);
    //         out.print(rem + " ");
    //     }
    // }

    // private int getRem(long elem, Queue<Long> pq) {
    //     pq.remove(elem);
    //     long score = elem;
    //     int rem = 0;

    //     while(!pq.isEmpty() && score >= pq.peek()) {
    //         long top = pq.poll();
    //         score = score + top;
    //         rem++;
    //     }
    //     return rem;
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
