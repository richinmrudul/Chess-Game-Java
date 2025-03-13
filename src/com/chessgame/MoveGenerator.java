package com.chessgame;

import java.util.ArrayList;
import java.util.List;

public class MoveGenerator {
    public static List<Move> generateAllMoves(ChessBoard board, boolean whiteTurn) {
        List<Move> moves = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = board.getPiece(row, col);
                if (piece != null && piece.isWhite() == whiteTurn) {
                    moves.addAll(generateMovesForPiece(board, row, col, piece));
                }
            }
        }
        return moves;
    }

    public static List<Move> generateMovesForPiece(ChessBoard board, int row, int col, ChessPiece piece) {
        List<Move> moves = new ArrayList<>();
        int direction = piece.isWhite() ? -1 : 1;

        int newRow = row + direction;
        if (newRow >= 0 && newRow < 8) {
            moves.add(new Move(row, col, newRow, col));
        }
        return moves;
    }
}
