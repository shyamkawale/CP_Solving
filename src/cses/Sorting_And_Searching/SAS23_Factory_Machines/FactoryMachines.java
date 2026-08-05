package cses.Sorting_And_Searching.SAS23_Factory_Machines;

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

/*
https://cses.fi/problemset/task/1620

A factory has n machines which can be used to make products. 
Your goal is to make a total of t products.

For each machine, you know the number of seconds it needs to make a single product. 
The machines can work simultaneously, and you can freely decide their schedule.

What is the shortest time needed to make t products?
*/
public class FactoryMachines {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sorting_And_Searching/SAS23_Factory_Machines/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sorting_And_Searching/SAS23_Factory_Machines/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        FactoryMachines solver = new FactoryMachines();

        int t = 1;
        while (t-- > 0) {
            int machineCnt = in.nextInt();
            int productCnt = in.nextInt();
            int[] machines = new int[machineCnt];
            for(int i=0; i<machineCnt; i++) {
                machines[i] = in.nextInt();
            }
            solver.solve(machineCnt, productCnt, machines);
        }

        out.flush();
    }

    private void solve(int machineCnt, int productCnt, int[] machines) {
        Arrays.sort(machines);

        long left = 0;
        long right = (long) machines[0] * productCnt;

        long ans = -1;

        // F F F F T* T T T T
        // 0 1 2 3 4 5 6 7 8
        while(left <= right) {
            long mid = left + (right - left) / 2;

            if(canMakeProducts(machines, mid, productCnt)) {
                ans = mid;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        out.println(ans);
    }

    private boolean canMakeProducts(int[] machines, long thresholdTime, long reqProductCnt) {
        long totalProducts = 0;

        for (int machineTime : machines) {
            // thresholdTime madhye machine kiti products banavu shakte..
            long productCntByMachine = thresholdTime / machineTime;
            totalProducts = totalProducts + productCntByMachine;

            // Early exit prevents unnecessary iterations and potential long overflow
            if (totalProducts >= reqProductCnt) {
                return true;
            }
        }

        return false;
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
