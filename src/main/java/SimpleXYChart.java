

import javax.swing.JFrame;
        import org.jfree.chart.ChartFactory;
        import org.jfree.chart.ChartPanel;
        import org.jfree.chart.JFreeChart;
        import org.jfree.chart.plot.PlotOrientation;
        import org.jfree.data.xy.XYSeries;
//import org.jfree.util.PublicCloneable;
        import org.jfree.data.xy.XYSeriesCollection;
        import static java.lang.Math.*;

public class SimpleXYChart {
    public static void Draw(double[] Ex, double[] RK, double[] A) {

        JFrame frame = new JFrame(); //создаем каркас окна
        frame.setTitle("Lab7"); //заголовок окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //создаем один ряд данных
        XYSeries series = new XYSeries("Exact value");
        XYSeries series1 = new XYSeries("Runge-Kutta");
        XYSeries series2 = new XYSeries("Adams");
        //добавляем точки на график
        double h = Runge_Kutta.getH();
        double j = PI;
        for(int i=0; i<Runge_Kutta.getN();i++)
        {
            series.add(j,Ex[i]);
            j+=0.1;
        }

        j = PI;
        for(int i=0; i<Runge_Kutta.getN();i++)
        {
            series1.add(j,RK[i]);
            j+=0.1;
        }
        j = PI;
        for(int i=0; i<Runge_Kutta.getN();i++)
        {
            series2.add(j,A[i]);
            j+=0.1;
        }

// сразу же добавим ряд в набор данных
        XYSeriesCollection data = new XYSeriesCollection(series);
        data.addSeries(series1);
        data.addSeries(series2);


// создаем диаграмму
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Solutions", //Название диаграммы
                "X",  //Подпись оси X
                "Y",  //Подпись оси Y
                data,//данные
                PlotOrientation.VERTICAL, //ориентация
                true, //включить легенду
                true, //подсказки
                false // urls
        );

        //создаем панель для графика
        final ChartPanel chartPanel = new ChartPanel(chart);
        //устанавливаем размер диаграммы (можна также воспользоваться методами JFreeChart этого)
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        //добавляем панель на созданный фрейм
        frame.setContentPane(chartPanel);
        //подгоняем размеры фрейма
        frame.pack();
        //делаем все видимым
        frame.setVisible(true);
    }

    public static void Draw2(double[] Er1, double[] Er2) {

        JFrame frame = new JFrame();
        frame.setTitle("Lab7");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        XYSeries series = new XYSeries("Errors Runge-Kutta");
        XYSeries series1 = new XYSeries("Errors Adams");
        double h = Runge_Kutta.getH();
        double j = 0.001;


        series.add(0.001, Er1[0]);
        series.add(0.002, Er1[1]);
        series.add(0.005, Er1[2]);
        series.add(0.01, Er1[3]);
        series.add(0.02, Er1[4]);



        series1.add(0.001,Er2[0]);
        series1.add(0.002,Er2[1]);
        series1.add(0.005,Er2[2]);
        series1.add(0.01,Er2[3]);
        series1.add(0.02,Er2[4]);



        XYSeriesCollection data = new XYSeriesCollection(series);
        data.addSeries(series1);


        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Errors",
                "h",
                "Y",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}