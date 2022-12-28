package application;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ChessMatch chess = new ChessMatch();
		List<ChessPiece> captured = new ArrayList();

		while (!chess.isCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chess, captured);
				System.out.println();
				System.out.print("Souce: ");
				ChessPosition source = UI.readChessPosition(sc);
				boolean[][] possibleMoves = chess.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chess.getPieces(), possibleMoves);

				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = chess.performChessMove(source, target);
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
				if (chess.getPromoted() != null) {
					System.out.println("Enter piece for promotion (B/N/R/Q): ");
					String type = sc.nextLine().toUpperCase();
					while (!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")) {
						System.out.println("Invalid value! Enter piece for promotion (B/N/R/Q): ");
						type = sc.nextLine().toUpperCase();
					}
					chess.replacePromotedPiece(type);

				}
			} catch (ChessException | InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			UI.clearScreen();
			UI.printMatch(chess, captured);
		}

	}

}
