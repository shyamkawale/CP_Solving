package different.OA_Question_1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class OAQuestion1 {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/different/OA_Question_1/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/different/OA_Question_1/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        OAQuestion1 solver = new OAQuestion1();

        int t = 1;
        while (t-- > 0) {
            String[] teams = new String[] {"a:Essendon","b:East Coast","c:Swans","d:Tigers"};
            String[] fixtures = new String[] {"a:b","a:c","a:d","b:a","b:c","b:d","c:a","c:b","c:d","d:a","d:b","d:c"};
            String[] results = new String[] {"37:55","44:50","111:88","102:42","112:81","81:36","72:39","38:64","57:53","46:65","37:73","95:62"};
            solver.solve(teams, fixtures, results);
        }

        out.flush();
    }

    private void solve(String[] teams, String[] fixtures, String[] results) {
        // 0 -> points, 1 -> score
        Map<String, long[]> teamScores = new HashMap<>();

        Map<String, String> teamNames = new HashMap<>();
        for(String team: teams) {
            teamNames.put(team.split(":")[0], team.split(":")[1]);
            teamScores.put(team.split(":")[0], new long[3]);
        }

        for(int i=0; i<fixtures.length; i++) {
            String firstTeam = fixtures[i].split(":")[0];
            String secondTeam = fixtures[i].split(":")[1];

            long firstTeamScore = Long.valueOf(results[i].split(":")[0]);
            long secondTeamScore = Long.valueOf(results[i].split(":")[1]);

            long[] score1 = teamScores.get(firstTeam);
            long[] score2 = teamScores.get(secondTeam);

            score1[1] = score1[1] + firstTeamScore;
            score1[2] = score1[2] + secondTeamScore;
            
            score2[1] = score2[1] + secondTeamScore;
            score2[2] = score2[2] + firstTeamScore;

            if(firstTeamScore > secondTeamScore) {
                score1[0] = score1[0] + 4;
            }
            else if(firstTeamScore < secondTeamScore) {
                score2[0] = score2[0] + 4;
            }
            else {
                score1[0] = score1[0] + 2;
                score2[0] = score2[0] + 2;
            }
        }

        PriorityQueue<Map.Entry<String, long[]>> minHeap = new PriorityQueue<>((a, b) -> {
            long[] scoreA = a.getValue();
            long[] scoreB = b.getValue();

            if(scoreA[0] != scoreB[0]) {
                return Long.compare(scoreA[0], scoreB[0]);
            }

            long percentA = 100 * scoreA[1] / scoreA[2];
            long percentB = 100 * scoreB[1] / scoreB[2];

            return Long.compare(percentA, percentB);
        });

        for(Map.Entry<String, long[]> entry: teamScores.entrySet()) {
            minHeap.offer(entry);

            if(minHeap.size() > 2) {
                minHeap.poll();
            }
        }

        String[] res = new String[2];
        res[1] = teamNames.get(minHeap.poll().getKey());
        res[0] = teamNames.get(minHeap.poll().getKey());

        System.out.println(Arrays.toString(res));
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
