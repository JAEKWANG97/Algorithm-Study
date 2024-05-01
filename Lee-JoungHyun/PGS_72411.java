import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
 
class Solution {
    static String str;
	static HashMap<String, Integer> menu;
	static boolean isVisited[];
	static int max[];
 
	public static String[] solution(String[] orders, int[] course) {
		ArrayList<String> answer = new ArrayList<String>();
 
		max = new int[course.length];
		menu = new HashMap<String, Integer>();
		for (int i = 0; i < orders.length; i++) {
			char a[] = orders[i].toCharArray();
			Arrays.sort(a);
			str = String.copyValueOf(a);
			
			for (int j = 0; j < course.length; j++) {
				isVisited = new boolean[str.length()];
				comb(0, 0, "", j, course[j]);
			}
		}
 
		for (String s : menu.keySet())
			for (int i = 0; i < course.length; i++)
				if (course[i] == s.length() && max[i] != 1 && menu.get(s) == max[i])
					answer.add(s);
 
		return answer.stream().sorted().map(s -> s).toArray(String[]::new);
	}
 
	private static void comb(int cur, int cnt, String s, int j, int N) {
		if (cnt == N) {
			menu.put(s, menu.containsKey(s) ? menu.get(s) + 1 : 1);
			max[j] = Math.max(max[j], menu.get(s));
			return;
		}
 
		for (int i = cur; i < str.length(); i++) {
			isVisited[i] = true;
			comb(i + 1, cnt + 1, s + str.charAt(i), j, N);
		}
	}
}