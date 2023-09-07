import java.util.ArrayList;

class Solution {
    public int evalRPN(String[] tokens) {
        ArrayList<Integer> stack = new ArrayList<>();
        int index = 0;
        for (String entry : tokens) {
            int code = (int) entry.charAt(0);
            if (code == 42 || code == 43 || code == 47 || (code == 45 & entry.length() == 1)) {
                //evaluate expression
                int operand1 = stack.remove(--index);
                int operand2 = stack.remove(--index);
                int result = 0;
                switch (code) {
                    case (42):
                        result = operand1 * operand2;
                        break;
                    case (43):
                        result = operand1 + operand2;
                        break;
                    case (47):
                        result = operand2 / operand1;
                        break;
                    case (45):
                        result = operand2 - operand1;
                        break;
                }
                stack.add(index++, result);
            } else {
                int multiplier = 1;
                if ((int) entry.charAt(0) == 45) {
                    entry = entry.substring(1);
                    multiplier *= -1;
                }
                int num = convertStringToInt(entry);
                num *= multiplier;
                stack.add(index++, num);
            }
        }
        return stack.remove(--index);
    }

    public int convertStringToInt(String s) {
        int ascii = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int code = (int) s.charAt(i);
            int value = code - 48;
            ascii += value * Math.pow(10, s.length() - 1 - i);
        }
        return ascii;
    }

    public static void main(String[] args) {
        String[] eval = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        String num = "123";
        Solution test = new Solution();
        int ans = test.evalRPN(eval);
        System.out.println(ans);
    }
}