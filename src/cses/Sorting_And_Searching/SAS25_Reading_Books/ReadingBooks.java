package cses.Sorting_And_Searching.SAS25_Reading_Books;

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
https://cses.fi/problemset/task/1631

There are n books, and Kotivalo and Justiina are going to read them all. 
For each book, you know the time it takes to read it.
They both read each book from beginning to end, and they cannot read a book at the same time. 
What is the minimum total time required?

Input
The first input line has an integer n: the number of books.
The second line has n integers t1,t2,...,tn: the time required to read each book.

Output
Print one integer: the minimum total time.
*/
public class ReadingBooks {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sorting_And_Searching/SAS25_Reading_Books/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sorting_And_Searching/SAS25_Reading_Books/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        ReadingBooks solver = new ReadingBooks();

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
        Arrays.sort(nums);

        long sum = 0L;
        long maxi = 0L;
        for(int num: nums) {
            maxi = Math.max(maxi, num);
            sum = sum + num;
        }

        out.println(Math.max(sum, maxi*2));
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
