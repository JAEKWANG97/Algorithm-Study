class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for (int i = 0; i < n; i++) {
            int floor = arr1[i] | arr2[i];
            char[] tmp = new char[n];
            for (int j = n - 1; j > -1; j--) {
                if ((floor & (1 << j)) != 0) {
                    tmp[n - 1 - j] = '#';
                } else {
                    tmp[n - 1 - j] = ' ';
                }
            }
            answer[i] = new String(tmp);
        }
        
        return answer;
    }
}