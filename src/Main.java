public class Main {
    public static void main(String[] args) throws MatrixException {
        Complex[][] a = {
                { new Complex(2, 0), new Complex(2, 0), new Complex(-1, -2) },
                { new Complex(3, 0), new Complex(4, -1), new Complex(2, -1) },
                { new Complex(1, 1), new Complex(2, 0), new Complex(-1, -2) }
        };
        ComplexMatrix m = new ComplexMatrix(a);
        // System.out.println(m);
        // System.out.println(m.multiply(m.transpose()));
        // System.out.println(m.add(m));
        try {
            System.out.println(m.inverse());
            System.out.println(m);
        } catch (MatrixException e) {
            System.out.println(e.getMessage());
        }
    }
}
