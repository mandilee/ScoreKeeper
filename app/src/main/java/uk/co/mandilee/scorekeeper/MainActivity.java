package uk.co.mandilee.scorekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText teamANameText;
    private EditText teamBNameText;
    private EditText pointsText;
    private EditText roundsText;

    // grab the default values for the fields
    private String teamAName;
    private String teamBName;
    private float points;
    private int rounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // grab the button
        Button beginBtn = (Button) findViewById(R.id.beginButton);
        Button defaultBtn = (Button) findViewById(R.id.defaultButton);

        // get the EditText fields for later
        teamANameText = (EditText) findViewById(R.id.teamAName);
        teamBNameText = (EditText) findViewById(R.id.teamBName);
        pointsText = (EditText) findViewById(R.id.points);
        roundsText = (EditText) findViewById(R.id.rounds);

        beginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean nextPage = true;
                teamAName = teamANameText.getText().toString();
                teamBName = teamBNameText.getText().toString();

                // check that points entered are numeric
                if (isFloat(pointsText.getText().toString())) {
                    points = Float.valueOf(pointsText.getText().toString());
                } else {
                    nextPage = false;
                }

                // check that rounds entered are numeric
                if (isInt(roundsText.getText().toString())) {
                    rounds = Integer.valueOf(roundsText.getText().toString());
                } else {
                    nextPage = false;
                }

                if (nextPage) {
                    Intent scoreKeeperIntent = new Intent(MainActivity.this, ScoreKeeper.class);
                    scoreKeeperIntent.putExtra("TEAM_A_NAME", teamAName);
                    scoreKeeperIntent.putExtra("TEAM_B_NAME", teamBName);
                    scoreKeeperIntent.putExtra("POINTS", points);
                    scoreKeeperIntent.putExtra("ROUNDS", rounds);
                    startActivity(scoreKeeperIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "Points and Rounds must be numbers", Toast.LENGTH_LONG).show();
                }
            }
        });

        defaultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamAName = getString(R.string.default_team_a);
                teamBName = getString(R.string.default_team_b);
                points = Float.valueOf(getString(R.string.default_points));
                rounds = Integer.valueOf(getString(R.string.default_rounds));
                teamANameText.setText(teamAName);
                teamBNameText.setText(teamAName);
                pointsText.setText(String.valueOf(points));
                roundsText.setText(String.valueOf(rounds));
            }
        });
    }

    /**
     * check if given parameter is a float
     *
     * @param value - see if this is a float
     * @return true if no exception thrown; false if exception thrown (not a float)
     */
    private boolean isFloat(String value) {
        try {
            Float.parseFloat(value); // intentionally ignored result
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * check if given parameter is an integer
     * @param value - see if this is an integer
     * @return true if no exception thrown; false if exception thrown (not an integer)
     */
    private boolean isInt(String value) {
        try {
            Integer.parseInt(value); // intentionally ignored result
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
