package com.example.tipcal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText CheckAmt; //Check amount
    private EditText NumPeople; //Number of people
    private EditText TipPer; //Tip percentage

    private TextView TotalBill; //Total of the Bill
    private TextView BillPeople; //Total paid by each person
    private TextView TotalTip; // Total amount of Tip
    private TextView TipPeople; //Amount of Tip paid by each person

    private Button Calculate; //Pressing this button will calculate the needed information

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckAmt = (EditText) findViewById(R.id.CheckAmt);
        NumPeople = (EditText) findViewById(R.id.NumPeople);
        TipPer = (EditText) findViewById(R.id.TipPer);
        TipPer.setText("");
        TipPer.setHint("Default: 15%");

        TotalBill = (TextView) findViewById(R.id.TotalBill);
        BillPeople = (TextView) findViewById(R.id.BillPeople);
        TotalTip = (TextView) findViewById(R.id.TotalTip);
        TipPeople = (TextView) findViewById(R.id.TipPeople);

        Typeface typeface = getResources().getFont(R.font.droid_serif_bold);
        TotalBill.setTypeface(typeface);
        BillPeople.setTypeface(typeface);
        TotalTip.setTypeface(typeface);
        TipPeople.setTypeface(typeface);

        Calculate = (Button) findViewById(R.id.Calculate);
        Typeface typeface2 = getResources().getFont(R.font.alfa_slab_one);
        Calculate.setTypeface(typeface2);
        Calculate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String S_CheckAmt = CheckAmt.getText().toString();
                String S_NumPeople = NumPeople.getText().toString();
                String S_TipPer = TipPer.getText().toString();
                boolean check = true;


                if (S_CheckAmt.equals("") | S_CheckAmt == null) {
                    CheckAmt.setText("0");
                    check = false;

                }
                if (S_NumPeople.equals("") | S_NumPeople == null | S_NumPeople.equals("0")) {
                    Toast.makeText(MainActivity.this,"Number of people should " +
                            "be greater than 0", Toast.LENGTH_LONG).show();
                    NumPeople.setText("");
                    check = false;
                }
                if (S_TipPer.equals("") | S_TipPer == null) {
                    TipPer.setText("15");
                }


                if (check) {
                    Double D_CheckAmt = Double.parseDouble(CheckAmt.getText().toString());
                    int I_NumPeople = Integer.parseInt(NumPeople.getText().toString());
                    Double D_TipPer = Double.parseDouble(TipPer.getText().toString());

                    Double D_TotalBill = D_CheckAmt * (1 + D_TipPer/100);
                    Double D_BillPeople = D_TotalBill / I_NumPeople;
                    Double D_TotalTip = D_CheckAmt * (D_TipPer/100);
                    Double D_TipPeople = D_TotalTip / I_NumPeople;

                    TotalBill.setText("$" + String.format("%.2f", D_TotalBill));
                    BillPeople.setText("$" + String.format("%.2f", D_BillPeople));
                    TotalTip.setText("$" + String.format("%.2f", D_TotalTip));
                    TipPeople.setText("$" + String.format("%.2f", D_TipPeople));
                }
                else{
                    TotalBill.setText("");
                    BillPeople.setText("");
                    TotalTip.setText("");
                    TipPeople.setText("");
                }


            }
        });


    }
}