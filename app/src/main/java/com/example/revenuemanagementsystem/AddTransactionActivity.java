package com.example.revenuemanagementsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.app.DatePickerDialog;

import java.text.DateFormat;
import java.util.Calendar;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import org.w3c.dom.Text;

public class AddTransactionActivity extends AppCompatActivity {

    Spinner spinner;
    EditText amt,note,etDate;
    CardView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_transaction);

        spinner = findViewById(R.id.spinnerType);
        amt=findViewById(R.id.etAmount);
        note=findViewById(R.id.etNote);
        etDate=findViewById(R.id.etDate);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.transaction_type,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        etDate = findViewById(R.id.etDate);

        etDate.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddTransactionActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {

                        String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        etDate.setText(date);

                    },
                    year, month, day
            );

            datePickerDialog.show();
        });
        button=findViewById(R.id.btnSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amountStr = amt.getText().toString();
                String type = spinner.getSelectedItem().toString();
                String date = etDate.getText().toString();
                String noteStr = note.getText().toString();

                if (amountStr.isEmpty() || date.isEmpty()) {
                    amt.setError("Required");
                    etDate.setError("Required");
                    return;
                }

                double amount = Double.parseDouble(amountStr);

                Transaction transaction = new Transaction(type, amount, date, noteStr);

                DataStore.transactionList.add(transaction);

                Toast.makeText(AddTransactionActivity.this,
                        "Transaction Saved", Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }
}