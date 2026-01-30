package cses.Sorting_And_Searching.SAS4_Concert_Tickets;

import java.io.*;
import java.util.*;

/*
https://cses.fi/problemset/task/1091

There are n concert tickets available, each with a certain price.
Then, m customers arrive, one after another.

Each customer announces the maximum price they are willing to pay for a ticket,
and after this, they will get a ticket with the nearest possible price such that it does not exceed the maximum price.
*/
@SuppressWarnings("unused")
public class ConcertTickets {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sorting_And_Searching/SAS4_Concert_Tickets/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sorting_And_Searching/SAS4_Concert_Tickets/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        ConcertTickets solver = new ConcertTickets();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();

            int[] custMaxPrice = new int[m];
            for(int i=0; i<m; i++) {
                custMaxPrice[i] = in.nextInt();
            }

            int[] ticketPrice = new int[n];
            for(int i=0; i<n; i++) {
                ticketPrice[i] = in.nextInt();
            }
            solver.solve1(n, m, ticketPrice, custMaxPrice);

            // List<Integer> ticketList = new ArrayList<>(n);
            // for(int i=0; i<n; i++) {
            //     ticketList.add(in.nextInt());
            // }
            // solver.solve2(n, m, ticketList, custMaxPrice);

            // TreeMap<Integer, Integer> ticketListMap = new TreeMap<>();
            // for(int i=0; i<n; i++) {
            //     int key = in.nextInt();
            //     ticketListMap.put(key, ticketListMap.getOrDefault(key, 0)+1);
            // }
            // solver.solve3(n, m, ticketListMap, custMaxPrice);
        }

        out.flush();
    }


    // Solution 3
    // Used TreeMap to find floorKey(instead of manually doing that)
    private void solve3(int n, int m, TreeMap<Integer, Integer> ticketListMap, int[] custMaxPrice) {
        // For a customer find appropriate ticket
        for(int i=0; i<m; i++) {
            Integer jans = ticketListMap.floorKey(custMaxPrice[i]);

            if(jans != null) {
                out.println(jans);
                ticketListMap.computeIfPresent(jans, (k, v) -> v==1 ? null : v-1);
            }
            else {
                out.println(-1);
            }
        }
    }

    // Solution 2
    // Manually finding floorKey
    private void solve2(int n, int m, List<Integer> ticketList, int[] custMaxPrice) {
        Collections.sort(ticketList);

        // For a customer find appropriate ticket
        for(int i=0; i<m; i++) {
            int jans = findUpperBound(ticketList, custMaxPrice[i]);

            if(jans != -1) {
                out.println(ticketList.get(jans));
                ticketList.remove(jans);
            }
            else {
                out.println(-1);
            }

        }
    }

    private int findUpperBound(List<Integer> ticketList, int x) {
        int left = 0;
        int right = ticketList.size()-1;
        int ans = -1;

        while(left <= right) {
            int mid = left + (right-left)/2;

            if(ticketList.get(mid) <= x) {
                ans = mid;
                left = mid+1;
            }
            else {
                right = mid-1;
            }
        }

        return ans;
    }

    // Solution 1
    private void solve1(int n, int m, int[] ticketPrice, int[] custMaxPrice) {
        Arrays.sort(ticketPrice);
        boolean[] booked = new boolean[n];

        
        for(int i=0; i<m; i++) {
            int jans = -1;
            for(int j=0; j<n; j++) {
                if(ticketPrice[j] > custMaxPrice[i]) break;
                if(booked[j]) continue;

                jans = j;
            }

            if(jans != -1) {
                booked[jans] = true;
                out.println(ticketPrice[jans]);
            }
            else {
                out.println(-1);
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
