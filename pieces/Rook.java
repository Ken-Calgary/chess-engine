package pieces;

import java.awt.image.BufferedImage;

import main.Board;
public class Rook extends Piece{
	public Rook(Board board, int col, int row, boolean isWhite) {
		super(board);
		this.column = col;
		this.row = row;
		this.xPos = col * board.tileSize;
		this.yPos = row * board.tileSize;
		this.isWhite = isWhite;
		this.name = "Rook";
		
		// Grabs the rook in the "ChessPieces.png" and sizes it to fit the board
		this.sprite = sheet.getSubimage(4 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
	}
	
	public boolean isValidMovement(int column, int row) {
		return this.column == column || this.row == row;
	}
	
	public boolean moveCollidesWithPiece(int column, int row) {
		// checks left
		if (this.column > column) {
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
