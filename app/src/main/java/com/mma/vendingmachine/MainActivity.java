package com.mma.vendingmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    BottleDispenser dispenser = BottleDispenser.getInstance();
    Context context = null;
    TextView text = null;
    Spinner spinner = null;
    SeekBar seek = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        text = findViewById(R.id.textView);
        spinner = findViewById(R.id.spinnerBottle);
        seek = findViewById(R.id.seekBar);

        ArrayAdapter<Bottle> adapter = new ArrayAdapter<Bottle>(this, android.R.layout.simple_spinner_item, dispenser.getList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void purchaseAction(View view) {
        int index = spinner.getSelectedItemPosition();

        if (index >= 0) {
            Bottle bottle = (Bottle) spinner.getSelectedItem();

            if (dispenser.purchase(bottle)) {
                String text = String.format("KACHUNK! %s tipahti masiinasta!", bottle.getName());
                this.text.setText(text);
                receipt(bottle);
            }
        }
    }

    public void insertAction(View view) {
        int money = seek.getProgress();
        String text = "Klink! Lisää rahaa laitteeseen!";
        dispenser.insertMoney(money);
        this.text.setText(text);
        seek.setProgress(0);
    }

    public void returnAction(View view) {
        String text = String.format("Klink klink. Rahaa tuli ulos %.2f€", ((float)dispenser.getMoney() / 100));
        dispenser.returnMoney();
        this.text.setText(text);
    }

    private void receipt(Bottle bottle) {
        String filename = "receipt.txt";

        try {
            OutputStream os = context.openFileOutput(filename, Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(os);

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            String output = String.format("Receipt%n%s%n%s%n", sdf.format(date), bottle.toString());

            osw.write(output);
            osw.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe");
        }
    }
}
