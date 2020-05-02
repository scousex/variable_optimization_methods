package Methods;

import Common.Maths;
import Function.MultipleVariablesFunction;

public class JeevesHook  {

    MultipleVariablesFunction function;
    double e = 0.0001;
    double[] dx;
    double a = 2;

    public JeevesHook(MultipleVariablesFunction function) {
        this.function = function;
    }

    public double[] minimize(double[] x0, double[] ddx, double k){
        this.a = k;
        this.dx = ddx.clone();
        return research(search(x0,ddx,k));
    }

    private SearchedVector search(double[] x, double[] ddx, double a){

        double[][] y = new double[x.length][x.length];
        double[] fs = new double[x.length + 1];
        int k = 0;
        for (int i = 0; i < x.length + 1; i++) {
            if (i < x.length) {
                System.arraycopy(x, 0, y[i], 0, x.length);
                y[i][i] += this.dx[i];
                fs[i] = f(y[i]);
            } else fs[i] = f(x);
        }

        if (Maths.equals(fs, 1, true)) {
            return new SearchedVector(x,y[0],y.length);
        }
        double minf = Maths.min(fs, 0, fs[1]);

        for (int i = 0; i < fs.length; i++) {
            if (minf == fs[i]) {
                k = i;
                break;
            }
        }
        if (k >= x.length ) {
            if(ddx[0]!=-this.dx[0])
                return search(x, minusDx(this.dx), a);
            else return new SearchedVector(x,y[0],k);
        }
        return new SearchedVector(x, y[k], k);
    }
    private double[] research(SearchedVector vec){
        if (vec.i >= vec.x.length) {
            if (Maths.finLength(this.dx) < e) {
                return vec.x;
            } else{
                for (int i = 0; i < this.dx.length; i++) {
                    this.dx[i] = this.dx[i]/this.a;
                }
                return research(search(vec.x,this.dx,a));
            }
        }
        else{
             vec.y[vec.i] += this.dx[vec.i];
             return research(search(vec.y,this.dx,a));
        }
    }

    private double f(double x[]){
       return function.f(x);
    }

    private double[] minusDx(double ddx[]){
        double dx[] = ddx.clone();
        for(int i = 0; i<dx.length; i++){
            dx[i] = -dx[i];
        }
        return dx;
    }

    class SearchedVector {
        double[] x, y;
        int i;

        SearchedVector(double[] x, double[] y, int i) {
            this.x = x;
            this.y = y;
            this.i = i;
        }

    }

}
