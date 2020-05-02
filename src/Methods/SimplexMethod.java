package Methods;

import Common.Maths;
import Function.MultipleVariablesFunction;

public class SimplexMethod {
    MultipleVariablesFunction function;
    double coordMatrix[][] = new double[][]{};

    public double[] minimize(double[] x, int n, double alfa, double gamma){
        this.completeMatrix(x,n);
        return calculate(n,alfa,gamma);
    }
    private double[] calculate(int n, double alfa, double gamma){
        double[] fs = new double[n+1];
        for(int i = 0; i < n+1; i++){
            fs[i] = f(coordMatrix[i]);
        }
        double fmin = Maths.min(fs,0,fs[1]);
        double fmax = Maths.max(fs,0,fs[1]);
        double[] cG = new double[n];
        int imax = 0;
        int imin = 0;

        //Здесь находим сумму всех вершин для центра тяжести
        for(int i = 0; i < n+1; i++){
            if(fs[i]!=fmax){
                cG = Maths.sumOfVectors(cG,coordMatrix[i]);
            }
            else{
                imax = i;
            }
            if(fs[i]==fmin) imin=i;
        }
        if(stop(coordMatrix)) return coordMatrix[imin];
        //Вычитаем максимальную вершину, получаем центр тяжести
        for(int j = 0; j < n; j++){
            cG[j] =cG[j] - coordMatrix[imax][j];
            cG[j] /= n;
        }
        //Находим вершину-отражение наихудшего симплекса
        double[] newX = new double[n];
        for(int i = 0; i<n;i++){
            newX[i] = cG[i] - coordMatrix[imax][i];        }
        //Если функция при новом симплексе меньше наилучшей функции
        if(f(newX)<fmax){
            double[] stretchedX = newX.clone();
            //растягиваем новый симплекс
            for(int i = 0; i<stretchedX.length; i++){
                stretchedX[i] *= alfa;
                cG[i] *= (1-alfa);
                stretchedX[i] = stretchedX[i]+cG[i];
            }
            if(f(stretchedX)<f(newX)){
                coordMatrix[imax] = stretchedX;
            }
            else coordMatrix[imax] = newX;
            return calculate(n,alfa,gamma);
        }
        //иначе
        //сжимаем симплекс
        else  {
            double[] constrictX = coordMatrix[imin].clone();

            for (int i = 0; i < constrictX.length; i++) {
                constrictX[i] *= gamma;
                cG[i] *= (1 - gamma);
                constrictX[i] = constrictX[i] + cG[i];
            }
            if (f(constrictX) < fmax) {
                coordMatrix[imax] = constrictX;
            } else coordMatrix[imax] = newX;
            return calculate(n, alfa, gamma);
        }

    }


    public SimplexMethod(MultipleVariablesFunction function) {
        this.function = function;
    }

    private void completeMatrix(double[] x, int n){
        this.coordMatrix = new double[n+1][n];
        for(int i = 0; i<n+1; i++){
            for(int j = 0; j<n; j++){
                if(i==0){
                    this.coordMatrix[i][j]= x[j];
                }
                else if((i-1)==j){
                    this.coordMatrix[i][j] = (Math.sqrt(n+1)+n-1)/(n*Math.sqrt(2));
                }
                else if(i!=0){
                    this.coordMatrix[i][j] = (Math.sqrt(n+1)-1)/(n*Math.sqrt(2));
                }
               // this.coordMatrix[i][j]+=x[j];
            }
        }
    }

    private boolean stop(double[][] x){
        double sum = 0;
        double otkl = 0;
        for(double[] xj:x){
            sum += f(xj);
        }
        sum/=x.length;
        for(double[] xj:x){
            otkl += Math.pow(f(xj)-sum,2);
        }
        otkl/=x.length;
        otkl=Math.sqrt(otkl);

        return otkl < 0.5;
    }

    private double f(double[] x){
        return function.f(x);
    }
}
