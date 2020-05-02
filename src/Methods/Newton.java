package Methods;

import Common.Maths;
import Function.Function;

public class Newton {
    double e = 0.0001;
    Function function;

    public Newton(Function function) {
        this.function = function;
    }

    public double minimize(double x0){
        double x = x0 - (dif(x0,1)/dif(x0,2));
        if(dif(x,1)<e) return x;
        else return minimize(x);
    }

    private double dif(double x, int n){
        return function.dif(x,n);
    }
}
