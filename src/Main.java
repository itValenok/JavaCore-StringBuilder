public class Main {
    public static void main(String[] args) {
        StringBuilderCustom sc = new StringBuilderCustom(25);
        sc.append("Hello");
        System.out.println(sc.snapShot());
        sc.append(" World!");
        System.out.println(sc.snapShot());
        sc.undo();
        System.out.println(sc.snapShot());
        sc.append(" World!");
        sc.append(" none");
        sc.append("yes");
        System.out.println(sc.snapshot.getLast());
    }
}
