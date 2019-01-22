import java.lang.Math;
import java.util.Set;
import java.util.HashSet;

class Solution {
    
    // abc -> a
    // abc -> cabc
    // a -> abc
    // ab -> bab
    
    public int repeatedStringMatch(String A, String B) {
        int cnt = 1;
        StringBuilder sb = new StringBuilder(A);
        while (sb.length() < B.length()) {
            sb.append(A);
            cnt++;
        }
        if (isSubStr(sb.toString(), B)) return cnt;
        if (isSubStr(sb.append(A).toString(), B)) return cnt+1;
        return -1;
    }
    private static boolean isSubStr(String a, String b) {
        int[] pTable = new int[b.length() + 1 + a.length()];
        char[] patt = (b + "#" + a).toCharArray();
        
        // populate jump table
        for (int i=1; i<patt.length; i++) {
            int p = pTable[i-1];
            
            while (0 < p && patt[i] != patt[p])
                p = pTable[p-1];
            
            if (patt[i] == patt[p])
                p++;
          
            pTable[i] = p;
        }
    
        for (int i=b.length()+1; i<patt.length; i++)
            if (b.length() <= pTable[i])
                return true;
        return false;
    }
}