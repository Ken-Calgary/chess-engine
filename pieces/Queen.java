package pieces;

import java.awt.image.BufferedImage;

import main.Board;
public class Queen extends Piece{
	public Queen(Board board, int col, int row, boolean isWhite) {
		super(board);
		this.column = col;
		this.row = row;
		this.xPos = col * board.tileSize;
		this.yPos = row * board.tileSize;
		this.isWhite = isWhite;
		this.name = "Queen";
		
		// Grabs the queen in the "ChessPieces.png" and sizes it to fit the board
		this.sprite = sheet.getSubimage(1 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
	}
}
