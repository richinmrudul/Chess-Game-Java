package com.chessgame;

public class MoveValidator {

    public static boolean isValidMove(ChessBoard board, int startRow, int startCol, int endRow, int endCol) {
        ChessPiece piece = board.getPiece(startRow, startCol);
        if (piece == null) return false; // No piece at starting square

        ChessPiece target = board.getPiece(endRow, endCol);
        boolean isWhite = piece.isWhite();

        // Prevent moving onto own pieces
        if (target != null && target.isWhite() == isWhite) return false;

        switch (piece.getType()) {
            case PAWN:
                return isValidPawnMove(board, startRow, startCol, endRow, endCol, isWhite);
            case ROOK:
                return isValidRookMove(board, startRow, startCol, endRow, endCol);
            case KNIGHT:
                return isValidKnightMove(startRow, startCol, endRow, endCol);
            case BISHOP:
                return isValidBishopMove(board, startRow, startCol, endRow, endCol);
            case QUEEN:
                return isValidQueenMove(board, startRow, startCol, endRow, endCol);
            case KING:
                return isValidKingMove(startRow, startCol, endRow, endCol);
        }
        return false;
    }

    // ✅ **Pawn movement: Forward and diagonal capture**
    private static boolean isValidPawnMove(ChessBoard board, int startRow, int startCol, int endRow, int endCol, boolean isWhite) {
        int direction = isWhite ? -1 : 1;
        int startLine = isWhite ? 6 : 1;

        // Normal move
        if (startCol == endCol && board.getPiece(endRow, endCol) == null) {
            if (endRow == startRow + direction) return true; // Move one square
            if (startRow == startLine && endRow == startRow + 2 * direction && board.getPiece(startRow + direction, endCol) == null) return true; // Move two squares
        }
        // Capture
        if (Math.abs(startCol - endCol) == 1 && endRow == startRow + direction && board.getPiece(endRow, endCol) != null) {
            return true;
        }
        return false;
    }

    // ✅ **Rook movement: Straight lines**
    private static boolean isValidRookMove(ChessBoard board, int startRow, int startCol, int endRow, int endCol) {
        if (startRow != endRow && startCol != endCol) return false; // Must be straight line

        int rowStep = Integer.compare(endRow, startRow);
        int colStep = Integer.compare(endCol, startCol);

        int r = startRow + rowStep, c = startCol + colStep;
        while (r != endRow || c != endCol) {
            if (board.getPiece(r, c) != null) return false;
            r += rowStep;
            c += colStep;
        }
        return true;
    }

    // ✅ **Knight movement: L-shape**
    private static boolean isValidKnightMove(int startRow, int startCol, int endRow, int endCol) {
        int rowDiff = Math.abs(startRow - endRow);
        int colDiff = Math.abs(startCol - endCol);
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }

    // ✅ **Bishop movement: Diagonal**
    private static boolean isValidBishopMove(ChessBoard board, int startRow, int startCol, int endRow, int endCol) {
        if (Math.abs(startRow - endRow) != Math.abs(startCol - endCol)) return false; // Must be diagonal

        int rowStep = Integer.compare(endRow, startRow);
        int colStep = Integer.compare(endCol, startCol);

        int r = startRow + rowStep, c = startCol + colStep;
        while (r != endRow || c != endCol) {
            if (board.getPiece(r, c) != null) return false;
            r += rowStep;
            c += colStep;
        }
        return true;
    }

    // ✅ **Queen movement: Rook + Bishop**
    private static boolean isValidQueenMove(ChessBoard board, int startRow, int startCol, int endRow, int endCol) {
        return isValidRookMove(board, startRow, startCol, endRow, endCol) || isValidBishopMove(board, startRow, startCol, endRow, endCol);
    }

    // ✅ **King movement: One step any direction**
    private static boolean isValidKingMove(int startRow, int startCol, int endRow, int endCol) {
        return Math.abs(startRow - endRow) <= 1 && Math.abs(startCol - endCol) <= 1;
    }
}
