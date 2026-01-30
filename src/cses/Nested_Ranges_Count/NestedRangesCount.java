package cses.Nested_Ranges_Count;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class NestedRangesCount {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Nested_Ranges_Count/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Nested_Ranges_Count/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        NestedRangesCount solver = new NestedRangesCount();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int[][] intervals = new int[n][2];
            for(int i=0; i<n; i++) {
                intervals[i][0] = in.nextInt();
                intervals[i][1] = in.nextInt();
            }
            solver.solve(n, intervals);
        }

        out.flush();
    }

    private void solve(int n, int[][] intervals) {
        int[][] arr = new int[n][3];
        for(int i=0; i<n; i++) {
            arr[i][0] = intervals[i][0];
            arr[i][1] = intervals[i][1];
            arr[i][2] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            if(a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(b[1], a[1]);
        });

        int[] contains = new int[n];
        List<Integer> minRight = new ArrayList<>();
        for(int i=n-1; i>=0; i--) {
            int cnt = binarySearchToFindMins(minRight, arr[i][1]);

            if(cnt != -1) {
                contains[arr[i][2]] = cnt;
            }
            minRight.add(arr[i][1]);
            Collections.sort(minRight);
        }

        int[] containedBy = new int[n];
        List<Integer> maxRight = new ArrayList<>();
        for(int i=0; i<n; i++) {
            int cnt = binarySearchToFindMaxs(maxRight, arr[i][1]);

            if(cnt != -1) {
                containedBy[arr[i][2]] = cnt;
            }
            maxRight.add(arr[i][1]);
            Collections.sort(maxRight);
        }

        for(int cnt: contains) {
            out.print(cnt + " ");
        }
        out.println();

        for(int cnt: containedBy) {
            out.print(cnt + " ");
        }
        out.println();
    }

    private int binarySearchToFindMins(List<Integer> minRight, int n) {
        int left = 0;
        int right = minRight.size()-1;
        int ans = -1;

        while(left <= right) {
            int mid = left + (right-left)/2;

            if(minRight.get(mid) <= n) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
                ans = mid;
            }
        }

        return ans == -1 ? minRight.size() : ans;
    }

    private int binarySearchToFindMaxs(List<Integer> maxRight, int n) {
        int left = 0;
        int right = maxRight.size()-1;
        int ans = -1;

        while(left <= right) {
            int mid = left + (right-left)/2;

            if(maxRight.get(mid) < n) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
                ans = mid;
            }
        }

        return ans == -1 ? -1 : maxRight.size()-ans;
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
