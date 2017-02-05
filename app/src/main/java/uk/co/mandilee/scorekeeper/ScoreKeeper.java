package uk.co.mandilee.scorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreKeeper extends AppCompatActivity {
    private TextView akaScoreTextView;
    private TextView akaWinsTextView;
    private TextView shiroScoreTextView;
    private TextView shiroWinsTextView;

    private float akaScore;
    private float shiroScore;
    private int akaWins;
    private int shiroWins;

    private String teamAName;
    private String teamBName;
    private float minutes;
    private float points;
    private int rounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // recovering the instance state
        if (savedInstanceState != null) {
            akaScore = savedInstanceState.getFloat("AKA_SCORE");
            shiroScore = savedInstanceState.getFloat("SHIRO_SCORE");
            akaWins = savedInstanceState.getInt("AKA_WINS");
            shiroWins = savedInstanceState.getInt("SHIRO_WINS");
        }
        setContentView(R.layout.activity_score_keeper);

        // set team names from previous activity
        TextView teamA = (TextView) findViewById(R.id.teamAName);
        teamAName = getIntent().getStringExtra("TEAM_A_NAME");
        teamA.setText(teamAName);
        TextView teamB = (TextView) findViewById(R.id.teamBName);
        teamBName = getIntent().getStringExtra("TEAM_B_NAME");
        teamB.setText(teamBName);

        // grab settings from previous activity
        minutes = getIntent().getFloatExtra("MINUTES", R.string.default_minutes);
        points = getIntent().getFloatExtra("POINTS", R.string.default_points);
        rounds = getIntent().getIntExtra("ROUNDS", R.string.default_rounds);

        // initialize member TextView so we can manipulate it later
        akaScoreTextView = (TextView) findViewById(R.id.aka_score);
        shiroScoreTextView = (TextView) findViewById(R.id.shiro_score);
        akaWinsTextView = (TextView) findViewById(R.id.aka_wins);
        shiroWinsTextView = (TextView) findViewById(R.id.shiro_wins);

        if (savedInstanceState == null) {
            resetScores();
            resetWins();
        }
    }// This callback is called only when there is a saved instance previously saved using

    // onSaveInstanceState(). We restore some state in onCreate() while we can optionally restore
    // other state here, possibly usable after onStart() has completed.
    // The savedInstanceState Bundle is same as the one used in onCreate().
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        //*
        akaScoreTextView.setText(String.valueOf(savedInstanceState.getFloat("AKA_SCORE")));
        shiroScoreTextView.setText(String.valueOf(savedInstanceState.getFloat("SHIRO_SCORE")));
        akaWinsTextView.setText(String.valueOf(savedInstanceState.getInt("AKA_WINS")));
        shiroWinsTextView.setText(String.valueOf(savedInstanceState.getInt("SHIRO_WINS")));
        //*/
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putFloat("AKA_SCORE", akaScore);
        outState.putFloat("SHIRO_SCORE", shiroScore);
        outState.putInt("AKA_WINS", akaWins);
        outState.putInt("SHIRO_WINS", shiroWins);

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    /*
        Add one point to the Aka Score
     */
    public void akaIppon(View v) {
        akaScore += 1;
        didAkaWin();
    }


    /*
        Add half a point to the Aka Score
     */
    public void akaWazaAri(View v) {
        akaScore += 0.5;
        didAkaWin();
    }

    /*
        Aka First Warning - Half a penalty point to Shiro
     */
    public void akaKeikoku(View v) {
        shiroScore += 0.5;
        didShiroWin();
    }

    /*
        Aka Second Warning - One penalty point to Shiro
     */
    public void akaHansokuShui(View v) {
        shiroScore += 1;
        didShiroWin();
    }

    /*
        Aka Won
     */
    public void akaNoKachi(View v) {
        akaScore = points;
        didAkaWin();
    }

    /*
        Set score in aka_score TextView
     */
    private void akaSetScore() {
        akaScoreTextView.setText(String.valueOf(akaScore));
    }

    /*
        Set number of wins in aka_wins TextView
     */
    private void akaSetWins() {
        akaWinsTextView.setText(String.valueOf(akaWins));
    }

    /*
        Check to see if Aka won (3 points = win)
        If they did, add 1 win and reset both scores.
        Otherwise, set Aka score
     */
    private void didAkaWin() {
        if (akaScore >= points) {
            akaWins += 1;
            akaSetWins();
            if (akaWins >= rounds) {
                Toast.makeText(getApplicationContext(), teamAName + " WINS!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Round goes to " + teamAName + "!", Toast.LENGTH_SHORT).show();
            }
            resetScores();
        } else {
            akaSetScore();
        }
    }


    /*
        Add one point to the Shiro Score
     */
    public void shiroIppon(View v) {
        shiroScore += 1;
        didShiroWin();
    }

    /*
        Add half a point to the Shiro Score
     */
    public void shiroWazaAri(View v) { // half a point to shiro/Red Team
        shiroScore += 0.5;
        didShiroWin();
    }

    /*
        Shiro First Warning - Half a penalty point to Aka
     */
    public void shiroKeikoku(View v) {
        akaScore += 0.5;
        didAkaWin();
    }

    /*
        Shiro Second Warning - One penalty point to Aka
     */
    public void shiroHansokuShui(View v) { // shiro Warning; one penalty point to Shiro/White Team
        akaScore += 1;
        didAkaWin();
    }

    /*
        Shiro Won
     */
    public void shiroNoKachi(View v) {
        shiroScore = points;
        didShiroWin();
    }

    /*
        Set score in shiro_score TextView
     */
    private void shiroSetScore() {
        shiroScoreTextView.setText(String.valueOf(shiroScore));
    }

    /*
        Set number of wins in shiro_wins TextView
     */
    private void shiroSetWins() {
        shiroWinsTextView.setText(String.valueOf(shiroWins));
    }

    /*
        Check to see if Shiro won (3 points = win)
        If they did, add 1 win and reset both scores.
        Otherwise, set Shiro score
     */
    private void didShiroWin() {
        if (shiroScore >= points) {
            shiroWins += 1;

            if (akaWins >= rounds) {
                Toast.makeText(getApplicationContext(), teamBName + " WINS!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Round goes to " + teamBName + "!", Toast.LENGTH_SHORT).show();
            }
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
        resetWins();
    }


    private void resetScores() {
        akaScore = 0;
        akaSetScore();
        shiroScore = 0;
        shiroSetScore();
    }

    private void resetWins() {
        akaWins = 0;
        akaSetWins();
        shiroWins = 0;
        shiroSetWins();
    }
}
