package codeforces.problems.C2175_Needle_in_a_Haystack;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class C2175_Needle_in_a_Haystack {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/problems/C2175_Needle_in_a_Haystack/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/problems/C2175_Needle_in_a_Haystack/output.txt")
                : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        C2175_Needle_in_a_Haystack solver = new C2175_Needle_in_a_Haystack();

        int t = in.nextInt();
        while (t-- > 0) {
            String s1 = in.next();
            String s2 = in.next();

            solver.solve(s1, s2);
        }

        out.flush();
    }

    private void solve(String s, String t) {
        // 1. Calculate Frequency of characters
        int[] tFreq = new int[26];
        for (char c : t.toCharArray()) {
            tFreq[c - 'a']++;
        }

        // Subtract characters needed for s to find the "extras"
        for (char c : s.toCharArray()) {
            tFreq[c - 'a']--;
            // If frequency drops below zero, t didn't have enough characters
            if (tFreq[c - 'a'] < 0) {
                out.println("Impossible");
                return;
            }
        }

        // 2. Precompute "Next Different Character" for logic lookahead
        // nextDiff[i] stores the first character in s after index i that is NOT equal
        // to s[i]
        char[] nextDiff = new char[s.length()];
        char lastDifferent = 0; // 0 represents end of string or no different char found yet

        for (int i = s.length() - 1; i >= 0; i--) {
            nextDiff[i] = lastDifferent;
            // If the current char is different from the one preceding it (visually to the
            // right),
            // it becomes the "last different" for the next iteration.
            if (i > 0 && s.charAt(i) != s.charAt(i - 1)) {
                lastDifferent = s.charAt(i);
            }
        }
        // Note: The loop calculates what the previous index (i-1) needs to know.
        // We need to shift/adjust slightly or rewrite the loop for clarity.
        // Let's rewrite the loop to be more intuitive:

        // Correct Loop: From right to left, track the first non-identical character
        // seen so far.
        char nonIdenticalSeen = 0;
        for (int i = s.length() - 2; i >= 0; i--) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                nonIdenticalSeen = s.charAt(i + 1);
            }
            // If s[i] == s[i+1], nonIdenticalSeen remains whatever it was for i+1
            nextDiff[i] = nonIdenticalSeen;
        }

        StringBuilder result = new StringBuilder();

        // 3. Merge Logic
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);

            // A. Append all extras strictly smaller than current sChar
            for (int c = 0; c < sChar - 'a'; c++) {
                while (tFreq[c] > 0) {
                    result.append((char) ('a' + c));
                    tFreq[c]--;
                }
            }

            // B. Handle extras EQUAL to current sChar
            // We only print equal extras NOW if the next different character in s is
            // strictly LARGER.
            // If the next different character in s is SMALLER, we wait (letting sChar go
            // first helps lexicographically).
            if (nextDiff[i] > sChar) {
                while (tFreq[sChar - 'a'] > 0) {
                    result.append(sChar);
                    tFreq[sChar - 'a']--;
                }
            }

            // C. Append the required character from s
            result.append(sChar);
        }

        // 4. Append any remaining extras (those larger than the last chars of s, or
        // held back equal chars)
        for (int c = 0; c < 26; c++) {
            while (tFreq[c] > 0) {
                result.append((char) ('a' + c));
                tFreq[c]--;
            }
        }

        out.println(result.toString());
    }

    // private void solve(String s, String t) {
    // Map<Character, Long> sFreqMap = new HashMap<>();
    // TreeMap<Character, Long> tFreqMap = new TreeMap<>();

    // for(Character ch: s.toCharArray()) {
    // sFreqMap.put(ch, sFreqMap.getOrDefault(ch, 0L)+1);
    // }
    // for(Character ch: t.toCharArray()) {
    // tFreqMap.put(ch, tFreqMap.getOrDefault(ch, 0L)+1);
    // }

    // StringBuilder sb = new StringBuilder();
    // int si = 0;
    // while(!sFreqMap.isEmpty() && !tFreqMap.isEmpty()) {
    // Character tCh = tFreqMap.firstKey();
    // Long tChFreq = tFreqMap.get(tCh);

    // Character sCh = s.charAt(si);
    // Long sChFreq = sFreqMap.get(sCh);

    // if(sChFreq > tFreqMap.getOrDefault(sCh, 0L)) {
    // out.println("IMPOSSIBLE");
    // return;
    // }

    // if(tCh == sCh) {
    // sb.append(tCh);
    // sFreqMap.computeIfPresent(sCh, (key, val) -> val == 1 ? null : val-1);
    // tFreqMap.compute(tCh, (key, val) -> val == 1 ? null : val-1);
    // si++;
    // }
    // else if(!sFreqMap.containsKey(tCh)) {
    // for(int i=0; i<tChFreq; i++) {
    // sb.append(tCh);
    // }
    // tFreqMap.remove(tCh);
    // }
    // else {
    // Long diff = tChFreq - sFreqMap.getOrDefault(tCh, 0L);
    // for(int i=0; i<diff; i++) {
    // sb.append(tCh);
    // }
    // sb.append(sCh);
    // sFreqMap.computeIfPresent(sCh, (key, val) -> val == 1 ? null : val-1);
    // tFreqMap.computeIfPresent(tCh, (key, val) -> val == diff ? null : val-diff);
    // tFreqMap.computeIfPresent(sCh, (key, val) -> val == 1 ? null : val-1);
    // si++;
    // }
    // }

    // while(!tFreqMap.isEmpty()) {
    // Character tCh = tFreqMap.firstKey();
    // Long tChFreq = tFreqMap.get(tCh);

    // for(int i=0; i<tChFreq; i++) {
    // sb.append(tCh);
    // }

    // tFreqMap.remove(tCh);
    // }

    // out.println(sb.toString());
    // }

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
