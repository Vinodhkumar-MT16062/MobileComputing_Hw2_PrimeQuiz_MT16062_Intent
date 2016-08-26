package vinodhkumar.hw1_mt16062_primequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private TextView mNumberTextView;
    private int randNumber;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mHintButton;
    private Button mAnswerButton;
    private final Random rand = new Random();
    private static final String STATE_NUMBER = "randomNumber";
    public static final String QUESTION = "MT16062_INTENT_MSG";
    public static final int HINT_CODE = 1;
    public static final int ANSWER_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CharSequence correctText = "Bingo! You are right.";
        CharSequence incorrectText = "Sorry! Try again.";
        final Toast correctToast = Toast.makeText(getApplicationContext(), correctText,Toast.LENGTH_SHORT);
        final Toast incorrectToast = Toast.makeText(getApplicationContext(), incorrectText, Toast.LENGTH_SHORT);

        mNumberTextView = (TextView)findViewById(R.id.numberTextView);
        mTrueButton = (Button)findViewById(R.id.trueButton);
        mFalseButton = (Button)findViewById(R.id.falseButton);
        mNextButton = (Button)findViewById(R.id.nextButton);
        mHintButton = (Button)findViewById(R.id.hintButton);
        mAnswerButton = (Button)findViewById(R.id.answerButton);

        if(savedInstanceState != null){
            mNumberTextView.setText(savedInstanceState.getString(STATE_NUMBER));
        }
        else {
            generateRandomNumber();
        }
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                generateRandomNumber();
            }

        });

        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String str = String.valueOf(((TextView) findViewById(R.id.numberTextView)).getText());
                if(isPrime(Integer.parseInt(str))){
                    correctToast.show();
                }
                else{
                    incorrectToast.show();
                }
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String str = String.valueOf(((TextView) findViewById(R.id.numberTextView)).getText());
                if(isPrime(Integer.parseInt(str))){
                    incorrectToast.show();
                }
                else{
                    correctToast.show();
                }
            }
        });

        mHintButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),HintActivity.class);
                startActivityForResult(intent, HINT_CODE);
            }
        });

        mAnswerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),CheatActivity.class);
                intent.putExtra(QUESTION, Integer.parseInt(mNumberTextView.getText().toString()));
                startActivityForResult(intent,ANSWER_CODE);
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putString(STATE_NUMBER,String.valueOf(mNumberTextView.getText()));
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data){
        if(requestCode==HINT_CODE && resultCode==RESULT_OK)
        {
            Toast.makeText(getApplicationContext(),"Hint Taken!",Toast.LENGTH_SHORT).show();
        }
        else if(requestCode==ANSWER_CODE && resultCode==RESULT_OK)
        {
            Toast.makeText(getApplicationContext(),"Answer Seen!",Toast.LENGTH_SHORT).show();
        }
    }

    private void generateRandomNumber(){
        int max = 1000;
        int min = 1;
        randNumber = rand.nextInt((max - min) + 1) + min;
        mNumberTextView.setText(String.valueOf(randNumber));
    }

    private boolean isPrime(int n) {
        //check if n is a multiple of 2
        if (n%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }
}