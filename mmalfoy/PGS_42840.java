import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        int[] people = {0, 0, 0};
        for (int i = 0; i < answers.length; i++) {
            if (solveOne(i, answers[i])) {
                people[0] ++;
            }
            if (solveTwo(i, answers[i])) {
                people[1] ++;
            }
            if (solveThree(i, answers[i])) {
                people[2] ++;
            }
        }
        int max = 0;
        List<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (people[i] == max) {
                tmp.add(i);
            } else if (people[i] > max) {
                max = people[i];
                tmp = new ArrayList<>();
                tmp.add(i);
            }
        }
        answer = tmp.stream().mapToInt(i -> i + 1).toArray();
        return answer;
    }

    public boolean solveOne(int n, int answer) {
        int[] solution = {1, 2, 3, 4, 5};
        return solution[n%5] == answer;
    }

    public boolean solveTwo(int n, int answer) {
        int[] solution = {2, 1, 2, 3, 2, 4, 2, 5};
        return solution[n%8] == answer;
    }

    public boolean solveThree(int n, int answer) {
        int[] solution = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        return solution[n%10] == answer;
    }

    


}