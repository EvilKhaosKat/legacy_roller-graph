package views.optionsframe;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @author LAP
 *
 */
public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	
	public MainFrame(String title) {
		super(title);
		tabbedPane = new JTabbedPane();
		setLayout(new BorderLayout());
		add(tabbedPane, BorderLayout.CENTER);
		//Test
		tabbedPane.addTab("Test tab", new ChartPanel(ChartFactory.createLineChart(
				null, null, null, new DefaultCategoryDataset(), PlotOrientation.VERTICAL, false, false, false)));
		tabbedPane.addTab("Comport", new SerialPortSettings());
	}
	//TODO write tabs count function
	public int getTabsCount() {
		return 0;
	}
}
