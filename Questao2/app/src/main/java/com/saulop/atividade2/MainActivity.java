package com.saulop.atividade2;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout salaryInputLayout;
    private TextInputEditText salaryEditText;
    private RadioGroup percentageRadioGroup;
    private MaterialButton calculateButton;
    private CardView resultCardView;
    private TextView originalSalaryTextView;
    private TextView percentageTextView;
    private TextView increaseTextView;
    private TextView newSalaryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        setupCalculateButton();
    }

    private void initViews() {
        salaryInputLayout = findViewById(R.id.salaryInputLayout);
        salaryEditText = findViewById(R.id.salaryEditText);
        percentageRadioGroup = findViewById(R.id.percentageRadioGroup);
        calculateButton = findViewById(R.id.calculateButton);
        resultCardView = findViewById(R.id.resultCardView);
        originalSalaryTextView = findViewById(R.id.originalSalaryTextView);
        percentageTextView = findViewById(R.id.percentageTextView);
        increaseTextView = findViewById(R.id.increaseTextView);
        newSalaryTextView = findViewById(R.id.newSalaryTextView);
    }

    private void setupCalculateButton() {
        calculateButton.setOnClickListener(v -> {
            if (validateInputs()) {
                calculateSalary();
            }
        });
    }

    private boolean validateInputs() {
        String salaryStr = salaryEditText.getText().toString().trim();
        if (TextUtils.isEmpty(salaryStr)) {
            salaryInputLayout.setError(getString(R.string.error_empty_salary));
            return false;
        } else {
            salaryInputLayout.setError(null);
        }

        if (percentageRadioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, R.string.error_select_percentage, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void calculateSalary() {
        double salary = Double.parseDouble(salaryEditText.getText().toString().trim());

        int percentage;
        int selectedRadioButtonId = percentageRadioGroup.getCheckedRadioButtonId();

        if (selectedRadioButtonId == R.id.radio40) {
            percentage = 40;
        } else if (selectedRadioButtonId == R.id.radio45) {
            percentage = 45;
        } else {
            percentage = 50;
        }

        double increase = salary * percentage / 100.0;
        double newSalary = salary + increase;

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String formattedSalary = currencyFormatter.format(salary).replace("R$", "").trim();
        String formattedIncrease = currencyFormatter.format(increase).replace("R$", "").trim();
        String formattedNewSalary = currencyFormatter.format(newSalary).replace("R$", "").trim();

        originalSalaryTextView.setText(getString(R.string.original_salary, formattedSalary));
        percentageTextView.setText(getString(R.string.percentage_format, percentage));
        increaseTextView.setText(getString(R.string.increase_value, formattedIncrease));
        newSalaryTextView.setText(getString(R.string.new_salary, formattedNewSalary));

        resultCardView.setVisibility(View.VISIBLE);
    }
}