package vinodhkumar.hw1_mt16062_primequiz;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * Created by VinodhKumar on 27/08/16.
 */
public class CheatActivity extends AppCompatActivity {

    private TextView mNumberTextView;
    private TextView mAnswerTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int number,defaultValue = 0;
        setContentView(R.layout.activity_cheat);
        mNumberTextView = (TextView)findViewById(R.id.numberHolderView);
        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
        number = getIntent().getIntExtra(MainActivity.QUESTION,defaultValue);
        mNumberTextView.setText(String.valueOf(number));
        if(isPrime(number)){
            mAnswerTextView.setText(" is a prime number!");
        }
        else{
            mAnswerTextView.setText(" is not a prime number!");
        }
        setResult(Activity.RESULT_OK);
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
