package chess;

import boardgame.Position;

public class ChessPosition {
	private char column;
	private int row;

	public ChessPosition(char column, int row) {
		if (column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Erro instantiating ChessPosition. Valid values are from a1 a h8");
		}
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	protected Position toPosition() {
		return new Position(8 - row, column - 'a');
	}

	protected static ChessPosition fromPosotion(Position po) {
		return new ChessPosition((char)( 'a' + po.getColumn()), 8 - po.getRow());
	}
	
	@Override
	public String toString() {
	return "" + column + row;
	}

}
