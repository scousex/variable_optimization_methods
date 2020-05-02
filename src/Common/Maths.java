package Common;

public class Maths {

    /**
     * Функция для сравнения двух чисел чтобы программа не вычисляла бесконечно.
     * @param a - первое число
     * @param b - второе число
     * @param e - погрешность сравнения
     * @return возвращает результат сравнения с погрешностью e
     */
    public static boolean equals(double a, double b, double e){
        return Math.abs(a-b) < e;
    }

    public static boolean equals(double numbers[], int i, boolean lastPair){
        return i==numbers.length?lastPair:equals(numbers,i+1,Maths.equals(numbers[i-1],numbers[i],0.0001));
    }

    /**
     *
     * @param a - первое значение отрезка
     * @param b - второе значение отрезка
     * @param x - проверяемое значение
     * @return возвращает true если x находится на отрезке [a,b]
     */
    public static boolean isOnSegment(double a,double b,double x){
        if(a==b) return false;
        if(a<b){
            return (x>=a && x<=b);
        }else{
            return (x>=b && x<=a);
        }
    }

    public static double min(double[] numbers, int a, double n) {
        return a==numbers.length?n:Math.min(n,min(numbers,a+1,numbers[a]<n?numbers[a]:n));
    }
    public static double max(double[] numbers, int a, double n) {
        return a==numbers.length?n:Math.max(n,max(numbers,a+1,numbers[a]>n?numbers[a]:n));
    }
    /**
     * @Description: функция для вычисления значения условия останова
     * @Param Входящий параметр - вектор X
     */

    public static double finLength(double x[]){
        double sum = 0;
        for(double i:x){
            sum+=i;
        }
        return Math.sqrt(sum);
    }

    public static double[] sumOfVectors(double[] a, double[] b){
        if(a.length!=b.length) return null;
        double[] res = new double[a.length];
        for(int i=0;i<a.length;i++){
            res[i] = a[i]+b[i];
        }
        return res;
    }

    public static double[] minusVector(double[] a, double[] b){
        if(a.length!=b.length) return null;
        double[] res = new double[a.length];
        for(int i=0;i<a.length;i++){
            res[i] = a[i]-b[i];
        }
        return res;
    }
}
