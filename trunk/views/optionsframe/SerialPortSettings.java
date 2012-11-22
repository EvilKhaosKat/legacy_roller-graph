package views.optionsframe;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import connection.ComPortPreferences;


public class SerialPortSettings extends JPanel {
	private static final long serialVersionUID = 1L;
	private JComboBox<String> serialPorts;
	private JComboBox<String> speeds;
	private JComboBox<String> dataBits;
	private JComboBox<String> parity;
	private JComboBox<String> stopBits;
	
	public SerialPortSettings() {
		super();
		serialPorts = new JComboBox<String>();
		speeds = new JComboBox<String>();
		dataBits = new JComboBox<String>();
		parity = new JComboBox<String>();
		stopBits = new JComboBox<String>();
		setLayout(new GridBagLayout());
		createGUI();
	}
	
	private void createGUI() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		
		add(new JLabel("Доступные com порты"), gbc);
		setSerialPortsContent();
		gbc.gridy++;
		gbc.fill = GridBagConstraints.BOTH;
		add(serialPorts, gbc);
		gbc.gridy++;
		
		add(new JLabel("Скорость соединения"), gbc);
		setSpeedsContent();
		gbc.gridy++;
		add(speeds, gbc);
		gbc.gridy++;
		
		add(new JLabel("Биты данных"), gbc);
		gbc.gridy++;
		setDataBitsContent();
		add(dataBits, gbc);
		gbc.gridy++;
		
		add(new JLabel("Биты чётности"),gbc);
		gbc.gridy++;
		setParityBitsContent();
		add(parity, gbc);
		gbc.gridy++;
		
		add(new JLabel("Стоп биты"), gbc);
		gbc.gridy++;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTH;
		setStopBitsContent();
		add(stopBits, gbc);
	}
	
	private void setSerialPortsContent() {
		List<String> serialPortsList = ComPortPreferences.getComPortsAsStringList();
		
		for (String serialPortName : serialPortsList) {
			serialPorts.addItem(serialPortName);
		}
	}

	private void setSpeedsContent() {
		// Скорость должна быть здесь
		speeds.addItem("115200");
	}
	
	private void setDataBitsContent() {
		dataBits.addItem("4 data bits");
		dataBits.addItem("5 data bits");
		dataBits.addItem("6 data bits");
		dataBits.addItem("7 data bits");
		dataBits.addItem("8 data bits");
		dataBits.setSelectedIndex(4);
	}
	
	private void setParityBitsContent() {
		//TODO добавить parity bits, хотя бы SerialPort.PARITY_NONE; его будет достаточно
	}
	
	private void setStopBitsContent() {
		stopBits.addItem("1");
		stopBits.addItem("1.5");
		stopBits.addItem("2");
	}

	/**
	 * @return the serialPorts
	 */
	public JComboBox<String> getSerialPorts() {
		return serialPorts;
	}

	/**
	 * @return the speeds
	 */
	public JComboBox<String> getSpeeds() {
		return speeds;
	}

	/**
	 * @return the dataBits
	 */
	public JComboBox<String> getDataBits() {
		return dataBits;
	}

	/**
	 * @return the parity
	 */
	public JComboBox<String> getParity() {
		return parity;
	}

	/**
	 * @return the stopBits
	 */
	public JComboBox<String> getStopBits() {
		return stopBits;
	}
}
