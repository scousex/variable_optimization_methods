package Methods;

import Common.Maths;
import Function.Function;

public class Dichotomy {
    double e = 0.0001;
    double y, z, L;
    Function function;

    public Dichotomy(Function function, double e) {
        this.function = function;
        this.e = e;
    }

    public Dichotomy(Function fun) {
        this.function = fun;
    }

    public double calculate(double a, double b){

        y = ((a + b) - e) / 2;
        z = ((a + b) + e) / 2;
        if(function.f(y)>function.f(z)){
            a = y;
        }
        else{
            b = z;
        }
        L = Math.abs(b - a);

        if(Maths.equals(e,L,e*0.01)) {
            return (a+b)/2;
        }
        else{
           return calculate(a,b);
        }
    }
}
