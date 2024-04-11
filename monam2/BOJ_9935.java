import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9935 { //백준 9935 문자열 폭발 - 60분
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tg = br.readLine();
        String bomb = br.readLine();
        StringBuilder sb = new StringBuilder();

        int bombLength = bomb.length();

        for(int i = 0; i < tg.length(); i++){
            sb.append(tg.charAt(i));
            if(sb.length() >= bombLength){
                boolean isSame = true;
                for(int j = 0; j < bombLength; j++){
                    if(sb.charAt(sb.length() - bombLength + j) != bomb.charAt(j)){
                        isSame = false;
                        break;
                    }
                }
                if(isSame){
                    sb.delete(sb.length() - bombLength, sb.length());
                }
            }
        }

        if(sb.length() == 0){
            System.out.println("FRULA");
        }else{
            System.out.println(sb.toString());
        }
    }
}
