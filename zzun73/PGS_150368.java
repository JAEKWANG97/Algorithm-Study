class PGS_150368 {
    static int maxPeople, maxCost;

    void helper(int depth, int[] discounts, int[][] users, int[] emoticons) {
        if (depth == emoticons.length) {
            calc(discounts, users, emoticons);
            return;
        }
        for (int discountRate = 10; discountRate <= 40; discountRate += 10) {
            discounts[depth] = discountRate;
            helper(depth + 1, discounts, users, emoticons);
        }
    }

    void calc(int[] discounts, int[][] users, int[] emoticons) {
        int curPeople, curCost;
        curPeople = curCost = 0;

        for (int[] user : users) {
            int sum = 0;
            for (int i = 0; i < emoticons.length; i++) {
                if (discounts[i] >= user[0]) {
                    sum += emoticons[i] / 100 * (100 - discounts[i]);
                }
            }
            if (sum >= user[1]) {
                curPeople++;
            } else {
                curCost += sum;
            }
        }

        if (curPeople > maxPeople) {
            maxPeople = curPeople;
            maxCost = curCost;
        } else if (curPeople == maxPeople) {
            maxCost = Math.max(maxCost, curCost);
        }
    }

    public int[] solution(int[][] users, int[] emoticons) {
        helper(0, new int[emoticons.length], users, emoticons);
        return new int[]{maxPeople, maxCost};
    }
}
