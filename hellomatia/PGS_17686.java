package hellomatia;

import java.util.*;

class PGS_17686 {

    static class FileName {
        String head;
        int number;
        int idx;

        FileName(String head, int number, int idx) {
            this.head = head;
            this.number = number;
            this.idx = idx;
        }
    }

    static PriorityQueue<FileName> fileNames;

    public String[] solution(String[] files) {
        String[] answer = new String[files.length];

        // 파일명은 100글자 이내로, 영문 대소문자, 숫자, 공백, 마침표, 빼기부호만 이루어져있다.
        // 파일명은 영문자로 시작하며, 숫자를 하나이상 포함

        // 파일명은 크게 HEAD, NUMBER, TAIL 세부분으로 이루어져있음
        // HEAD 숫자가 아닌 문자, 최소 한 글자 이상 (정렬기준 : 사전순, 대소문자 구분X)
        // NUMBER 한 글자에서 최대 다섯글자 사이의 연속된 수로 이루어져잇음, 앞쪽에 0이 올수 있음, 0~99999 사이, 00000이나 0101등도 가능(정렬기준 : 숫자순 0은 무시)
        // TAIL은 그 나머지 부분, 여기에 숫자가 다시 나타날 수 있음, 아무 글자도 없을 수 있음(정렬기준 : 입력이 들어오는 순)

        // fies의 이름을 HEAD, NUMBER, TAIL로 분리하는 과정 진행

        // 우선순위 큐를 선언하여 주어진 조건으로 정렬하게 해줌
        fileNames = new PriorityQueue<>((o1, o2) -> {
            if (o1.head.equals(o2.head)) {
                if (o1.number == o2.number) {
                    return o1.idx - o2.idx;
                }
                return o1.number - o2.number;
            }
            return o1.head.compareTo(o2.head);
        });

        sprationFileName(files);

        int idx = 0;
        while (!fileNames.isEmpty()) {
            FileName now = fileNames.poll();
            answer[idx++] = files[now.idx];
        }

        return answer;
    }

    static void sprationFileName(String[] files) {

        for (int i = 0; i < files.length; i++) {
            String fileName = files[i];
            fileName = fileName.toUpperCase();

            int index = 0;
            // head 부분 추출
            StringBuilder head = new StringBuilder();
            while (fileName.length() - 1 >= index) {
                char ch = fileName.charAt(index);
                if (('A' <= ch && ch <= 'Z') || ch == '-' || ch == ' ' || ch == '.') {
                    head.append(ch);
                } else break;
                index++;
            }
            System.out.println(head.toString());

            //number 부분 추출
            int number = 0;
            int count = 0;
            while (count != 5 && fileName.length() - 1 >= index) {
                char ch = fileName.charAt(index);
                if ('0' <= ch && ch <= '9') {
                    number *= 10;
                    number += (ch - '0');
                } else break;
                index++;
            }

            System.out.println(number);

            fileNames.offer(new FileName(head.toString(), number, i));
        }
    }
}