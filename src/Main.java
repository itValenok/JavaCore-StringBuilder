public class Main {
    public static void main(String[] args) {
        StringBuilderCustom sc = new StringBuilderCustom(25);
        sc.append("Helloo");
        System.out.println(sc.snapShot());
        sc.append(" World!");
        System.out.println(sc.snapShot());
        sc.undo();
        System.out.println(sc.snapShot());
    }
}
