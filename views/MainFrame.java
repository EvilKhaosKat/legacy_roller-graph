package views;

import org.extdev.connection.ComPortConnection;
import controllers.Supervisor;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import org.extdev.device.si30.SI30Counter;

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

import models.MainContainer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

import org.extdev.device.si30.SI30Counter;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import models.Measurement;
import models.newVersion.drawer.Drawer;
import models.newVersion.reader.Reader;
import models.postprocessing.PostProcessorThreeAverage;
import models.postprocessing.PostProcessorThreeSimple;
import views.optionsframe.SerialPortSettings;

/**
 * @author LAP, KhaosKat
 *
 */
public class MainFrame extends JFrame {

    private JMenu menuFile;
    private JMenu menuOptions;
    private JMenu testMenu;
    private JMenu workModeMenu;
    private JMenu dataSourceMenu;
    private JMenuBar mainMenuBar;
    private JMenuItem testMenuItem1;
    private JMenuItem testMenuItem2;
    private JMenuItem saveEtalonMenuItem;
    private JMenuItem loadEtalonMenuItem;
    private JMenuItem menuOptionsItem1;
    private JMenuItem connectMenuItem;
    private JMenuItem workModeRealtimeUsual;
    private JMenuItem workModeThreeAverage;
    private JMenuItem workModeThreeSimplified;
    private JMenuItem dataSourceRealDeviceMenuItem;
    private JMenuItem dataSourceGeneratorMenuItem;
    private JFreeChart rollerDiagrammer;
    private ChartPanel contentChartPanel;
    private JPanel chartPanel;
    private XYSeriesCollection dataset;
    private JPanel toolPanel;
    //private JLabel speed;
    //private JLabel peakSpeed;
    //private JButton startAndStopButton;
    private IndicationPanel leftIndicationPanel;
    private IndicationPanel rightIndicationPanel;
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
                try {
                    connectMenuItemActionPerfomed(evt);
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage());
                }
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



        workModeMenu = new JMenu("Режим работы");
        workModeRealtimeUsual = new JMenuItem(MainContainer.WORKMODE_TITLE_REALTIME_USUAL);
        workModeRealtimeUsual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainContainer.setWorkMode(MainContainer.WORKMODE_REALTIME_USUAL);
                MainContainer.getMainFrame().setTitle("Roller graph. " + MainContainer.WORKMODE_TITLE_REALTIME_USUAL);
            }
        });
        workModeMenu.add(workModeRealtimeUsual);

        workModeThreeAverage = new JMenuItem(MainContainer.WORKMODE_TITLE_POSTPROCESSING_THREE_AVERAGE);
        workModeThreeAverage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainContainer.setWorkMode(MainContainer.WORKMODE_POSTPROCESSING_THREE_AVERAGE);
                MainContainer.getMainFrame().setTitle("Roller graph. " + MainContainer.WORKMODE_TITLE_POSTPROCESSING_THREE_AVERAGE);
            }
        });
        workModeMenu.add(workModeThreeAverage);

        workModeThreeSimplified = new JMenuItem(MainContainer.WORKMODE_TITLE_POSTPROCESSING_THREE_SIMPLIFIED);
        workModeThreeSimplified.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainContainer.setWorkMode(MainContainer.WORKMODE_POSTPROCESSING_THREE_SIMPLIFIED);
                MainContainer.getMainFrame().setTitle("Roller graph. " + MainContainer.WORKMODE_TITLE_POSTPROCESSING_THREE_SIMPLIFIED);
            }
        });
        workModeMenu.add(workModeThreeSimplified);

        mainMenuBar.add(workModeMenu);


        dataSourceMenu = new JMenu("Источник данных");
        dataSourceRealDeviceMenuItem = new JMenuItem("Реальное устройство");
        dataSourceRealDeviceMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainContainer.setDataSource(MainContainer.REAL_DEVICE);
                //MainContainer.getMainFrame().setTitle("Roller graph. " + MainContainer.WORKMODE_TITLE_REALTIME_USUAL);
            }
        });
        dataSourceMenu.add(dataSourceRealDeviceMenuItem);

        dataSourceGeneratorMenuItem = new JMenuItem("Генератор чисел");
        dataSourceGeneratorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainContainer.setDataSource(MainContainer.SIMPLE_RANDOM_GENERATOR);
                //MainContainer.getMainFrame().setTitle("Roller graph. " + MainContainer.WORKMODE_TITLE_POSTPROCESSING_THREE_AVERAGE);
            }
        });
        dataSourceMenu.add(dataSourceGeneratorMenuItem);

        mainMenuBar.add(dataSourceMenu);


        setJMenuBar(mainMenuBar);

        //startServer.addActionListener(new java.awt.event.ActionListener() {
        //    public void actionPerformed(java.awt.event.ActionEvent evt) {
        //        startServerActionPerformed(evt);
        //   }
        //});
    }

    private void createChartField() {
        dataset = new XYSeriesCollection();

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
        Drawer d = null;
        //TODO проверить
        switch (MainContainer.getWorkMode()) {
            case MainContainer.WORKMODE_REALTIME_USUAL:
                System.out.println("WORKMODE_REALTIME_USUAL");
                d = new Drawer(r, dataset);
                d.startDrawing();
                //DrawingOrganizer.startDrawing();
                break;

            case MainContainer.WORKMODE_POSTPROCESSING_THREE_AVERAGE:
                System.out.println("WORKMODE_POSTPROCESSING_THREE_AVERAGE");
                //ибо постоработка будет после нажатия "стоп" то ничего не делаем более
                break;

            case MainContainer.WORKMODE_POSTPROCESSING_THREE_SIMPLIFIED:
                System.out.println("WORKMODE_POSTPROCESSING_THREE_SIMPLIFIED");
                break;
        }


        /*Measurement postprocessedMeasurement = null;
                
         //TODO создать обработанный экземпляр
         d = new Drawer(postprocessedMeasurement, dataset, true);
         d.startDrawing();*/

    }

    public void menuItemStopActionPerformed(java.awt.event.ActionEvent evt) {
        //TODO обработчик нажатия
        //DrawingOrganizer.startDrawing();
        MainContainer.isReading = false;
        Drawer d = null;
        
        Measurement postprocessedMeasurement = null;
        
        //TODO потенциальный баг. если режим работы сменят после начала работы - будет не классно. Надо бы наверно блочить эту возможность
        switch (MainContainer.getWorkMode()) {
            case MainContainer.WORKMODE_REALTIME_USUAL:
                System.out.println("WORKMODE_REALTIME_USUAL");
                //ибо реалтайм - ничего более особенного делать не надо
                break;

            case MainContainer.WORKMODE_POSTPROCESSING_THREE_AVERAGE:
                System.out.println("WORKMODE_POSTPROCESSING_THREE_AVERAGE");

                //TODO создать обработанный экземпляр "измерения"
                PostProcessorThreeAverage postProcessor = new PostProcessorThreeAverage();
                postprocessedMeasurement = postProcessor.doPostProcess(MainContainer.getListMeasurements().get(MainContainer.getListMeasurements().size() - 1));
                //переприсвоим последнее измерение на 
                MainContainer.getListMeasurements().set(MainContainer.getListMeasurements().size() - 1, postprocessedMeasurement);
                d = new Drawer(postprocessedMeasurement, dataset, true);
                d.startDrawing();
                break;

            case MainContainer.WORKMODE_POSTPROCESSING_THREE_SIMPLIFIED:
                System.out.println("WORKMODE_POSTPROCESSING_THREE_SIMPLIFIED");

                //TODO создать обработанный экземпляр "измерения"
                PostProcessorThreeSimple postProcessorSimple = new PostProcessorThreeSimple();
                postprocessedMeasurement = postProcessorSimple.doPostProcess(MainContainer.getListMeasurements().get(MainContainer.getListMeasurements().size() - 1));
                //переприсвоим последнее измерение на 
                MainContainer.getListMeasurements().set(MainContainer.getListMeasurements().size() - 1, postprocessedMeasurement);
                d = new Drawer(postprocessedMeasurement, dataset, true);
                d.startDrawing();
                break;
        }
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
                System.out.println("Error during trying to close fos after exception." + ex.getMessage());
            }
        }


    }

    public void menuItemLoadEtalonActionPerformed(ActionEvent evt) {
        Measurement measurement = null;
        try {
            FileInputStream fis = new FileInputStream(MainContainer.getDefaultEtalonMeasurementFilename());
            ObjectInputStream oin = new ObjectInputStream(fis);
            measurement = (Measurement) oin.readObject();

            //TestSerial ts = (TestSerial) oin.readObject();
            MainContainer.getListMeasurements().add(measurement);

            Drawer d = new Drawer(measurement, dataset, true);
            d.startDrawing();
        } catch (Exception ex) {
            System.out.println("Error during loading etalon." + ex.getMessage());
        }
    }

    public void connectMenuItemActionPerfomed(java.awt.event.ActionEvent evt) {
        SI30Counter si30Counter = MainContainer.getSi30Counter();
        if (MainContainer.getComPortPreferences() != null) {
            System.out.println("Using not standart com port pref." + MainContainer.getComPortPreferences().getPortName());
            ((ComPortConnection) si30Counter.getConnection()).setSerialPortPreferences(MainContainer.getComPortPreferences());
        }
        try {
            si30Counter.connect();
            System.out.println("Connected");
            Supervisor.startThatProcess();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }



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
        //TODO каждый раз создавать обьект идея не самая хорошая, может быть можно сделать лучше
        Double d = new Double(newSpeed);
        Double bd = new BigDecimal(d).setScale(2, RoundingMode.HALF_UP).doubleValue();
        //d=d/14.625; //40km=585. 585/40 = 14.625 
        System.out.println("Setting current speed:" + d);
        ((ValueIndicationPanel) rightIndicationPanel.innerPanelList.get(0)).setSpeedValue(bd.toString());
        //new BigDecimal(3.675f).setScale(2, RoundingMode.HALF_UP).floatValue()
        //String.format("%.2f", d)
        //((ToolPanel) toolPanel).setSpeedCaption(d.toString());
        //speed.setText("Speed: "+d.toString());
    }

    public void setAccelerateTimeCaption(double accTime) {
        Double a = new Double(accTime);
        a = a / 1000; //перевод из миллисекунд в секунды
        ((ValueIndicationPanel) rightIndicationPanel.innerPanelList.get(3)).setSpeedValue(a.toString());
        //третья индикационная панель - панель со значением времени разгона
    }

    public void setDecelerateTimeCaption(double decTime) {
        Double d = new Double(decTime);
        d = d / 1000; //перевод из миллисекунд в секунды
        ((ValueIndicationPanel) rightIndicationPanel.innerPanelList.get(4)).setSpeedValue(d.toString());
        //четвертая индикационная панель - панель со значением времени выбега
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

        tempSpeedIndicationPanel = new ValueIndicationPanel("0.0", "Время разгона, с");
        rightIndicationPanel.addElementPanel(tempSpeedIndicationPanel);
        tempSpeedIndicationPanel = new ValueIndicationPanel("0.0", "Время выбега, с");
        rightIndicationPanel.addElementPanel(tempSpeedIndicationPanel);

        /*tempSpeedIndicationPanel = new ValueIndicationPanel("40.0","t разгона");
         rightIndicationPanel.addElementPanel(tempSpeedIndicationPanel);
         tempSpeedIndicationPanel = new ValueIndicationPanel("100.0","t выбега");
         rightIndicationPanel.addElementPanel(tempSpeedIndicationPanel);*/

        //TODO исправить и еще один костыль
        //startAndStopButton = new JButton("Start");
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
