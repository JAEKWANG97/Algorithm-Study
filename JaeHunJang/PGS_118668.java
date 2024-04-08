import java.util.Arrays;
import java.util.PriorityQueue;

class PGS_118668 {
	// 코딩 테스트 공부 - 160분
	// https://school.programmers.co.kr/learn/courses/30/lessons/118668
	class Solution {
		public int solution(int alp, int cop, int[][] problems) {
			int answer = 0;

			int maxAlp = 0, maxCop = 0;
			for (int i = 0; i < problems.length; i++) {
				maxAlp = Math.max(problems[i][0], maxAlp);
				maxCop = Math.max(problems[i][1], maxCop);
			}

			if (maxAlp <= alp && maxCop <= cop) {
				return answer;
			}

			alp = Math.min(alp, maxAlp);
			cop = Math.min(cop, maxCop);

			int dp[][] = new int[maxAlp+2][maxCop+2];

			for (int i = 0; i < dp.length; i++) {
				Arrays.fill(dp[i], Integer.MAX_VALUE);
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
						int alp_req = problem[0],
								cop_req = problem[1],
								alp_rwd = problem[2],
								cop_rwd = problem[3],
								cost = problem[4];

						if (i >= alp_req && j >= cop_req) {
							int newAlp = Math.min(maxAlp, i + alp_rwd); // 새로운 알고력
							int newCop = Math.min(maxCop, j + cop_rwd); // 새로운 코딩력
							dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], dp[i][j] + cost); // 최소 비용 업데이트
						}
					}
				}
			}

			return dp[maxAlp][maxCop];
		}
	}
}