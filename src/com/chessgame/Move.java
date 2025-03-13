package com.chessgame;

public class Move {
    public int startRow, startCol;
    public int endRow, endCol;

    public Move(int sr, int sc, int er, int ec) {
        this.startRow = sr;
        this.startCol = sc;
        this.endRow = er;
        this.endCol = ec;
    }
}
