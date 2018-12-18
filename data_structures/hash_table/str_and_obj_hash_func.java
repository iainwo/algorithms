public class StringHasher {
    public static void main(String[] args) {
        System.out.println(StringHasher.hash("abc"));
        System.out.println(StringHasher.hash("def"));
        System.out.println(StringHasher.hash("fgc"));
        System.out.println(StringHasher.hash(" "));
        
        System.out.println(StringHasher.objHash("new", 1, 2));
        System.out.println(StringHasher.objHash("gew", 3, 4));
        System.out.println(StringHasher.objHash("vue", 4, 4));
        System.out.println(StringHasher.objHash("vue", 3, 4));
    }
    
    private static int hash(String s) {
        int hash = 0;
        for (int i = 0; i < s.length(); i++)
            hash = (31*hash + s.charAt(i)) % (1 << 31);
        
        return hash;
    }
    private static int objHash(String s, int a, int b) {
        long z1 = 11111L;
        long z2 = 22222L;
        long z3 = 33333L;
        long zz = 312456123L;
        
        long sHash = StringHasher.hash(s) & ((1L<<32)-1);
        long aHash = a & ((1L<<32)-1);
        long bHash = b & ((1L<<32)-1);
        
        int hash = (int) ((zz * (
                    (z1 * sHash) +
                    (z2 * aHash) +
                    (z3 * bHash))) >>> 32);
        return hash;
    }
}
