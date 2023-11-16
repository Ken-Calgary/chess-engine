package pieces;

import java.awt.image.BufferedImage;
import main.Board;
import main.Move;

/**
 * King class that extends the Piece abstract class
 * 
 * @author Ken Liu
 * @version 1.0
 * @since 2023-11-16
 */
public class King extends Piece{
	/**
	 * King constructor that creates a King piece
	 * 
	 * @param board Set the board as the parent
	 * @param col	Set the Bishop vertical position
	 * @param row	Set the Bishop horizontal position
	 * @param isWhite	Set the color of the piece
	 */
	public King(Board board, int col, int row, boolean isWhite) {
		super(board);
		this.column = col;
		this.row = row;
		this.xPos = col * board.tileSize;
		this.yPos = row * board.tileSize;
		this.isWhite = isWhite;
		this.name = "King";
		
		// Grabs the king in the "ChessPieces.png" and sizes it to fit the board
		this.sprite = sheet.getSubimage(0 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
	}
	
	/**
	 * Checks if its a valid move made by King
	 * 
	 * @param column
	 * @param row
	 * @return boolean
	 */
	public boolean isValidMovement(int column, int row) {
		return Math.abs((column - this.column) * (row - this.row)) == 1 || Math.abs(column - this.column) + Math.abs(row - this.row) == 1 || canCastle(column, row);
	}
	
	/**
	 * Checks if the King can castle on either side
	 * 
	 * @param column
	 * @param row
	 * @return	boolean
	 */
	private boolean canCastle(int column, int row) {
		if (this.row == row) {
			if (column == 6) {
				
				Piece rook = board.getPiece(7, row);
				
				// Checks if the Rook or King has moved yet for the rightmost piece
				if (rook != null && rook.isFirstMove && this.isFirstMove) {
					return 	board.getPiece(5, row) == null &&
							board.getPiece(6, row) == null &&
							!board.checkScanner.isKingChecked(new Move(board, this, 5, row));
				}
			} else if (column == 2) {
				Piece rook = board.getPiece(0, row);
				
				// Checks if the Rook or King has moved yet for the leftmost piece
				if (rook != null && rook.isFirstMove && this.isFirstMove) {
					return 	board.getPiece(1, row) == null &&
							board.getPiece(2, row) == null &&
							board.getPiece(3, row) == null &&
							!board.checkScanner.isKingChecked(new Move(board, this, 3, row));
				}
			}
		}
		
		return false;
	}
}
