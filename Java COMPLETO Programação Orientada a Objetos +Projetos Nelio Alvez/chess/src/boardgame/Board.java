package boardgame;

public class Board {

	private int rows;
	private int coluns;
	private Piece[][] pieces;

	public Board(int rows, int coluns) {
		if (rows < 1 || coluns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 and row and 1 column!");
		}
		this.rows = rows;
		this.coluns = coluns;
		this.pieces = new Piece[rows][coluns];
	}

	public int getRows() {
		return rows;
	}

	public int getColuns() {
		return coluns;
	}

	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Postion not in the board!");
		}
		return pieces[row][column];
	}

	public Piece piece(Position position) {
		return piece(position.getRow(), position.getColumn());
	}

	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position + " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}

	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < coluns;
	}

	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}

	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Postion not in the board!");
		}
		return piece(position) != null;
	}

	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Postion not in the board!");
		}
		if (piece(position) == null) {
			return null;
		}
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}

}
