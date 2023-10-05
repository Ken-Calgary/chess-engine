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
}
