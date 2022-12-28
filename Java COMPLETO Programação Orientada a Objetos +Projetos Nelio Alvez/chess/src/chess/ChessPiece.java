package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	private Color color;
	
	private int moveCount;

	public Color getColor() {
		return color;
	}

	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p.getColor() != color;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosotion(position);
	}
	
	public void increaseMoveCount() {
		this.moveCount++;
	}
	
	public void decrementMoveCount() {
		this.moveCount--;
	}

	public int getMoveCount() {
		return moveCount;
	}
	
	

}
