public class PGS_92342 {
    int len = 11, maxDiff = 0;
    int[] maxRyan = new int[len];

    public int[] solution(int n, int[] info) {
        int[] ryan = new int[len];
        getArcheryScore(n, info, ryan, 0);

        return maxDiff > 0 ? maxRyan : new int[]{-1};
    }

    public void getArcheryScore(int n, int[] info, int[] ryan, int score) {
        if (n == 0 || score == len) {
            setMaxRyanAndDiff(info, ryan);
            return;
        }

        int arrow = 0;
        if (info[score] < n) arrow = info[score] + 1;
        else if (score == len - 1) arrow = n;

        int tmp = info[score];
        if (arrow > 0) {
            info[score] = 0;
            ryan[score] = arrow;
            getArcheryScore(n - arrow, info, ryan, score + 1);
        }
        info[score] = tmp;
        ryan[score] = 0;
        getArcheryScore(n, info, ryan, score + 1);
    }

    public void setMaxRyanAndDiff(int[] info, int[] ryan) {
        int sum = 0;
        for (int i = 0; i < len; i++) {
            if (info[i] > 0) {
                sum -= 10 - i;
            }
        }
        for (int i = 0; i < len; i++) {
            if (ryan[i] > 0) {
                sum += 10 - i;
            }
        }

        if (this.maxDiff < sum) {
            for (int r = 0; r < len; r++) {
                this.maxRyan[r] = ryan[r];
            }
            this.maxDiff = sum;
        } else if (this.maxDiff == sum) {
            for (int i = len - 1; i >= 0; i--) {
                if (this.maxRyan[i] < ryan[i]) {
                    for (int r = 0; r < len; r++) {
                        this.maxRyan[r] = ryan[r];
                    }
                    break;
                } else if (this.maxRyan[i] > ryan[i]) {
                    break;
                }
            }
        }
    }
}
