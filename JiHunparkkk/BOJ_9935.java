import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        Stack<Character> stack = new Stack<>();
        Stack<Character> temp = new Stack<>();

        String str = br.readLine();
        String bomb = br.readLine();
        int len = bomb.length();

        for (int rp = 0; rp < str.length(); rp++) {
            stack.push(str.charAt(rp));
            while (!stack.isEmpty() && len>0 && bomb.charAt(len - 1) == stack.peek()) {
                temp.push(stack.pop());
                len--;
            }
            if(len!=0){
                while(!temp.isEmpty()){
                    stack.push(temp.pop());
                }
            }
            len = bomb.length();
            if(!temp.isEmpty())
                temp.clear();
        }

        if(stack.size()>0) {
            for (int i = 0; i < stack.size(); i++) {
                sb.append(stack.get(i));
            }
        }else{
            sb.append("FRULA");
        }
        System.out.println(sb);
    }
}

