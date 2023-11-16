package pieces;

import java.awt.image.BufferedImage;

import main.Board;

/**
 * Bishop class that extends the Piece abstract class
 * 
 * @author Ken Liu
 * @version 1.0
 * @since 2023-11-16
 */
public class Bishop extends Piece{
	/**
	 * Bishop constructor that creates a bishop piece
	 * 
	 * @param board Set the board as the parent
	 * @param col	Set the Bishop vertical position
	 * @param row	Set the Bishop horizontal position
	 * @param isWhite	Set the color of the piece
	 */
	public Bishop(Board board, int col, int row, boolean isWhite) {
		super(board);
		this.column = col;
		this.row = row;
		this.xPos = col * board.tileSize;
		this.yPos = row * board.tileSize;
		this.isWhite = isWhite;
		this.name = "Bishop";
		// Grabs the bishop in the "ChessPieces.png" and sizes it to fit the board
		this.sprite = sheet.getSubimage(2 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
	}
	
	/**
	 * Checks if its a valid move made by Bishop
	 * 
	 * @param column
	 * @param row
	 * @return boolean
	 */
	public boolean isValidMovement(int column, int row) {
		return Math.abs(this.column - column) == Math.abs(this.row - row);
	}
	
	/**
	 * Checks if the Bishop is being blocked diagonally
	 * 
	 * @param column	
	 * @param row
	 * @return boolean 
	 */
	public boolean moveCollidesWithPiece(int column, int row) {
		// up left
		if (this.column > column && this.row > row) {
			for (int i = 1; i < Math.abs(this.column - column); i++) {
				if(board.getPiece(this.column - i, this.row - i) != null) {
					return true;
				}
			}
		}
		
		// up right
		if (this.column < column && this.row > row) {
			for (int i = 1; i < Math.abs(this.column - column); i++) {
				if(board.getPiece(this.column + i, this.row - i) != null) {
					return true;
				}
			}
		}
		
		// down left
		if (this.column > column && this.row < row) {
			for (int i = 1; i < Math.abs(this.column - column); i++) {
				if(board.getPiece(this.column - i, this.row + i) != null) {
					return true;
				}
			}
		}
		
		// down right
		if (this.column < column && this.row < row) {
			for (int i = 1; i < Math.abs(this.column - column); i++) {
				if(board.getPiece(this.column + i, this.row + i) != null) {
					return true;
				}
			}
		}
		return false;
	}
}
