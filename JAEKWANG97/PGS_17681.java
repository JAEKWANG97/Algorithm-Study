class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        return test(n, arr1, arr2);
    }

    private static String[] test(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if ((arr1[i] & (1 << j)) != 0 || (arr2[i] & (1 << j)) != 0) {
                    temp.append("#");
                } else {
                    temp.append(" ");
                }
            }
            temp.reverse();

            answer[i] = temp.toString();
        }
        return answer;
    }
}
