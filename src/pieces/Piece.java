package pieces;

import javax.imageio.ImageIO;

import main.Board;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Abstract Piece class that is to be extended by all other piece
 * 
 * @author Ken Liu
 * @version 1.0
 * @since 2023-11-16
 */
public abstract class Piece {
	public int column, row;
	public int xPos, yPos;
	public int value;
	
	public boolean isFirstMove = true;
	public boolean isWhite;
	
	public String name;
	
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
	
	/**
	 * Piece constructor to set the Board for the child classes
	 * @param board
	 */
	public Piece(Board board) {
		this.board = board;
	}
	
	/**
	 * Method to check if its a valid move
	 * 
	 * @param column
	 * @param row
	 * @return
	 */
	abstract public boolean isValidMovement(int column, int row);
	
	/**
	 * Method to check if move collides with any piece
	 * 
	 * @param column
	 * @param row
	 * @return
	 */
	public boolean moveCollidesWithPiece(int column, int row) {
		return false;
	}
	
	/**
	 * Draws the piece at the specified x and y coordinate
	 * of the board.
	 * 
	 * @param g2d
	 */
	public void paint(Graphics2D g2d) {
		g2d.drawImage(sprite, xPos, yPos, null);
	}
}
