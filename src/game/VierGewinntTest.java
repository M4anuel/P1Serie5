package game;

import java.util.Arrays;

public class VierGewinntTest{
    public static final int COLS = 7;
    public static final int ROWS = 6;

    public static void main(String[] args) {
        Token[][] board = new Token[ COLS ][ ROWS ]; // 7 columns with 6 fields each
        for ( Token[] column : board ) {
            Arrays.fill( column, Token.empty );
        }
        IPlayer[] players = new IPlayer[ 2 ]; // two players
        board[0][0]=Token.player1;
        board[0][1]=Token.player1;
        board[0][2]=Token.player1;
        board[6][0]=Token.player1;
        board[5][0]=Token.player1;
        board[4][0]=Token.player1;
        board[1][1]=Token.player1;
        board[2][2]=Token.player1;
        board[3][0]=Token.player2;
        board[3][1]=Token.player2;
        board[3][2]=Token.player2;
        System.out.println(displayBoard(board));
    }
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
}