import java.util.ArrayDeque;
import java.util.Deque;

public class StringBuilderCustom {
    private final char[] value;
    private int index = 0;
    public Deque<String> snapshot = new ArrayDeque<>();
    public StringBuilderCustom(int capacity) {
        this.value = new char[capacity];
    }

    public String snapShot() {
        return snapshot.getLast();
    }

    public void append(String s) {
        s.getChars(0, s.length(), value, index);
        index += s.length();
        snapshot.addLast(new String(value).substring(0, index + 1));
        if (snapshot.size() > 20) {
            snapshot.removeFirst();
        }
    }

    public void undo() {
        snapshot.removeLast();
        java.util.Arrays.fill(value, '\0');
        index = 0;
        snapshot.getLast().getChars(0, snapshot.getLast().length(), value, index);
        index = snapshot.getLast().length();

    }
}
