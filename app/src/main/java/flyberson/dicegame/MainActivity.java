package flyberson.dicegame;

import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView rollResult;

    Button rollButton;

//field to hold score
    private int score;

    Random rand;
// create dice
    int die1;
    int die2;
    int die3;

    //ArrayList for dice
    ArrayList<Integer> dice;

    //ArrayList for dice images
    ArrayList<ImageView> diceImageViews;

    //Score text
    TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // set initial score
        score=0;
        FloatingActionButton fab= (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                rollDice(view);
            }
        })

        ;
        rollResult=(TextView)findViewById(R.id.rollResult);
        rollButton = (Button) findViewById(R.id.rollButton);
        rand = new Random();

        // initialize arrayList
        dice=new ArrayList<Integer>();

        ImageView die1Image =(ImageView) findViewById(R.id.die1Image) ;
        ImageView die2Image =(ImageView) findViewById(R.id.die2Image) ;
        ImageView die3Image =(ImageView) findViewById(R.id.die3Image) ;
        diceImageViews = new ArrayList<ImageView>();
        diceImageViews.add(die1Image);
        diceImageViews.add(die2Image);
        diceImageViews.add(die3Image);
        // create greeting
        Toast.makeText(getApplicationContext(),"Welcome to DiceGame",Toast.LENGTH_SHORT).show();

        scoreText = (TextView) findViewById(R.id.scoreText);
    }

    public void rollDice(View view) {

        rollResult.setText("Clicked!");
        //Roll die
        die1 = rand.nextInt(6)+1;
        die2 = rand.nextInt(6)+1;
        die3 = rand.nextInt(6)+1;

        // add dice to dice Array
        dice.clear();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        for (int dieOfSet = 0; dieOfSet < 3; dieOfSet++) {
            String imageName = "die_"+dice.get(dieOfSet) + ".png";

            try {
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream,null);
                diceImageViews.get(dieOfSet).setImageDrawable(d);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        String msg = "You rolled a " + die1 + " a "+ die2 +" and "+die3;

        if(die1==die2 && die1==die3){
            int scoreDelta = die1*100;
            msg="You Rolled a triple "+die1+"You score "+scoreDelta+ " points";
            score +=scoreDelta;
        }
        else if (die1==die2 || die1==die3 || die2==die3){
            msg = "You rolled doubles for 50 points";
                    score+=50;
        }
        else {
            msg="You didn't score this roll. Try again!";
        }
        rollResult.setText(msg);
        scoreText.setText("Score:" + score);
    }
}
