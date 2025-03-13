package com.chessgame;

public class ChessBoard {
    private ChessPiece[][] board = new ChessPiece[8][8];
    private boolean whiteToMove = true;

    public ChessBoard() {
        setupBoard();
    }

    public ChessPiece getPiece(int row, int col) {
        return board[row][col];
    }

    public ChessPiece movePiece(int startRow, int startCol, int endRow, int endCol) {
        ChessPiece movingPiece = board[startRow][startCol];
        ChessPiece capturedPiece = board[endRow][endCol];

        board[endRow][endCol] = movingPiece;
        board[startRow][startCol] = null;
        whiteToMove = !whiteToMove;

        return capturedPiece;
    }

    public ChessPiece makeMove(Move move) {
        return movePiece(move.startRow, move.startCol, move.endRow, move.endCol);
    }

    public void undoMove(Move move, ChessPiece capturedPiece) {
        board[move.startRow][move.startCol] = board[move.endRow][move.endCol];
        board[move.endRow][move.endCol] = capturedPiece;
        whiteToMove = !whiteToMove;
    }

    public boolean isWhiteToMove() {
        return whiteToMove;
    }

    private void setupBoard() {
        board[0][0] = new ChessPiece(PieceType.ROOK, true);
        board[0][1] = new ChessPiece(PieceType.KNIGHT, true);
        board[0][2] = new ChessPiece(PieceType.BISHOP, true);
        board[0][3] = new ChessPiece(PieceType.QUEEN, true);
        board[0][4] = new ChessPiece(PieceType.KING, true);
        board[0][5] = new ChessPiece(PieceType.BISHOP, true);
        board[0][6] = new ChessPiece(PieceType.KNIGHT, true);
        board[0][7] = new ChessPiece(PieceType.ROOK, true);

        for (int i = 0; i < 8; i++) {
            board[1][i] = new ChessPiece(PieceType.PAWN, true);
            board[6][i] = new ChessPiece(PieceType.PAWN, false);
        }

        board[7][0] = new ChessPiece(PieceType.ROOK, false);
        board[7][1] = new ChessPiece(PieceType.KNIGHT, false);
        board[7][2] = new ChessPiece(PieceType.BISHOP, false);
        board[7][3] = new ChessPiece(PieceType.QUEEN, false);
        board[7][4] = new ChessPiece(PieceType.KING, false);
        board[7][5] = new ChessPiece(PieceType.BISHOP, false);
        board[7][6] = new ChessPiece(PieceType.KNIGHT, false);
        board[7][7] = new ChessPiece(PieceType.ROOK, false);
    }
}
