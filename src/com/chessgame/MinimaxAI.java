package com.chessgame;

import java.util.List;

public class MinimaxAI {
    private static final int MAX_DEPTH = 3;

    public static Move findBestMove(ChessBoard board) {
        List<Move> moves = MoveGenerator.generateAllMoves(board, board.isWhiteToMove());
        if (moves.isEmpty()) return null;

        Move bestMove = moves.get(0);
        int bestScore = Integer.MIN_VALUE;

        for (Move move : moves) {
            ChessPiece captured = board.makeMove(move);
            int score = minimax(board, MAX_DEPTH - 1, false);
            board.undoMove(move, captured);

            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private static int minimax(ChessBoard board, int depth, boolean maximizing) {
        if (depth == 0) return evaluateBoard(board);

        List<Move> moves = MoveGenerator.generateAllMoves(board, board.isWhiteToMove());
        if (maximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (Move move : moves) {
                ChessPiece captured = board.makeMove(move);
                int eval = minimax(board, depth - 1, false);
                board.undoMove(move, captured);
                maxEval = Math.max(maxEval, eval);
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Move move : moves) {
                ChessPiece captured = board.makeMove(move);
                int eval = minimax(board, depth - 1, true);
                board.undoMove(move, captured);
                minEval = Math.min(minEval, eval);
            }
            return minEval;
        }
    }

    private static int evaluateBoard(ChessBoard board) {
        int score = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = board.getPiece(row, col);
                if (piece != null) score += piece.isWhite() ? 1 : -1;
            }
        }
        return score;
    }
}
