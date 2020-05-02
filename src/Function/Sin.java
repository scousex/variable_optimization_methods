package Function;

public class Sin implements Function {

    @Override
    public double f(double x) {
        return Math.pow(Math.sin(x),1);
    }

    @Override
    public double dif(double x, int n){
        return 0;
    }
}
