package com.mfundoza.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;

import com.mfundoza.navigation.databinding.ActivityMainBinding;

import java.text.DateFormat;
import java.util.Calendar;

import static com.mfundoza.navigation.SignUpFragment.dateOfBirth;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    ActivityMainBinding binding;
    NavHostFragment navHostFragment;
    static NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);

        dateOfBirth = cal.getTime();

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(dateOfBirth);

        SignUpFragment.binding.tilDateOfBirth.getEditText().setText(currentDateString);

    }
}