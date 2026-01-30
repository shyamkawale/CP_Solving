package cses.Sliding_Window.Sliding_Window_Mode;

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
import java.util.TreeMap;
import java.util.TreeSet;

public class SlidingWindowMode {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sliding_Window/Sliding_Window_Mode/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sliding_Window/Sliding_Window_Mode/output.txt")
                : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        SlidingWindowMode solver = new SlidingWindowMode();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] nums = new int[n];
            for(int i=0; i<n; i++) {
                nums[i] = in.nextInt();
            }
            // solver.solve1(n, k, nums);
            solver.solve2(n, k, nums);
        }

        out.flush();
    }

    private void solve2(int n, int k, int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        TreeMap<Integer, TreeSet<Integer>> freqToNums = new TreeMap<>();

        int start = 0;
        int end = 0;
        while(end < n) {
            freqMap.put(nums[end], freqMap.getOrDefault(nums[end], 0)+1);
            freqToNums.putIfAbsent(freqMap.get(nums[end]), new TreeSet<>());
            freqToNums.get(freqMap.get(nums[end])).add(nums[end]);
            if(end-start+1 < k) {
                end++;
            }
            else {
                int maxFreq = freqToNums.lastKey();
                int maxFreqElem = freqToNums.get(maxFreq).first();
                out.print(maxFreqElem + " ");

                int startElem = nums[start];
                int startElemFreq = freqMap.get(startElem);
                freqMap.computeIfPresent(startElem, (key, val) -> (val == 1) ? null : val-1);

                freqToNums.get(startElemFreq).remove(nums[start]);
                if(freqToNums.get(startElemFreq).size() == 0) {
                    freqToNums.remove(startElemFreq);
                }

                start++;
                end++;
            }
        }
        out.println();
    }

    private void solve1(int n, int k, int[] nums) {
        Map<Integer, Integer> freqMap = new TreeMap<>();
 
        int start = 0;
        int end = 0;
        while(end < n) {
            freqMap.put(nums[end], freqMap.getOrDefault(nums[end], 0)+1);
            if(end-start+1 < k) {
                end++;
            }
            else {
                int maxFreqElem = -1;
                int maxFreq = 0;
                for(Map.Entry<Integer, Integer> entry: freqMap.entrySet()) {
                    if(entry.getValue() > maxFreq) {
                        maxFreqElem = entry.getKey();
                        maxFreq = entry.getValue();
                    }
                }
                out.print(maxFreqElem + " ");
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
