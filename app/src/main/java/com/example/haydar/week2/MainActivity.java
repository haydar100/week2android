package com.example.haydar.week2;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class MainActivity extends Activity implements TextWatcher {
    private EditText name;
    private EditText phoneNumber;
    private EditText email;
    private Button datePicker;
    private RadioButton male;
    private RadioButton female;
    private EditText geboortedatum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.Name);
        phoneNumber = (EditText) findViewById(R.id.telefoonNummer);
        email = (EditText) findViewById(R.id.emailAdress);
        male = (RadioButton) findViewById(R.id.Male);
        female = (RadioButton) findViewById(R.id.Male);
        geboortedatum = (EditText) findViewById(R.id.birthdate);

        name.addTextChangedListener(this);
        phoneNumber.addTextChangedListener(this);
        email.addTextChangedListener(this);


       geboortedatum.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // TODO Auto-generated method stub
               new DatePickerDialog(MainActivity.this, date, myCalendar
                       .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                       myCalendar.get(Calendar.DAY_OF_MONTH)).show();
           }
       });

    }

    public void OK(View v) {
        Log.i("Naam" ,""+name.getText().toString());
        Log.i("telefoonmummer", ""+phoneNumber.getText().toString());
        Log.i("email", ""+email.getText().toString());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
      Validate();
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Validate();
    }

    @Override
    public void afterTextChanged(Editable editable) {
       Validate();
    }

    public void Validate() {
        if (name.getText().toString().length() == 0) {
            name.setError("Vul een naam in");
        }
        if (!isValidEmail(email.getText().toString())) {
            email.setError("Vul een geldig email adress in !");
        }
        if (phoneNumber.getText().toString().length() == 0) {
            phoneNumber.setError("Vul een telefoonnummer in");
        }
    }


    Calendar myCalendar = Calendar.getInstance();


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };


    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        geboortedatum.setText(sdf.format(myCalendar.getTime()));
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
