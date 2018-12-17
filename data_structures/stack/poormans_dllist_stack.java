
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
        try {
            System.out.println("Popping empty stack [" + stack.pop() + "]");
        } catch (StackException e) {
          System.out.println("Triggered StackException: " + e);  
        }
    }
    
    StackImpl<ContentType> stack = new LLStack<>();
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
class LLStack<ContentType> implements StackImpl<ContentType> {
    StackFrame<ContentType> stack = null;
    int size = 0;
    
    @Override public void grow(int currSize, int newSize) {
        // Do nothing
    }
    @Override public void push(ContentType t) {
        StackFrame<ContentType> sf = new StackFrame<>(t);
        
        if (0 == getSize()) {
            sf.setParent(sf);
            sf.setChild(sf);
            
            stack = sf;
        } else if (1 == getSize()) {
            StackFrame root = stack;
            
            root.setChild(sf);
            root.setParent(sf);
            
            sf.setChild(root);
            sf.setParent(root);
        } else if (2 <= getSize()) {
            StackFrame root = stack;
            StackFrame tail = stack.getParent();
            
            tail.setChild(sf);
            root.setParent(sf);
            
            sf.setChild(root);
            sf.setParent(tail);
        } else {
            System.out.println("WARN: Impossible Push Case. Shouldn't get here.");
        }
        
        size++;
    }
    @Override public ContentType pop() {
        ContentType retVal = peek();
        
        if (1 == getSize())
            stack = null;
        else if (2 == getSize()) {
            StackFrame oldRoot = stack;
            StackFrame newRoot = oldRoot.getChild();
            
            // De-reference for garbage-collection
            oldRoot.setParent(null);
            oldRoot.setChild(null);
            
            newRoot.setParent(newRoot);
            newRoot.setChild(newRoot);
            
            stack = newRoot;
        } else if (3 <= getSize()) {
            StackFrame oldRoot = stack;
            StackFrame newRoot = oldRoot.getChild();
            
            newRoot.setParent(oldRoot.getParent());
            oldRoot.getParent().setChild(newRoot);
            
            // De-reference GC.
            oldRoot.setChild(null);
            oldRoot.setParent(null);
            
            stack = newRoot;
        } else {
            System.out.println("WARN: Impossible Pop Case. Shouldn't get here.");
        }
        
        size--;
        
        return retVal;
    }
    @Override public ContentType peek() {
        return stack.getVal();
    }
    @Override public int getOptimalGrowthSize(int i) {
        return 1;
    }
    @Override public int getOptimalReductSize(int i) {
        return getOptimalGrowthSize(i);
    }
    @Override public int getSize() {
        return size;
    }
    
    private class StackFrame<T> {
        StackFrame parent = null;
        StackFrame child = null;
        T val = null;
        
        private StackFrame() {/* Do nothing. Uncallable. */}
        StackFrame(T val) {
            this();
            this.val = val;
        }
        
        T getVal() { return val; }
        StackFrame getChild() { return child; }
        StackFrame getParent() { return parent; }
        void setChild(StackFrame c) { this.child = c; }
        void setParent(StackFrame p) { this.parent = p; }
    }
}
