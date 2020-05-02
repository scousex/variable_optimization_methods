package Function;

public class LogFeaturingSin implements Function {

    final double min = 0;
    final double max = Math.PI/4;

    @Override
    public double f(double x) {
        return Math.log(1 + x*x) - Math.sin(x);
    }
    @Override
    public double dif(double x, int n){
        if(n==1) return 2*(x/(1+x*x))-Math.cos(x);
        else if(n==2) return -4*((x*x)/Math.pow((x*x+1),2))+Math.sin(x)+(2/(x*x+1));
        return 0;
    }
}
