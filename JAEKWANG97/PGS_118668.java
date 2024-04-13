import java.util.*;


class Solution {

        public int solution(int alp, int cop, int[][] problems) {
            int maxAlp = alp, maxCop = cop;
            for (int[] p : problems) {
                maxAlp = Math.max(maxAlp, p[0]);
                maxCop = Math.max(maxCop, p[1]);
            }

            int[][] dp = new int[maxAlp + 1][maxCop + 1];
            for (int[] row : dp) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
            dp[alp][cop] = 0;

            for (int i = alp; i <= maxAlp; i++) {
                for (int j = cop; j <= maxCop; j++) {
                    if (i > alp) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                    }
                    if (j > cop) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                    }
                    for (int[] problem : problems) {
                        int reqAlp = problem[0], reqCop = problem[1], rwdAlp = problem[2], rwdCop = problem[3], cost = problem[4];
                        if (i >= reqAlp && j >= reqCop) {
                            int newAlp = Math.min(maxAlp, i + rwdAlp);
                            int newCop = Math.min(maxCop, j + rwdCop);
                            dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], dp[i][j] + cost);
                        }
                    }
                }
            }

            return dp[maxAlp][maxCop];
        }

    }
