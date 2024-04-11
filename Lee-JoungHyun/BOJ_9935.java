import java.io.*;
import java.util.Stack;

public class BOJ_9935 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String regex = br.readLine();
        int regexSize = regex.length();

        Stack<Character> stack = new Stack<>();

        for(int i=0; i<line.length(); i++) {
            stack.push(line.charAt(i));

            if(stack.size()>= regexSize) {
                boolean flag = true;

                for(int j=0; j<regexSize; j++) {
                    if(stack.get(stack.size()-regexSize+j) != regex.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    for(int j=0; j<regexSize; j++) {
                        stack.pop();
                    }
                }
            }

        }

        StringBuilder sb = new StringBuilder();
        for(Character c : stack) {
            sb.append(c);
        }
        System.out.println(sb.length()==0? "FRULA" : sb.toString());
    }
}