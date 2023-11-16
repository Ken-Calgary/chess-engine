package main;

import javax.swing.*;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

import java.awt.*;
import java.util.ArrayList;

/**
 * The Board class creates the actual chess board. As well as,
 * setting up all the pieces, interaction, and etc.
 * 
 * @author Ken Liu
 * @version 1.0
 * @since 2023-11-14
 */
public class Board extends JPanel {
    public int tileSize = 85;

    private final int COLUMNS = 8;
    private final int ROWS = 8;
    
    private ArrayList<Piece> pieceList = new ArrayList<>();
    public Piece selectedPiece;
    
    Input input = new Input(this);
    public CheckScanner checkScanner = new CheckScanner(this);
    
    public int enPassantTile = -1;
    
    /**
     * Board constructor to initialize the board. Sets up all the
     * mouse actions, pieces and etc.
     * 
     */
    public Board() {
        this.setPreferredSize(new Dimension(COLUMNS*tileSize, ROWS*tileSize));
        
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        
        addBlackPieces();
        addWhitePieces();
    }
    
    /**
     * Get the tile number of the piece
     * 
     * @return tileNumber
     */
    public int getTileNum(int column, int row) {
    	return row * ROWS + column;
    }

    /**
     * Adds all the black pieces onto the board
     */
    public void addBlackPieces() {
    	pieceList.add(new King(this, 4, 0, false));
    	pieceList.add(new Queen(this, 3, 0, false));
    	pieceList.add(new Bishop(this, 2, 0, false));
    	pieceList.add(new Bishop(this, 5, 0, false));
    	pieceList.add(new Knight(this, 6, 0, false));
    	pieceList.add(new Knight(this, 1, 0, false));
    	pieceList.add(new Rook(this, 0, 0, false));
    	pieceList.add(new Rook(this, 7, 0, false));
    	
    	for (int i = 0; i < COLUMNS; i++) {
        	pieceList.add(new Pawn(this, i, 1, false));
    	}
    }
    
    /**
     * Adds all the white pieces onto the board
     */
    public void addWhitePieces() {
    	pieceList.add(new King(this, 4, 7, true));
    	pieceList.add(new Queen(this, 3, 7, true));
    	pieceList.add(new Bishop(this, 2, 7, true));
    	pieceList.add(new Bishop(this, 5, 7, true));
    	pieceList.add(new Knight(this, 6, 7, true));
    	pieceList.add(new Knight(this, 1, 7, true));
    	pieceList.add(new Rook(this, 0, 7, true));
    	pieceList.add(new Rook(this, 7, 7, true));
    	
    	for (int i = 0; i < COLUMNS; i++) {
        	pieceList.add(new Pawn(this, i, 6, true));
    	}
    }
    
    /**
     * Gets the selected piece
     * 
     * @param column the location of the piece in terms of 
     * @param row
     * @return piece
     */
    public Piece getPiece(int column, int row) {
    	for (Piece piece: pieceList) {
    		if ((piece.column == column) && (piece.row == row)) {
    			return piece;
    		}
    	}
    	return null;
    }
    
    public void makeMove(Move move) {
    	
    	if (move.piece.name.equals("Pawn")) {
    		movePawn(move);
    	} else if (move.piece.name.equals("King")) {
    		moveKing((move));
    	}
    	
    	move.piece.column = move.newColumn;
    	move.piece.row = move.newRow;
    	
    	move.piece.xPos = move.newColumn * tileSize;
    	move.piece.yPos = move.newRow * tileSize;
    	
    	move.piece.isFirstMove = false;
    	
    	capture(move.capture);
    }
    
    /**
     * Method specifically for pawn, as pawns have a few
     * additional rules
     * 
     * @param move
     */
    public void movePawn(Move move) {
    	int colourIndex = move.piece.isWhite ? 1: -1;
    	
    	// en passant
    	if (getTileNum(move.newColumn, move.newRow) == enPassantTile) {
    		move.capture = getPiece(move.newColumn, move.newRow + colourIndex);
    	}
    	
    	if (Math.abs(move.piece.row - move.newRow) == 2) {
    		enPassantTile = getTileNum(move.newColumn, move.newRow + colourIndex);
    	} else {
    		enPassantTile = -1;
    	}
    	
    	//promotions
    	colourIndex = move.piece.isWhite ? 0 : 7;
    	if(move.newRow == colourIndex) {
    		promotePawn(move);
    	}
    
    }
    
    /**
     * 
     * 
     * @param piece	Piece to be removed
     */
    public void capture(Piece piece) {
    	pieceList.remove(piece);
    }
    
    /**
     * Method to promote pawns by prompting user to select 
     * the piece they want to promote to
     * 
     * @param move	
     */
    private void promotePawn(Move move) {
    	final String[] PIECEPROMOTIONS = {"Queen", "Bishop", "Knight", "Rook"};
    	
    	// Prompts the user to pick a piece to promote the pawn to
        int promotedPiece = JOptionPane.showOptionDialog(null, "Select a piece to be promoted to",
        		"Promotion", 0, JOptionPane.INFORMATION_MESSAGE, null, PIECEPROMOTIONS, null);
        
        // Add the selected piece onto the board
        switch(promotedPiece) {
	        case 0:
	        	pieceList.add(new Queen(this, move.newColumn, move.newRow, move.piece.isWhite));
	        	break;
	        case 1:
	        	pieceList.add(new Bishop(this, move.newColumn, move.newRow, move.piece.isWhite));
	        	break;
	        case 2:
	        	pieceList.add(new Knight(this, move.newColumn, move.newRow, move.piece.isWhite));
	        	break;
	        case 3:
	        	pieceList.add(new Rook(this, move.newColumn, move.newRow, move.piece.isWhite));
	        	break;
        }
        
    	capture(move.piece);
    }
    
    /**
     * Method to check if a move made was valid
     * 
     * @param move
     * @return
     */
    public boolean isValidMove(Move move) {
    	// Try capturing team piece, return false
    	if (sameTeam(move.piece, move.capture)) {
    		return false;
    	}
    	
    	// Check if the movement is valid
    	if (!move.piece.isValidMovement(move.newColumn, move.newRow)) {
    		return false;
    	}
    	
    	// Checks if the movement collides with another piece
    	if (move.piece.moveCollidesWithPiece(move.newColumn, move.newRow)) {
    		return false;
    	}
    	
    	// Checks if the king is in checked
    	if (checkScanner.isKingChecked(move)) {
    		return false;
    	}
    	
    	return true;
    }
    
    /**
     * Check if the compared pieces are the same color
     * 
     * @param p1 Selected Piece
     * @param p2 Piece 
     * @return	boolean
     */
    public boolean sameTeam(Piece p1, Piece p2) {
    	if (p1 == null || p2 == null) {
    		return false;
    	}
    	
    	return p1.isWhite == p2.isWhite;
    }
    
    /**
     * Method to find where the king is located on the board
     * 
     * @param isWhite 
     * @return Piece
     */
    public Piece findKing(boolean isWhite) {
    	for (Piece piece: pieceList) {
    		if(isWhite == piece.isWhite && piece.name.equals("King")) {
    			return piece;
    		}
    	}
    	
    	return null;
    }
    
    /**
     * Method to move the King
     * 
     * @param move
     */
    private void moveKing(Move move) {
    	if (Math.abs(move.piece.column - move.newColumn) == 2) {
    		Piece rook;
    		if (move.piece.column < move.newColumn) {
    			rook = getPiece(7, move.piece.row);
    			rook.column = 5;
    		} else {
    			rook = getPiece(0, move.piece.row);
    			rook.column = 3;
    		}
    		
    		rook.xPos = rook.column * tileSize;
    	}
    }
    
    /**
     * Repaints the board after any actions made by the user
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Paints the board 
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0 ; c < COLUMNS; c++) {
                g2d.setColor((c+r) % 2 == 0 ? new Color(227,198,181): new Color(157,105,53));
                g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
            }
        }
        
        
        // Highlights where the piece can move 
        if (selectedPiece != null) {
        	for (int r = 0; r < ROWS; r++) {
            	for (int c = 0; c < COLUMNS; c++) {
            		if (isValidMove(new Move(this, selectedPiece, c, r))) {
            			g2d.setColor(new Color(68, 180, 57, 190));
            			g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
            		}
            	}
            }
        }
        
        // Paints each piece
        for (Piece piece: pieceList) {
        	piece.paint(g2d);
        }
    }
}