/* ************************************************************************* *\
*                Programmierung 1 HS 2020 - Serie 5-1                         * 
\* ************************************************************************* */
package game;

import java.util.Arrays;

/** A very stupid computer player */
public class ComputerPlayer implements IPlayer
{
	private Token token;

	/**a
	 * Strategy is to chose a random column and select
	 * the next valid column to the right (the chosen included)
	 */
	public int getNextColumn( Token[][] board ){
		//Was der ComputerPlayer spielen kann
		if(findBestColumn(board)!=-1){
			return findBestColumn(board);
		} else {
			java.util.Random generator = new java.util.Random();
			int col = generator.nextInt(board.length);
			int step = 0;
			while (isColFull(col, board)) {
				col = (col + 1) % board.length;
			}
			return col;
		}
	}

	public int findBestColumn(Token[][] board ){
		//Was der ComputerPlayer spielen kann
		int[] possibilities = new int[7];
		for(int i=0; i<board.length; i++){
			for (int j = 0; j < board[0].length; j++){
				if(board[i][j]==Token.empty){
					possibilities[i]=j;
					break;
				}
			}
		}
		//rund um jeden Spot wird gezählt, wieviele von seinen Tokens in einer Linie liegen
		//die längste Linie wird in den Array strengh gespeichert
		System.out.println(Arrays.toString(possibilities));
		int[] strengh = new int[7];
		for(int i=0; i<board.length;i++) {
			int c0= 0, c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0, c6 = 0;
			for (int j=1; j <= 3; j++) {
				if (j == c0 + 1 && possibilities[i] - j >= 0 && board[i][possibilities[i] - j] == Token.player2) {//nach unten
					c0++;
				} else if (j == c1 + 1 && i - j >= 0 && board[i - j][possibilities[i]] == Token.player2) {//nach links
					c1++;
				} else if (j == c2 + 1 && i + j < board.length && board[i + j][possibilities[i]] == Token.player2) {//nach rechts
					c2++;
				} else if (j == c3 + 1 && i - j >= 0 && possibilities[i] - j >= 0 && board[i - j][possibilities[i] - j] == Token.player2) {//nach unten links
					c3++;
				} else if (j == c4 + 1 && i + j < board.length && possibilities[i] - j >= 0 && board[i + j][possibilities[i] - j] == Token.player2) {//nach unten rechts
					c4++;
				} else if (j == c5 + 1 && i - j >= 0 && possibilities[i] + j < board[0].length && board[i - j][possibilities[i] + j] == Token.player2) {//nach oben links
					c5++;
				} else if (j == c6 + 1 && i + j < board.length && possibilities[i] + j < board[0].length && board[i + j][possibilities[i] + j] == Token.player2) {//nach oben rechts
					c6++;
				}
			}

			if (c0 < c1) c0 = c1;
			if (c0 < c2) c0 = c2;
			if (c0 < c3) c0 = c3;
			if (c0 < c4) c0 = c4;
			if (c0 < c5) c0 = c5;
			if (c0 < c6) c0 = c6;
			strengh[i] = c0;
		}
		System.out.println(Arrays.toString(strengh));
		//die längste linie Aller Spots wird in int strongest gespeichert
		//die Position von der Zahl strongest im Array wird zurückgegeben
		//Falls es ein Fehler gäbe gibt es -1 zurück und der Computer spielt nach zufall
		int strongest=0;
		for (int j : strengh) {
			if (strongest < j) strongest = j;
		}
		for(int i=0; i<strengh.length;i++) {
			if (strongest == strengh[i]) {
				System.out.println("i: ");
				System.out.println(i);
				return i;
			}
		}
		return -1;
	}


	/**
	 * @return true if the column col is already full and false otherwise.
	 */
	private boolean isColFull( int col, Token[][] board )
	{
		int topRow = board[ col ].length - 1;
		return ( board[ col ][ topRow ] != Token.empty );
	}


	public void setToken( Token token )
	{
		this.token = token;
	}

	public Token getToken()
	{
		return this.token;
	}

	public String getPlayersName()
	{
		return "Random Player";
	}
}