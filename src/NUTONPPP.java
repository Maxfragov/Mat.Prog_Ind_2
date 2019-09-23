import Jama.Matrix;

import static java.lang.Math.sqrt;
import static java.lang.StrictMath.abs;

public class NUTONPPP {
    double x0[]=new double [2];
    double e;


    NUTONPPP(double x0[], double e) {
        this.x0 = x0;
        this.e = e;
    }
    void um(double x[][],double y[],double e[]){
        e[0]=-(x[0][0]*y[0]+x[0][1]*y[1]);
        e[1]=-(x[1][0]*y[0]+x[1][1]*y[1]);

    }
    double ft(double x[],double t, double x1[]) {

        return  3*(x[0]+x1[0]*t)*(x[0]+x1[0]*t)+4*(x[1]+x1[1]*t)*(x[1]+x1[1]*t)-2*(x[0]+x1[0]*t)*(x[1]+x1[1]*t)+x[0]+x1[0]*t;

    }
    double f(double x[]) {
        return 3*x[0]*x[0]+4*x[1]*x[1]-2*x[0]*x[1]+x[0];
    }
    double fx1(double x[]) {
        return 6*x[0] -2*x[1]+1;
    }
    double fy1(double x []) {
        return 8*x[1] -2*x[0];
    }
    double fx2(double x[]) {
        return 6;
    }
    double fy2(double x[]) {
        return 8;
    }
    double fxy(double x[]) {
        return -2;
    }
    double dl(double x[])
    {
        return sqrt(x[0]*x[0]+x[1]*x[1]);
    }
    double r[][]=new double[2][2];
    double r1[][]=new double[2][2];
    double H1[][]=new double[2][2];
    double d[]= new double [2];
    double w[]=new double[2];
    private double dih1(double[] x, double t, double[] w) {
        double r;
        double ser;
        int k = 0;
        double a,b;
        double x1;
        double x2;
        a=-1;
        b=1;
        double alp=0.00002;
        double q1 = 0;
        e=0.00005;

        while ((b - a) > 2*e) {
            ser = (a + b) / 2;
            x1 = (ser - alp/2);
            x2 = (ser + alp/2);


            if (ft(x, x1,w) < ft(x, x2,w))
                b = x2;
            if (ft(x, x1,w) > ft(x, x2,w))
                a = x1;
            if (ft(x, x1,w) == ft(x, x2,w)){
                a = x1;
                b = x2;}
            k = k + 1;
        }
        double q = (a + b) / 2;
        System.out.println("mint = " +q );
        return q;
    }
    void p(){
        double t = 0;
        double x1[]=new double[2];
        r[0][0]=fx2(x0);
        r[0][1]=fxy(x0);
        r[1][0]=fxy(x0);
        r[1][1]=fy2(x0);
        int k=0;
        Matrix H= new Matrix(r);
        Matrix r1=H.inverse();
        Matrix D=new Matrix(d,1);
        H1=r1.getArray();
        Matrix W=new Matrix(w,1);
        H.print(1,2);
        r1.print(1,2);
        System.out.println(H1[0][0]+" "+H1[0][1]);
        System.out.println(H1[1][0]+" "+H1[1][1]);
        do {
            w[0] = fx1(x0);
            System.out.println("w0 = " + w[0]);
            w[1] = fy1(x0);
            System.out.println("w1 = " + w[1]);

            W.set(0,0,fx1(x0));
            W.set(0,1,fy1(x0));



            if (dl(w)<e) break;
            if (r1.det()>0) {
                um(H1,w,d);}
            else {d[0]=-w[0];
                d[1]=-w[1];}
            t=dih1(x0,t,d);
            x1[0]=x0[0]+t*d[0];
            x1[1]=x0[1]+t*d[1];
            if ((abs(x1[0]-x0[0]) <e) & (abs(x1[1]-x0[1]) <e) &  (abs(f(x1)-f(x0)) <e))  break;

            x0[0]=x1[0];
            x0[1]=x1[1];
            System.out.println("x и y  = " + x0[0]+"  "+x0[1]);
            System.out.println("Итерация № = " + k);
            k = k + 1;

        }while(100>k);
        System.out.println("Количество итераций = " + k);
        System.out.println("Точка минимума = " + x0[0]+"  "+x0[1]);
        System.out.println("Минимум Функции = " + f(x0));

    }








}