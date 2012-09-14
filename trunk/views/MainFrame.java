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

import models.DrawingOrganizer;
import models.MainContainer;
import models.ReadingOrganizer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

import device.si30.SI30Counter;

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
	
	private void createMenu(){
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

        testMenu = new JMenu("Тестовое меню");
        testMenuItem1 = new JMenuItem("Тестовое чтение элементов 1");
        testMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testMenuItem1ActionPerformed(evt);
            }
        });
        testMenu.add(testMenuItem1);
        testMenuItem2 = new JMenuItem("Тест");
        testMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testMenuItem2ActionPerformed(evt);
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


    // -----------------------------------------------------------------------------------------------------------------
    // обработчики нажатий

    private void testMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        //TODO обработчик нажатия
        ReadingOrganizer.startReading();
        //DrawingOrganizer.startDrawing();
    }

    private void testMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
        //TODO обработчик нажатия

        //DrawingOrganizer.startDrawing();
    }

    private void connectMenuItemActionPerfomed(java.awt.event.ActionEvent evt) {
        SI30Counter si30Counter = MainContainer.getSi30Counter();
        si30Counter.connect();
        System.out.println("Connected");
    }


    // обработчики нажатий
    //------------------------------------------------------------------------------------------------------------------




    private void createChartField(){
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
	
	private void createGUI(){
		createMenu();
		createChartField();
		pack();
	}
	public MainFrame(String title){
		super(title);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		createGUI();

	}

}
