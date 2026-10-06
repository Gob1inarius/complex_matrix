public class Main {
    public static void main(String[] args) throws Exception {
        Complex c1 = new Complex(4, 2);
        Complex c2 = new Complex(1, 1);
        Complex zero = new Complex(0, 0);
        System.out.println(c1.add(c2));
        System.out.println(c1.subtract(c2));
        System.out.println(c1.multiply(c2));
        System.out.println(c1.divide(c2));
        System.out.println(c1.divide(zero));
    }
}
