import java.io.*; 
import java.util.*; 
public class Sys {

    private Double[][] coef;
    private Double[][] sols;
    private Double[][] rowReduction;
    private int n;

    /*
    public Sys (double[][] coef, double[][] sols) {
        this.coef = coef;
        this.sols = sols;
        n = coef.length;
        rowReduction = new double[n][n+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rowReduction[i][j] = coef[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            rowReduction[i][n] = sols[i][0];
        }
    }
    */
    
    public Sys (Equation[] e, int n) {
        this.n = n;
        double[][] coef = new double[n][n];
        double[][] sols = new double[1][n];
        for (int i = 0; i < n; i++) {
            //System.out.println(Arrays.toString(e[i].getCoefs()));
            //System.out.println(e[i].getSol());
            coef[i] = e[i].getCoefs();
            sols[0][i] = e[i].getSol();
        }

        rowReduction = new Double[n][n+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rowReduction[i][j] = coef[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            //rowReduction[i][n] = sols[i][0];
            rowReduction[i][n] = sols[0][i];
        }
        //System.out.println(Arrays.toString(rowReduction[0]) + " " + Arrays.toString(rowReduction[1]) + " " + Arrays.toString(rowReduction[2]));
    }
    
    public Double[][] getRR() {
        return rowReduction;
    }

    public double[] solver () {
        Double[][] rr = rowReduce(); 
        double[] varValues = new double[n];
        for (int i = 0; i < n; i++) {
            varValues[i] = rowReduction[i][n];
        }
        return varValues;
    }
    
    public Double[][] rowReduce () {
        double[] colVals = new double[n];
        double[] tempVals = new double[n+1];
        double scaleF;
        double tempVal;
        int inc;
        for (int i = 0; i < n; i++) {
            // scale down first row 
            scaleF = rowReduction[i][i];
            inc = 1;
            while (scaleF == 0) {
                for (int j = 0; j < n+1; j++) {
                    tempVals[j] = rowReduction[i][j];
                }
                for (int j = 0; j < n+1; j++) {
                    rowReduction[i][j] = rowReduction[i+inc][j];
                }
                for (int j = 0; j < n+1; j++) {
                    rowReduction[i+inc][j] = tempVals[j];
                }
                scaleF = rowReduction[i][i];
                inc += 1;
            }
            for (int j = i; j < n+1; j++) {
                rowReduction[i][j] /= scaleF;
            }
            // for other rows
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    tempVal = rowReduction[j][i];
                    for (int p = 0; p < n+1; p++) {
                        // scale original
                        tempVals[p] = rowReduction[i][p] * tempVal;
                        // subtract and set difference equal to the original column
                        rowReduction[j][p] -= tempVals[p];
                    }
                }
            }
            //System.out.println(Arrays.toString(rowReduction[0]) + " " + Arrays.toString(rowReduction[1]) + Arrays.toString(rowReduction[2]));
        }
        return rowReduction;
    }
}