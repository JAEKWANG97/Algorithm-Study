package hellomatia;

import java.util.Scanner;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.PriorityQueue;

public class CT_코드트리채점기 {

    static class Url implements Comparable<Url> {
        int tme, id;
        int num;

        public Url(int tme, int id, int num) {
            this.tme = tme;
            this.id = id;
            this.num = num;
        }

        @Override
        public int compareTo(Url url) {
            if(this.id != url.id) return this.id - url.id;
            return this.tme - url.tme;
        }
    }
    public static final int MAX_D = 300;
    public static final int MAX_N = 50000;
    public static final int INF = 1987654321;

    public static int q;
    public static int n;

    public static TreeSet[] isInReadyq = new TreeSet[MAX_D + 1];

    public static PriorityQueue<Integer> restJudger = new PriorityQueue<>();

    public static int[] judgingDomain = new int[MAX_N + 1];

    public static int[] s = new int[MAX_D + 1];
    public static int[] g = new int[MAX_D + 1];
    public static int[] e = new int[MAX_D + 1];

    public static TreeMap<String, Integer> domainToIdx = new TreeMap<>();
    public static int cnt;

    public static int ans;

    public static PriorityQueue<Url>[] urlPq = new PriorityQueue[MAX_D + 1];

    public static void init(Scanner sc) {
        String url;
        n = sc.nextInt();
        url = sc.next();

        for(int i = 1; i <= n; i++) restJudger.add(i);

        int idx = 0;
        for(int i = 0; i < url.length(); i++) {
            if(url.charAt(i) == '/') idx = i;
        }

        String domain = url.substring(0, idx);
        Integer val = Integer.valueOf(url.substring(idx + 1));
        int num = val;

        if(!domainToIdx.containsKey(domain)) {
            cnt++;
            domainToIdx.put(domain, cnt);
        }
        int domainIdx = domainToIdx.get(domain);

        isInReadyq[domainIdx].add(num);

        Url newUrl = new Url(0, 1, num);
        urlPq[domainIdx].add(newUrl);

        ans++;
    }

    public static void newUrl(Scanner sc) {
        int tme, id;
        String url;
        tme = sc.nextInt();
        id = sc.nextInt();
        url = sc.next();

        int idx = 0;
        for(int i = 0; i < url.length(); i++) {
            if(url.charAt(i) == '/') idx = i;
        }

        String domain = url.substring(0, idx);
        Integer val = Integer.valueOf(url.substring(idx + 1));
        int num = val;

        if(!domainToIdx.containsKey(domain)) {
            cnt++;
            domainToIdx.put(domain, cnt);
        }
        int domainIdx = domainToIdx.get(domain);

        if(isInReadyq[domainIdx].contains(num)) {
            return;
        }

        isInReadyq[domainIdx].add(num);

        Url newUrl = new Url(tme, id, num);
        urlPq[domainIdx].add(newUrl);

        ans++;
    }

    public static void assign(Scanner sc) {
        int tme;
        tme = sc.nextInt();

        if(restJudger.isEmpty()) return;

        int minDomain = 0;
        Url minUrl = new Url(0, INF, 0);

        for(int i = 1; i <= cnt; i++) {
            if(e[i] > tme) continue;

            if(!urlPq[i].isEmpty()) {
                Url curUrl = urlPq[i].peek();

                if(minUrl.id > curUrl.id || (minUrl.id == curUrl.id && minUrl.tme > curUrl.tme)) {
                    minUrl = curUrl;
                    minDomain = i;
                }
            }
        }

        if(minDomain > 0) {
            int judgerIdx = restJudger.peek(); restJudger.poll();

            urlPq[minDomain].poll();

            s[minDomain] = tme;
            e[minDomain] = INF;

            judgingDomain[judgerIdx] = minDomain;

            isInReadyq[minDomain].remove(minUrl.num);

            ans--;
        }
    }

    public static void finish(Scanner sc) {
        int tme, id;
        tme = sc.nextInt();
        id = sc.nextInt();

        if(judgingDomain[id] == 0) return;

        restJudger.add(id);
        int domainIdx = judgingDomain[id];
        judgingDomain[id] = 0;

        g[domainIdx] = tme - s[domainIdx];
        e[domainIdx] = s[domainIdx] + 3 * g[domainIdx];
    }

    public static void check(Scanner sc) {
        int tme;
        tme = sc.nextInt();

        System.out.print(ans + "\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        q = sc.nextInt();

        for(int i = 1; i <= MAX_D; i++) {
            urlPq[i] = new PriorityQueue<Url>();
        }

        for(int i = 1; i <= MAX_D; i++) {
            isInReadyq[i] = new TreeSet<Integer>();
        }

        while(q-- > 0) {
            int query;
            query = sc.nextInt();

            if(query == 100) {
                init(sc);
            }
            if(query == 200) {
                newUrl(sc);
            }
            if(query == 300) {
                assign(sc);
            }
            if(query == 400) {
                finish(sc);
            }
            if(query == 500) {
                check(sc);
            }
        }
    }
}