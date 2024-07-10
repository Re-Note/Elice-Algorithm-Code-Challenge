import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(decompLen(s));
    }
    
    public static int decompLen(String s) {
        Stack<Integer> repeat = new Stack<>();
        Stack<Integer> len = new Stack<>();
        int curLen = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if (Character.isDigit(ch)) {
                int num = ch - '0';
                // 다음 문자가 '(' 인지 확인
                if (i + 1 < s.length() && s.charAt(i + 1) == '(') {
                    repeat.push(num);
                } else {
                    curLen++;
                }
            } else if (ch == '(') {
                len.push(curLen);
                curLen = 0;
            } else if (ch == ')') {
                int cnt = repeat.pop();
                curLen = len.pop() + curLen * cnt;
            }
        }
        
        return curLen;
    }
}