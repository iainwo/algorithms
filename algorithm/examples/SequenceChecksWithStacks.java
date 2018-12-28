import java.util.Stack;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class BalanceChecker {
    public static void main(String[] args) {
        final String TEST_1 = "()[]{}";
        final String TEST_2 = "(()";
        final String TEST_3 = "{([][]()({}))[}}";
        
        BalanceChecker.diag(null);
        BalanceChecker.diag("");
        BalanceChecker.diag(TEST_1);
        BalanceChecker.diag(TEST_2);
        BalanceChecker.diag(TEST_3);
        BalanceChecker.diag("{{{((([][][])))}}}");
        BalanceChecker.diag("{");
        BalanceChecker.diag("}");
        BalanceChecker.diag("][");
        BalanceChecker.diag("[]]");
        BalanceChecker.diag("([[][]])");
        BalanceChecker.diag("]([[][]])");
    }
    
    private static void diag(String s) {
        final String LE = "\n";
        System.out.println(""
            + "Testing '" + s + "'."     + LE
            + "Result: " + BalanceChecker.test(s) + "." + LE);
    }
    
    private static boolean test(String input) {
        boolean retBool = false;
        
        if (null == input);
        else if (input.isEmpty());
        else if (0 != input.length() % 2);
        else if (BalanceChecker.isClosure(Character.toString(input.charAt(0))));
        else {

            Stack<String> stack = new Stack<>();
        
            for (int i = 0; i < input.length(); i++) {
                String indexVal = Character.toString(input.charAt(i));
                
                if (BalanceChecker.isClosure(indexVal)) {
                    String stackTop = stack.pop();
                    if (!BalanceChecker.isComplement(stackTop, indexVal))
                        return false; 
                } else {
                    stack.push(indexVal);
                }
            }
            
            retBool = true;
        }
        
        return retBool;
    }
    private static boolean isClosure(String s) {
        return Pattern.matches("^(\\)|\\]|\\})$", s);
    }
    private static boolean isComplement(String a, String b) {
        String conjunction = a + b;
        System.out.println("Conjunction: " + conjunction);
        return Pattern.matches("^(\\(\\)|\\[\\]|\\{\\})$", conjunction);
    }
}
