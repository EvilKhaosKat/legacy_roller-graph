package views.optionsframe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class OptionsFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTabbedPane preferencesContent;
	private JPanel buttons;
	private JButton cancelButton;
	private JButton applyButton;
	
	public OptionsFrame(String title) {
		super(title);
		preferencesContent = new JTabbedPane();
		buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.RIGHT));
		cancelButton = new JButton("Отмена");
		applyButton = new JButton("Ок");
		buttons.add(cancelButton);
		buttons.add(applyButton);
		getContentPane().setLayout(new BorderLayout());
		add(preferencesContent, BorderLayout.CENTER);
		add(buttons, BorderLayout.PAGE_END);
		preferencesContent.addTab("Com settings", new SerialPortSettings());
		pack();
	}
}
