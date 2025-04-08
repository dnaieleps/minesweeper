package minesweeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class Homescreen implements ActionListener{
	public JSlider mineSlider, sizeSlider; 
	public static JFrame home; 
	public JButton playbutton;
	
	Homescreen() {
		home = new JFrame("Minesweeper!!"); 
		home.setSize(500, 500);
		home.setLocationRelativeTo(null);
		home.setLayout(null);
		home.getContentPane().setBackground(Color.LIGHT_GRAY);
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel title = new JLabel("Minesweeper", SwingConstants.CENTER);
		title.setBounds((home.getWidth()-200)/2, 80, 200, 50);
		title.setFont(new Font("Times New Roman", Font.BOLD, 35));
		
		mineSlider = new JSlider(5, 30); 
		mineSlider.setBounds((home.getWidth()-100)/2-100, (home.getHeight()-50)/2-30, 100, 50);
		mineSlider.setSnapToTicks(true);
		mineSlider.setPaintTicks(true);
		mineSlider.setMajorTickSpacing(5);
		JLabel mineLabel = new JLabel("% of mines", SwingConstants.CENTER); 
		mineLabel.setBounds((home.getWidth()-100)/2-100, (home.getHeight()-30)/2-70, 100, 30); 
		mineLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		sizeSlider = new JSlider(8, 20);
		sizeSlider.setBounds((home.getWidth()-100)/2+100, (home.getHeight()-50)/2-30, 100, 50);
		sizeSlider.setSnapToTicks(true);
		sizeSlider.setPaintTicks(true);
		sizeSlider.setMajorTickSpacing(2);
		JLabel sizeLabel = new JLabel("board dimensions", SwingConstants.CENTER);
		sizeLabel.setBounds((home.getWidth()-150)/2+100, (home.getHeight()-30)/2-70, 150, 30);
		sizeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		playbutton = new JButton("PLAY");
		playbutton.setBounds((home.getWidth()-200)/2, (home.getHeight()-50)/2+50, 200, 50);
		playbutton.addActionListener(this);
		
		home.add(title);
		home.add(mineSlider);
		home.add(mineLabel);
		home.add(sizeSlider);
		home.add(sizeLabel);
		home.add(playbutton);
		
		home.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		home.dispose();
		new Minesweeper(mineSlider.getValue(), sizeSlider.getValue());
	}
}
