package pieces;

import javax.imageio.ImageIO;

import main.Board;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Piece {
	public int column, row;
	public int xPos, yPos;
	
	public boolean isWhite;
	public String name;
	public int value;
	
	BufferedImage sheet;
	
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
	protected int sheetScale = sheet.getWidth() / 6;
	
	Image sprite;
	Board board;
	
	public Piece(Board board) {
		this.board = board;
	}
	
	public void paint(Graphics2D g2d) {
		g2d.drawImage(sprite, xPos, yPos, null);
	}
}
