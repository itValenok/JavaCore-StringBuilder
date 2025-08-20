import java.util.ArrayDeque;
import java.util.Deque;

public class StringBuilderCustom {
    private final char[] value;
    private int index = 0;
    private final Deque<String> snapshot = new ArrayDeque<>();
    public StringBuilderCustom(int capacity) {
        this.value = new char[capacity];
    }

    public String snapShot() {
        return snapshot.getLast();
    }

    public void append(String s) {
        s.getChars(0, s.length() - 1, value, index);
        index += s.length() - 1;
        snapshot.addLast(new String(value).substring(0, index));
    }

    public void undo() {
        snapshot.removeLast();
    }
}
