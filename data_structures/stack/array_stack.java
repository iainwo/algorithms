
/**
 * Optimal resizing came from this website's proof,
 * https://opendatastructures.org/ods-java/2_1_ArrayStack_Fast_Stack_O.html
 * 
 * The idea is that the total number of push/pop operations incur a sum of
 * resize operations to grow and shrink the memory to a sensible size. The
 * proof illustrates that this sensibility can be maintained in an inexpensive
 * way; such that, the total number of resize calls and the summation of their
 * computational costs is less than the computational overhead of pushing/popping.
 * 
 * O(m) >= E(n[i]) for all resize calls
 * i.e O(m) >= the aggregate number of resize computations
 * 
 * Where O(m) is the total number of push/pops.
 * 
 * The proof is based on the invariant that resize is only called after
 * `n/2 - 1` push/pop operations, and since there are only - `n` elements after
 * the push/pop ops. the cost of copying those elements is the summation of
 * the possible resize occurences and their respectives sizes at that point in
 * time; ultimately, that summation is less than the total push/pop calls it
 * would take to trigger those resizes.
 */
public class HackyStack<ContentType> {
    
    public static void main(String[] args) {
        HackyStack<String> stack = new HackyStack<>();
        System.out.println("Peeking stack [" + stack.peek() + "]");
        
        try {
            System.out.println("Popping empty stack [" + stack.pop() + "]");
        } catch (StackException e) {
          System.out.println("Triggered StackException: " + e);  
        }
        
        for (int i = 0; i < 100; i++) {
            System.out.println(""
                + "Pushing [" + i + "] on to the stack."
                + " Stack has size [" + stack.getSize() + "].");
            stack.push(String.valueOf(i));
        }
        System.out.println("Peeking stack [" + stack.peek() + "]");
        for (int i = 0; i < 100; i++) {
            System.out.println(""
                + "Pop #" + i + "."
                + " Popped value [" + stack.pop() + "]"
                + " Stack has size [" + stack.getSize() + "].");
        }
        System.out.println("Peeking stack [" + stack.peek() + "]");

    }
    
    StackImpl<ContentType> stack = new ArrayStack<>();
    int entryCount = 0;
    
    public void push(ContentType t) {
        if (!hasCapacity()) {
            int currSize = getSize();
            int optimalSize = getStack().getOptimalGrowthSize(currSize);

            getStack().grow(currSize, optimalSize);
        }
        
        getStack().push(t);
        entryCount++;
    }
    public ContentType pop() {
        ContentType retVal = null;
        if (0 >= getEntryCount())
            throw new UnderflowException();
        
        retVal = getStack().pop();
        entryCount--;
        
        if (hasExtraCapacity()) {
            int currSize = getEntryCount();
            int optimalSize = getStack().getOptimalReductSize(currSize);
            
            getStack().grow(currSize, optimalSize);
        }
        
        return retVal;
    }
    public ContentType peek() {
        ContentType retEntry = null;
        if (0 < getEntryCount())
            retEntry = getStack().peek();
        return retEntry;
    }
    public boolean isEmpty() {
        return 0 == getSize();
    }
    
    private boolean hasCapacity() {
        boolean retBool = false;
        
        if (null != getStack() && getEntryCount() < getSize())
            retBool = true;
            
        return retBool;
    }
    private boolean hasExtraCapacity() {
        boolean retBool = false;
        
        if (null != getStack() && 3*getEntryCount() <= getSize())
            retBool = true;
            
        return retBool;
    }
    private int getSize() { return getStack().getSize(); }
    private StackImpl<ContentType> getStack() { return stack; }
    private int getEntryCount() { return entryCount; }
    
    
}
class StackException extends RuntimeException {}
class OverflowException extends StackException {}
class UnderflowException extends StackException {}

interface StackImpl<ContentType> {
        void push(ContentType t);
        void grow(int currSize, int newSize);
        ContentType pop();
        ContentType peek();
        int getOptimalGrowthSize(int currSize);
        int getOptimalReductSize(int currSize);
        int getSize();
    }
class ArrayStack<ContentType> implements StackImpl<ContentType> {
    ContentType[] stack = null;
    int currIdx = 0;
    
    @Override public void grow(int currSize, int newSize) {
        ContentType[] tmp = (ContentType[])new Object[newSize];
        
        if (0 < currSize)
            System.arraycopy(stack, 0, tmp, 0, currSize);
            
        stack = tmp;
    }
    @Override public void push(ContentType t) {
        stack[currIdx++] = t;
    }
    @Override public ContentType pop() {
        ContentType retVal = peek();
        stack[--currIdx] = null;
        return retVal;
    }
    @Override public ContentType peek() {
        return stack[currIdx-1];
    }
    @Override public int getOptimalGrowthSize(int i) {
        int retSize = 1;
        
        if (0 != i)
            retSize = 2*i;
            
        return retSize;
    }
    @Override public int getOptimalReductSize(int i) {
        return getOptimalGrowthSize(i);
    }
    @Override public int getSize() {
        int retSize = 0;
        
        if (null != stack)
            retSize = stack.length;
        
        return retSize;
    }
}
