package game;
/* ************************************************************************* *\
*                Programmierung 1 HS 2020 - Serie 5-1                         * 
\* ************************************************************************* */

/** Interface for game.VierGewinnt players */
public interface IPlayer
{

	/**
	 * @return the name(s) of the programmer(s) or a team name.
	 */
	String getPlayersName();

	/**
	 * @presumes the board is not full 
	 * @return the next move (number of column, starting from 0) of the player 
	 */
	int getNextColumn( Token[][] board );

	/**
	 * Assign the player a token (e.g. 'X' or 'O') 
	 * @param token the game.Token the player should use
	 */
	void setToken( Token token );

	/**
	 * @return the token assigned to the player or null, if not yet set.
	 */
	Token getToken();
}
