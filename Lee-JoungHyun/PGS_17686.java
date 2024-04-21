import java.util.*;

class Solution {
    
    private class File implements Comparable<File> {
        String head;
        int number;
        int cnt;
        String origin;
        
        public File(String str, int cnt) {
            char[] tmp = str.toCharArray();
            String head = "", number = "", tail = "";
            int poz = 0;
            for (char t : tmp) { 
                if ((poz == 1 || poz == 0) && Character.isDigit(t)) {
                    poz = 1;
                    number += t;
                } else if (poz == 0 ) {
                    head += t;
                } else {
                    poz = 3;
                    tail += t;
                }
            }
            this.head = head.toUpperCase();
            this.number = Integer.parseInt(number);
            this.cnt = cnt;
            this.origin = str;
        }
        
        public int compareTo(File o) {
            if (!this.head.equals(o.head)) {
                return this.head.compareTo(o.head);
            } else if (number != o.number) {
                return number - o.number;
            } else {
                return cnt - o.cnt;
                //return this.tail.compareTo(o.tail);
            }
        }
        
        
        public String toString() {
            return origin;
            //return "head: " + head + ", num: " + number + ", tail: " + tail;
        }
        
    }
    
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        PriorityQueue<File> pq = new PriorityQueue<>();
        
        for (int i = 0; i < files.length; i++) {
            pq.add(new File(files[i], i));
        }
        
        for (int i = 0; i < files.length; i++) {
            answer[i] = pq.poll().origin;
        }
        
        return answer;
    }
}