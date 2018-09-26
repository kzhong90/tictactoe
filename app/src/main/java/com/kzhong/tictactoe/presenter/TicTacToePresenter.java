package com.kzhong.tictactoe.presenter;

import com.kzhong.tictactoe.interfaces.TicTacToeView;
import com.kzhong.tictactoe.model.GameBoard;
import com.kzhong.tictactoe.model.Player;

public class TicTacToePresenter {

    private GameBoard mGameBoard;
    private TicTacToeView mView;

    public TicTacToePresenter(TicTacToeView view) {
        mView = view;
        mGameBoard = new GameBoard();
    }

    public void onButtonClicked(int row, int col) {
        Player movingPlayer = mGameBoard.mark(row, col);

        if(movingPlayer != null) {
            mView.setButtonText(row, col, movingPlayer.toString());
            mView.setNextPlayer(mGameBoard.getCurrentTurn().toString());

            if(mGameBoard.getWinner() != null) {
                mView.showWinner(movingPlayer.toString());
            }
        }
    }

    public void onRestartClicked() {
        mView.setNextPlayer(Player.X.toString());
        mView.clearButtons();
        mGameBoard.restart();
    }
}
