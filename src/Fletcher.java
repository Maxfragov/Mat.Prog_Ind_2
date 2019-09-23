
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.abs;

public class Fletcher {
    double x0[]=new double [2];
    double e;


    Fletcher(double x0[], double e) {

        this.x0 = x0;
        this.e = e;


    }
    double ft(double x[],double t, double x1[]) {

        return  3*(x[0]+x1[0]*t)*(x[0]+x1[0]*t)+4*(x[1]+x1[1]*t)*(x[1]+x1[1]*t)-2*(x[0]+x1[0]*t)*(x[1]+x1[1]*t)+x[0]+x1[0]*t;

    }

    double w[]=new double[2];
    double w1[]=new double[2];
    double f(double x[]) {
        return 3*x[0]*x[0]+4*x[1]*x[1]-2*x[0]*x[1]+x[0];
    }
    double fx1(double x[]) {
        return 6*x[0]-2*x[1]+1;
    }
    double fy1(double x []) {
        return 8*x[1]-2*x[0];
    }
    double fx2(double x,double y) {
        return 6;
    }
    double fy2(double x,double y) {
        return 8;
    }
    double fxy(double x,double y) {
        return -2;
    }
    double dl(double x[])
    {
        return sqrt(x[0]*x[0]+x[1]*x[1]);
    }

    double q[]=new double[2];

    double s[]=new double[2];
    double xx[][]=new double[2][2];
    double l;
    double t;
    double d[]= new double [2];
    double d1[]= new double [2];


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



    void p() {
        int k= 0;
        double b;
        double t = 0;
        double x[];
        x=new double [2];
        double x1[];
        x1=new double [2];
        x=x0;
        w[0] = fx1(x0);

        w[1] = fy1(x0);




        do {
            System.out.println("Итерация № = " + k);

            System.out.println("w10 = " + w1[0]);
            System.out.println("w11 = " + w1[1]);
            if (dl(w)<e)  break;
            if (k==0)
                  {d[0]=-w[0];
                   d[1]=-w[1];
                   d1[0]=d[0];
                   d1[1]=d[1];
                   }
             if ( k!=0)
           { b=(dl(w1)*dl(w1))/(dl(w)*dl(w));
               System.out.println("b = " + b);
            d1[0]=-w1[0]+b*d[0];
            d1[1]=-w1[1]+b*d[1];
               System.out.println("d0 = " + d1[0]);
               System.out.println("d1 = " + d1[1]);}

            t=dih1(x0,t,d1);
            x1[0] = x0[0] + (t * d1[0]);
            x1[1] = x0[1] + (t * d1[1]);
            w[0]=fx1(x0);
            w[1]=fy1(x0);
            d[0]=d1[0];

            d[1]=d1[1];

            w1[0]=fx1(x1);
            w1[1]=fy1(x1);


            if ((abs(x1[0]-x0[0]) <e) & (abs(x1[1]-x0[1]) <e) &  (abs(f(x1)-f(x0)) <e))  break;

            x0[0]=x1[0];
            x0[1]=x1[1];

            System.out.println("x и y  = " + x0[0]+"  "+x0[1]);

            k = k + 1;


        }
        while (k<1000) ;
        System.out.println("Количество итераций = " + k);
        System.out.println("Точкi минимума = " + x0[0]+"  "+x0[1]);
        System.out.println("Минимум Функции = " + f(x0));
    }
}
