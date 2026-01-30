package cses.Sliding_Window.Sliding_Window_Mex;

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
import java.util.TreeSet;

public class SlidingWindowMex {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sliding_Window/Sliding_Window_Mex/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sliding_Window/Sliding_Window_Mex/output.txt")
                : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        SlidingWindowMex solver = new SlidingWindowMex();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] nums = new int[n];
            for(int i=0; i<n; i++) {
                nums[i] = in.nextInt();
            }
            solver.solve1(n, k, nums);
            solver.solve2(n, k, nums);
        }

        out.flush();
    }

    private void solve2(int n, int k, int[] nums) {
        int[] freq = new int[k+1];
        TreeSet<Integer> missing = new TreeSet<>();

        for (int i = 0; i <= k; i++) {
            missing.add(i);
        }

        int start = 0;
        int end = 0;

        while(end < n) {
            if (nums[end] <= k) {
                if (freq[nums[end]] == 0) missing.remove(nums[end]);
                freq[nums[end]]++;
            }

            if(end-start+1 < k) {
                end++;
            }
            else {
                out.print(missing.first() + " ");

                if(nums[start] <= k) {
                    freq[nums[start]]--;
                    if(freq[nums[start]] == 0) missing.add(nums[start]);
                }
                start++;
                end++;
            }
        }

        out.println();
    }

    private void solve1(int n, int k, int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
 
        int start = 0;
        int end = 0;
        while(end < n) {
            freqMap.put(nums[end], freqMap.getOrDefault(nums[end], 0)+1);
            if(end-start+1 < k) {
                end++;
            }
            else {
                int i = 0;
                // answer will be from [0-k] only, becoz window size is k.
                while(i <= k) {
                    if(!freqMap.containsKey(i)) {
                        out.print(i + " ");
                        break;
                    }
                    i++;
                }
                freqMap.computeIfPresent(nums[start], (key, val) -> (val == 1) ? null : val-1);
                start++;
                end++;
            }
        }
        out.println();
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
