package Package;

import java.awt.Font;
import java.util.*;

public class Main {
	


	private static final boolean DEBUG = false;
  static Random rn = new Random();
	static int EMPTY = 0;
	static int move=0; //even number is X's move, odd number is odd's move

	public static void main(String[] args) {

		// Allocate identifiers to represent game state
		//
		// Using an array of int so that summing along a row, a column or a
		// diagonal is a easy test for a win
	  int EMPTY = 0;
		final int X_SHAPE = 1;
		final int O_SHAPE = -1;
		int[][] board = new int[3][3]; //3dimensional array
		int col = 0, row = 0;
		
		
		// Setup graphics and draw empty board
		StdDraw.setPenRadius(0.04);							// draw thicker lines
		StdDraw.line(0, 0.33, 1, 0.33);
		StdDraw.line(0, 0.66, 1, 0.66);
		StdDraw.line(0.33, 0, 0.33, 1.0);
		StdDraw.line(0.66, 0, 0.66, 1.0);
		StdDraw.setFont(new Font("SansSerif", Font.PLAIN,75)); //Sets to a nicer font
		  
		  
		

			// use mouse position to get slot
			do {
				
				//At the start of each turn whoevers move it is is printed out
				if(move %2 == 0) //Using modulus finds who's move it is currently 
				{
					
					System.out.println("\tHuman move ...");
					boolean mousePressed = false;
					do {
						
						if (StdDraw.mousePressed()) {
							col = (int) (StdDraw.mouseX() * 3);	
							row = (int) (StdDraw.mouseY() * 3);
							mousePressed = true;
						}
					 //If the mouse hasnt been pressed or the box clicked is not empty
					}while(!mousePressed || board[row][col] != EMPTY); 
					
				}	
				else
				{
					System.out.println("\tComputers move ...");
					
					int[] placeHolder = computersTurn(board);
					row = placeHolder[0];
					col = placeHolder[1];
				}
				board[row][col] = (move % 2 == 0 ? X_SHAPE : O_SHAPE); //Sets value of Position [row][col] to X_Shape(1)
				
				double x = col * .33 + 0.15; //Calculates the x pos. of where to draw
				double y = row * .33 + 0.15; //Calculates the y pos. of where to draw

				StdDraw.text(x, y, (move % 2 == 0 ? "X" : "O")); //Draws an X OR O depending on move number
				move++;
			}while(whoWon(board) == 0 && !isBoardFull(move));
			
			System.out.println("GAME OVER)");
			if(whoWon(board) == 0)
			{
				System.out.println("We have a Draw!");
			}
			else if(whoWon(board) == 1)
			{
				System.out.println("You have beaten the computer!!");
			}
			else{
				System.out.println("The Computer has won!");
			}
	}
	
	public static int[] computersTurn(int[][] ph)
	{
		int r=0, c=0;
		
		for(int x = 0;x<3;x++)
		{
			int sum = ph[x][0] + ph[x][1] + ph[x][2];
			int sum2 = ph[0][x] + ph[1][x] + ph[2][x];
			int sum3 = ph[0][0] + ph[1][1]+ ph[2][2];
			int sum4 = ph[0][2] + ph[1][1] + ph[2][0];
			if(sum == -2 || sum == 2) 
			{
				r = x;
				if(ph[x][0] == EMPTY){c=0; break;}
				if(ph[x][1] == EMPTY){c=1; break;}
				if(ph[x][2] == EMPTY){c=2; break;}
			}
			else if(sum2 == -2 || sum2 == 2) 
			{
				
				if(ph[0][x] == EMPTY){r=0; break;}
				if(ph[1][x] == EMPTY){r=1; break;}
				if(ph[2][x] == EMPTY){r=2; break;}
			}
			else if(sum3 == -2 || sum3 == 2) 
			{
				if(ph[0][0] == EMPTY){r=0;c=0; break;}
				if(ph[1][1] == EMPTY){r=1;c=1; break;}
				if(ph[2][2] == EMPTY){r=2;c=2; break;}
			}
			else if(sum4 == -2 || sum4 == 2) 
			{
				if(ph[0][2] == EMPTY){r=0;c=2; break;}
				if(ph[1][1] == EMPTY){r=1;c=1; break;}
				if(ph[2][0] == EMPTY){r=2;c=0; break;}
			}
			else {
				do{
				r = rn.nextInt(3);
				c = rn.nextInt(3);
					}	while(ph[r][c] != EMPTY);
			}
		}
		int position[] = {r,c}; //Sets the position in which the computer will make its move
		return position;
	}
	
	public static boolean isBoardFull(int moveCount)
	{
		return moveCount == 9;
	}
	
	public static int whoWon(int[][] ph)
	{
		int j = 0;
		for(int x = 0;x<3;x++)
		{
			int sum = ph[x][0] + ph[x][1] + ph[x][2];
			
			int sum2 = ph[0][x] + ph[1][x] + ph[2][x];
			int sum3 = ph[0][0] + ph[1][1]+ ph[2][2];
			if (sum2 == 3 || sum == 3 || sum3 == 3) { j = 1; break;}
			if (sum2 == -3 || sum == -3 || sum3 == -3) { j = -1; break;}
			
		}
		
		return j;
	}
	
}
