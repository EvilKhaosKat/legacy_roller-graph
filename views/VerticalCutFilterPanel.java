/**
 * 
 */
package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * @author LAP
 *
 */
public class VerticalCutFilterPanel extends JPanel{
	private JLabel leftCountLabel;
	private JLabel rightCountLabel;
	private JSpinner leftCutSpinner;
	private JSpinner rightCutSpinner;
	
	public VerticalCutFilterPanel() {
		leftCountLabel = new JLabel("Количество с лева:");
		rightCountLabel = new JLabel("Количество справа:");
		leftCutSpinner = new JSpinner(new SpinnerNumberModel());
		rightCutSpinner = new JSpinner(new SpinnerNumberModel());
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets.right = 2;
		gbc.insets.top = 2;
		gbc.weightx = 1;
		gbc.weighty = 0;
		add(leftCountLabel, gbc);
		gbc.gridy++;
		add(leftCutSpinner, gbc);
		gbc.gridy++;
		add(rightCountLabel, gbc);
		gbc.gridy++;
		gbc.weighty = 1;
		add(rightCutSpinner, gbc);
	}
	
	public int getLeftCutCount() {
		return (Integer)leftCutSpinner.getModel().getValue();
	}
	
	public int getRightCutCount() {
		return (Integer)rightCutSpinner.getModel().getValue();
	}
}
