package com.chessgame;

import java.util.List;

public class CheckDetector {
    public static boolean isKingInCheck(ChessBoard board, boolean whiteTurn) {
        return false;
    }

    public static boolean isCheckmate(ChessBoard board, boolean whiteTurn) {
        return isKingInCheck(board, whiteTurn);
    }
}
