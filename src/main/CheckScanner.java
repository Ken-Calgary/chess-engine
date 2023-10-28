package main;

import pieces.Piece;

public class CheckScanner {
	Board board;
	
	public CheckScanner(Board board) { 
		this.board = board;
	}
	
	public boolean isKingChecked(Move move) {
		Piece king = board.findKing(move.piece.isWhite);
		assert king != null;
		
		int kingColumn = king.column;
		int kingRow = king.row;
		
		if (board.selectedPiece != null && board.selectedPiece.name.equals("King")) {
			kingColumn = move.newColumn;
			kingRow = move.newRow;
			
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
	
	private boolean checkKnight(Piece piece, Piece king, int column, int row) {
		return piece != null && !board.sameTeam(piece, king) && piece.name.equals("Knight") && !(piece.column == column && piece.row == row);
	}
	
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
	
	private boolean checkKing(Piece piece, Piece king) {
		return piece != null && !board.sameTeam(piece, king) && piece.name.equals("King");
	}
	
	private boolean checkedByPawn(int column, int row, Piece king, int kingColumn, int kingRow) {
		int colourValue = king.isWhite ? -1 : 1;
		
		return 	checkPawn(board.getPiece(kingColumn + 1, kingRow + colourValue), king, column, row) ||
				checkPawn(board.getPiece(kingColumn - 1, kingRow + colourValue), king, column, row);
	}
	
	private boolean checkPawn(Piece piece, Piece king, int column, int row) {
		return piece != null && !board.sameTeam(piece, king) && piece.name.equals("Pawn");
	}
}
