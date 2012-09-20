/**
 *
 */
package views;

import device.si30.SI30Counter;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import models.oldVersion.DrawingOrganizer;
import models.MainContainer;
import models.oldVersion.ReadingOrganizer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

import device.si30.SI30Counter;
import models.Measurement;
import models.newVersion.drawer.Drawer;
import models.newVersion.reader.Reader;

/**
 * @author LAP
 *
 */
public class MainFrame extends JFrame {

    private JMenu menuFile;
    private JMenu menuOptions;
    private JMenu testMenu;
    private JMenuBar mainMenuBar;
    private JMenuItem testMenuItem1;
    private JMenuItem testMenuItem2;
    private JMenuItem connectMenuItem;
    private JFreeChart rollerDiagrammer;
    private ChartPanel contentChartPanel;
    private XYSeriesCollection dataset;
    /**
     *
     */
    private static final long serialVersionUID = -1587654314948932245L;

    private void createMenu() {
        mainMenuBar = new JMenuBar();

        menuFile = new JMenu("Файл");

        connectMenuItem = new JMenuItem("Подключить");
        connectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectMenuItemActionPerfomed(evt);
            }
        });
        menuFile.add(connectMenuItem);



        //connectMenuItemActionPerfomed
        menuFile.add(new JSeparator());
        menuFile.add(new JMenuItem("Выход"));
        mainMenuBar.add(menuFile);

        menuOptions = new JMenu("Настройки");
        menuOptions.add(new JMenuItem("Emptyness"));
        mainMenuBar.add(menuOptions);

        testMenu = new JMenu("Измерение");
        testMenuItem1 = new JMenuItem("Пуск");
        testMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemStartActionPerformed(evt);
            }
        });
        testMenu.add(testMenuItem1);
        testMenuItem2 = new JMenuItem("Стоп");
        testMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemStopActionPerformed(evt);
            }
        });
        testMenu.add(testMenuItem2);
        mainMenuBar.add(testMenu);


        setJMenuBar(mainMenuBar);

        //startServer.addActionListener(new java.awt.event.ActionListener() {
        //    public void actionPerformed(java.awt.event.ActionEvent evt) {
        //        startServerActionPerformed(evt);
        //   }
        //});
    }

    private void createChartField() {
        dataset = new XYSeriesCollection();
        DrawingOrganizer.setDataset(dataset);

        rollerDiagrammer = ChartFactory.createXYLineChart(null, null, null, dataset, PlotOrientation.VERTICAL, false, false, false);
        contentChartPanel = new ChartPanel(rollerDiagrammer);
        setContentPane(contentChartPanel);

        //XYSeries series1 = new XYSeries("first");
        //series1.add(5,10);
        //series1.add(4,11);
        //dataset.addSeries(series1);

    }

    // -----------------------------------------------------------------------------------------------------------------
    // обработчики нажатий
    private void menuItemStartActionPerformed(java.awt.event.ActionEvent evt) {
        //TODO обработчик нажатия
        //ReadingOrganizer.startReading();
        Measurement measurement = new Measurement();
        MainContainer.getListMeasurements().add(measurement);
        Reader r=new models.newVersion.reader.Reader(measurement);
        r.startReading();
        Drawer d=new Drawer(r, dataset);
        d.startDrawing();
        //DrawingOrganizer.startDrawing();
    }

    private void menuItemStopActionPerformed(java.awt.event.ActionEvent evt) {
        //TODO обработчик нажатия
        //DrawingOrganizer.startDrawing();
        MainContainer.isReading=false;
    }

    private void connectMenuItemActionPerfomed(java.awt.event.ActionEvent evt) {
        SI30Counter si30Counter = MainContainer.getSi30Counter();
        si30Counter.connect();
        System.out.println("Connected");
    }

    // обработчики нажатий
    //------------------------------------------------------------------------------------------------------------------
    private void createGUI() {
        createMenu();
        createChartField();
        pack();
    }

    public MainFrame(String title) {
        super(title);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        createGUI();

    }
}
