package cses.Sorting_And_Searching.SAS2_Apartments;

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
https://cses.fi/problemset/task/1084

There are n userDesiredSizes and m free apartmentSizes. 
Your task is to distribute the apartmentSizes so that as many userDesiredSizes as possible will get an apartment.
Each applicant has a desired apartment size, and they will accept any apartment whose size is close enough to the desired size.
If the desired size of an applicant is x, they will accept any apartment whose size is between x-k and x+k.

Print one integer: the number of userDesiredSizes who will get an apartment.
*/
@SuppressWarnings("unused")
public class Apartments {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sorting_And_Searching/SAS2_Apartments/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sorting_And_Searching/SAS2_Apartments/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        Apartments solver = new Apartments();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();

            int[] userDesiredSizes = new int[n];
            for (int i = 0; i < n; i++) {
                userDesiredSizes[i] = in.nextInt();
            }
            int[] apartmentSizes = new int[m];
            for (int i = 0; i < m; i++) {
                apartmentSizes[i] = in.nextInt();
            }
            solver.solve(n, m, k, userDesiredSizes, apartmentSizes);
        }

        out.flush();
    }

    /*
        ** APPROACH-3 (Same as Approach 1, 2 but different way of writing**
    */
    private void solve(int n, int m, int k, int[] userDesiredSizes, int[] apartmentSizes) {
        Arrays.sort(userDesiredSizes);
        Arrays.sort(apartmentSizes);

        int cnt = 0;
        int applicantIdx = 0;
        int apartmentIdx = 0;

        while(applicantIdx < n && apartmentIdx < m) {

            if(userDesiredSizes[applicantIdx] < apartmentSizes[apartmentIdx] - k) {
                applicantIdx++;
            }
            else if(Math.abs(userDesiredSizes[applicantIdx]-apartmentSizes[apartmentIdx]) <= k) {
                cnt++;
                applicantIdx++;
                apartmentIdx++;
            }
            else if(userDesiredSizes[applicantIdx] > apartmentSizes[apartmentIdx] + k) {
                apartmentIdx++;
            } 
        }

        out.println(cnt);
    }

    /*
        ** APPROACH-1 (For a Apartment find Applicant) **
    */
    private void solve1(int n, int m, int k, int[] userDesiredSizes, int[] apartmentSizes) {
        Arrays.sort(userDesiredSizes);
        Arrays.sort(apartmentSizes);

        int cnt = 0;
        int applicantIdx = 0;

        // for a apartment find applicant
        for(int i=0; i<m; i++) {
            for(int j=applicantIdx; j<n; j++) {
                if(Math.abs(apartmentSizes[i]-userDesiredSizes[j]) <= k) {
                    cnt++;
                    applicantIdx = j+1;
                    break;
                }
                else if(userDesiredSizes[j] > apartmentSizes[i]) {
                    break;
                }
            }
        }

        out.println(cnt);
    }

    /*
        ** APPROACH-2 (For a Applicant find Apartment) **
    */
    private void solve2(int n, int m, int k, int[] userDesiredSizes, int[] apartmentSizes) {
        Arrays.sort(userDesiredSizes);
        Arrays.sort(apartmentSizes);

        int cnt = 0;
        int apartmentIdx = 0;

        // for a applicant find apartment
        for(int i=0; i<n; i++) {
            for(int j=apartmentIdx; j<m; j++) {
                if(Math.abs(apartmentSizes[j]-userDesiredSizes[i]) <= k) {
                    cnt++;
                    apartmentIdx = j+1;
                    break;
                }
                else if(apartmentSizes[j] > userDesiredSizes[i]) {
                    break;
                }
            }
        }

        out.println(cnt);
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
