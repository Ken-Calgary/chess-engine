package pieces;

import java.awt.image.BufferedImage;
import main.Board;

/**
 * Rook class that extends the Piece abstract class
 * 
 * @author Ken Liu
 * @version 1.0
 * @since 2023-11-16
 */
public class Rook extends Piece{
	/**
	 * Rook constructor to create the rook piece 
	 * and place it on the board
	 * 
	 * @param board Set the board as the parent
	 * @param col	Set the Rook vertical position
	 * @param row	Set the Rook horizontal position
	 * @param isWhite	Set the color of the piece
	 */
	public Rook(Board board, int col, int row, boolean isWhite) {
		super(board);
		this.column = col;
		this.row = row;
		this.xPos = col * board.tileSize;
		this.yPos = row * board.tileSize;
		this.isWhite = isWhite;
		this.name = "Rook";
		
		// Grabs the rook in the "ChessPieces.png" and sizes it to fit the board
		this.sprite = sheet.getSubimage(4 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale)
				.getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
	}
	
	
	/**
	 * Checks if its a valid move made by Rook
	 * 
	 * @param column
	 * @param row
	 */
	public boolean isValidMovement(int column, int row) {
		return this.column == column || this.row == row;
	}
	
	/**
	 * Checks if the rook is blocked by other pieces
	 * 
	 * @param column	
	 * @param row
	 */
	public boolean moveCollidesWithPiece(int column, int row) {
		// checks left
		if (this.column > column) {
			System.out.println(column);
			for (int c = this.column - 1; c > column; c--) {
				if (board.getPiece(c, this.row)!= null ) {
					return true;
				}
			}
		}
		
		// checks right
		if (this.column < column) {
			for (int c = this.column + 1; c < column; c++) {
				if (board.getPiece(c, this.row) != null) {
					return true;
				}
			}
		}
		
		// checks up
		if (this.row > row) {
			for (int r = this.row - 1; r > row; r--) {
				if (board.getPiece(this.column, r)!= null ) {
					return true;
				}
			}
		}
		
		// checks down
		if (this.row < row) {
			for (int r = this.row + 1; r < row; r++) {
				if (board.getPiece(this.column, r) != null) {
					return true;
				}
			}
		}
		
		return false;
	}
}
