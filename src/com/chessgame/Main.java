package com.chessgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    private ChessBoard chessBoard = new ChessBoard();
    private Button[][] pieceButtons = new Button[8][8];
    private Rectangle[][] squares = new Rectangle[8][8];
    private int selectedRow = -1, selectedCol = -1;
    private Rectangle lastMoveSquare = null;

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        setupBoard(grid);

        Scene scene = new Scene(grid, 480, 480);
        primaryStage.setTitle("Chess Game with AI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupBoard(GridPane grid) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Rectangle square = new Rectangle(60, 60);
                square.setFill((row + col) % 2 == 0 ? Color.BEIGE : Color.DARKGREY);
                squares[row][col] = square;
                grid.add(square, col, row);

                Button pieceButton = new Button();
                pieceButton.setPrefSize(60, 60);
                updateButton(pieceButton, row, col);

                final int r = row, c = col;
                pieceButton.setOnAction(e -> movePiece(r, c));

                grid.add(pieceButton, col, row);
                pieceButtons[row][col] = pieceButton;
            }
        }
    }

    private void movePiece(int row, int col) {
        if (selectedRow == -1) {
            if (chessBoard.getPiece(row, col) != null && chessBoard.isWhiteToMove()) {
                selectedRow = row;
                selectedCol = col;
            }
        } else {
            if (MoveValidator.isValidMove(chessBoard, selectedRow, selectedCol, row, col)) {
                ChessPiece captured = chessBoard.movePiece(selectedRow, selectedCol, row, col);
                highlightLastMove(selectedRow, selectedCol);
                refreshBoard();
                checkGameStatus();

                if (captured != null) {
                    showAlert("Piece Captured!", "You captured a piece!");
                }

                Move aiMove = MinimaxAI.findBestMove(chessBoard);
                if (aiMove != null) {
                    ChessPiece aiCaptured = chessBoard.movePiece(aiMove.startRow, aiMove.startCol, aiMove.endRow, aiMove.endCol);
                    highlightLastMove(aiMove.startRow, aiMove.startCol);
                    refreshBoard();
                    checkGameStatus();

                    if (aiCaptured != null) {
                        showAlert("AI Captured a Piece!", "The AI captured one of your pieces.");
                    }
                }

                selectedRow = -1;
                selectedCol = -1;
            }
        }
    }

    private void refreshBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                updateButton(pieceButtons[row][col], row, col);
            }
        }
    }

    private void updateButton(Button button, int row, int col) {
        ChessPiece piece = chessBoard.getPiece(row, col);
        if (piece != null) {
            button.setText(piece.getSymbol());
            stylePiece(button, piece);
        } else {
            button.setText("");
        }
    }

    private void highlightLastMove(int row, int col) {
        if (lastMoveSquare != null) {
            lastMoveSquare.setFill((row + col) % 2 == 0 ? Color.BEIGE : Color.DARKGREY);
        }
        lastMoveSquare = squares[row][col];
        lastMoveSquare.setFill(Color.LIGHTBLUE);
    }

    private void checkGameStatus() {
        if (CheckDetector.isKingInCheck(chessBoard, chessBoard.isWhiteToMove())) {
            showAlert("Check!", "Your king is in check!");
        }
        if (CheckDetector.isCheckmate(chessBoard, chessBoard.isWhiteToMove())) {
            showAlert("Checkmate!", chessBoard.isWhiteToMove() ? "AI Wins! Nice Try!" : "Congratulations! You Win!");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void stylePiece(Button button, ChessPiece piece) {
        if (piece == null) return;
        if (piece.isWhite()) {
            button.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
        } else {
            button.setStyle("-fx-font-weight: bold; -fx-text-fill: black;");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
