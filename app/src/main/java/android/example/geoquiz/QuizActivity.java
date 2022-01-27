package android.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.*; //Resource for setting ID for widgets

import android.view.*;  //Import for listener

import org.w3c.dom.Text;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton, mFalseButton, mNextButton;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_vietnam, true),
            new Question(R.string.question_1, false),
            new Question(R.string.question_2, true),
            new Question(R.string.question_3, false),
            new Question(R.string.question_4, true),
            new Question(R.string.question_5, false),
    };

    //Update Next Question function
    private void updateQuestion(){
        int question = mQuestionBank[mCurrIndex].getmTextResId();
        mQuestionTextView.setText(question);
    }

    //Check if the answer is correct
    private void checkAnswer(Boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrIndex].ismAnswerTrue();
        int messageResId = 0;

        if(userPressedTrue == answerIsTrue)
            messageResId = R.string.correct_toast;
        else
            messageResId = R.string.incorrect_toast;

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    private int mCurrIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);

        //Getting References to Widgets
        //Method calls  Casting Button      Passing the resource ID
        //     |          |                         |
        //     V          V                         V
        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mNextButton = (Button)findViewById(R.id.next_button);

        //Next Button Listener
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Execute code goes here...
                mCurrIndex = (mCurrIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        //True Button Listener
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Execute code goes here...
                checkAnswer(true);
            }
        });

        //False Button Listener
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Execute code goes here...
                checkAnswer(false);
            }
        });

    }
}