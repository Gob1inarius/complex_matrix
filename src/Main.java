public class Main {
    public static void main(String[] args) throws Exception {
        Complex[][] a = {
                { new Complex(1, 2), new Complex(3, 4), new Complex(-1, -2) },
                { new Complex(5, 6), new Complex(7, 8), new Complex(2, -1) }
        };
        ComplexMatrix m = new ComplexMatrix(a);
        System.out.println(m);
        System.out.println(m.getRows() + " x " + m.getCols());

        Complex[][] j = {
                { new Complex(1, 0), new Complex(2, 0) },
                { new Complex(3, 0) }
        };
        try {
            new ComplexMatrix(j);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            new ComplexMatrix(null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        Complex[][] b = { { new Complex(1, 0), null } };
        try {
            new ComplexMatrix(b);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
