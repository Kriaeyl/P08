package sg.edu.rp.dmsd.p08;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText et1;
    EditText et2;
    Button b1;
    Button b2;
    TextView tv1;
    TextView tv2;
    TextView tv3;


    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("owo", tv1.getText().toString());
        prefEdit.putString("uwu", tv2.getText().toString());
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        tv1.setText(prefs.getString("owo", "Last Calculated Date: "));
        tv2.setText(prefs.getString("uwu", "Last Calculated BMI: "));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.editText);
        et2 = findViewById(R.id.editText2);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        tv1 = findViewById(R.id.textView2);
        tv2 = findViewById(R.id.textView3);
        tv3 = findViewById(R.id.textView4);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
                String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                        (now.get(Calendar.MONTH)+1) + "/" +
                        now.get(Calendar.YEAR) + " " +
                        now.get(Calendar.HOUR_OF_DAY) + ":" +
                        now.get(Calendar.MINUTE);

                tv1.setText("Last Calculated Date: " + datetime);
                double bmi = Integer.parseInt(et1.getText().toString()) / (Double.parseDouble(et2.getText().toString())*Double.parseDouble(et2.getText().toString()));
                tv2.setText("Last Calculated BMI: " + bmi);
                if (bmi < 18.5) {
                    tv3.setText("You are Underweight");
                }
                else if (bmi < 25) {
                    tv3.setText("Your BMI is normal");
                }
                else if (bmi < 30) {
                    tv3.setText("You are overweight");
                }
                else {
                    tv3.setText("You are obese");
                }

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setText("Last Calculated Date: ");
                tv2.setText("Last Calculated BMI: ");
                tv3.setText("");
                et1.setText("");
                et2.setText("");
            }
        });
    }
}
