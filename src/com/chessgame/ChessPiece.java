package com.chessgame;

public class ChessPiece {
    private PieceType type;
    private boolean isWhite;

    public ChessPiece(PieceType type, boolean isWhite) {
        this.type = type;
        this.isWhite = isWhite;
    }

    public PieceType getType() {
        return type;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public String getSymbol() {
        return isWhite ? type.getWhiteSymbol() : type.getBlackSymbol();
    }
}
