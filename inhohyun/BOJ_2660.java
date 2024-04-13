
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2660 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());

		int[][] arr = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i != j) {
					arr[i][j] = Integer.MAX_VALUE;
				}

			}
		} // 최대값으로 초기화

		// 입력 받기
		while (true) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (a == -1 && b == -1) {
				break;
			}

			// 양방향 연결
			arr[a][b] = arr[b][a] = 1;

		}

		// 플로이드-와샬 알고리즘을 이용해 모든 노드 간의 최단 거리 구하기
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (arr[i][k] != Integer.MAX_VALUE && arr[k][j] != Integer.MAX_VALUE) {
						arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
					}
				}
			}
		}

		int minScore = Integer.MAX_VALUE; // 회장 후보의 최소 점수
		int[] scores = new int[n + 1]; // 각 노드의 점수를 저장할 배열

		// 각 노드의 점수 계산하기
		for (int i = 1; i <= n; i++) {
			int maxScore = 0; // 해당 노드의 점수 초기화
			for (int j = 1; j <= n; j++) {
				if (arr[i][j] != Integer.MAX_VALUE) {
					maxScore = Math.max(maxScore, arr[i][j]);
				}
			}
			scores[i] = maxScore; // 해당 노드의 점수 저장
			minScore = Math.min(minScore, maxScore); // 회장 후보의 최소 점수 갱신
		}

		StringBuilder candidates = new StringBuilder(); // 회장 후보들을 저장할 StringBuilder
		int candidateCount = 0; // 회장 후보의 수

		// 회장 후보 찾기
		for (int i = 1; i <= n; i++) {
			if (scores[i] == minScore) {
				candidateCount++;
				candidates.append(i).append(" ");
			}
		}

		// 결과 출력
		System.out.println(minScore + " " + candidateCount);
		System.out.println(candidates);
	}
}
