import java.util.*;
import java.io.*;

class Solution {
    static int users[][], emoticons[], permu[], usersSize, emoSize;
    static int maxPlus, maxEarning;


    static void solve() {
        int plusUser = 0;
        int eSum = 0;

        for (int i = 0; i < usersSize; i++) {
            int pSum = 0;

            for (int j = 0; j < emoSize; j++) {
                if (permu[j] < users[i][0]) continue;
                pSum += (100 - permu[j]) * emoticons[j] / 100;

            }

            if (pSum >= users[i][1]) {
                plusUser++;
            } else {
                eSum += pSum;
            }

        }

        if (maxPlus < plusUser) {
            maxPlus = plusUser;
            maxEarning = eSum;
        } else if (maxPlus == plusUser) {
            maxEarning = Math.max(maxEarning, eSum);
        }



    }



    static void permutation(int c) {
        if (c == emoSize) {
            solve();
            return;
        }

        for (int i = 1; i <= 4; i++) {
            permu[c] = i * 10;
            permutation(c + 1);
        }

    }

    public int[] solution(int[][] users_, int[] emoticons_) {
        int[] answer = {};
        users = users_;
        emoticons = emoticons_;
        emoSize = emoticons.length;
        usersSize = users.length;
        permu = new int[emoSize];
        //init end;
        
        //dupermu
        permutation(0);
        
        answer = new int[2];
        answer[0] = maxPlus;
        answer[1] = maxEarning;
        
        
        return answer;
    }
}
