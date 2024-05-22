package com.example.dialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button alertDialogButton;
    private Button progressDialogButton;
    private Button datePickerDialogButton;
    private Button timePickerDialogButton;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alertDialogButton = findViewById(R.id.alertDialogButton);
        progressDialogButton = findViewById(R.id.progressDialogButton);
        datePickerDialogButton = findViewById(R.id.datePickerDialogButton);
        timePickerDialogButton = findViewById(R.id.timePickerDialogButton);

        alertDialogButton.setOnClickListener(view -> showAlertDialog());
        progressDialogButton.setOnClickListener(view -> showProgressDialog());
        datePickerDialogButton.setOnClickListener(view -> showDatePickerDialog());
        timePickerDialogButton.setOnClickListener(view -> showTimePickerDialog());
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert Dialog")
                .setMessage("This is an Alert Dialog")
                .setPositiveButton("OK", (dialog, which) -> {
                    Toast.makeText(MainActivity.this, "OK clicked", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    Toast.makeText(MainActivity.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
                })
                .setNeutralButton("Ignore", (dialog, which) -> {
                    Toast.makeText(MainActivity.this, "Ignore clicked", Toast.LENGTH_SHORT).show();
                });
        builder.create().show();
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Progress Dialog");
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
        progressDialog.setMax(100);
        progressDialog.show();

        new Thread(() -> {
            try {
                while (progressDialog.getProgress() <= progressDialog.getMax()) {
                    Thread.sleep(200);
                    progressDialog.incrementProgressBy(10);
                    if (progressDialog.getProgress() == progressDialog.getMax()) {
                        progressDialog.dismiss();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, monthOfYear, dayOfMonth) -> {
            String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
            Toast.makeText(MainActivity.this, "Selected Date: " + date, Toast.LENGTH_SHORT).show();
        }, year, month, day);
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute1) -> {
            String time = hourOfDay + ":" + minute1;
            Toast.makeText(MainActivity.this, "Selected Time: " + time, Toast.LENGTH_SHORT).show();
        }, hour, minute, true);
        timePickerDialog.show();
    }
}
