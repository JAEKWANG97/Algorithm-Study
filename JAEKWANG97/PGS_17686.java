import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    static class File implements Comparable<File> {
        String head;
        String number;
        String tail;

        public int compareTo(File o) {
            int value = this.head.toLowerCase().compareTo(o.head.toLowerCase());
            if (value == 0) {
                value = Integer.parseInt(this.number) - Integer.parseInt(o.number);
            }
            return value;
        }
    }

    public String[] solution(String[] files) {
        List<File> fileList = new ArrayList<>();  // Ensure the list is reinitialized for each call
        splitFile(files, fileList);
        Collections.sort(fileList);
        String[] answer = new String[fileList.size()];
        for (int i = 0; i < fileList.size(); i++) {
            answer[i] = fileList.get(i).head + fileList.get(i).number + fileList.get(i).tail;
        }
        return answer;
    }

    private static void splitFile(String[] files, List<File> fileList) {
        for (String file : files) {
            File f = new File();
            int firstDigitIndex = findFirstDigitIndex(file);
            int nonDigitAfterIndex = findNonDigitAfterIndex(file, firstDigitIndex);
            f.head = file.substring(0, firstDigitIndex);
            f.number = file.substring(firstDigitIndex, nonDigitAfterIndex);
            f.tail = file.substring(nonDigitAfterIndex);
            fileList.add(f);
        }
    }

    private static int findFirstDigitIndex(String file) {
        for (int i = 0; i < file.length(); i++) {
            if (Character.isDigit(file.charAt(i))) {
                return i;
            }
        }
        return file.length();
    }

    private static int findNonDigitAfterIndex(String file, int start) {
        for (int i = start; i < file.length(); i++) {
            if (!Character.isDigit(file.charAt(i))) {
                return i;
            }
        }
        return file.length(); 
    }
}
