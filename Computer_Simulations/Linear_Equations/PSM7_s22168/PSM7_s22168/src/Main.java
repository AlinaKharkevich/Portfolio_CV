import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String res;

        System.out.println("Write the Plate size with 'x' like '40x40': ");
        res = scanner.nextLine().trim();
        if (!Pattern.compile("^\\d+x\\d+$").matcher(res).matches()) {
            System.out.println("Size is wrong");
            System.exit(1);
        }

        String[] dec = res.split("X");
        double[][] arrey = new double[Integer.parseInt(dec[1]) + 1][Integer.parseInt(dec[0]) + 1];

        System.out.println("Write FOUR temperatures like '50 100 150 200'");
        res = scanner.nextLine();
        if (!Pattern.compile("^\\d+\\s\\d+\\s\\d+\\s\\d+$").matcher(res).matches()) {
            System.out.println("Size is wrong");
            System.exit(1);
        }
        String[] tempr = res.split(" ");

        //rows,columns
        for (int j = 0; j < 1; j++) {
            for (int i = 1; i < arrey[j].length - 1; i++) {
                arrey[j][i] = Double.parseDouble(tempr[0]);
            }
        }
        for (int j = arrey.length - 1; j > arrey.length - 2; j--) {
            for (int i = 1; i < arrey[j].length - 1; i++) {
                arrey[j][i] = Double.parseDouble(tempr[1]);
            }
        }

        for (int j = 1; j < arrey.length - 1; j++) {
            for (int i = 0; i < 1; i++) {
                arrey[j][i] = Double.parseDouble(tempr[2]);
            }
        }
        for (int j = 1; j < arrey.length - 1; j++) {
            for (int i = arrey[j].length - 1; i > arrey[j].length - 2; i--) {
                arrey[j][i] = Double.parseDouble(tempr[3]);
            }
        }

        ArrayList<Double> bVal = new ArrayList<>();

        for (int i = arrey.length - 2; i > 0; i--) {
            for (int j = 1; j < arrey[i].length - 1; j++) {
                double t = arrey[i - 1][j] - 4 * arrey[i][j] + arrey[i + 1][j] + arrey[i][j - 1] + arrey[i][j + 1];
                bVal.add(0 - t);
            }
        }

        double[] b = new double[bVal.size()];
        for (int i = 0; i < bVal.size(); i++) {
            b[i] = bVal.get(i);
        }

        ArrayList<ArrayList<Double>> fin = new ArrayList<>();
        for (int i = arrey.length - 2; i > 0; i--) {
            for (int j = 1; j < arrey[i].length - 1; j++) {
                ArrayList<Double> arr = new ArrayList<>();

                if (arrey[i - 1][j] == 0) arrey[i - 1][j] = 1;
                if (arrey[i + 1][j] == 0) arrey[i + 1][j] = 1;
                if (arrey[i][j - 1] == 0) arrey[i][j - 1] = 1;
                if (arrey[i][j + 1] == 0) arrey[i][j + 1] = 1;
                arrey[i][j] = -4;

                for (int c = arrey.length - 2; c > 0; c--) {
                    for (int d = 1; d < arrey[c].length - 1; d++) {
                        arr.add(arrey[c][d]);
                        arrey[c][d] = 0;
                    }
                }
                fin.add(arr);
            }
        }

        double[][] ei = new double[fin.size()][];

        for (int i = 0; i < fin.size(); i++) {
            ArrayList<Double> row = fin.get(i);
            double[] copy = new double[row.size()];
            for (int j = 0; j < row.size(); j++) {
                copy[j] = row.get(j);
            }
            ei[i] = copy;
        }

        double[][] eiOpposite = Opposite(ei);
        System.out.println();
        double[] d = Multiply(b, eiOpposite);
        int k = 0;
        for (int i = arrey.length - 2; i > 0; i--) {
            for (int j = 1; j < arrey[i].length - 1; j++) {
                arrey[i][j] = d[k++];
            }
        }
        try {
            Write(arrey);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static double[] Multiply(double[] matrix1, double[][] matrix2) {
        double[] mul = new double[matrix1.length];

        for (int i = 0; i < mul.length; i++) {
            for (int j = 0; j < matrix2.length; j++) {
                mul[i] += (matrix1[j] * matrix2[j][i]);
            }
        }
        return mul;
    }

    private static double[][] Opposite(double a[][]) {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;
        GaussianEl(a, index);
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i] * b[index[i]][k];
        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

    private static void GaussianEl(double a[][], int index[]) {
        int n = index.length;
        double e[] = new double[n];

        for (int i = 0; i < n; ++i)
            index[i] = i;

        for (int i = 0; i < n; ++i) {
            double e1 = 0;
            for (int j = 0; j < n; ++j) {
                double e0 = Math.abs(a[i][j]);
                if (e0 > e1) e1 = e0;
            }
            e[i] = e1;
        }

        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= e[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            int m = index[j];
            index[j] = index[k];
            index[k] = m;
            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];

                a[index[i]][j] = pj;

                for (int l = j + 1; l < n; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }

    private static void Write(double[][] arr) throws FileNotFoundException {
        String file = "result.csv";
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        PrintWriter pw = new PrintWriter(fileOutputStream);

        for (double[] arr1 : arr) {
            StringBuilder content = new StringBuilder();
            for (double arr2 : arr1) {
                content.append(arr2).append(",");
            }
            pw.println(content.toString());
        }
        System.out.println("The result is inside: " + file);
        pw.close();
    }
}
