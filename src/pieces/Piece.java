package pieces;

import javax.imageio.ImageIO;

import main.Board;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Piece {
	public int column, row;
	public int xPos, yPos;
	
	public boolean isFirstMove = true;
	public boolean isWhite;
	public String name;
	public int value;
	
	
	public BufferedImage sheet;
	
	{
		// Reads the image "ChessPieces.png" in the "res" folder 
		// for placing each piece onto the board
		try {
			sheet = ImageIO.read(ClassLoader.getSystemResource("ChessPieces.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Splits the scale into 6, because of 6 unique pieces.
	public int sheetScale = sheet.getWidth() / 6;
	
	protected Image sprite;
	protected Board board;
	
	public Piece(Board board) {
		this.board = board;
	}
	
	public boolean isValidMovement(int column, int row) {
		return true;
	}
	
	public boolean moveCollidesWithPiece(int column, int row) {
		return false;
	}
	
	public void paint(Graphics2D g2d) {
		g2d.drawImage(sprite, xPos, yPos, null);
	}
}
