
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

//7662 이중 우선순위 큐
class BOJ_7662 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int test = 0; test < t; test++) {
			TreeMap<Integer, Integer> tree = new TreeMap<>();
			int k = sc.nextInt();
			// 명령어
			for (int a = 0; a < k; a++) {
				String m = sc.next();
				// 삽입
				if (m.equals("I")) {
					int number = sc.nextInt();
					// 중복된 값이면 개수 추가하기
					if (tree.get(number) != null) {
						tree.put(number, tree.get(number) + 1);
					} else {
						tree.put(number, 1);
					}

//					System.out.println(set);

					// 삭제
				} else {
					int number = sc.nextInt();
					// 원소가 없으면 무시
					if (tree.isEmpty())
						continue;

					// 최대값 삭제하기
					if (number == 1) {
						// 최대값 뽑기
						Entry<Integer, Integer> entry = tree.pollLastEntry();
						if (entry.getValue() > 1) {
							// 뽑은 최대값 개수 -1해서 다시 넣어주기, 만약 개수가 0이되면 다시 넣어줄 필요가 없으니 의미없음
							tree.put(entry.getKey(), entry.getValue() - 1);
						}

					}
					// 최솟값 삭제
					else if (number == -1) {
						Entry<Integer, Integer> entry = tree.pollFirstEntry();
						if (entry.getValue() > 1) {
							// 뽑은 최대값 개수 -1해서 다시 넣어주기, 만약 개수가 0이되면 다시 넣어줄 필요가 없으니 의미없음
							tree.put(entry.getKey(), entry.getValue() - 1);
						}

					}

				}

			}
			if (tree.size() == 0) {
				System.out.println("EMPTY");
			} else {
				int max = tree.lastKey();
				int min = tree.firstKey();
				System.out.println(max + " " + min);
			}

		}
	}
}
