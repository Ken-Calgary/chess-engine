package pieces;

import java.awt.image.BufferedImage;

import main.Board;

/**
 * Knight class that extends the Piece abstract class
 * 
 * @author Ken Liu
 * @version 1.0
 * @since 2023-11-16
 */
public class Knight extends Piece{
	/**
	 * Knight constructor that creates a Knight piece
	 * 
	 * @param board Set the board as the parent
	 * @param col	Set the Knight vertical position
	 * @param row	Set the Knight horizontal position
	 * @param isWhite	Set the color of the piece
	 */
	public Knight(Board board, int col, int row, boolean isWhite) {
		super(board);
		this.column = col;
		this.row = row;
		this.xPos = col * board.tileSize;
		this.yPos = row * board.tileSize;
		this.isWhite = isWhite;
		this.name = "Knight";
		
		// Grabs the knight in the "ChessPieces.png" and sizes it to fit the board
		this.sprite = sheet.getSubimage(3 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
	}
	
	/**
	 * Checks if its a valid move made by Knight
	 * 
	 * @param column
	 * @param row
	 * @return boolean
	 */
	public boolean isValidMovement(int column, int row) {
		return Math.abs(column - this.column) * Math.abs(row - this.row) == 2;
	}
}
