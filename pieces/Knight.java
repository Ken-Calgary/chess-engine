package pieces;

import java.awt.image.BufferedImage;

import main.Board;
public class Knight extends Piece{
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
	
	// Validates the knights' movement
	public boolean isValidMovement(int column, int row) {
		return Math.abs(column - this.column) * Math.abs(row - this.row) == 2;
	}
}
