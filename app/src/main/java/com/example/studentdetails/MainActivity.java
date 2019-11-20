package com.example.studentdetails;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner spincountry;
    private AutoCompleteTextView actv1;
    private String[] batch = {"22A","22B","22C"};
    private Button btnsave;
    TextView tvoutputname, tvoutputgender, tvoutputcountry, tvoutputbatch;
    RadioGroup rdgroup;
    EditText etname;
    AlertDialog.Builder builer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        builer = new AlertDialog.Builder(this);

        spincountry = findViewById(R.id.spincountry);
        actv1 = findViewById(R.id.actv1);
        btnsave = findViewById(R.id.btnsave);
        etname = findViewById(R.id.etname);
        tvoutputname = findViewById(R.id.tvoutputname);
        tvoutputgender = findViewById(R.id.tvoutputgender);
        tvoutputcountry = findViewById(R.id.tvoutputcountry);
        tvoutputbatch = findViewById(R.id.tvoutputbatch);
        rdgroup = findViewById(R.id.rdgroup);

        String countries[] = {"Nepal","China","India"};
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries);
        spincountry.setAdapter(adapter);

        ArrayAdapter stringadapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, batch);
        actv1.setAdapter(stringadapter);
        actv1.setThreshold(1);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builer.setMessage("Do you want to save?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    tvoutputname.setText(String.valueOf(etname.getText()));
                                    RadioButton selectgender = findViewById(rdgroup.getCheckedRadioButtonId());
                                    tvoutputgender.setText(String.valueOf(selectgender.getText()));
                                    tvoutputcountry.setText(String.valueOf(spincountry.getSelectedItem()));
                                    tvoutputbatch.setText(String.valueOf(actv1.getText()));
                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builer.create();
                alert.setTitle("Save");
                alert.show();


            }

        });
    }
}
