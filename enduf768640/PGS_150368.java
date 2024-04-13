import java.util.Arrays;

class PGS_150368 {

    private static User[] userArray;
    private static int[] emoticonMoney;

    private int emoticonPlusCount;
    private int sum;

    public int[] solution(int[][] users, int[] emoticons) {
        userArray = new User[users.length];
        for (int i = 0; i < users.length; i++) {
            userArray[i] = new User(users[i][0], users[i][1]);
        }
        emoticonMoney = emoticons.clone();

        setRates(new int[emoticons.length], 0, emoticons.length);

        return new int[] {emoticonPlusCount, sum};
    }

    private void setRates(int[] rates, int count, int N) {
        if (count == N) {
            int emoticonPlusCount = (int) Arrays.stream(userArray)
                    .map(user -> user.getAllEmoticonMoney(rates))
                    .filter(money -> money[0] == -1)
                    .mapToInt(money -> money[1])
                    .count();

            int sum = Arrays.stream(userArray)
                    .map(user -> user.getAllEmoticonMoney(rates))
                    .filter(money -> money[0] == 0)
                    .mapToInt(money -> money[1])
                    .sum();

            if (this.emoticonPlusCount == emoticonPlusCount) {
                if (this.sum < sum) {
                    this.sum = sum;
                }
            }

            if (this.emoticonPlusCount < emoticonPlusCount) {
                this.emoticonPlusCount = emoticonPlusCount;
                this.sum = sum;
            }

            return;
        }

        for (int i = 1; i <= 4; i++) {
            rates[count] = i * 10;
            setRates(rates, count + 1, N);
        }
    }

    private static class User {

        private int rate;
        private int price;

        public User(int rate, int price) {
            this.rate = rate;
            this.price = price;
        }

        public int[] getAllEmoticonMoney(int[] rates) {
            int totalMoney = 0;

            for (int i = 0; i < rates.length; i++) {
                if (rates[i] < this.rate) {
                    continue;
                }

                totalMoney += emoticonMoney[i] * (100 - rates[i]) / 100;
            }

            if (totalMoney >= this.price) {
                return new int[] {-1, totalMoney};
            } else {
                return new int[] {0, totalMoney};
            }
        }
    }
}

