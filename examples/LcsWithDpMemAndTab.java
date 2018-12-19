import java.lang.StringBuilder;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        LongestCommonSubsequence.find("a", "a");
        LongestCommonSubsequence.find("abc", "aabbcc");
        LongestCommonSubsequence.find("abc", "afffffbgggc");
        LongestCommonSubsequence.find("empty bottle", "nematode knowledge");
        LongestCommonSubsequence.find(
            "abcdefghijklm", "abzcdzefzgzzzzzhizzzzjzkzlzzmzzzzzzzz");
        /*
        LongestCommonSubsequence.find(
            "the lord of the rings", 
            ""
                + "once upon a time, in a little well known place called the");
                + " shire, at the end of quiet road, find an open green door"
                + " and an invitation to the prancing pony.");
        */
    }
    
    /**
     * Test method to display the strings under test and the result.
     * @param a the first string
     * @param b the second string
     * @return the common subsequence
     */
    public static String find(String a, String b) {
        System.out.println("------------ [Finding lcs] --------------------");
        String lcs = lcs(a, b);
        System.out.println(""
            + "Found a " + ((null != lcs) ? lcs.length() : 0) + " char length"
            + " LCS [" + lcs + "]"
            + " for [" + a + "] and [" + b + "].\n");
        return lcs;
    }
    
    /**
     * Resolve the lcs between two strings.
     * @param a the first string
     * @param b the second string
     * @return the lcs in a string
     */
    public static String lcs(String a, String b) {
        int[][] memoizeMap = new int[1+a.length()][1+b.length()];
        for (int i=0; i<1+a.length(); i++)
            for (int j=0; j<1+b.length(); j++)
                memoizeMap[i][j] = -1;
        
        int[][] tabulationMap = new int[1+a.length()][1+b.length()];
        for (int i=0; i<1+a.length(); i++)
            for (int j=0; j<1+b.length(); j++)
                tabulationMap[i][j] = -1;      
                
        lcsMemoize(memoizeMap, a, 0, b, 0);
        lcsTabulation(tabulationMap, a, b);
        printLcsTable(memoizeMap, a, b);
        printLcsTable(tabulationMap, a, b);
        return getLcsString(memoizeMap, a, b);
    }
    /**
     * Use memoization to calculate the LCS
     * @param lcs the heatmap to record culmulative lcs distances
     * @param a the first string
     * @param i the index in string a
     * @param b the second string
     * @param j the index in string b
     */
    private static int lcsMemoize(int[][] lcs, String a, int i, String b, int j) {
        if (a.length() == i || b.length() == j) lcs[i][j] = 0;
        else if (String.valueOf(a.charAt(i)).equals(String.valueOf(b.charAt(j)))) lcs[i][j] = 1 + lcsMemoize(lcs, a, 1+i, b, 1+j);
        else {
            lcs[1+i][j] = lcsMemoize(lcs, a, 1+i, b, j);
            lcs[i][1+j] = lcsMemoize(lcs, a, i, b, 1+j);
            lcs[i][j] = (lcs[1+i][j] >= lcs[i][1+j]) ? lcs[1+i][j] : lcs[i][1+j];
        }
        return lcs[i][j];
    }
    /**
     * Use tabulation to calculate the LCS
     * @param lcs the heatmap to record the culmulative lcs distances
     * @param a the first string
     * @param i the index in string a
     * @param b the second string
     * @param j the index in string b
     */
    private static int lcsTabulation(int[][] lcs, String a, String b) {
        for (int k=a.length(); k>=0; k--)
            for (int h=b.length(); h>=0; h--) {
                if (a.length() == k || b.length() == h) {
                    lcs[k][h] = 0;
                } else if (
                    String.valueOf(a.charAt(k)).equals(
                        String.valueOf(b.charAt(h)))) {
                    lcs[k][h] = 1 + lcs[1+k][1+h];
                } else {
                    lcs[k][h] = Math.max(lcs[1+k][h], lcs[k][1+h]);
                }
            }
        return lcs[0][0];
    }
    
    /**
     * Resolve the longest sequence in the cumulative LCS table
     * @param lcs sequence table recording longest paths
     * @return the first longest string
     */
    private static String getLcsString(int[][] lcs, String a, String b) {
        StringBuilder sb = new StringBuilder();
        
        int maxi = a.length();
        int maxj = b.length();
        
        int i = 0;
        int j = 0;
        while (i < maxi && j < maxj) {
            if (String.valueOf(a.charAt(i)).equals(String.valueOf(b.charAt(j)))) {
                sb.append(a.charAt(i));
                i++;
                j++;
            } else if (lcs[1+i][j] >= lcs[i][1+j]) {
                i++;
            } else {
                j++;
            }
        }
        
        return sb.toString();
    }
    private static void printLcsTable(int[][] heatmap, String a, String b) {
        for (int i=0; i<heatmap.length; i++) {

            for (int j=0; j<heatmap[0].length; j++) {
                if (0 == i && 0 == j) {
                    System.out.print("  ");
                    for (int k=0; k<b.length(); k++) {
                        System.out.print(" " + b.charAt(k) + " ");
                    }
                    System.out.print("\n");
                }
                if (0 == j) {
                    System.out.print(((a.length() > i) ? a.charAt(i) : " ") + " ");
                }
                System.out.print(((0 <= heatmap[i][j]) ? "+" + heatmap[i][j] : heatmap[i][j]) + " ");
            }
            System.out.print("\n");
        }
    }
}
