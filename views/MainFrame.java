/**
 * 
 */
package views;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


/**
 * @author LAP
 *
 */
public class MainFrame extends JFrame {
	private JMenu file;
	private JMenu options;
	private JMenuBar mainMenu;
	private JFreeChart rollerDiagrammer;
	private ChartPanel contentChartPanel;
	private XYDataset dataset;
	/**
	 * 
	 */
	private static final long serialVersionUID = -1587654314948932245L;
	
	private void createMenu(){
		mainMenu = new JMenuBar();
		file = new JMenu("Файл");
		options = new JMenu("Настройки");
		file.add(new JMenuItem("Подключить"));
		file.add(new JSeparator());
		file.add(new JMenuItem("Выход"));
		mainMenu.add(file);
		options.add(new JMenuItem("Emptyness"));
		mainMenu.add(options);
		setJMenuBar(mainMenu);
	}
	
	private void createChartField(){
		dataset = (XYDataset)((XYSeriesCollection) new XYSeriesCollection());
		rollerDiagrammer = ChartFactory.createXYLineChart(null, null, null, dataset, PlotOrientation.VERTICAL, false, false, false);
		contentChartPanel = new ChartPanel(rollerDiagrammer);
		setContentPane(contentChartPanel);
	}
	
	private void createGUI(){
		createMenu();
		createChartField();
		pack();
	}
	public MainFrame(String title){
		super(title);
		createGUI();
	}
}
