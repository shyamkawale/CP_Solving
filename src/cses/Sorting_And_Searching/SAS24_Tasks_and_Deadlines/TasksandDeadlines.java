package cses.Sorting_And_Searching.SAS24_Tasks_and_Deadlines;

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
https://cses.fi/problemset/task/1630

You have to process n tasks. 
Each task has a duration and a deadline, and you will process the tasks in some order one after another. 
Your reward for a task is d-f where d is its deadline and f is your finishing time. 
(The starting time is 0, and you have to process all tasks even if a task would yield negative reward.)
What is your maximum reward if you act optimally?

Input
The first input line has an integer n: the number of tasks.
After this, there are n lines that describe the tasks. 
Each line has two integers a and d: the duration and deadline of the task.

Output
Print one integer: the maximum reward.
*/
public class TasksandDeadlines {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sorting_And_Searching/SAS24_Tasks_and_Deadlines/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sorting_And_Searching/SAS24_Tasks_and_Deadlines/output.txt") : System.out;
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
