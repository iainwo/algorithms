import java.util.Arrays;
public class AnagramAuthority {
    public static void main(String[] args) {
        AnagramAuthority.validate(null, null);
        AnagramAuthority.validate(null, "");
        AnagramAuthority.validate("", null);
        AnagramAuthority.validate("", "");
        AnagramAuthority.validate("a", "");
        AnagramAuthority.validate("", "a");
        AnagramAuthority.validate("a", "a");
        AnagramAuthority.validate("abc", "a");
        AnagramAuthority.validate("a", "abc");
        AnagramAuthority.validate("same", "same");
        AnagramAuthority.validate("same", "emas");
        AnagramAuthority.validate("same", "asme");
        AnagramAuthority.validate("same", "asee");
        AnagramAuthority.validate("samesame", "sameemas");
    }
    private final static int CHAR_SET_LEN = 200;
    
    /**
     * This convenience method will print the results of an anagram validation.
     * @param s1 the first anagram
     * @param s2 the second anagram
     */
    public static void validate(String s1, String s2) {
        
        /* Sorting - O(nlogn) */
        boolean isAnagram = isAnagram(s1, s2);
        System.out.println(""
            + "String [" + s1 + "] and [" + s2 + "]"
            + " are anagrams [" + isAnagram + "].");
        
        /* HashMap - O(n) */
        isAnagram = isAnagramWithHashMap(s1, s2);
        System.out.println(""
            + "String [" + s1 + "] and [" + s2 + "]"
            + " are anagrams [" + isAnagram + "].");
    }
    
    /**
     * This method will determine if two anagrams share the same family of anagrams.
     * @param a1 the first anagram
     * @param a2 the second anagram
     * @return the resulting determination
     */
    public static boolean isAnagram(String a1, String a2) {
        boolean retBool = true;
        
        if (null == a1) {
            retBool = false;
        } else if (null == a2) {
            retBool = false;
        } else if (a1.length() != a2.length()) {
            retBool = false;            
        } else {
            char[] seq1 = new char[a1.length()];
            char[] seq2 = new char[a2.length()];
            a1.getChars(0, a1.length(), seq1, 0);
            a2.getChars(0, a2.length(), seq2, 0);

            Arrays.sort(seq1);
            Arrays.sort(seq2);
            
            for (int i=0; i<a1.length(); i++)
                if (seq1[i] != seq2[i]) {
                    retBool = false;
                    break;
                }
        }
            
        return retBool;
    }
    
    /**
     * This method will determine if two anagrams share the same family of anagrams. Uses Hashing.
     * @param a1 the first anagram
     * @param a2 the second anagram
     * @return the resulting determination
     */
    public static boolean isAnagramWithHashMap(String a1, String a2) {
        boolean retBool = true;
        
        if (null == a1) {
            retBool = false;
        } else if (null == a2) {
            retBool = false;
        } else if (a1.length() != a2.length()) {
            retBool = false;
        } else {
            int len = a1.length();
            int[] counts = new int[CHAR_SET_LEN];
            
            char[] firstSeq = new char[len];
            char[] secondSeq = new char[len];
            a1.getChars(0, len, firstSeq, 0);
            a2.getChars(0, len, secondSeq, 0);
            
            for (int i=0; i<len; i++)
                counts[(int) firstSeq[i]]++;
            for (int i=0; i<len; i++)
                counts[(int) secondSeq[i]]--;
            for (int i=0; i<CHAR_SET_LEN; i++)
                if (0 != counts[i]) {
                    retBool = false;
                    break;
                }
        }
        return retBool;
    }
}
