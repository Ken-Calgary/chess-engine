package pieces;

import java.awt.image.BufferedImage;

import main.Board;
public class Pawn extends Piece{
	public Pawn(Board board, int col, int row, boolean isWhite) {
		super(board);
		this.column = col;
		this.row = row;
		this.xPos = col * board.tileSize;
		this.yPos = row * board.tileSize;
		this.isWhite = isWhite;
		this.name = "Pawn";
		
		// Grabs the pawn in the "ChessPieces.png" and sizes it to fit the board
		this.sprite = sheet.getSubimage(5 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
	}
	
	public boolean isValidMovement(int column, int row) {
		int colourIndex = isWhite ? 1 : -1;
		
		// push pawn 1
		if ((this.column == column) && (this.row - colourIndex == row) && (board.getPiece(column, row) == null)) {
			return true;
		}
		
		// push pawn 2
		if (isFirstMove && (this.column == column) && (this.row - colourIndex * 2 == row) && 
				(board.getPiece(column, row) == null) && (board.getPiece(column, row + colourIndex) == null)) {
			return true;
		}
		
		// capture left
		if ((this.column - 1 == column) && (this.row - colourIndex == row) && (board.getPiece(column, row) != null)) {
			return true;
		}
		
		// capture left
		if ((this.column + 1 == column) && (this.row - colourIndex == row) && (board.getPiece(column, row) != null)) {
			return true;
		}
		
		// en passant left
		if (board.getTileNum(column, row) == board.enPassantTile && column == this.column - 1 &&
				row == this.row - colourIndex && board.getPiece(column, row + colourIndex) != null) {
			return true;
		}
		
		// en passant right
		if (board.getTileNum(column, row) == board.enPassantTile && column == this.column + 1 &&
				row == this.row - colourIndex && board.getPiece(column, row + colourIndex) != null) {
			return true;
		}
		
		return false;
	}
}
