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


/*
https://cses.fi/problemset/task/1164/

There is a large hotel, and n customers will arrive soon. 
Each customer wants to have a single room.
You know each customer's arrival and departure day.
Two customers can stay in the same room if
the departure day of the first customer is earlier than the arrival day of the second customer.

What is the minimum number of rooms that are needed to accommodate all customers? 
And how can the rooms be allocated?

Input
The first input line contains an integer n: the number of customers.
Then there are n lines, each of which describes one customer. 
Each line has two integers a and b: the arrival and departure day.

Output
Print first an integer k: the minimum number of rooms required.
After that, print a line that contains the room number of each customer in the same order as in the input. 
The rooms are numbered 1,2,...,k. You can print any valid solution.
*/

/**
 * RoomAllocation solves the minimum number of rooms (interval partitioning) problem.
 *
 * Given n customers with arrival and departure days, this class determines the minimum
 * number of rooms required so that no two customers occupy the same room on overlapping
 * days, and assigns a room number to each customer. This is also known as the Interval
 * Partitioning Problem (or Minimum Number of Platforms / Meeting Rooms II).
 *
 * Two approaches are implemented:
 *
 * Approach 1 (solve1) - Brute Force Greedy:
 * 1. Sort intervals by arrival day (ties broken by departure day).
 * 2. For each unassigned interval, open a new room and greedily assign as many later
 *    non-overlapping intervals as possible to the same room.
 * 3. Repeat until all intervals are assigned.
 * Time Complexity: O(n^2)
 * Space Complexity: O(n)
 *
 * Approach 2 (solve2) - Min-Heap Greedy:
 * 1. Sort intervals by arrival day.
 * 2. Maintain a min-heap of currently occupied rooms keyed by their earliest free day
 *    (departure time of the customer currently in the room).
 * 3. For each interval, if the room with the smallest end time is still occupied
 *    (endTime >= current arrival), allocate a new room; otherwise, reuse that room and
 *    update its end time.
 * 4. The total number of rooms ever opened is the answer.
 * Time Complexity: O(n log n) due to sorting and heap operations
 * Space Complexity: O(n) for the heap and answer array
 *
 */
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
