import static java.lang.Math.PI;

public class Main {

    static Runge_Kutta A = new Runge_Kutta(0.01,PI/2,0,PI/2,PI/2+1);
    static Adams B = new Adams();

    public static void main(String[] args)
    {
        A.run();
        B.run();
        SimpleXYChart.Draw(A.getyExact(), A.getyComp(), B.getyComp());
        SimpleXYChart.Draw2(A.getErr(),B.getErr());
    }
}