/*
 * 445 Database
 * Ariel McNamara and Audrey
 */

package BossViews;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Color;

public class StatisticsView extends JPanel {

	/**
	 * Create the panel.
	 */
	public StatisticsView() {

		// Size of layoutPane W: 788, H: 424
		setBounds(0, 0, 788, 424);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 102, 0));
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(255, 102, 0));
		panel.add(panel_2, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 153, 51));
		panel_1.setBounds(0, 11, 561, 78);
		panel_2.add(panel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(153, 153, 51));
		panel_3.setBounds(0, 101, 561, 302);
		panel_2.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 153, 51));
		panel_4.setBounds(571, 11, 217, 392);
		panel_2.add(panel_4);

	}
}
