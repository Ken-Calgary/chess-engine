package main;

import pieces.Piece;

/**
 *  The CheckScanner class is used to check if the King is in check
 *  
 * @author Ken Liu
 * @version 1.0
 * @since 2023-11-14
 */
public class CheckScanner {
	private Board board;
	
	/**
	 * CheckScanner constructor to set the Board object
	 * 
	 * @param board
	 */
	public CheckScanner(Board board) { 
		this.board = board;
	}
	
	/**
	 * Method to check if the King piece is in check
	 * 
	 * @param move
	 * @return boolean
	 */
	public boolean isKingChecked(Move move) {
		Piece king = board.findKing(move.piece.isWhite);
		assert king != null;
		
		int kingColumn = king.column;
		int kingRow = king.row;
		
		if (board.selectedPiece != null && board.selectedPiece.name.equals("King")) {
			kingColumn = move.newColumn;
			kingRow = move.newColumn;
			
		}
		
		return 	checkedByRook(move.newColumn, move.newRow, king, kingColumn, kingRow, 0, 1) || // up
				checkedByRook(move.newColumn, move.newRow, king, kingColumn, kingRow, 0, -1) || // down
				checkedByRook(move.newColumn, move.newRow, king, kingColumn, kingRow, 1, 0) || // right
				checkedByRook(move.newColumn, move.newRow, king, kingColumn, kingRow, -1, 0) || // left
				
				checkedByBishop(move.newColumn, move.newRow, king, kingColumn, kingRow, 1, 1) || // down right
				checkedByBishop(move.newColumn, move.newRow, king, kingColumn, kingRow, 1, -1) || // up right
				checkedByBishop(move.newColumn, move.newRow, king, kingColumn, kingRow, -1, -1) || // up left
				checkedByBishop(move.newColumn, move.newRow, king, kingColumn, kingRow, -1, 1) || // down left
		
				checkedByKnight(move.newColumn, move.newRow, king, kingColumn, kingRow) ||
				
				checkedByPawn(move.newColumn, move.newRow, king, kingColumn, kingRow) ||
				
				checkedByKing(king, kingColumn, kingRow);
	}
	
	/**
	 * Method to check if the King is being checked by a Rook
	 * 
	 * @param column
	 * @param row
	 * @param king
	 * @param kingColumn
	 * @param kingRow
	 * @param columnValue
	 * @param rowValue
	 * @return
	 */
	private boolean checkedByRook(int column, int row, Piece king, int kingColumn, int kingRow, int columnValue, int rowValue) {
		for (int i = 1; i < 8; i++) {
			if (kingColumn + (i * columnValue) == column && kingRow + (i * rowValue) == row) {
				break;
			}
			
			Piece piece = board.getPiece(kingColumn + (i * columnValue), kingRow + (i * rowValue));
			if (piece != null && piece != board.selectedPiece) {
				if (!board.sameTeam(piece,  king) && (piece.name.equals("Rook") || piece.name.equals("Queen"))) {
					return true;
				}
				break;
			}
		}
		
		return false;
	}
	
	/**
	 * Method to check if the King is being checked by a Bishop
	 * 
	 * @param column
	 * @param row
	 * @param king
	 * @param kingColumn
	 * @param kingRow
	 * @param columnValue
	 * @param rowValue
	 * @return
	 */
	private boolean checkedByBishop(int column, int row, Piece king, int kingColumn, int kingRow, int columnValue, int rowValue) {
		for (int i = 1; i < 8; i++) {
			if (kingColumn - (i * columnValue) == column && kingRow - (i * rowValue) == row) {
				break;
			}
			
			Piece piece = board.getPiece(kingColumn - (i * columnValue), kingRow - (i * rowValue));
			if (piece != null && piece != board.selectedPiece) {
				if (!board.sameTeam(piece,  king) && (piece.name.equals("Bishop") || piece.name.equals("Queen"))) {
					return true;
				}
				break;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Method to check if the King is being checked by a Knight
	 * 
	 * @param column
	 * @param row
	 * @param king
	 * @param kingColumn
	 * @param kingRow
	 * @return
	 */
	private boolean checkedByKnight(int column, int row, Piece king, int kingColumn, int kingRow) {
		return 	checkKnight(board.getPiece(kingColumn - 1, kingRow - 2), king, column, row) ||
				checkKnight(board.getPiece(kingColumn - 1, kingRow + 2), king, column, row) ||
				checkKnight(board.getPiece(kingColumn - 2, kingRow - 1), king, column, row) ||
				checkKnight(board.getPiece(kingColumn - 2, kingRow + 1), king, column, row) ||
				checkKnight(board.getPiece(kingColumn + 1, kingRow - 2), king, column, row) ||
				checkKnight(board.getPiece(kingColumn + 1, kingRow + 2), king, column, row) ||
				checkKnight(board.getPiece(kingColumn + 2, kingRow - 1), king, column, row)	||
				checkKnight(board.getPiece(kingColumn + 2, kingRow + 1), king, column, row);
	}
	
	/**
	 * Method to check if the King is check by a Knight
	 * 
	 * @param piece
	 * @param king
	 * @param column
	 * @param row
	 * @return
	 */
	private boolean checkKnight(Piece piece, Piece king, int column, int row) {
		return piece != null && !board.sameTeam(piece, king) && piece.name.equals("Knight") && !(piece.column == column && piece.row == row);
	}
	
	/**
	 * Check if the King is being checked by the enemy King
	 * 
	 * @param king
	 * @param kingColumn
	 * @param kingRow
	 * @return
	 */
	private boolean checkedByKing(Piece king, int kingColumn, int kingRow) {
		return 	checkKing(board.getPiece(kingColumn - 1 , kingRow - 1), king) ||
				checkKing(board.getPiece(kingColumn - 1 , kingRow + 1), king) ||
				checkKing(board.getPiece(kingColumn - 1 , kingRow), king) ||
				checkKing(board.getPiece(kingColumn + 1 , kingRow - 1), king) ||
				checkKing(board.getPiece(kingColumn + 1 , kingRow + 1), king) ||
				checkKing(board.getPiece(kingColumn + 1 , kingRow), king) ||
				checkKing(board.getPiece(kingColumn, kingRow - 1), king) ||
				checkKing(board.getPiece(kingColumn, kingRow + 1), king);
	}
	
	/**
	 * Method to check if King is check by the enemy King
	 * 
	 * @param piece
	 * @param king
	 * @return
	 */
	private boolean checkKing(Piece piece, Piece king) {
		return piece != null && !board.sameTeam(piece, king) && piece.name.equals("King");
	}
	
	/**
	 * Method to check if the King is being checked by a Pawn
	 * 
	 * @param column
	 * @param row
	 * @param king
	 * @param kingColumn
	 * @param kingRow
	 * @return
	 */
	private boolean checkedByPawn(int column, int row, Piece king, int kingColumn, int kingRow) {
		int colourValue = king.isWhite ? -1 : 1;
		
		return 	checkPawn(board.getPiece(kingColumn + 1, kingRow + colourValue), king, column, row) ||
				checkPawn(board.getPiece(kingColumn - 1, kingRow + colourValue), king, column, row);
	}
	
	/**
	 * Check if the King is check by a Pawn
	 * 
	 * @param piece
	 * @param king
	 * @param column
	 * @param row
	 * @return
	 */
	private boolean checkPawn(Piece piece, Piece king, int column, int row) {
		return piece != null && !board.sameTeam(piece, king) && piece.name.equals("Pawn");
	}
}
