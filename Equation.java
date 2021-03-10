public class Equation {

    private double[] coefs;
    private double sol;
    private int numVars;

    public Equation (double[] coefs, double sol, int numVars)
    {
        this.coefs = coefs;
        this.sol = sol;
        this.numVars = numVars;
    }

    public double[] getCoefs () {
        return coefs;
    }

    public double getSol() {
        return sol;
    } 

    public int getN() {
        return numVars;
    }
}