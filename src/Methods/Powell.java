package Methods;

import Common.Maths;
import Function.Function;

public class Powell {

    double e = 0.0001;
    double y, z, L;
    Function function;

    public Powell(Function function) {
        this.function = function;
    }

    /**
     *
     * @param x1 - точка начала вычислений
     * @param dx - шаг по оси x
     * @return оптимальное значение параметра x
     */
    public double calculate(double x1, double dx){

        double x2, x3;
        x2 = x1 + dx;

        if(f(x1)>f(x2)){
            x3 = x1 + 2*dx;
        }
        else{ x3 = x1 - dx; }

        if(x3<x1){
            x2 = x1;
            x1 = x3;
            x3 = x2+dx;
        }

        return minimize(x1,x2,x3,dx);
    }

    private double minimize(double x1, double x2, double x3, double dx){
        double Xmin;
        Xmin = optimalX(x1,x2,x3);
        double a1 = (f(x2) - f(x1))/(x2-x1);
        double a2 = (1/(x3-x2))*(((f(x3)-f(x1))/(x3-x1)) - ((f(x2)-f(x1))/(x2-x1)));

        double Xagr = ((x2+x1)/2) - (a1/(2*a2));

        if(!Maths.isOnSegment(x1,x3,Xagr)){
            return calculate(Xagr,dx);
        }
        if(Math.abs(f(Xmin)-f(Xagr))<e  && Math.abs(Xmin - Xagr)<e){
            return Xagr;
        }else{
            if(Maths.equals(Xmin,Xagr,e*0.1)){
                return minimize(Xmin-dx,Xmin,Xmin+dx,dx);
            }
            else{
                return minimize(Xagr-dx,Xagr,Xagr+dx,dx);
            }
        }
    }

    private double f(double x){
        return function.f(x);
    }

    private double optimalX(double x1,double x2,double x3){

        if(f(x1)<=f(x2)&& f(x1)<=f(x3)) return x1;
        else if(f(x2)<=f(x1)&& f(x2)<=f(x3)) return x2;
            return x3;
    }
}
