package cses.Sorting_And_Searching.SAS13_Playlist;

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

/*
https://cses.fi/problemset/task/1141

You are given a playlist of a radio station since its establishment. 
The playlist has a total of n songs.
What is the longest sequence of successive songs where each song is unique?

SlidingWindow Problem
*/
public class Playlist {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sorting_And_Searching/SAS13_Playlist/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sorting_And_Searching/SAS13_Playlist/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        Playlist solver = new Playlist();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int[] nums = new int[n];
            for(int i=0; i<n; i++) {
                nums[i] = in.nextInt();
            }
            solver.solve(n, nums);
        }

        out.flush();
    }

    private void solve(int n, int[] nums) {
        int start = 0;
        int end = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();
        int maxLen = 0;

        while(end < n) {
            if(freqMap.containsKey(nums[end])) {
                while(freqMap.containsKey(nums[end])) {
                    freqMap.computeIfPresent(nums[start], (k,v) -> v==1 ? null : v-1);
                    start++;
                }
            }
            else {
                freqMap.put(nums[end], 1);
                maxLen = Math.max(maxLen, end-start+1);
                end++;
            }
        }

        out.println(maxLen);
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
