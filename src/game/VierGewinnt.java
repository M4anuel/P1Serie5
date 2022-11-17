package game;/* ************************************************************************* *\
*                Programmierung 1 HS 2020 - Serie 5-1                         * 
\* ************************************************************************* */
import java.util.Arrays;
import java.util.Scanner;
public class VierGewinnt
{
	public static final int COLS = 7;
	public static final int ROWS = 6;
	private Token[][] board = new Token[ COLS ][ ROWS ]; // 7 columns with 6 fields each
	private IPlayer[] players = new IPlayer[ 2 ]; // two players
	/** initialize board and players and start the game */
	public void play()
	{
		// initialize the board
		for ( Token[] column : this.board ) {
			Arrays.fill( column, Token.empty );
		}
		/* initialize players */
		players[ 0 ] = new HumanPlayer();
		System.out.print( "Play against a human opponent? (y / n) " );
		String opponent = new Scanner( System.in ).nextLine().toLowerCase();
		while ( ( 1 != opponent.length() ) || ( -1 == ( "yn".indexOf ( opponent ) ) ) ) {
			System.out.print( "Can't understand your answer. Play against a human opponent? (y / n) " );
			opponent = new Scanner( System.in ).nextLine().toLowerCase();
		}
		if ( opponent.equals( "y" ) ) {
			players[ 1 ] = new HumanPlayer();
		} else {
			players[ 1 ] = new ComputerPlayer();
		}
		players[ 0 ].setToken( Token.player1 );
		players[ 1 ].setToken( Token.player2 );
		/* play... */
		boolean solved = false;
		int currentPlayer = ( new java.util.Random() ).nextInt( 2 );  //choose randomly who begins
		System.out.println( "current player: " + players[currentPlayer].getPlayersName()+" and he will have the symbol "+players[currentPlayer].getToken());
		int insertCol, insertRow; // starting from 0
		while ( !solved && !this.isBoardFull() ) {
			// get player's next "move"
			// note that we pass only a copy of the board as an argument,
			// otherwise the player would be able to manipulate the board and cheat!
			insertCol = players[ currentPlayer ].getNextColumn( getCopyOfBoard() ); //gets column-1 for array access, checks if it's even possible to insert
			// insert the token and get the row where it landed
			insertRow = this.insertToken( insertCol, players[ currentPlayer ].getToken() );
			// check if the game is over

			solved = this.checkVierGewinnt( insertCol, insertRow );
			//switch to other player
			if ( !solved )
				currentPlayer = ( currentPlayer + 1 ) % 2;
		}
		System.out.println( displayBoard( this.board ) );
		if ( solved )
			System.out.println( "Player " + players[ currentPlayer ].getToken() + " wins!" );
		else
			System.out.println( "Draw! Game over." );
	}
	/**
	 * Inserts the token at the specified column (if possible)
	 * @param column the column to insert the token
	 * @param tok the players token
	 * @return the row where the token landed
	 */
	private int insertToken( int column, Token tok ) {
		for (int i = 0; i < this.board[column].length; i++){
			if (this.board[column][i]==Token.empty){
				this.board[column][i] = tok;
				return i;
			}
		}
		return 0;
	}


	/**
	 * Checks if every position is occupied
	 * @returns true, if the board is full.
	 */
	private boolean isBoardFull() {
		for (Token[] columns : this.board) {
			for (Token tokens : columns){
				if (tokens == Token.empty) {
					return false;
				}
			}
		}
		return true;
	}


	/**
	 * Checks for at least four equal tokens in a row in
	 * either direction, starting from the given position.
	 */

	/**
	 * Checks for at least four equal tokens in a row in
	 * either direction, starting from the given position.
	 */
	private boolean checkVierGewinnt( int col, int row ){
		Token tok = this.board[col][row];
		return checkColumnWin(col, row, tok)|| checkRowWin(col,row,tok)||checkDiagonalWin(col,row,tok);
	}
	private boolean checkColumnWin(int col, int row, Token tok){
		int inrow = 1;
		//only has to check downwards, since you'll never have placed tokens above the one you just put in
		for (int i = 1; i<row+1&&i<4;i++){
			if (this.board[col][row-i]==tok){
				inrow++;
			}
			else{i = row+1;}
		}
		return inrow >= 4;
	}
	private boolean checkRowWin(int col, int row, Token tok){
		int inrow = 1;
		for (int i = 1; i<this.board.length-col && i<4;i++){//test-1 for bug
			if (this.board[col+i][row]==tok){
				inrow++;
			}
			else break;
			if(inrow >=4) break;
		}
		for (int i = 1; i<col+1&&i<4;i++){
			if (this.board[col-i][row]==tok){
				inrow++;
			}
			else break;
			if(inrow >=4) break;
		}
		return inrow>=4;
	}
	private boolean checkDiagonalWin(int row, int col, Token tok){
		return checkBLTR(row,col,tok)||checkBRTL(row,col,tok);
	}
	//BLTR = Bottom Left to Top Right
	private boolean checkBLTR(int row, int col, Token tok){
		int inrow = 1;
		//top right direction (in the output it's the bottom right direction)
		for (int i = 1; i < 4 && i < COLS-col-1 && i < row; i++){
			if (this.board[col+i][row-i]==tok){
				inrow++;
			}
			else break;
		}
		//bottom left direction
		for (int i = 1; i < 4 && i < col+1 && i < ROWS-row-1; i++){
			if (this.board[col+i][row-i]==tok){
				inrow++;
			}
			else break;
		}
		if (inrow >= 4) System.out.println("BLTR");
		return inrow>=4;
		/*
		for (int i = 1; i<this.board.length-row&&i<this.board[col].length;i++){
			try{
				if (this.board[col+i][row+i]==tok){
					inrow++;
				}
				else{i = this.board.length;}
			}catch (ArrayIndexOutOfBoundsException ignored){i=this.board.length;}
		}
		for (int i = 1; i<col+1&&i<row+1;i++){
			if (this.board[col-i][row-i]==tok){
				inrow++;
			}
			else{i = col+1;}
		}
		return inrow>=4;
		 */
	}
	//BRTL = Bottom Right to Top Left
	private boolean checkBRTL(int row, int col, Token tok){
		int inrow = 1;
		//top left direction
		for (int i = 1; i < 4 && i < col+1 && i < row; i++){
			if (this.board[col-i][row-i]==tok){
				inrow++;
			}
			else break;
		}
		//bottom right direction
		for (int i = 1; i < 4 && i < COLS-col-1 && i < ROWS-row; i++){
			if (this.board[col+i][row+i]==tok){
				inrow++;
			}
			else break;
		}
		if (inrow >= 4) System.out.println("BRTL");
		return inrow>=4;
		/*
		for (int i = 1; i<(this.board[col].length)-row && i<(this.board.length)-col; i++){
			try{
			if (this.board[col-i][row+i]==tok){
				inrow++;
			}
			else{i=this.board.length;}
			}catch (ArrayIndexOutOfBoundsException ignored){i=this.board.length;}
		}
		for (int i = 1; i<row && i<col; i++){
			if (this.board[col+i][row-i]==tok){
				inrow++;
			}
			else{i=row;}
		}
		 */
	}



	/** Returns a (deep) copy of the board array */










	private Token[][] getCopyOfBoard()
	{
		Token[][] copiedBoard = new Token[ COLS ][ ROWS ];
		for ( int i = 0; i < copiedBoard.length; i++ ) {
			for ( int j = 0; j < copiedBoard[ i ].length; j++ ) {
				copiedBoard[ i ][ j ] = this.board[ i ][ j ];
			}
		}
		return copiedBoard;
	}
	/** returns a graphical representation of the board */
	public static String displayBoard( Token[][] myBoard )
	{
		String rowDelimiter = "+";
		String rowNumbering = " ";
		for ( int col = 0; col < myBoard.length; col++ ) {
			rowDelimiter += "---+";
			rowNumbering += " " + ( col + 1 ) + "  ";
		}
		rowDelimiter += "\n";
		String rowStr;
		String presentation = rowDelimiter;
		for ( int row = myBoard[ 0 ].length - 1; row >= 0; row-- ) {
			rowStr = "| ";
			for ( int col = 0; col < myBoard.length; col++ ) {
				rowStr += myBoard[ col ][ row ].toString() + " | ";
			}
			presentation += rowStr + "\n" + rowDelimiter;
		}
		presentation += rowNumbering;
		return presentation;
	}
	/** main method, starts the program */
	public static void main( String args[] )
	{
		VierGewinnt game = new VierGewinnt();
		game.play();
	}
}