import java.util.HashMap;
import java.util.Map;

class PGS_42888 {
    public String[] solution(String[] record) {

        Map<String, String> map = new HashMap<>();
        int count = 0;
        for (String str : record) {
            if (str.startsWith("Enter") || str.startsWith("Change")) {
                String[] arr = str.split(" ");
                map.put(arr[1], arr[2]);
            }
            if (str.startsWith("Change")) {
                count++;
            }
        }

        String[] answer = new String[record.length - count];
        int idx = 0;
        for (String str : record) {
            if (str.startsWith("Enter")) {
                String[] arr = str.split(" ");
                answer[idx] = map.get(arr[1]) + "님이 들어왔습니다.";
                idx++;
            } else if (str.startsWith("Leave")) {
                String[] arr = str.split(" ");
                answer[idx] = map.get(arr[1]) + "님이 나갔습니다.";
                idx++;
            }
        }

        return answer;
    }
}