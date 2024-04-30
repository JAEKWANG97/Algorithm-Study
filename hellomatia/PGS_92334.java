package hellomatia;

import java.util.*;

class PGS_92334 {

    class User {
        int num;
        String name;
        int count;
        Set<Integer> hateMeUsersNum;

        User(int num, String name) {
            this.num = num;
            this.name = name;
            hateMeUsersNum = new HashSet<>();
        }

        public void addHateMeUsers(int num) {
            if (hateMeUsersNum.contains(num)) {
                return;
            }
            hateMeUsersNum.add(num);
            count++;
        }
    }

    public int[] solution(String[] id_list, String[] report, int k) {

        User[] users = new User[id_list.length];
        Map<String, Integer> userIdNumMap = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            users[i] = new User(i, id_list[i]);
            userIdNumMap.put(id_list[i], i);
        }

        PriorityQueue<User> pq = new PriorityQueue<>((o1, o2) -> o2.count - o1.count);
        for (String repo : report) {
            StringTokenizer st = new StringTokenizer(repo, " ");
            int userNum = userIdNumMap.get(st.nextToken());
            int hateUserNum = userIdNumMap.get(st.nextToken());
            users[hateUserNum].addHateMeUsers(userNum);
        }

        for (int i = 0; i < id_list.length; i++) {
            pq.offer(users[i]);
        }

        int[] answer = new int[id_list.length];

        while (!pq.isEmpty() && pq.peek().count >= k) {
            User now = pq.poll();
            for (int hateUser : now.hateMeUsersNum) {
                answer[hateUser]++;
            }
        }

        return answer;
    }
}
