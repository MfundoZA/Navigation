package com.mfundoza.navigation;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.google.android.material.snackbar.Snackbar;
import com.mfundoza.navigation.databinding.FragmentSignUpBinding;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {
    public static FragmentSignUpBinding binding;

    DatePickerFragment datePickerFragment; // Class that extends DialogFragment

    String title;
    String firstName;
    String lastName;
    public static Date dateOfBirth;
    String email;
    String password;
    boolean premiumService;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment SignUpFragment.
     */
    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.tilDateOfBirth.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDatePicker();
            }
        });

        binding.btnClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                clearForm();
            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void displayDatePicker() {
        datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getParentFragmentManager(), "datePicker");
    }

    private void clearForm() {
        binding.spnTitle.setSelection(0);
        binding.tilFirstName.getEditText().setText("");
        binding.tilLastName.getEditText().setText("");
        binding.tilDateOfBirth.getEditText().setText("");
        binding.tilEmail.getEditText().setText("");
        binding.tilPassword.getEditText().setText("");

        if (binding.rdbYes.isChecked()) {
            binding.rdbYes.setChecked(false);
        } else {
            binding.rdbNo.setChecked(false);
        }

        Snackbar.make(binding.getRoot(), "Form Cleared!", Snackbar.LENGTH_SHORT).show();
    }

    private void submitForm() {
        // TODO create and call a form validation method

        title = binding.spnTitle.getSelectedItem().toString();
        firstName = binding.tilFirstName.getEditText().getText().toString();
        lastName = binding.tilLastName.getEditText().getText().toString();
        email = binding.tilEmail.getEditText().getText().toString();
        password = binding.tilPassword.getEditText().getText().toString();

        if (binding.rdbYes.isChecked()) {
            premiumService = true;
        } else {
            premiumService = false;
        }

        // TODO Use Navigation Actions to send form data to next Fragment
        MainActivity.navController.navigate(R.id.action_signUpFragment_to_profileFragment);
        //NavDirections action = SignUpFragment

        Snackbar.make(binding.getRoot(), "Form Submitted!", Snackbar.LENGTH_SHORT).show();
    }
}