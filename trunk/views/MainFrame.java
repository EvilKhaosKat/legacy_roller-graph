/**
 * 
 */
package views;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import models.generatorOfCoordinates;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;


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
        menuFile.add(new JMenuItem("Подключить"));
		menuFile.add(new JSeparator());
		menuFile.add(new JMenuItem("Выход"));
        mainMenuBar.add(menuFile);

        menuOptions = new JMenu("Настройки");
		menuOptions.add(new JMenuItem("Emptyness"));
		mainMenuBar.add(menuOptions);

        testMenu = new JMenu("Тестовое меню");
        testMenuItem1 = new JMenuItem("Тестовое добавление элементов 1");
        testMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testMenuItem1ActionPerformed(evt);
            }
        });
        testMenu.add(testMenuItem1);
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
    }






    // обработчики нажатий
    //------------------------------------------------------------------------------------------------------------------




    private void createChartField(){
        dataset = new XYSeriesCollection();
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
