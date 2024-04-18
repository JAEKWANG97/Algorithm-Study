import java.util.*;
import java.io.*;

class Applicant implements Comparable<Applicant> {
    int code, score;
    
    public Applicant(int code, int score) {
        this.code = code;
        this.score = score;
    }
    
    public int compareTo(Applicant o) {
        if (this.code != o.code) {
            return this.code - o.code;
        }
        return this.score - o.score;
    }
    
    public int compare(Applicant o) {
        return this.code - o.code;
    }
    
    public String toString() {
        return code + " " + score;
    }
    
}

class Solution {
    
    public int[] solution(String[] info, String[] query) {
        
        int[] answer = new int[query.length];
        HashMap<String, Integer> map = new HashMap<>();
        setting(map);
        
        List<Applicant> applicants = new ArrayList<>();
        
        StringTokenizer st;
        // 1. parsing
        for (String person : info) {
            st = new StringTokenizer(person);
            int lag = map.get(st.nextToken());
            int end = map.get(st.nextToken());
            int or = map.get(st.nextToken());
            int food = map.get(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            
            for (int a = 0; a < 2; a++)
                for (int b = 0; b < 2; b++)
                    for (int c = 0; c < 2; c++)
                        for (int d = 0; d < 2; d++) {
                            int code = 0;
                            if (a == 0) code += lag * 1000;
                            if (b == 0) code += end * 100;
                            if (c == 0) code += or * 10;
                            if (d == 0) code += food;
                            
                            applicants.add(new Applicant(code, score));
                        }
        }
        Collections.sort(applicants);
        //System.out.println(applicants);
        int ans = 0;
        for (String person : query) {
            st = new StringTokenizer(person);
            int code = 0;
            for (int i = 0; i < 4; i++) {
                String temp = st.nextToken();
                code += temp.equals("-") ? 0 : map.get(temp) * Math.pow(10, 3-i);
                if (i != 3) st.nextToken();
            }
            int score = Integer.parseInt(st.nextToken());
            
            Applicant comp = new Applicant(code, score);
          
            
            answer[ans++] = upperBound(applicants, comp) - lowerBound(applicants, comp);
        }
        
        return answer;
    }
    
    private void setting(HashMap<String, Integer> hs) {
        hs.put("cpp", 1);
        hs.put("java", 2);
        hs.put("python", 3);
        hs.put("backend", 4);
        hs.put("frontend", 5);
        hs.put("junior", 6);
        hs.put("senior", 7);
        hs.put("chicken", 8);
        hs.put("pizza", 9);
    }
    
    private int upperBound(List<Applicant> arr, Applicant comp) {
        int left = 0;
        int right = arr.size();
        
        while (left < right) {
            int mid = (left + right) / 2;
            int com = arr.get(mid).compare(comp);
            // mid 객체가 비교조건보다 작다면
            if(com <= 0) {
        	    left = mid + 1;
            }
            else {
        	    right = mid;
            }
        }
        return right;
    }
    private int lowerBound(List<Applicant> arr, Applicant comp) {
        int left = 0;
        int right = arr.size();
        
        while (left < right) {
            int mid = (left + right) / 2;
            int com = arr.get(mid).compareTo(comp);
            // mid 객체가 비교조건보다 작다면
            if(com < 0) {
        	    left = mid + 1;
            }
            else {
        	    right = mid;
            }
        }
        return right;
    }
}