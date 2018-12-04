package com.example.jacek.healthy_eating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;

import javax.xml.datatype.Duration;

import Dao.ActivityLevel;
import Dao.DatabaseHelper;
import Dao.User;

public class UserData extends AppCompatActivity {
    private TextView textViewAge;
    private TextView textViewHeight;
    private TextView textViewWeight;

    private EditText editTextAge;
    private EditText editTextHeight;
    private EditText editTextWeight;

    private Spinner spinnerActivity;

    private Button buttonSave;

    private DatabaseHelper db;
    private static List<User> users = new LinkedList<>();
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        db = DatabaseHelper.getInstance(getApplicationContext());
        users = db.getAllUsers();

        user = db.getUser(1);

        initializeComponents();
        seedData(user);
    }

    private void initializeComponents() {
        textViewAge = findViewById(R.id.textViewAge);
        textViewHeight = findViewById(R.id.textViewHeight);
        textViewWeight = findViewById(R.id.textViewWeight);

        editTextAge = findViewById(R.id.editTextAge);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);

        spinnerActivity = findViewById(R.id.spinnerActivity);
        final ArrayAdapter<ActivityLevel> spinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, ActivityLevel.values());
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerActivity.setAdapter(spinnerAdapter);

        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = Toast.makeText(getApplicationContext(),
                        spinnerActivity.getSelectedItem().toString(),
                        Toast.LENGTH_LONG);
                t.show();
            }
        });
    }

    private void seedData(User user) {
        if (user != null) {
            editTextAge.setText(String.valueOf(user.getAge()));
            editTextHeight.setText(String.valueOf(user.getHeight()));
            editTextWeight.setText(String.valueOf(user.getWeight()));
        }
    }
}
