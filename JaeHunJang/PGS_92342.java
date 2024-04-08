class PGS_92342 {
	// 양궁대회
	class Solution {
		int N, maxScore;
		int[] lion, apeach, answer = {-1};
		final int MAX_SCORE = 10;
		public int[] solution(int n, int[] info) {
			N = n;
			lion = new int[info.length];
			apeach = info;
			maxScore = Integer.MIN_VALUE; // 최고 점수

			dfs(0);

			return answer;
		}

		public void dfs(int cnt) {
			if (cnt == N) {
				int score = calcScore();
				// 라이온이 더 큰 점수차로 이겼을 때 갱신
				// 같은 경우에 갱신하는 것은 뒤에 더 많이 맞춘 경우
				if (score > 0 && maxScore <= score) {
					maxScore = score;
					answer = lion.clone();
				}

				return;
			}

			for (int i = 0; i <= MAX_SCORE; i++) {
				if (lion[i] > apeach[i]) break;
				lion[i]++;
				dfs(cnt+1);
				lion[i]--;
			}
		}

		public int calcScore() { // 점수차 계산
			int suml=0, suma=0;
			for (int i = 0; i < lion.length; i++) {
				if (lion[i] == 0 && apeach[i] == 0) continue; // 둘다 못맞추면 점수 계산 x
				if (lion[i] > apeach[i]) {
					suml += MAX_SCORE - i;
				} else if (lion[i] <= apeach[i]) {
					suma += MAX_SCORE - i;
				}
			}
			return suml - suma;
		}
	}
}