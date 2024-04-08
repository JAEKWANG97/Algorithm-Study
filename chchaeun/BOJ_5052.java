import java.io.*;
import java.util.*;

public class BOJ_5052 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static boolean numberCheck(String s1, String s2){
        for (int i = 0; i < s1.length(); i++){
            if (s1.charAt(i) != s2.charAt(i)){
                return false;
            }
        }

        return true;
    }

    public static boolean caseCheck(ArrayList<String> numbers){
        for (int i = 0; i < numbers.size() - 1; i++){
            if (numberCheck(numbers.get(i), numbers.get(i+1))){
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++){
            int N = Integer.parseInt(br.readLine());

            ArrayList<String> numbers = new ArrayList<>();
            for (int i = 0; i < N; i++){
                numbers.add(br.readLine());
            }
            Collections.sort(numbers);

            if (caseCheck(numbers)){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }
}
