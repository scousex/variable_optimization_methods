package Methods;

import Common.Maths;
import Function.Function;

public class Bolzano {
    double e = 0.0001;
    Function function;

    public Bolzano(Function function) {
        this.function = function;
    }

    public double minimize(double a, double b){
        double z = (a+b)/2;
        if(dif(z,1)<0){
            a = z;
        }
        else if(dif(z,1)>0){
            b = z;
        }
        if(!Maths.equals(dif(z,1),e,e)) return minimize(a,b);
        return z;
    }

    private double dif(double x, int n){
        return function.dif(x,n);
    }
}
