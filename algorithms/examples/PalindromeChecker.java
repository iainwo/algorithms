import java.util.Deque;
import java.util.LinkedList;

public class PalindromeAuthority {
    public static void main(String[] args) {
        PalindromeAuthority.validate("abc");
        PalindromeAuthority.validate("aa");
        PalindromeAuthority.validate("");
        PalindromeAuthority.validate(null);
        PalindromeAuthority.validate("aba");
        PalindromeAuthority.validate("bbbbbabbbbc");
        PalindromeAuthority.validate("1234567890987654321");
        PalindromeAuthority.validate("12345678900987654321");
    }
    
    /**
     * This method will output the user the results of a Palindrome check.
     * @param raw the string which to test palindrome membership for
     */
    public static void validate(String raw) {
        
        /* Iterative Palindrome */
        boolean retBool = isPalindrome(raw);
        System.out.println(""
            + "String [" + raw + "] is a palindrome [" + retBool + "].");
        
        /* Deque Palindrome */
        retBool = isPalindromeWithDeque(raw);
        System.out.println(""
            + "String [" + raw + "] is a isPalindromeWithDeque [" + retBool + "].");
        
        /* Recursion Palindrome */
        retBool = isPalindromeWithRecursion(raw);
        System.out.println(""
            + "String [" + raw + "] is a isPalindromeWithRecursion [" + retBool + "].");
    }
    
    /**
     * This method tests if a string is palindromic.
     * @param raw the string to test
     * @return the conclusion that a string is a palindrome
     */
    public static boolean isPalindrome(String raw) {
        boolean retBool = true;
        if (null == raw || raw.isEmpty()) {
            retBool = false;
        } else {
            int len = raw.length();
            if (1 == len) {
                retBool = true;
            } else {
                char[] seq = new char[len];
                raw.getChars(0, len, seq, 0);
                int offset = (0 == len%2) ? 1 : 2;
                for (int i=0; i<len/2; i++)
                    if (seq[len/2 - 1 - i] != seq[len/2 - 1 + offset + i]) {
                        retBool = false;
                        break;
                    }
            }
        }
        return retBool;
    }
    
    /**
     * This method tests if a string is palindormic.
     * Uses a Deque.
     * @param raw string to test
     * @return the conclusion that a string is a palindrome
     */
    public static boolean isPalindromeWithDeque(String r) {
        boolean retBool = true;
        if (null == r || r.isEmpty()) {
            retBool = false;
        } else if (1 == r.length()) {
            // Do nothing.
        }else {
            Deque<Character> d = new LinkedList<>();
            
            char[] seq = new char[r.length()];
            r.getChars(0, r.length(), seq, 0);
            for (int i=0; i<r.length(); i++)
                d.addLast(seq[i]);
            for (int i=0; i<r.length()/2; i++)
                if (d.removeFirst() != d.removeLast()) {
                    retBool = false;
                    break;
                }
        }
                
        return retBool;
    }
    
    /**
     * This method tests if a string is palindromic.
     * Uses recursion
     * @param raw string to test
     * @return the determination
     */
    public static boolean isPalindromeWithRecursion(String r) {
        if (null == r) return false;
        if (r.isEmpty()) return false;
        
        char[] seq = new char[r.length()];
        r.getChars(0, r.length(), seq, 0);
        return isPalindromeWithRecursionHelper(
                    seq,
                    r.length(),
                    0,
                    r.length());
    }
    
    /**
     * This is a helper method which performs the recursion to check for a palindrome
     * @param seq the char array palindrome
     * @param arrLen the size of the char array
     * @param origin the start of this substring
     * @param len the size of the substring
     */
    private static boolean isPalindromeWithRecursionHelper(
        char[] seq,
        int arrLen,
        int origin,
        int len) {
        if (0 >= arrLen) return false;
        if (1 == arrLen) return true;
        if (0 >= len) return false;
        if (arrLen - arrLen/2 >= len) return true;
        
        return seq[origin] == seq[len-1]
                && isPalindromeWithRecursionHelper(
                    seq,
                    arrLen,
                    origin+1,
                    len-1);     
    }
}
