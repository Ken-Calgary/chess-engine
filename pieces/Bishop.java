package pieces;

import java.awt.image.BufferedImage;

import main.Board;

public class Bishop extends Piece{
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
	
	public boolean isValidMovement(int column, int row) {
		return Math.abs(this.column - column) == Math.abs(this.row - row);
	}
	
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
