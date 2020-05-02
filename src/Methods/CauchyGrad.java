package Methods;

import Function.Function;
import Function.MultipleVariablesFunction;

import static Common.Maths.sumOfVectors;

public class CauchyGrad {

    MultipleVariablesFunction function;
    double e = 0.1;
    Powell powell = null;
    double[] x;
    double[] d;

    public double[] minimize(double[] x0){
        this.x = x0;
        this.d = function.grad(x);
        powell = new Powell(new LambdaFun());
        double lambda = powell.calculate(-2,2);

        double[] newX = new double[x.length];

        for(int i = 0; i<x.length; i++){
            newX[i] = x[i] + lambda*d[i];
        }

        if(stop(newX)) return newX;
        return minimize(newX);
    }


    public CauchyGrad(MultipleVariablesFunction function) {
        this.function = function;
    }

    private double f(double[] x){
        return function.f(x);
    }

    private double dif(double[] x, int i, int n){
        return function.dif(x,i,0.5,n);
    }

    private double[] grad(double[] x){
        return function.grad(x);
    }

    private boolean stop(double[] newX){
        double sum = 0;
        for(int i=0; i<x.length; i++){
            sum += Math.pow(newX[i]-x[i],2);
        }
        return Math.sqrt(sum)<e;
    }


    class LambdaFun implements Function{

        @Override
        public double f(double lambda) {
            double[] d0 = CauchyGrad.this.d.clone();
            double[] x0 = CauchyGrad.this.x.clone();
            for(int i=0; i<d0.length; i++){
                d0[i]*=lambda;
            }
            return CauchyGrad.this.f(sumOfVectors(x0,d0));
        }

        @Override
        public double dif(double lambda, int n) {
            double[] d0 = CauchyGrad.this.d.clone();
            double[] x = CauchyGrad.this.x.clone();
            for(int i=0; i<d0.length; i++){
                d0[i]*=lambda;
            }
            return CauchyGrad.this.f(sumOfVectors(x,d0));

        }
    }
}
