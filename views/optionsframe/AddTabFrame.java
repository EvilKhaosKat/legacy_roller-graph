package views.optionsframe;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;

/**
 * 
 */

/**
 * @author LAP
 *
 */
public class AddTabFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel tabNameLabel;
	private JTextField tabNameText;
	private JScrollPane treeScrollPane;
	private JTree seriesDataTree;
	private JSplitPane dataPreferencesSplitter;
	private JPanel seriesPreferencesPanel;
	private JPanel buttonsPanel;
	private JButton applyButton;
	private JButton cancelButton;

	public AddTabFrame() {
		super("Добавить вкладку");
		GridBagConstraints gbc = new GridBagConstraints();
		getContentPane().setLayout(new GridBagLayout());
		tabNameLabel = new JLabel("Название вкладки:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.insets.bottom = 2;
		gbc.insets.right = 5;
		gbc.insets.top = 2;
		getContentPane().add(tabNameLabel, gbc);
		gbc.gridx++;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		tabNameText = new JTextField();
		getContentPane().add(tabNameText, gbc);
		gbc.insets.top = 0;
		gbc.weightx = 0;
		gbc.gridx--;
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		seriesDataTree = new JTree();
		treeScrollPane = new JScrollPane(seriesDataTree);
		seriesPreferencesPanel = new JPanel();
		dataPreferencesSplitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, seriesPreferencesPanel);
		getContentPane().add(dataPreferencesSplitter, gbc);
		gbc.gridy++;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_END;
		buttonsPanel = new JPanel();
		applyButton = new JButton("Принять");
		cancelButton = new JButton("Отмена");
		buttonsPanel.add(applyButton);
		buttonsPanel.add(cancelButton);
		((FlowLayout)buttonsPanel.getLayout()).setAlignment(FlowLayout.RIGHT);
		getContentPane().add(buttonsPanel, gbc);
	}
}
