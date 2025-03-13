package com.chessgame;

public enum PieceType {
    KING("K", "k"), QUEEN("Q", "q"), ROOK("R", "r"),
    BISHOP("B", "b"), KNIGHT("N", "n"), PAWN("P", "p");

    private final String whiteSymbol;
    private final String blackSymbol;

    PieceType(String whiteSymbol, String blackSymbol) {
        this.whiteSymbol = whiteSymbol;
        this.blackSymbol = blackSymbol;
    }

    public String getWhiteSymbol() {
        return whiteSymbol;
    }

    public String getBlackSymbol() {
        return blackSymbol;
    }
}
