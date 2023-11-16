package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import pieces.Piece;

/**
 * The Input class takes care of the mouse interaction of 
 * the user. Using the MouseAdapter library
 * 
 * @author Ken Liu
 * @version 1.0
 * @since 2023-11-14
 */
public class Input extends MouseAdapter{
	private Board board;
	
	// Input constructor to set the Board
	public Input(Board board) {
		this.board = board;
	}
	
	
	/**
	 * Allows the user to select any piece
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		int column = e.getX() / board.tileSize;
		int row = e.getY() / board.tileSize;
		
		Piece selectedPiece = board.getPiece(column, row);
		if (selectedPiece != null) {
			board.selectedPiece = selectedPiece;
		}
	}
	
	/**
	 * Enables the user to move each piece
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (board.selectedPiece != null) {
			board.selectedPiece.xPos = e.getX() - board.tileSize / 2;
			board.selectedPiece.yPos = e.getY() - board.tileSize / 2;
			
			board.repaint();
		}
		
	}

	/**
	 * Moves piece after releasing mouse
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		int column = e.getX() / board.tileSize;
		int row = e.getY() / board.tileSize;
		
		if (board.selectedPiece != null) {
			Move move = new Move(board, board.selectedPiece, column, row);
			
			if (board.isValidMove(move)) {
				board.makeMove(move);
			} else {
				board.selectedPiece.xPos = board.selectedPiece.column * board.tileSize;
				board.selectedPiece.yPos = board.selectedPiece.row * board.tileSize;
			}
		}
		
		board.selectedPiece = null;
		board.repaint();
		
	}

}
