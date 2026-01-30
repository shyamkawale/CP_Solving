package cses.Sliding_Window.Sliding_Window_Median;

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
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SlidingWindowMedian {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sliding_Window/Sliding_Window_Median/input.txt")
                : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sliding_Window/Sliding_Window_Median/output.txt")
                : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        SlidingWindowMedian solver = new SlidingWindowMedian();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
            }
            solver.solve(n, k, nums);
        }

        out.flush();
    }

    private void solve(int n, int k, int[] nums) {
        MedianFinderOp medianFinder = new MedianFinderOp();
        int start = 0;
        int end = 0;

        while (end < n) {
            medianFinder.add(nums[end]);

            if (end - start + 1 < k) {
                end++;
            } else {
                out.print(medianFinder.getMedian() + " ");

                medianFinder.remove(nums[start]);
                start++;
                end++;
            }
        }

        out.println();
    }

    static class MedianFinderOp {
        private PriorityQueue<Integer> maxHeap;  // first-half
        private int leftSize;
        private PriorityQueue<Integer> minHeap;  // second-half
        private int rightSize;

        private Map<Integer, Integer> delayedRemovalMap;
        
        public MedianFinderOp() {
            maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
            leftSize = 0;
            minHeap = new PriorityQueue<>();
            rightSize = 0;
            delayedRemovalMap = new HashMap<>();
        }

        public void add(int num) {
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num);
                leftSize++;
            } else {
                minHeap.offer(num);
                rightSize++;
            }

            rebalance();
        }

        public int getMedian() {
            prune(maxHeap);
            prune(minHeap);

            return maxHeap.peek();
        }

        public void remove(int num) {
            if (!maxHeap.isEmpty() && num <= maxHeap.peek()) {
                leftSize--;
            } else {
                rightSize--;
            }

            delayedRemovalMap.put(num, delayedRemovalMap.getOrDefault(num, 0)+1);
        }

        private void rebalance() {
            while (leftSize - rightSize > 1) {
                rightSize++;
                leftSize--;
                minHeap.offer(maxHeap.poll());
                prune(maxHeap);
            }

            while (rightSize > leftSize) {
                rightSize--;
                leftSize++;
                maxHeap.offer(minHeap.poll());
                prune(minHeap);
            }
        }

        private void prune(PriorityQueue<Integer> heap) {
            while(delayedRemovalMap.containsKey(heap.peek())) {
                delayedRemovalMap.computeIfPresent(heap.peek(), (key, val) -> val == 1 ? null : val-1);
                heap.poll();
            }
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
