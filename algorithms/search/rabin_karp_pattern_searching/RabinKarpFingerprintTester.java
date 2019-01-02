public class BitDump {
    public static void main(String[] args) {
        int h = 1;
        
        System.out.println(Character.getNumericValue('a'));
        System.out.println("Calculating 4-th polynomial...");
        printBits(genPolHash(5, 101));
        printBits(genPol('a', 4, 101));
        System.out.println("Creating rolling hash of 'abcde'");
        printBits(genHash("abcde"));
        printBits((256*(genHash("abcde") - genPol('a', 4, 101)))%101 + genHash("f"));
        System.out.println("Creating rolling hash of 'bcdef'");
        printBits(genHash("bcdef"));
    }
    private static String getBits(int i) {return Integer.toBinaryString(i);}
    private static void printBits(int i) {
        System.out.println(Integer.toBinaryString(i));
    }
    
    private static int genHash(String s) {
        int hash = 0;
        
        for (int i=0; i<s.length(); i++) {
            System.out.print("preshift[" + hash + "]: ");
            printBits(hash);
            System.out.print("shift[" + hash*256 + "]: ");
            printBits(hash*256);
            System.out.print("add[" + (hash*256 + Character.getNumericValue(s.charAt(i))) + "](" + s.charAt(i) + "): ");
            printBits(hash*256 + Character.getNumericValue(s.charAt(i)));
            hash = (hash*256 
                + Character.getNumericValue(
                    s.charAt(i))) % 101;
            System.out.print("postappend[" + hash + "]: ");
            printBits(hash);
        }
        
        return hash;
    }
    private static int genPolHash( int i, int p) {
        int h = 1;
        for (int j=0; j<i; j++) {
            h = (h*256) % 101;
        }
        return h;
    }
    private static int genPol(char c, int i, int p) {
        int h = 1;
        for (int j=0; j<i; j++) {
            h = (h*256) % 101;
            //printBits(h);
        }
        h = (h*Character.getNumericValue(c)) % p;
        return h;
    }
}