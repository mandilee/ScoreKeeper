package uk.co.mandilee.scorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
        Add one point to the Aka Score
     */
    public void akaIppon(View v) {
        TextView akaScoreTextView = (TextView) findViewById(R.id.aka_score);
        float akaScore = Float.valueOf(akaScoreTextView.getText().toString());
        akaScore += 1;

        didAkaWin(akaScore);
    }

    /*
        Add half a point to the Aka Score
     */
    public void akaWazaAri(View v) {
        TextView akaScoreTextView = (TextView) findViewById(R.id.aka_score);
        float akaScore = Float.valueOf(akaScoreTextView.getText().toString());
        akaScore += 0.5;

        didAkaWin(akaScore);
    }

    /*
        Aka First Warning - Half a penalty point to Shiro
     */
    public void akaKeikoku(View v) {
        TextView shiroScoreTextView = (TextView) findViewById(R.id.shiro_score);
        float shiroScore = Float.valueOf(shiroScoreTextView.getText().toString());
        shiroScore += 0.5;

        didShiroWin(shiroScore);
    }

    /*
        Aka Second Warning - One penalty point to Shiro
     */
    public void akaHansokuShui(View v) {
        TextView shiroScoreTextView = (TextView) findViewById(R.id.shiro_score);
        float shiroScore = Float.valueOf(shiroScoreTextView.getText().toString());
        shiroScore += 1;

        didShiroWin(shiroScore);
    }

    /*
        Aka Won
     */
    public void akaNoKachi(View v) {
        didAkaWin(3);
    }

    /*
        Set score in aka_score TextView
     */
    private void akaSetScore(float akaScore) {
        TextView akaScoreTextView = (TextView) findViewById(R.id.aka_score);
        akaScoreTextView.setText(String.valueOf(akaScore));
    }

    /*
        Set number of wins in aka_wins TextView
     */
    private void akaSetWins(int akaWins) {
        TextView akaWinsTextView = (TextView) findViewById(R.id.aka_wins);
        akaWinsTextView.setText(String.valueOf(akaWins));
    }

    /*
        Check to see if Aka won (3 points = win)
        If they did, add 1 win and reset both scores.
        Otherwise, set Aka score
     */
    private void didAkaWin(float akaScore) {
        if (akaScore == 3) {
            TextView akaWinsTextView = (TextView) findViewById(R.id.aka_wins);
            int akaWins = Integer.valueOf(akaWinsTextView.getText().toString());
            akaWins += 1;
            akaSetWins(akaWins);
            akaSetScore(0);
            shiroSetScore(0);
        } else {
            akaSetScore(akaScore);
        }
    }


    /*
        Add one point to the Shiro Score
     */
    public void shiroIppon(View v) {
        TextView shiroScoreTextView = (TextView) findViewById(R.id.shiro_score);
        float shiroScore = Float.valueOf(shiroScoreTextView.getText().toString());
        shiroScore += 1;

        didShiroWin(shiroScore);
    }

    /*
        Add half a point to the Shiro Score
     */
    public void shiroWazaAri(View v) { // half a point to shiro/Red Team
        TextView shiroScoreTextView = (TextView) findViewById(R.id.shiro_score);
        float shiroScore = Float.valueOf(shiroScoreTextView.getText().toString());
        shiroScore += 0.5;

        didShiroWin(shiroScore);
    }

    /*
        Shiro First Warning - Half a penalty point to Aka
     */
    public void shiroKeikoku(View v) {
        TextView akaScoreTextView = (TextView) findViewById(R.id.aka_score);
        float akaScore = Float.valueOf(akaScoreTextView.getText().toString());
        akaScore += 0.5;

        didAkaWin(akaScore);
    }

    /*
        Shiro Second Warning - One penalty point to Aka
     */
    public void shiroHansokuShui(View v) { // shiro Warning; one penalty point to Shiro/White Team
        TextView akaScoreTextView = (TextView) findViewById(R.id.aka_score);
        float akaScore = Float.valueOf(akaScoreTextView.getText().toString());
        akaScore += 1;

        didAkaWin(akaScore);
    }

    /*
        Shiro Won
     */
    public void shiroNoKachi(View v) {
        didShiroWin(3);
    }

    /*
        Set score in shiro_score TextView
     */
    private void shiroSetScore(float shiroScore) {
        TextView shiroScoreTextView = (TextView) findViewById(R.id.shiro_score);
        shiroScoreTextView.setText(String.valueOf(shiroScore));
    }

    /*
        Set number of wins in shiro_wins TextView
     */
    private void shiroSetWins(int shiroWins) {
        TextView shiroWinsTextView = (TextView) findViewById(R.id.shiro_wins);
        shiroWinsTextView.setText(String.valueOf(shiroWins));
    }

    /*
        Check to see if Shiro won (3 points = win)
        If they did, add 1 win and reset both scores.
        Otherwise, set Shiro score
     */
    private void didShiroWin(float shiroScore) {
        if (shiroScore == 3) {
            TextView shiroWinsTextView = (TextView) findViewById(R.id.shiro_wins);
            int shiroWins = Integer.valueOf(shiroWinsTextView.getText().toString());
            shiroWins += 1;
            shiroSetWins(shiroWins);
            akaSetScore(0);
            shiroSetScore(0);
        } else {
            shiroSetScore(shiroScore);
        }
    }

    /*
        Reset all scores and wins
     */
    public void reset(View v) {
        akaSetScore(0);
        shiroSetScore(0);
        akaSetWins(0);
        shiroSetWins(0);


    }
}
