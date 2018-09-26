package com.kzhong.tictactoe.model;

import static com.kzhong.tictactoe.model.Player.X;
import static com.kzhong.tictactoe.model.Player.O;

public class GameBoard {

    private Cell[][]  mCell = new Cell[4][4];

    private Player winner;
    private GameState mGameState;
    private Player currentTurn;

    private int boardSize;

    public GameBoard() {
        restart();
    }

    public void restart() {
        initCells();
        winner = null;
        currentTurn = Player.X;
        mGameState = GameState.IN_PROGRESS;
        boardSize = mCell.length;
    }

    public Player mark(int row, int col) {
        Player movingPlayer = null;

        if(isValid(row, col)) {
            mCell[row][col].setPlayer(currentTurn);
            movingPlayer = currentTurn;

            if(checkPlayerWon(currentTurn, row, col)) {
                mGameState = GameState.DONE;
                winner = currentTurn;
            } else {
                nextPlayersTurn();
            }
        }

        return movingPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getCurrentTurn() {
        return currentTurn;
    }

    private void initCells() {
        for(int i=0;i<mCell.length; i++) {
            for(int j=0; j<mCell.length; j++) {
                mCell[i][j] = new Cell();
            }
        }
    }

    private boolean isValid(int row, int col) {
        if(mGameState==GameState.DONE) {
            return false;
        } else if(mCell[row][col].getPlayer() != null) {
            return false;
        } else {
            return true;
        }
    }

    private void nextPlayersTurn() {
        currentTurn = currentTurn == X ? O : X;
    }

    private boolean checkPlayerWon(Player player, int row, int col) {

        return (horizontalWin(player, row) ||
                verticalWin(player, col) ||
                diagonalWinLeftToRight(player) ||
                diagonalWinRightToLeft(player) ||
                fourCornerWin(player));
    }

    private boolean horizontalWin(Player player, int row) {
        int counter=0;
        for(int i=0;i<boardSize;i++) {
            if(mCell[row][i].getPlayer()==player) {
                counter++;
            }
        }
        return counter==boardSize;
    }

    private boolean verticalWin(Player player, int col) {
        int counter=0;
        for(int i=0; i<boardSize;i++) {
            if(mCell[i][col].getPlayer()==player) {
                counter++;
            }
        }
        return counter==boardSize;
    }

    private boolean diagonalWinLeftToRight(Player player) {
        int counter = 0;
        for (int i = 0; i < boardSize; i++) {
            if (mCell[i][i].getPlayer() == player) {
                counter++;
            }
        }
        return counter == boardSize;
    }

    private boolean diagonalWinRightToLeft(Player player) {
        int counter = 0;
        for (int i = 0; i < boardSize; i++) {
            if (mCell[i][(boardSize-1)-i].getPlayer() == player) {
                counter++;
            }
        }
        return counter == boardSize;
    }

    private boolean fourCornerWin(Player player) {
        return mCell[0][0].getPlayer() == player &&
               mCell[0][boardSize-1].getPlayer() == player &&
               mCell[boardSize-1][0].getPlayer() == player &&
               mCell[boardSize-1][boardSize-1].getPlayer() == player;
    }

    private enum GameState {
        IN_PROGRESS,
        DONE
    }
}
