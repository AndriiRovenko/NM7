import static java.lang.Math.*;

interface myFunction
{
    double f(double x);
}
class Diffur implements myFunction
{
    public double f(double x)
    {
        return Math.exp(x);
//return(0.5*sin(2*x));
    }
}

public class Runge_Kutta {

    private static double h;
    private static double[] comp;
    private static double[] exactly;
    private static double[] err;
    private static double k1,k2,k3,k4;
    private static int n;
    private static double a, b;

    Runge_Kutta (double step, double x0, double y0, double l, double r)
    {
        h = step;
        a = l;
        b = r;
        n = (int)(1/h);
        comp = new double[n];
        exactly = new double[n];
        err = new double[n];
        comp[0] = y0;
    }

    private static double meas(double y)//вычисление очередного y
    {
        return (y+(h/6)*(k1+2*k2+2*k3+k4));
    }

    public static double func(double x,double y)
    {
        return 2*x*sin(x) + y/tan(x);

    }

    private static void koeff(double x, double y)//вычисление коэфф.
    {
        k1 = func(x, y);
        k2 = func(x + h / 2, y + h * k1 / 2);
        k3 = func(x + h / 2, y + h * k2 / 2);
        k4 = func(x + h, y + h * k3);
    }

    private static void exactlyMeas()//вычисление точных значений
    {
        Diffur esSol = new Diffur();
        double x = a;

        int t=0;
        do {
            for(int i=0;i<n; i++)
            {
                exactly[i] = esSol.f(x);
                x+=h;
            }
            System.out.println("T = "+t);
            t++;
            System.out.println("x="+x+"b="+b);
        }while (x>b);
        System.out.println("Good");
    }

    private static void aproxingMeas()//вычисление приблеженных значений
    {
        double x = a;
        for(int i=1;i<n;i++)
        {
            koeff(x, comp[i - 1]);
            comp[i] = meas(comp[i-1]);
            x+=h;
        }
    }

    private static void err()//относительная погрешность для Метода Рунге-Кутты
    {
        for(int i=0;i<n;i++)
        {
            err[i] = abs((abs(exactly[i] - comp[i])/exactly[i]));
        }
    }

    public static void run()
    {
        aproxingMeas();
        exactlyMeas();
        err();
        System.out.println("Runge-Kutta:");
        System.out.println("h = "+h);
        for(int i=0;i<n;i++)
        {
            System.out.println("Computed value = "+comp[i]+"       Exactly value = "+ exactly[i]+"       Residual = "+err[i]);
        }
    }

    public static int getN()//к-ство шагов
    {
        return n;
    }

    public static double getH()//шаг
    {
        return h;
    }

    public static double[] getyExact()//точные значения
    {
        return exactly;
    }

    public static double[] getyComp()//точные значения
    {
        return comp;
    }

    public static double getA()//к-ство шагов
    {
        return a;
    }

    public static double getB()//к-ство шагов
    {
        return b;
    }

    public static double[] getErr()
    {
        return err;
    }

}
