AFL Ladder Problem - Summary
Problem Statement
Given teams, fixtures, and match results from an AFL season, determine the top 2 teams that qualify for finals.
 
Scoring System
 
Win: 4 points
Draw: 2 points each
Loss: 0 points
 
 
Ranking Rules (Priority Order)
Total Points (higher is better)
Percentage (tiebreaker): (100 × For / Against)
 
For: Total points scored by team across all matches
Against: Total points conceded by team across all matches
 
 
 
 
Input Format
Array A: Teams
Format: ["id:TeamName", ...]
Example: ["a:Essendon", "b:East Coast", "c:Swans", "d:Tigers"]
Array B: Fixtures
Format: ["homeId:awayId", ...]
Example: ["a:b", "a:c", "a:d", "b:a", "b:c", "b:d", "c:a", "c:b", "c:d", "d:a", "d:b", "d:c"]
Array C: Results
Format: ["homeScore:awayScore", ...]
Example: ["37:55", "44:50", "111:88", "102:42", "112:81", "81:36", "72:39", "38:64", "57:53", "46:65", "37:73", "95:62"]
 
First value = home team score
Second value = away team score
Each result corresponds to the fixture at the same index in Array B
 
 
Output Format
Return: ["1st Place Team Name", "2nd Place Team Name"]
Example: ["East Coast", "Swans"]
 
Constraints & Assumptions
 
No draws (can assume all matches have a winner)
At least 2 teams exist
All arrays are non-null and properly formatted
B.Length == C.Length (every fixture has a result)
 
 
Error Handling
Return ["", ""] if:
 
Any input array is null
Array lengths don't match (B vs C)
Invalid format (missing colons, non-numeric scores)
Team ID in fixture doesn't exist in teams array
Less than 2 teams available
 
 
Example Walkthrough
Input:
A = ["a:Essendon", "b:East Coast", "c:Swans", "d:Tigers"]
 
B = ["a:b", "a:c", "a:d", "b:a", "b:c", "b:d", "c:a", "c:b", "c:d", "d:a", "d:b", "d:c"]
 
C = ["37:55", "44:50", "111:88", "102:42", "112:81", "81:36", "72:39", "38:64", "57:53", "46:65", "37:73", "95:62"]
 
Processing:
Match 1: Essendon 37 vs East Coast 55 → East Coast wins (+4 pts)
Match 2: Essendon 44 vs Swans 50 → Swans wins (+4 pts)
...and so on
 
Final Standings:
Team         | Wins | Losses | Points | For  | Against | Percentage
-------------|------|--------|--------|------|---------|------------
East Coast   |  5   |   1    |   20   | 329  |  256    | 128.52%
Swans        |  4   |   2    |   16   | 356  |  319    | 111.60%
Tigers       |  2   |   4    |    8   | 347  |  380    | 91.32%
Essendon     |  1   |   5    |    4   | 305  |  382    | 79.84%
 
 
 
Output:
["East Coast", "Swans"]
 
--------------------code below----
using System;
using System.Collections.Generic;
 
class Solution {
    // Using a struct for memory efficiency 
    public struct TeamData {
        public string Name;
        public int Points;
        public int For;
        public int Against;
        public double Percentage => Against == 0 ? 0 : (100.0 * For) / Against;
    }
 
    public string[] solution(string[] A, string[] B, string[] C) {
        try {
            if (A == null || B == null || C == null || B.Length != C.Length)
                return new[] { "", "" };
 
            var teamMap = new Dictionary<string, int>(A.Length);
            var ladder = new TeamData[A.Length];
 
            // 1. Initialize Teams
            for (int j = 0; j < A.Length; j++) {
                int colon = A[j].IndexOf(':');
                if (colon <= 0 || colon == A[j].Length - 1) return new[] { "", "" };
                
                ladder[j].Name = A[j].Substring(colon + 1);
                teamMap[A[j].Substring(0, colon)] = j;
            }
 
            // 2. Process Matches
            for (int i = 0; i < B.Length; i++) {
                if (!TrySplit(B[i], out string k1, out string k2) || 
                    !TrySplit(C[i], out string s1, out string s2) ||
                    !teamMap.TryGetValue(k1, out int hIdx) || 
                    !teamMap.TryGetValue(k2, out int aIdx)) return new[] { "", "" };
 
                int score1 = int.Parse(s1);
                int score2 = int.Parse(s2);
 
                // Use 'ref' to modify struct values in the array
                ref var t1 = ref ladder[hIdx];
                ref var t2 = ref ladder[aIdx];
 
                t1.For += score1; t1.Against += score2;
                t2.For += score2; t2.Against += score1;
 
                if (score1 > score2) t1.Points += 4;
                else if (score2 > score1) t2.Points += 4;
                else { t1.Points += 2; t2.Points += 2; }
            }
 
            // 3. Find Top 2
            int first = -1, second = -1;
            for (int j = 0; j < ladder.Length; j++) {
                if (first == -1 || IsBetter(ladder[j], ladder[first])) {
                    second = first;
                    first = j;
                } else if (second == -1 || IsBetter(ladder[j], ladder[second])) {
                    second = j;
                }
            }
 
            if (first == -1 || second == -1) return new[] { "", "" };
            return new string[] { ladder[first].Name, ladder[second].Name };
        }
        catch {
            return new string[] { "", "" };
        }
       
        return new string[] { "", "" }; 
    }
 
    private static bool IsBetter(TeamData a, TeamData b) {
        if (a.Points != b.Points) return a.Points > b.Points;
        return a.Percentage > b.Percentage;
    }
 
    private static bool TrySplit(string input, out string a, out string b) {
        int idx = input.IndexOf(':');
        if (idx <= 0 || idx == input.Length - 1) {
            a = b = null;
            return false;
        }
        a = input.Substring(0, idx);
        b = input.Substring(idx + 1);
        return true;
    }
}
 