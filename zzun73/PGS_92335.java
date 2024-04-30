import java.util.StringTokenizer;

class PGS_92335 {
    static boolean isPrime(long num) {
        if (num == 0 || num == 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }

        for (long i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    public int solution(int n, int k) {
        String str = Integer.toString(n, k);
        StringTokenizer st = new StringTokenizer(str, "0");
        int answer = 0;

        while (st.hasMoreTokens()) {
            if (isPrime(Long.parseLong(st.nextToken()))) {
                answer++;
            }
        }

        return answer;
    }
}
