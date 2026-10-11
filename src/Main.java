public class Main {
    public static void main(String[] args) throws MatrixException {
        Complex[][] a = {
                { new Complex(1, 2), new Complex(3, 4), new Complex(-1, -2) },
                { new Complex(5, 6), new Complex(7, 8), new Complex(2, -1) }
        };
        ComplexMatrix m = new ComplexMatrix(a);
        // System.out.println(m);
        System.out.println(m.multiply(m.transpose()));
        // System.out.println(m.add(m));
        try {
            System.out.println(m.multiply(m));
        } catch (MatrixException e) {
            System.out.println(e.getMessage());
        }
    }
}
