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
}