import java.io.*; 
import java.util.*; 

public class SysDriver {
    public static void main(String [] args) {
        
        System.out.println(" | LINEAR SYSTEM OF EQUATION SOLVER | ");
        System.out.println(" PRECONDITION: SYSTEM MUST BE SOLVABLE ");
        Scanner scan = new Scanner(System.in);
        System.out.println("How many variables/equations do you have?");
        int n = scan.nextInt();

        /*
        double[][] testC = new double[n][n];
        double[][] testS = new double[n][1];

        System.out.println("Enter equations in the form ax + by + cz + ... = C");
        for (int i = 1; i <= n; i++) {
            System.out.println("Equation " + i + ": ");
            for (int j = 1; j <= n; j++) {
                System.out.println("Variable " + j + " coefficient: ");
                testC[i-1][j-1] = scan.nextDouble();
            }
            System.out.println("Constant: ");
            testS[i-1][0] = scan.nextDouble();
        }
        */

        
        double[] tempCoef = new double[n];
        double tempSol;
        Equation[] equations = new Equation[n];
        Equation eq;
        System.out.println("Enter equations in the form ax + by + cz + ... = C");
        for (int i = 1; i <= n; i++) {
            tempCoef = new  double[n];

            System.out.println("Equation " + i + ": ");
            for (int j = 0; j < n; j++) {
                System.out.println("Variable " + (j+1) + " coefficient: ");
                tempCoef[j] = scan.nextDouble();
            }
            //System.out.println(Arrays.toString(tempCoef));
            System.out.println("Constant: ");
            tempSol = scan.nextDouble();

            equations[i-1] = new Equation(tempCoef, tempSol, n);
        }
        

        //Sys test = new Sys(testC, testS);
        Sys test = new Sys(equations, n);
        double[] varValues = test.solver();
        System.out.println("-------------");
        System.out.println("Solution to linear system of equations:");
        for (int i = 1; i <= n; i++) {
            System.out.println("Variable " + i + ": " + Math.round(varValues[i-1] * 100) / 100.0);
        }

    }
}