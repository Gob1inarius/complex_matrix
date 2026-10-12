public class ComplexMatrix {
    private final Complex[][] data;

    public ComplexMatrix(Complex[][] data) {
        int cols;

        if (data == null) {
            throw new IllegalArgumentException("Матрица равна null");
        }
        if (data.length == 0) {
            throw new IllegalArgumentException("Матрица не может быть пустой");
        }
        if (data[0] == null) {
            throw new IllegalArgumentException("Строка 0 равна null");
        } else {
            cols = data[0].length;
            if (cols == 0) {
                throw new IllegalArgumentException("Строки матрицы не могут быть пустыми");
            }
        }

        Complex[][] copy = new Complex[data.length][cols];

        for (int i = 0; i < data.length; ++i) {
            if (data[i] != null) {
                if (data[i].length == cols) {
                    for (int j = 0; j < data[i].length; ++j) {
                        if (data[i][j] != null) {
                            copy[i][j] = data[i][j];
                        } else {
                            throw new IllegalArgumentException("Элемент [" + i + "][" + j + "] равен null");
                        }
                    }
                } else {
                    throw new IllegalArgumentException(
                            "Строка " + i + " имеет длину " + data[i].length + ", ожидалось " + cols);
                }
            } else {
                throw new IllegalArgumentException("Строка " + i + " равна null");
            }
        }
        this.data = copy;
    }

    public int getRows() {
        return data.length;
    }

    public int getCols() {
        return data[0].length;
    }

    public Complex get(int i, int j) {
        return data[i][j];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; ++i) {
            sb.append("[ ");
            for (int j = 0; j < data[0].length; ++j) {
                if (j > 0) {
                    sb.append(", ").append(data[i][j]);
                } else {
                    sb.append(data[i][j]);
                }
            }
            sb.append(" ]\n");
        }
        return sb.toString();
    }

    public ComplexMatrix transpose() {
        Complex[][] transpMatrix = new Complex[data[0].length][data.length];
        for (int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[0].length; ++j) {
                transpMatrix[j][i] = data[i][j];
            }
        }
        return new ComplexMatrix(transpMatrix);
    }

    public ComplexMatrix add(ComplexMatrix other) throws MatrixException {
        if (data.length != other.getRows() || data[0].length != other.getCols()) {
            throw new MatrixException("Нельзя сложить матрицы " + data.length + "x" + data[0].length + " и " +
                    other.getRows() + "x" + other.getCols());
        }
        Complex[][] result = new Complex[data.length][data[0].length];
        for (int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[0].length; ++j) {
                result[i][j] = data[i][j].add(other.data[i][j]);
            }
        }
        return new ComplexMatrix(result);
    }

    public ComplexMatrix multiply(ComplexMatrix other) throws MatrixException {
        if (data[0].length != other.getRows()) {
            throw new MatrixException("Нельзя умножить матрицы " + data.length + "x" + data[0].length + " и " +
                    other.getRows() + "x" + other.getCols());
        }
        Complex[][] result = new Complex[data.length][other.getCols()];
        for (int i = 0; i < data.length; ++i) {
            for (int j = 0; j < other.getCols(); ++j) {
                Complex sum = new Complex(0, 0);
                for (int k = 0; k < data[0].length; ++k) {
                    sum = sum.add(data[i][k].multiply(other.data[k][j]));
                }
                result[i][j] = sum;
            }
        }
        return new ComplexMatrix(result);
    }

    public Complex determinant() throws MatrixException {
        if (data.length != data[0].length) {
            throw new MatrixException("Определитель может быть высчитан только для квадратных матриц. Текущий размер "
                    + data.length + "x" + data[0].length);
        }
        int n = data.length;
        Complex[][] w = new Complex[n][];
        for (int i = 0; i < n; ++i) {
            w[i] = data[i].clone();
        }
        Complex det = new Complex(1, 0);
        for (int c = 0; c < n; ++c) {
            double maxAbs = w[c][c].abs();
            int index = c;
            for (int r = c; r < n; ++r) {
                if (w[r][c].abs() > maxAbs) {
                    maxAbs = w[r][c].abs();
                    index = r;
                }
            }
            if (maxAbs < Complex.EPS) {
                return new Complex(0, 0);
            }
            if (index != c) {
                Complex[] tmp = w[c];
                w[c] = w[index];
                w[index] = tmp;
                det = det.multiply(new Complex(-1, 0));
            }
            det = det.multiply(w[c][c]);
            for (int r = c + 1; r < n; ++r) {
                Complex multiplier = w[r][c].divide(w[c][c]);
                for (int k = c; k < n; ++k) {
                    w[r][k] = w[r][k].subtract(w[c][k].multiply(multiplier));
                }
            }
        }
        return det;
    }

    public ComplexMatrix inverse() throws MatrixException {
        if (data.length != data[0].length) {
            throw new MatrixException("Матрица не квадратная");
        }
        int n = data.length;
        Complex[][] w = new Complex[n][n * 2];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                w[i][j] = data[i][j];
                if (i == j) {
                    w[i][j + n] = new Complex(1, 0);
                } else {
                    w[i][j + n] = new Complex(0, 0);
                }
            }
        }
        for (int c = 0; c < n; ++c) {
            double maxAbs = w[c][c].abs();
            int index = c;
            for (int r = c; r < n; ++r) {
                if (w[r][c].abs() > maxAbs) {
                    maxAbs = w[r][c].abs();
                    index = r;
                }
            }
            if (maxAbs < Complex.EPS) {
                throw new MatrixException("Матрица вырождена");
            }
            if (index != c) {
                Complex[] tmp = w[c];
                w[c] = w[index];
                w[index] = tmp;
            }
            Complex sup = w[c][c];
            for (int k = 0; k < n * 2; ++k) {
                w[c][k] = w[c][k].divide(sup);
            }
            for (int r = 0; r < n; ++r) {
                if (r == c) {
                    continue;
                }
                Complex multiplier = w[r][c];
                for (int k = 0; k < n * 2; ++k) {
                    w[r][k] = w[r][k].subtract(w[c][k].multiply(multiplier));
                }
            }
        }
        Complex[][] result = new Complex[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                result[i][j] = w[i][j + n];
            }
        }
        return new ComplexMatrix(result);
    }

    public ComplexMatrix divide(ComplexMatrix other) throws MatrixException {
        if (other.getRows() != other.getCols()) {
            throw new MatrixException("Делитель должен быть квадратной матрицей, а он "
                    + other.getRows() + "x" + other.getCols());
        }
        if (data[0].length != other.getRows()) {
            throw new MatrixException("Нельзя разделить матрицы " + data.length + "x" + data[0].length + " и "
                    + other.getRows() + "x" + other.getCols());
        }
        return this.multiply(other.inverse());
    }
}