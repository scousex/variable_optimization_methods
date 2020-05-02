package Methods;

import Common.Maths;
import Function.Function;

public class GoldenCut {
    double e = 0.0001;
    double g = (3 - Math.sqrt(5))/2;
    double y,z, L;

    Function function;

    public GoldenCut(Function function){
        this.function = function;
    }

    public GoldenCut(Function function, double e){
        this.function = function;
        this.e = e;
    }

    public double calculate(double a, double b){

        y = a + g*(b - a);
        z = a + b - y;
        return calculate(a,b,y,z);
    }

    public double calculate(double a, double b,double y, double z){

        if(function.f(y)>function.f(z)){
            a = y;
            y = z;
            z = a + b - z;
        }
        else{
            b = z;
            z = y;
            y = a + b - y;
        }
        L = Math.abs(b - a);
        if(Maths.equals(L,e,e*0.1)){
            return (a + b) / 2;
        }
        else { return calculate(a,b,y,z); }
    }



}
