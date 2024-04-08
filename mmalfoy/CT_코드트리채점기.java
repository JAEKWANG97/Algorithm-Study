import java.io.*;
import java.util.*;

public class CT_코드트리채점기 {

	static final int INF = 1_000_000_001;
	static Map<String, PriorityQueue<Pair<Pair<Integer, Integer>, String>>> waitingQueue = new HashMap<>();
	static Map<String, Set<Integer>> waitingUrls = new HashMap<>();
	static Map<String, Integer> retried = new HashMap<>();
	static Map<String, Integer> processStartTime = new HashMap<>();
	static Map<String, Boolean> judgingDomains = new HashMap<>();
	static PriorityQueue<Integer> judgerIdx = new PriorityQueue<>();
	static String[] judger;
	static int waitingCnt = 0;

	static class Pair<T, U> {
		T first;
		U second;

		Pair(T first, U second) {
			this.first = first;
			this.second = second;
		}
	}

	static Pair<String, Integer> getDomain(String url) {
		int i = url.indexOf('/');
		return new Pair<>(url.substring(0, i), Integer.parseInt(url.substring(i + 1)));
	}

	static void init(int N, String url) {
		judger = new String[N + 1];
		Arrays.fill(judger, "");
		for (int i = 1; i <= N; i++)
			judgerIdx.add(i);
		Pair<String, Integer> domainProblemId = getDomain(url);
		String domain = domainProblemId.first;
		int problemId = domainProblemId.second;
		waitingQueue.putIfAbsent(domain,
				new PriorityQueue<>((a, b) -> !a.first.first.equals(b.first.first) ? a.first.first - b.first.first
						: !a.first.second.equals(b.first.second) ? a.first.second - b.first.second
								: a.second.compareTo(b.second)));
		waitingQueue.get(domain).add(new Pair<>(new Pair<>(1, 0), url));
		waitingUrls.putIfAbsent(domain, new HashSet<>());
		waitingUrls.get(domain).add(problemId);
		waitingCnt++;
	}

	static void request(int mTime, int p, String url) {
		Pair<String, Integer> domainProblemId = getDomain(url);
		String domain = domainProblemId.first;
		int problemId = domainProblemId.second;
		waitingUrls.putIfAbsent(domain, new HashSet<>());
		if (waitingUrls.get(domain).contains(problemId))
			return;
		waitingQueue.putIfAbsent(domain,
				new PriorityQueue<>((a, b) -> !a.first.first.equals(b.first.first) ? a.first.first - b.first.first
						: !a.first.second.equals(b.first.second) ? a.first.second - b.first.second
								: a.second.compareTo(b.second)));
		waitingQueue.get(domain).add(new Pair<>(new Pair<>(p, mTime), url));
		waitingUrls.get(domain).add(problemId);
		waitingCnt++;
	}

	static void assign(int mTime) {
		if (judgerIdx.isEmpty())
			return;
		Pair<Pair<Integer, Integer>, String> x = new Pair<>(new Pair<>(INF, INF), "");
		for (Map.Entry<String, PriorityQueue<Pair<Pair<Integer, Integer>, String>>> entry : waitingQueue.entrySet()) {
			String domain = entry.getKey();
			if (judgingDomains.getOrDefault(domain, false))
				continue;
			if (!entry.getValue().isEmpty()) {
				if (retried.getOrDefault(domain, 0) <= mTime) {
					Pair<Pair<Integer, Integer>, String> top = entry.getValue().peek();
					if (top.first.first < x.first.first
							|| (top.first.first.equals(x.first.first) && top.first.second < x.first.second)) {
						x = top;
					}
				}
			}
		}
		if (x.second.equals(""))
			return;
		Pair<String, Integer> domainProblemId = getDomain(x.second);
		String domain = domainProblemId.first;
		int problemId = domainProblemId.second;
		waitingQueue.get(domain).poll();
		waitingUrls.get(domain).remove(problemId);
		waitingCnt--;

		int judgerId = judgerIdx.poll();
		judger[judgerId] = domain;
		judgingDomains.put(domain, true);
		processStartTime.put(domain, mTime);
	}

	static void finish(int mTime, int mId) {
		if (judger[mId].equals(""))
			return;
		String domain = judger[mId];
		judger[mId] = "";
		judgerIdx.add(mId);
		judgingDomains.put(domain, false);
		retried.put(domain, processStartTime.get(domain) + (mTime - processStartTime.get(domain)) * 3);
	}

	static int check(int mTime) {
		return waitingCnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int queryCnt = Integer.parseInt(br.readLine());
		while (queryCnt-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			switch (cmd) {
			case 100: {
				int N = Integer.parseInt(st.nextToken());
				String url = st.nextToken();
				init(N, url);
				break;
			}
			case 200: {
				int mTime = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				String url = st.nextToken();
				request(mTime, p, url);
				break;
			}
			case 300: {
				int mTime = Integer.parseInt(st.nextToken());
				assign(mTime);
				break;
			}
			case 400: {
				int mTime = Integer.parseInt(st.nextToken());
				int mId = Integer.parseInt(st.nextToken());
				finish(mTime, mId);
				break;
			}
			case 500: {
				int mTime = Integer.parseInt(st.nextToken());
				out.println(check(mTime));
				break;
			}
			default:
				break;
			}
		}
		out.flush();
	}
}
