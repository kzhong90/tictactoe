package com.kzhong.tictactoe.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kzhong.tictactoe.R;
import com.kzhong.tictactoe.interfaces.TicTacToeView;
import com.kzhong.tictactoe.presenter.TicTacToePresenter;

public class MainActivity extends AppCompatActivity implements TicTacToeView {

    private TicTacToePresenter mPresenter;

    private TextView currentPlayerText;
    private ViewGroup gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new TicTacToePresenter(this);
        currentPlayerText = findViewById(R.id.playerLabel);
        gridLayout = findViewById(R.id.grid_layout);
    }

    public void onCellButtonClicked(View v) {
        Button mButton = (Button) v;
        String tag = mButton.getTag().toString();
        int row = Integer.valueOf(tag.substring(0,1));
        int col = Integer.valueOf(tag.substring(1,2));

        mPresenter.onButtonClicked(row, col);

    }

    public void onRestartButtonClicked(View v) {
        mPresenter.onRestartClicked();
    }

    @Override
    public void setNextPlayer(String nextPlayer) {
        String text = "Current Player: " + nextPlayer;
        currentPlayerText.setText(text);
    }

    @Override
    public void showWinner(String winner) {
        String text = "Winner: " + winner;
        currentPlayerText.setText(text);
    }

    @Override
    public void clearButtons() {
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ((Button) gridLayout.getChildAt(i)).setText("");
        }
    }

    @Override
    public void setButtonText(int row, int col, String text) {
        Button btn = gridLayout.findViewWithTag("" + row + col);
        if(btn != null) {
            btn.setText(text);
        }
    }
}
