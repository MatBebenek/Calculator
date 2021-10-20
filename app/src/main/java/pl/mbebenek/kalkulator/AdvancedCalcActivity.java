package pl.mbebenek.kalkulator;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class AdvancedCalcActivity extends AppCompatActivity {
    TextView workingsTV;
    TextView resultsTV;

    String workings = "";
    String formula = "";
    String tempFormula = "";

    int clicksCounter = 0;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calculator);
        initTextViews();

        backButton = (Button) findViewById(R.id.mainMenu);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (savedInstanceState != null) {
            workings = savedInstanceState.getString("workings");
            workingsTV.setText(workings);
        }
    }

    private void initTextViews() {
        workingsTV = (TextView) findViewById(R.id.workingsTextView);
        resultsTV = (TextView) findViewById(R.id.resultTextView);
    }

    private void setWorkings(String givenValue) {
        workings = workings + givenValue;
        workingsTV.setText(workings);
    }

    private void removeWorkings() {
        StringBuffer sb = new StringBuffer(workings);
        sb.deleteCharAt(sb.length() - 1);
        workings = sb.toString();
        workingsTV.setText(workings);
    }

    public void equalsOnClick(View view) {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();

        String temp;
        temp = formula.replace("sin", "Math.sin").
                replace("cos", "Math.cos").
                replace("tan", "Math.tan").
                replace("ctg", "1/Math.tan").
                replace("sqrt", "Math.sqrt").
                replace("log", "Math.log").
                replace("ln", "Math.log10").
                replace("%", "/100*");

        try {
            result = (double) engine.eval(temp);
        } catch (ScriptException e) {
            Toast.makeText(this, "Wprowadzono błędne dane", Toast.LENGTH_SHORT).show();
        }

        if (result != null)
            resultsTV.setText(String.valueOf(result.doubleValue()));

    }

    private void checkForPowerOf() {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for (int i = 0; i < workings.length(); i++) {
            if (workings.charAt(i) == '^')
                indexOfPowers.add(i);
        }

        formula = workings;
        tempFormula = workings;
        for (Integer index : indexOfPowers) {
            changeFormula(index);
        }
        formula = tempFormula;
    }

    private void changeFormula(Integer index) {
        String numberLeft = "";
        String numberRight = "";

        for (int i = index + 1; i < workings.length(); i++) {
            if (isNumeric(workings.charAt(i)))
                numberRight = numberRight + workings.charAt(i);
            else
                break;
        }

        for (int i = index - 1; i >= 0; i--) {
            if (isNumeric(workings.charAt(i)))
                numberLeft = numberLeft + workings.charAt(i);
            else
                break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow(" + numberLeft + "," + numberRight + ")";
        tempFormula = tempFormula.replace(original, changed);
    }

    private boolean isNumeric(char c) {
        if ((c <= '9' && c >= '0') || c == '.')
            return true;

        return false;
    }


    public void clearOnClick(View view) {
        clicksCounter++;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (clicksCounter == 1) {
                    removeWorkings();
                } else if (clicksCounter == 2) {
                    workingsTV.setText("");
                    workings = "";
                    resultsTV.setText("");
                }
                clicksCounter = 0;
            }
        }, 500);

    }

    public void lefBracketOnCLick(View view) {
        setWorkings("(");
    }

    public void rightBracketOnClick(View view) {
        setWorkings(")");
    }

    public void powerOfOnClick(View view) {
        setWorkings("^");
    }

    public void divisionOnClick(View view) {
        setWorkings("/");
    }

    public void sevenOnClick(View view) {
        setWorkings("7");
    }

    public void eightOnClick(View view) {
        setWorkings("8");
    }

    public void nineOnClick(View view) {
        setWorkings("9");
    }

    public void timesOnClick(View view) {
        setWorkings("*");
    }

    public void fourOnClick(View view) {
        setWorkings("4");
    }

    public void fiveOnClick(View view) {
        setWorkings("5");
    }

    public void sixOnClick(View view) {
        setWorkings("6");
    }

    public void minusOnClick(View view) {
        setWorkings("-");
    }

    public void oneOnClick(View view) {
        setWorkings("1");
    }

    public void twoOnClick(View view) {
        setWorkings("2");
    }

    public void threeOnClick(View view) {
        setWorkings("3");
    }

    public void plusOnClick(View view) {
        setWorkings("+");
    }

    public void decimalOnClick(View view) {
        setWorkings(".");
    }

    public void zeroOnClick(View view) {
        setWorkings("0");
    }

    public void sqrtOnClick(View view) {
        setWorkings("sqrt");
    }

    public void sinusOnClick(View view) {
        setWorkings("sin");
    }

    public void cosinusOnClick(View view) {
        setWorkings("cos");
    }

    public void tangensOnClick(View view) {
        setWorkings("tan");
    }

    public void logOnClick(View view) {
        setWorkings("log");
    }

    public void lnOnClick(View view) {
        setWorkings("ln");
    }

    public void percentOnClick(View view) {
        setWorkings("%");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("workings", workings);
    }
}
