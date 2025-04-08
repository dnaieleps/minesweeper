package minesweeper;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Mine {
	private JButton cover; 
	private JLabel surroundingLabel; 
	private int surrounding, row, col; 
	private boolean bomb; 
	
	// tiles will always have a set size of 30x30 pixels
	Mine(int row, int col){
		cover = new JButton();
		cover.setSize(30, 30);
		cover.addActionListener(e -> checkMine());
		
		surrounding = 0; 
		bomb = false; 
		this.row = row; 
		this.col = col; 
		
		surroundingLabel = new JLabel("", SwingConstants.CENTER);
		surroundingLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		surroundingLabel.setSize(30, 30);
	}
	
	public void checkMine() {
		if(getSurroundingLabel().getText().equals("@")) {
			Minesweeper.gameOver();
		} else if (getSurrounding() == 0) {
			hideCover();
			updateSurroundings(getRow(), getCol());
		} else if (!getSurroundingLabel().getText().equals("@")){ 
			hideCover();
		} 
	}
	
	private void updateSurroundings(int row, int col) {
		int i = row; int j = col; Mine[][] field = Minesweeper.board; 
		
		if(i - 1 >= 0) {
			if(j - 1 >= 0 && !visited(i-1, j-1)) hideAndUpdate(i-1, j-1);
			if(j + 1 < field[i].length && !visited(i-1, j+1)) hideAndUpdate(i-1, j+1);
			if(!visited(i-1, j)) hideAndUpdate(i-1, j);
			
		}
		
		if(j - 1 >= 0 && !visited(i, j-1)) hideAndUpdate(i, j-1);
		if(j + 1 < field[i].length && !visited(i, j+1)) hideAndUpdate(i, j+1);
		
		if(i + 1 < field.length) {
			if(j - 1 >= 0 && !visited(i+1, j-1)) hideAndUpdate(i+1, j-1);
			if(j + 1 < field[i].length && !visited(i+1, j+1)) hideAndUpdate(i+1, j+1);
			if(!visited(i+1, j)) hideAndUpdate(i+1, j);
			
		}
	}
	private boolean visited(int row, int col) { 
		return !Minesweeper.board[row][col].getCover().isEnabled();
	}
	private void hideAndUpdate(int row, int col) {
		Minesweeper.board[row][col].hideCover(); 
		if(Minesweeper.board[row][col].surrounding == 0) {
			updateSurroundings(row, col); 
		}
	}


	public int getRow() {
		return row; 
	}
	public int getCol() {
		return col;
	}
	public JButton getCover() {
		return cover; 
	}
	public JLabel getSurroundingLabel() {
		return surroundingLabel; 
	}
	public int getSurrounding() { 
		return surrounding;
	}
	public boolean isBomb() { 
		return bomb;
	}
	
	
	public void setSurrounding(int s) { 
		surrounding = s; 
	}
	public void setBomb(boolean b) {
		bomb = b; 
	}
	public void setSurroundingLabel(Color c) {
		getSurroundingLabel().setBackground(c);
	}
	public void setSurroundingLabel(int s) {
		getSurroundingLabel().setText(Integer.toString(s)); 
	}
	public void setSurroundingLabel(String s) {
		getSurroundingLabel().setText(s);
	}
	public void incrementSurrounding(int amount) {
		setSurrounding(getSurrounding()+amount);
		setSurroundingLabel(getSurrounding());
	}
	public void hideCover() {
		cover.setVisible(false); 
		cover.setEnabled(false);
	}
}
