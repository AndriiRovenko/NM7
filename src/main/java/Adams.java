import static java.lang.Math.PI;
import static java.lang.Math.abs;

public class Adams {

    private static double h; //шаг
    private static double[] comp; //вычисленные значния
    private static double[] exact; //точные значения
    private static double[] err; //относительная погрешность
    private static int n; //число шагов
    private static double a, b; //интервал

    Adams()
    {
        h = Runge_Kutta.getH();
        comp = Runge_Kutta.getyComp();
        exact = Runge_Kutta.getyExact();
        n = Runge_Kutta.getN();
        a = Runge_Kutta.getA();
        b = Runge_Kutta.getB();
        err = new double[n];
    }

    public static void run()
    {
        double x = PI/2 + h*4;
        for(int i=4;i<n;i++)
        {
            comp[i] = comp[i-1]+(h/24)*
                    (55*Runge_Kutta.func(x-h,comp[i-1])-59*Runge_Kutta.func(x-2*h,comp[i-2])
                            +37*Runge_Kutta.func(x-3*h,comp[i-3])-9*Runge_Kutta.func(x-4*h,comp[i-4]));
            x+=h;
        }
        System.out.println("\n\nAdams method:");
        System.out.println("h = "+h);
        for(int i=0;i<n;i++)
        {
            err[i] = abs((abs(exact[i] - comp[i])/exact[i]));
            System.out.println("Computed value = "+comp[i]+"       Exactly value = "+ exact[i]+"       Relative error = "+
                    abs((abs(exact[i] - comp[i])/exact[i])));
        }
    }

    public static double[] getyComp()
    {
        return comp;
    }

    public static double[] getErr()
    {
        return err;
    }

}
