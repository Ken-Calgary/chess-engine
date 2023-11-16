package main;

import pieces.Piece;

/**
 * Move class that keeps track of piece placements
 * 
 * @author Ken Liu
 * @version 1.0
 * @since 2023-11-16
 */
public class Move {
	protected int oldColumn;
	protected int oldRow;
	protected int newColumn;
	protected int newRow;
	
	Piece piece;
	Piece capture;
	
	/**
	 * Move class constructor to keep track of piece placements
	 * 
	 * @param board Sets the board
	 * @param piece Current selected piece
	 * @param newColumn	New column placement
	 * @param newRow	New row placement
	 */
	public Move(Board board, Piece piece, int newColumn, int newRow) {
		this.oldColumn = piece.column;
		this.oldRow = piece.row;
		this.newColumn = newColumn;
		this.newRow = newRow;
		
		this.piece = piece;
		this.capture = board.getPiece(newColumn, newRow);
	}
	

}
