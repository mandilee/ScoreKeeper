package uk.co.mandilee.scorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    float akaScore;
    float shiroScore;
    int akaWins;
    int shiroWins;

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
        akaScore += 1;

        didAkaWin();
    }

    /*
        Add half a point to the Aka Score
     */
    public void akaWazaAri(View v) {
        TextView akaScoreTextView = (TextView) findViewById(R.id.aka_score);
        akaScore += 0.5;

        didAkaWin();
    }

    /*
        Aka First Warning - Half a penalty point to Shiro
     */
    public void akaKeikoku(View v) {
        TextView shiroScoreTextView = (TextView) findViewById(R.id.shiro_score);
        shiroScore += 0.5;

        didShiroWin();
    }

    /*
        Aka Second Warning - One penalty point to Shiro
     */
    public void akaHansokuShui(View v) {
        TextView shiroScoreTextView = (TextView) findViewById(R.id.shiro_score);
        shiroScore += 1;

        didShiroWin();
    }

    /*
        Aka Won
     */
    public void akaNoKachi(View v) {
        akaScore = 3;
        didAkaWin();

    }

    /*
        Set score in aka_score TextView
     */
    private void akaSetScore() {
        TextView akaScoreTextView = (TextView) findViewById(R.id.aka_score);
        akaScoreTextView.setText(String.valueOf(akaScore));
    }

    /*
        Set number of wins in aka_wins TextView
     */
    private void akaSetWins() {
        TextView akaWinsTextView = (TextView) findViewById(R.id.aka_wins);
        akaWinsTextView.setText(String.valueOf(akaWins));
    }

    /*
        Check to see if Aka won (3 points = win)
        If they did, add 1 win and reset both scores.
        Otherwise, set Aka score
     */
    private void didAkaWin() {
        if (akaScore == 3) {
            TextView akaWinsTextView = (TextView) findViewById(R.id.aka_wins);
            akaWins += 1;
            akaSetWins();
            resetScores();
        } else {
            akaSetScore();
        }
    }


    /*
        Add one point to the Shiro Score
     */
    public void shiroIppon(View v) {
        TextView shiroScoreTextView = (TextView) findViewById(R.id.shiro_score);
        shiroScore += 1;

        didShiroWin();
    }

    /*
        Add half a point to the Shiro Score
     */
    public void shiroWazaAri(View v) { // half a point to shiro/Red Team
        TextView shiroScoreTextView = (TextView) findViewById(R.id.shiro_score);
        shiroScore += 0.5;

        didShiroWin();
    }

    /*
        Shiro First Warning - Half a penalty point to Aka
     */
    public void shiroKeikoku(View v) {
        TextView akaScoreTextView = (TextView) findViewById(R.id.aka_score);
        akaScore += 0.5;

        didAkaWin();
    }

    /*
        Shiro Second Warning - One penalty point to Aka
     */
    public void shiroHansokuShui(View v) { // shiro Warning; one penalty point to Shiro/White Team
        TextView akaScoreTextView = (TextView) findViewById(R.id.aka_score);
        akaScore += 1;

        didAkaWin();
    }

    /*
        Shiro Won
     */
    public void shiroNoKachi(View v) {
        shiroScore = 3;
        didShiroWin();
    }

    /*
        Set score in shiro_score TextView
     */
    private void shiroSetScore() {
        TextView shiroScoreTextView = (TextView) findViewById(R.id.shiro_score);
        shiroScoreTextView.setText(String.valueOf(shiroScore));
    }

    /*
        Set number of wins in shiro_wins TextView
     */
    private void shiroSetWins() {
        TextView shiroWinsTextView = (TextView) findViewById(R.id.shiro_wins);
        shiroWinsTextView.setText(String.valueOf(shiroWins));
    }

    /*
        Check to see if Shiro won (3 points = win)
        If they did, add 1 win and reset both scores.
        Otherwise, set Shiro score
     */
    private void didShiroWin() {
        if (shiroScore == 3) {
            TextView shiroWinsTextView = (TextView) findViewById(R.id.shiro_wins);
            shiroWins += 1;
            shiroSetWins();
            resetScores();
        } else {
            shiroSetScore();
        }
    }

    /*
        Reset all scores and wins
     */
    public void reset(View v) {
        resetScores();
        akaWins = 0;
        akaSetWins();
        shiroWins = 0;
        shiroSetWins();
    }


    private void resetScores() {
        akaScore = 0;
        akaSetScore();
        shiroScore = 0;
        shiroSetScore();
    }
}
