package codeforces.problems.B2158_Split;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class B2158_Split {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/problems/B2158_Split/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/problems/B2158_Split/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        B2158_Split solver = new B2158_Split();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] nums = new int[2 * n];
            for (int i = 0; i < 2 * n; i++) {
                nums[i] = in.nextInt();
            }

            solver.solve(n, nums);
        }

        out.flush();
    }

    private void solve(int n, int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < 2 * n; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }

        int oddFreqCount = 0; // Elements appearing an Odd number of times
        int evenNotDivBy4Count = 0; // Even elements (half is odd) like 2, 6, 10 (4k + 2)
        int evenDivBy4Count = 0; // Even elements (half is even) like 4, 8, 12 (4k)

        for (int count : freqMap.values()) {
            if (count % 2 != 0) {
                oddFreqCount++;
            } else if (count % 4 != 0) {
                evenNotDivBy4Count++;
            } else {
                evenDivBy4Count++;
            }
        }

        // Odd elements give 1 point
        // Both types of Even elements give 2 points (2*evenNotDivBy4Count + 2*evenDivBy4Count)
        int ans = oddFreqCount + (2 * evenNotDivBy4Count) + (2 * evenDivBy4Count);

        // If evenDivBy4Count is Odd AND oddFreqCount is 0...
        // We have a parity mismatch that we can't fix with odd numbers.
        // We must subtract 2.
        if ((evenDivBy4Count % 2 != 0) && oddFreqCount == 0) {
            ans = ans - 2;
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
