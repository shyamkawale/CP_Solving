package cses.Room_Allocation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class RoomAllocation {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Room_Allocation/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Room_Allocation/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        RoomAllocation solver = new RoomAllocation();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int[][] intervals = new int[n][2];
            for(int i=0; i<n; i++) {
                intervals[i][0] = in.nextInt();
                intervals[i][1] = in.nextInt();
            }
            solver.solve1(n, intervals);
            solver.solve2(n, intervals);
        }

        out.flush();
    }

    // TC: O(nlogn)
    private void solve2(int n, int[][] intervals) {
        int[][] arr = new int[n][3];
        for(int i=0; i<n; i++) {
            arr[i][0] = intervals[i][0];
            arr[i][1] = intervals[i][1];
            arr[i][2] = i;
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        Queue<Room> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.endTime, b.endTime));

        int[] ans = new int[n];
        int roomCount = 0;

        for(int i=0; i<n; i++) {

            if(minHeap.isEmpty() || minHeap.peek().endTime >= arr[i][0]) {
                roomCount++;
                ans[arr[i][2]] = roomCount;
                minHeap.offer(new Room(arr[i][1], roomCount));
            }
            else {
                Room room = minHeap.poll();
                ans[arr[i][2]] = room.roomNum;
                room.endTime = arr[i][1];
                minHeap.offer(room);
            }
        }

        out.println(roomCount);
        for(int r: ans) {
            out.print(r + " ");
        }
        out.println();
    }

    private static class Room {
        int endTime;
        int roomNum;

        public Room(int endTime, int roomNum) {
            this.endTime = endTime;
            this.roomNum = roomNum;
        }
    }

    // TC: O(n^2)
    private void solve1(int n, int[][] intervals) {
        int[][] arr = new int[n][3];
        for(int i=0; i<n; i++) {
            arr[i][0] = intervals[i][0];
            arr[i][1] = intervals[i][1];
            arr[i][2] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            if(a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        int[] ans = new int[n];
        int room = 1;
        for(int i=0; i<n; i++) {
            if(ans[arr[i][2]] != 0) continue;
            int last = arr[i][1];
            ans[arr[i][2]] = room;
            
            for(int j=i+1; j<n; j++) {
                if(ans[arr[j][2]] != 0) continue;
                if(last < arr[j][0]) {
                    ans[arr[j][2]] = room;
                    last = arr[j][1];
                }
            }
            room++;
        }


        out.println(room-1);
        for(int r: ans) {
            out.print(r + " ");
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
