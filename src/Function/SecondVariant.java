package Function;

public class SecondVariant implements MultipleVariablesFunction {
    @Override
    public double f(double[] x) {
        return x[0]*x[0]-x[0]*x[1]+x[1]*x[1]-3*x[0]-6*x[1];
    }

    /**
     *
     * @param x- значение, по которому вычислять производную
     * @param i - порядковый номер параметра для частной производной (xi)
     * @param hi - приращение
     * @param n - порядок производной (макс 2).
     * @return
     */
    @Override
    public double dif(double x[], int i, double hi, int n){
        if(i==0) return 2*x[0]-x[1]-3;
        if(i==1) return -x[0]+2*x[1]-6;
        return 0;
    }
    @Override
    public double[] grad(double x[]){
        double[] grad = new double[x.length];
        for(int i=0;i<x.length;i++){
            grad[i] = dif(x,i,2,1);
        }
        return grad;
    }
}
