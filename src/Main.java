import Function.LogFeaturingSin;
import Function.SecondVariant;
import Function.Sin;
import Methods.*;

public class Main {
    public static void main(String[] args) {

        LogFeaturingSin fun = new LogFeaturingSin();
        SecondVariant multipleVariablesFun = new SecondVariant();

        System.out.println("Hello World! Оптимизируем функцию\n" +
                "f(x) = ln(1+x*x) - sin(x)");

        System.out.println("Метод дихотомии:");
        Dichotomy dich = new Dichotomy(fun);
        double resdich = dich.calculate(0, 2*Math.PI);
        System.out.println("Результат: " + resdich);
        System.out.println("Значение функции в точке: "+fun.f(resdich)+"\n");

        System.out.println("Метод золотого сечения:");
        GoldenCut goldenCut = new GoldenCut(fun);
        double resgold = goldenCut.calculate(0, 2*Math.PI);
        System.out.println("Результат: " + resgold);
        System.out.println("Значение функции в точке: "+fun.f(resgold)+"\n");

        System.out.println("Метод Пауэлла:");
        Powell powell = new Powell(fun);
        double respowell = powell.calculate(0, 0.01);
        System.out.println("Результат: " + respowell);
        System.out.println("Значение функции в точке: "+fun.f(respowell)+"\n");

        System.out.println("Метод Ньютона:");
        Newton newton = new Newton(fun);
        double resnewton = newton.minimize(Math.PI/4);
        System.out.println("Результат: " + resnewton);
        System.out.println("Значение функции в точке: "+fun.f(resnewton)+"\n");

        System.out.println("Метод Больцано:");
        Bolzano bolzano = new Bolzano(fun);
        double resbolzano = bolzano.minimize(0, Math.PI/4);
        System.out.println("Результат: " + resbolzano);
        System.out.println("Значение функции в точке: "+fun.f(resbolzano)+"\n");


        System.out.println("Оптимизируем функцию f(x) = x1^2 + x1*x2 + x2^2 - 3x1 - 6x2:\n");
        System.out.println("Прямые методы : \n");
        System.out.println("Метод Дживса-Хука:");
        JeevesHook jeevesHook = new JeevesHook(multipleVariablesFun);
        double[] resjeews = jeevesHook.minimize(new double[]{3,2}, new double[]{1,1},2);
        System.out.println("Результат: ");
        for(double x: resjeews){
            System.out.print(x+" ");
        }
        System.out.println("Значение функции в точке: " + multipleVariablesFun.f(resjeews) + "\n");

        System.out.println("Метод симплекса:");
        SimplexMethod simplexMethod = new SimplexMethod(multipleVariablesFun);
        double[] ressimplex = simplexMethod.minimize(new double[]{1.5,2.5},2,1,0.5);
        System.out.println("Результат: ");
        for(double x: ressimplex){
            System.out.print(x+" ");
        }
        System.out.println("Значение функции в точке: "+ multipleVariablesFun.f(ressimplex) + "\n");

        System.out.println("Градиентные методы : \n");

        System.out.println("Метод Коши:");
        CauchyGrad cauchyGrad = new CauchyGrad(multipleVariablesFun);
        double[] resCauchy = cauchyGrad.minimize(new double[]{2,1});
        System.out.println("Результат: ");
        for(double x: resCauchy){
            System.out.print(x+" ");
        }
        System.out.println("Значение функции в точке: "+ multipleVariablesFun.f(resCauchy) + "\n");
    }
}
