package Function;

public interface MultipleVariablesFunction {
    double f(double[] x);
    double dif(double[] x, int i, double hi, int n);
    double[] grad(double[] x);
}
