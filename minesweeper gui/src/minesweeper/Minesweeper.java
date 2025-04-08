package minesweeper;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class Minesweeper {
	public static JFrame game; 
	private int minecount; 
	public static Mine[][] board;
	
	Minesweeper(int percentage, int dimensions){ 
		game = new JFrame("Minesweeper!!");
		game.setSize(30*dimensions, 30*dimensions);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setLayout(new GridLayout(dimensions, dimensions));
		game.setLocationRelativeTo(null);
		
		board = new Mine[dimensions][dimensions]; 
		minecount = (dimensions*dimensions*percentage)/100;
		
		setBoard(dimensions);
		setMines(minecount);
		setSurroundings();
		
		game.setVisible(true);
	}
	
	// initializes board with empty mines
	private void setBoard(int dimensions) {
		for(int i = 0; i < dimensions; i++) {
			for(int j = 0; j < dimensions; j++) {
				JLayeredPane holder = new JLayeredPane();
				holder.setBackground(new Color(0x888f8a));
				holder.setOpaque(true);
				Mine tempMine = new Mine(i, j);
				holder.add(tempMine.getSurroundingLabel(), 1);
				holder.add(tempMine.getCover(), 0);
				board[i][j] = tempMine;
				
				game.add(holder);
			}
		}
	}
	
	// initializes the mines on the board
	private void setMines(int minecount) { 
		while(minecount > 0) {
			Random random = new Random(); 
			int row = random.nextInt(0, board.length); 
			int col = random.nextInt(0, board[0].length);
			
		   /* ensures that: 
		    * 1. the random mine chosen is not already a bomb 
			* 2. the random mine chosen is not already fully surrounded by bombs
			*/
			if(!board[row][col].isBomb()) { 
				board[row][col].setBomb(true);
				board[row][col].setSurroundingLabel("@");
				board[row][col].setSurroundingLabel(Color.red);
				board[row][col].getSurroundingLabel().setOpaque(true);
				minecount--; 
			}
		}
	}
	
	// sets the numbers on the board depending on how many mines surround an empty spot
	private void setSurroundings() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j].isBomb()) { 
					if(i - 1 >= 0) {
						if(j - 1 >= 0 && !board[i-1][j-1].isBomb()) board[i-1][j-1].incrementSurrounding(1);
						if(j + 1 < board[i].length && !board[i-1][j+1].isBomb()) board[i-1][j+1].incrementSurrounding(1);
						if(!board[i-1][j].isBomb()) board[i-1][j].incrementSurrounding(1);
					}
					
					if(j - 1 >= 0 && !board[i][j-1].isBomb()) board[i][j-1].incrementSurrounding(1);
					if(j + 1 < board[i].length && !board[i][j+1].isBomb()) board[i][j+1].incrementSurrounding(1); 
					
					if(i + 1 < board.length) {
						if(j - 1 >= 0 && !board[i+1][j-1].isBomb()) board[i+1][j-1].incrementSurrounding(1);
						if(j + 1 < board[i].length && !board[i+1][j+1].isBomb()) board[i+1][j+1].incrementSurrounding(1);
						if(!board[i+1][j].isBomb()) board[i+1][j].incrementSurrounding(1);
					}
				}
			}
		}
	}
	
	public static void gameOver() { 
		for(Mine[] m : board) {
			for(Mine mine : m) {
				mine.hideCover(); 
			}
		}
	}
}
