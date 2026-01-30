package cses.Tasks_and_Deadlines;

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

public class TasksandDeadlines {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Tasks_and_Deadlines/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Tasks_and_Deadlines/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        TasksandDeadlines solver = new TasksandDeadlines();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int[][] tasks = new int[n][2];
            for(int i=0; i<n; i++) {
                tasks[i][0] = in.nextInt();
                tasks[i][1] = in.nextInt();
            }
            solver.solve(n, tasks);
        }

        out.flush();
    }

    private void solve(int n, int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> Integer.compare(a[0], b[0]));

        long sum = 0;
        for(int i=0; i<n; i++) {
            sum = sum + tasks[i][1] - (long) (n-i) * tasks[i][0];
        }

        out.println(sum);
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
