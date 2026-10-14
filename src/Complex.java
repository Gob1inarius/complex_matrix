import java.util.Locale;

public class Complex {
    private final double re;
    private final double im;
    public static final double EPS = 1e-12;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public Complex add(Complex other) {
        return new Complex(this.re + other.re, this.im + other.im);
    }

    public Complex subtract(Complex other) {
        return new Complex(this.re - other.re, this.im - other.im);
    }

    public Complex multiply(Complex other) {
        return new Complex(this.re * other.re - this.im * other.im, this.re * other.im + other.re * this.im);
    }

    public Complex divide(Complex other) {
        double denom = other.re * other.re + other.im * other.im;
        if (other.abs() < EPS) {
            throw new ArithmeticException("Деление на ноль");
        }
        return new Complex((this.re * other.re + this.im * other.im) / denom,
                (this.im * other.re - this.re * other.im) / denom);
    }

    @Override
    public String toString() {
        double x = clean(re);
        double y = clean(im);
        if (y >= 0) {
            return fmt(x) + " + " + fmt(y) + "i";
        } else {
            return fmt(x) + " - " + fmt(-y) + "i";
        }
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    public double abs() {
        return Math.hypot(re, im);
    }

    private static double clean(double v) {
        return Math.abs(v) < 0.00005 ? 0.0 : v;
    }

    private static String fmt(double v) {
        return String.format(Locale.US, "%.4f", v);
    }
}
