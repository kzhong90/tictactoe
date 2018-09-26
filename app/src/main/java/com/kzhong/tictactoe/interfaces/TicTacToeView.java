package com.kzhong.tictactoe.interfaces;

public interface TicTacToeView {
    void setNextPlayer(String nextPlayer);
    void showWinner(String winningPlayer);
    void clearButtons();
    void setButtonText(int row, int col, String text);
}
