package views;

import connection.ComPortConnection;
import controllers.Supervisor;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import device.si30.SI30Counter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import models.oldVersion.DrawingOrganizer;
import models.MainContainer;
import models.oldVersion.ReadingOrganizer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

import device.si30.SI30Counter;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import models.Measurement;
import models.newVersion.drawer.Drawer;
import models.newVersion.reader.Reader;
import views.optionsframe.SerialPortSettings;

/**
 * @author LAP, KhaosKat
 *
 */
public class MainFrame extends JFrame {

    private JMenu menuFile;
    private JMenu menuOptions;
    private JMenu testMenu;
    private JMenuBar mainMenuBar;
    private JMenuItem testMenuItem1;
    private JMenuItem testMenuItem2;
    private JMenuItem saveEtalonMenuItem;
    private JMenuItem loadEtalonMenuItem;
    private JMenuItem menuOptionsItem1;
    private JMenuItem connectMenuItem;
    private JFreeChart rollerDiagrammer;
    private ChartPanel contentChartPanel;
    private JPanel chartPanel;
    private XYSeriesCollection dataset;
    private JPanel toolPanel;
    private JLabel speed;
    private JLabel peakSpeed;
    private JButton startAndStopButton;
    private IndicationPanel leftIndicationPanel;
    private IndicationPanel rightIndicationPanel;
    /**
     *
     */
    private static final long serialVersionUID = -1587654314948932245L;

    private void createToolPanel() {
        toolPanel = new ToolPanel();
        /*toolPanel = new JPanel();
         toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.PAGE_AXIS));
         speed = new JLabel("Speed");
         toolPanel.add(speed);
         peakSpeed = new JLabel("Peak speed:");
         toolPanel.add(peakSpeed);
         startAndStopButton = new JButton("Start");
         toolPanel.add(startAndStopButton);*/
        this.add(toolPanel, BorderLayout.LINE_END);
    }

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
        menuOptionsItem1 = new JMenuItem("Настройки com порта");
        menuOptionsItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JFrame test = new JFrame("Настройки com порта");
                test.getContentPane().add(new SerialPortSettings());
                test.pack();
                test.setVisible(true);
            }
        });
        menuOptions.add(menuOptionsItem1);
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

        saveEtalonMenuItem = new JMenuItem("Сохранить последнее измерение как эталон");
        saveEtalonMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSaveEtalonActionPerformed(evt);
            }
        });
        testMenu.add(saveEtalonMenuItem);

        loadEtalonMenuItem = new JMenuItem("Загрузить эталонное измерение");
        loadEtalonMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemLoadEtalonActionPerformed(evt);
            }
        });
        testMenu.add(loadEtalonMenuItem);

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
        chartPanel = new JPanel();
        chartPanel.setLayout(new BorderLayout());
        contentChartPanel = new ChartPanel(rollerDiagrammer);
        chartPanel.add(contentChartPanel, BorderLayout.CENTER);

        //TODO а вот аткое насильное добавление chartPanel создало мне небольшие проблемы. i hate you xD
        this.add(chartPanel, BorderLayout.CENTER);
        //setContentPane(chartPanel);

        //XYSeries series1 = new XYSeries("first");
        //series1.add(5,10);
        //series1.add(4,11);
        //dataset.addSeries(series1);

    }

    // -----------------------------------------------------------------------------------------------------------------
    // обработчики нажатий
    public void menuItemStartActionPerformed(java.awt.event.ActionEvent evt) {
        //TODO обработчик нажатия
        //ReadingOrganizer.startReading();
        Measurement measurement = new Measurement();
        MainContainer.getListMeasurements().add(measurement);
        Reader r = new models.newVersion.reader.Reader(measurement);
        r.startReading();
        Drawer d = new Drawer(r, dataset);
        d.startDrawing();
        //DrawingOrganizer.startDrawing();
    }

    public void menuItemStopActionPerformed(java.awt.event.ActionEvent evt) {
        //TODO обработчик нажатия
        //DrawingOrganizer.startDrawing();
        MainContainer.isReading = false;
    }

    public void menuItemSaveEtalonActionPerformed(ActionEvent evt) {
        FileOutputStream fos = null;
        try {
            Measurement measurement = MainContainer.getListMeasurements().get(
                    MainContainer.getListMeasurements().size() - 1);
            fos = new FileOutputStream(MainContainer.getDefaultEtalonMeasurementFilename());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(measurement);
            oos.flush();
            oos.close();
        } catch (Exception ex) {
            System.out.println("Error during saving etalon." + ex.getMessage());
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                System.out.println("Error during trying to close fos after exception.");
            }
        }


    }

    public void menuItemLoadEtalonActionPerformed(ActionEvent evt) {

        Measurement measurement = null;

        
        try {
            FileInputStream fis = new FileInputStream(MainContainer.getDefaultEtalonMeasurementFilename());
            ObjectInputStream oin = new ObjectInputStream(fis);
            measurement = (Measurement) oin.readObject();
        } catch (Exception ex) {
            System.out.println("Error during loading etalon."+ex.getMessage());
        }

        //TestSerial ts = (TestSerial) oin.readObject();

        MainContainer.getListMeasurements().add(measurement);

        Drawer d = new Drawer(measurement, dataset, true);
        d.startDrawing();






    }

    public void connectMenuItemActionPerfomed(java.awt.event.ActionEvent evt) {
        SI30Counter si30Counter = MainContainer.getSi30Counter();
        if (MainContainer.getComPortPreferences() != null) {
            System.out.println("Using not standart com port pref." + MainContainer.getComPortPreferences().getPortName());
            ((ComPortConnection) si30Counter.getConnection()).setSerialPortPreferences(MainContainer.getComPortPreferences());
        }
        si30Counter.connect();
        System.out.println("Connected");

        Supervisor.startThatProcess();
    }

    // обработчики нажатий
    //------------------------------------------------------------------------------------------------------------------
    private void createGUI() {
        createMenu();
        setSize(900, 700);
        createLeftIndicationPanel();
        createChartField();
        createRightIndicationPanel();
        //createToolPanel();
        //pack();
    }

    public MainFrame(String title) {
        super(title);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        createGUI();

    }

    public void setSpeedCaption(double newSpeed) {
        Double d = new Double(newSpeed);
        //d=d/14.625; //40km=585. 585/40 = 14.625 
        System.out.println("Setting current speed:" + d);
        ((ValueIndicationPanel) rightIndicationPanel.innerPanelList.get(0)).setSpeedValue(d.toString());
        //((ToolPanel) toolPanel).setSpeedCaption(d.toString());
        //speed.setText("Speed: "+d.toString());
    }

    private void createLeftIndicationPanel() {
        leftIndicationPanel = new IndicationPanel();
        this.add(leftIndicationPanel, BorderLayout.LINE_START);
        fillLeftIndicationPanel();
    }

    private void createRightIndicationPanel() {
        rightIndicationPanel = new IndicationPanel();
        this.add(rightIndicationPanel, BorderLayout.LINE_END);
        fillRightIndicationPanel();
    }

    private void fillLeftIndicationPanel() {
        TextPanel tempTextPanel = new TextPanel("<html> Установи автомобиль на стенд");
        leftIndicationPanel.addElementPanel(tempTextPanel);
        tempTextPanel = new TextPanel("Запусти двигатель");
        leftIndicationPanel.addElementPanel(tempTextPanel);
        tempTextPanel = new TextPanel("<html> Включи первую передачу. Разгонись до скорости 10 км/ч.");
        leftIndicationPanel.addElementPanel(tempTextPanel);
        tempTextPanel = new TextPanel("<html> Включи вторую передачу. Разгонись до скорости 20 км/ч.");
        leftIndicationPanel.addElementPanel(tempTextPanel);
        tempTextPanel = new TextPanel("<html> Включи третью передачу. Разгонись до скорости 30 км/ч.");
        leftIndicationPanel.addElementPanel(tempTextPanel);
        tempTextPanel = new TextPanel("Открой дроссельную заслонку.");
        leftIndicationPanel.addElementPanel(tempTextPanel);
        //TODO две особые панели с отслеживанием достижения скорости до 40 и 100 км
        tempTextPanel = new TextPanel("<html> Разогнались до 40 км/ч. Разгонись до 100 км/ч.");
        leftIndicationPanel.addElementPanel(tempTextPanel);
        tempTextPanel = new TextPanel("<html> Разогнались до 100 км/ч. Отпусти педаль.");
        leftIndicationPanel.addElementPanel(tempTextPanel);

    }

    private void fillRightIndicationPanel() {
        ValueIndicationPanel tempSpeedIndicationPanel = new ValueIndicationPanel();
        rightIndicationPanel.addElementPanel(tempSpeedIndicationPanel);

        tempSpeedIndicationPanel = new ValueIndicationPanel("40.0", "Vmin");
        rightIndicationPanel.addElementPanel(tempSpeedIndicationPanel);
        tempSpeedIndicationPanel = new ValueIndicationPanel("100.0", "Vmax");
        rightIndicationPanel.addElementPanel(tempSpeedIndicationPanel);

        /*tempSpeedIndicationPanel = new ValueIndicationPanel("40.0","t разгона");
         rightIndicationPanel.addElementPanel(tempSpeedIndicationPanel);
         tempSpeedIndicationPanel = new ValueIndicationPanel("100.0","t выбега");
         rightIndicationPanel.addElementPanel(tempSpeedIndicationPanel);*/

        //TODO исправить и еще один костыль
        startAndStopButton = new JButton("Start");
        //startAndStopButton.setPreferredSize(new Dimension(173, 94));
        toolPanel = new ToolPanel();
        rightIndicationPanel.add(toolPanel);
    }

    public IndicationPanel getLeftIndicationPanel() {
        return leftIndicationPanel;
    }

    public IndicationPanel getRightIndicationPanel() {
        return rightIndicationPanel;
    }
}
