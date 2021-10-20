package pl.mbebenek.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button aboutButton;
    private Button exitButton;
    private Button simpleButton;
    private Button advancedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutButton = (Button) findViewById(R.id.about);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutActivity();
            }
        });

        exitButton = (Button) findViewById(R.id.exit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        simpleButton = (Button) findViewById(R.id.simple);
        simpleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSimpleCalcActivity();
            }
        });

        advancedButton = (Button) findViewById(R.id.advanced);
        advancedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvancedCalcActivity();
            }
        });
    }

    public void openAboutActivity() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void openSimpleCalcActivity(){
        Intent intent = new Intent(this, SimpleCalcActivity.class);
        startActivity(intent);
    }

    public void openAdvancedCalcActivity(){
        Intent intent = new Intent(this, AdvancedCalcActivity.class);
        startActivity(intent);
    }
}