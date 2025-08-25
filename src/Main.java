import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Integer[] nums = {1, 2, 3, 4, 5};

        System.out.println(Arrays.toString(Main.filter(nums, n -> n * n)));
        System.out.println(Arrays.toString(Main.filter(nums, n -> n + 1)));
        String[] words = {"hello", "wordl", "java"};
        System.out.println(Arrays.toString(Main.filter(words, String::toUpperCase)));
        String[] words2 = {"kafka", "java", "kafka", "db", "java", "kafka", null, null};
        Map<String, Long> counts = count(words2);
        System.out.println(counts);

    }

    //filter?
    public static <T> T[] filter(T[] array, filter<T> filter) {
        T[] result = array.clone();
        for (int i = 0; i < result.length; i++) {
            result[i] = filter.apply(result[i]);
        }
        return result;
    }

    public static <K> Map<K, Long> count (K[] array) {
        Map<K, Long> result = new HashMap<>();
        for (K i: array) {
            result.merge(i, 1L, Long::sum);
        }
        return result;
    }
}
