import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1931 {
    static class room implements Comparable<room>{
        int start, end;

        room(int s, int e){
            start = s;
            end = e;
        }

        @Override
        public int compareTo(BOJ_1931.room o) {
            if(this.end != o.end) {
                return end - o.end;
            }

            return start - o.start;
        }
    }

    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());
        room[] rooms = new room[n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            rooms[i] = new room(s, e);

        }

        Arrays.sort(rooms); // 시작순, 같으면 끝나는 순으로 정리


        int end = rooms[0].end;
        int cnt = 1;
//		System.out.println("저장된 시간 : "+rooms[0].start+" "+rooms[0].end);
        for(int i = 1; i < n; i ++) {
            if(end <= rooms[i].start) {
                end = rooms[i].end;
//				System.out.println("저장된 시간 : "+rooms[i].start+" "+rooms[i].end);
                cnt++;
            }
        }

        System.out.println(cnt);

    }
}
