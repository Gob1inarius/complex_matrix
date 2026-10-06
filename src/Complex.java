public class Complex {
    private final double re;
    private final double im;
    private final double EPS = 1e-12;

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
        if (Math.abs(denom) < EPS) {
            throw new ArithmeticException("Деление на ноль");
        }
        return new Complex((this.re * other.re + this.im * other.im) / denom,
                (this.im * other.re - this.re * other.im) / denom);
    }

    @Override
    public String toString() {
        if (im >= 0) {
            return re + " + " + im + "i";
        } else {
            return re + " - " + Math.abs(im) + "i";
        }
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }
}
