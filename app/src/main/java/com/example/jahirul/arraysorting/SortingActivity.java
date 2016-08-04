package com.example.jahirul.arraysorting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Jahirul on 9/10/2015.
 * Project activity class for showing different sorting mechanism
 */

public class SortingActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    EditText etInput;
    TextView tvOutput;
    RadioGroup rgSortingOption;
    RadioButton rbCounting, rbBubble, rbMerge, rbInsertion;

    SortingUtility sortingUtility = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting);
        etInput = (EditText) findViewById(R.id.etInput);
        tvOutput = (TextView) findViewById(R.id.tvOutput);
        rgSortingOption = (RadioGroup) findViewById(R.id.rgSortingOption);
        rbCounting = (RadioButton) findViewById(R.id.rbCounting);
        rbCounting.setOnCheckedChangeListener(this);
        rbBubble = (RadioButton) findViewById(R.id.rbBubble);
        rbBubble.setOnCheckedChangeListener(this);
        rbMerge = (RadioButton) findViewById(R.id.rbMerge);
        rbMerge.setOnCheckedChangeListener(this);
        rbInsertion = (RadioButton) findViewById(R.id.rbInsertion);
        rbInsertion.setOnCheckedChangeListener(this);
        sortingUtility = new SortingUtility();
        // apply validation
        etInput.setFilters(new InputFilter[]{filter});
    }

    // Sorting button press enevt
    public void onSort(View v) {
        tvOutput.setText("Sorting...");
        String strInput = etInput.getText().toString().trim();
        // empty input validation
        if (strInput.isEmpty()) {
            etInput.setError("Please input your array");
            etInput.requestFocus();
            return;
        }
        //remove last charecter if it is comma
        if (strInput.substring(strInput.length() - 1).equals(",")) {
            strInput = strInput.substring(0, strInput.length() - 1);
        }
        // convert to string array by spliting comma
        String[] stArr = strInput.split(",");
        if (stArr.length == 0) {
            return;
        } else if (stArr.length == 1) {
            tvOutput.setText(strInput);
        }
        int[] intputIntArr = convertToIntergerArray(stArr);
        if (rgSortingOption.getCheckedRadioButtonId() == R.id.rbCounting) {
            sortingUtility.countingSort(intputIntArr);
        } else if (rgSortingOption.getCheckedRadioButtonId() == R.id.rbBubble) {
            sortingUtility.bubbleSort(intputIntArr);
        } else if (rgSortingOption.getCheckedRadioButtonId() == R.id.rbMerge) {
            sortingUtility.mergeSort(intputIntArr, 0, intputIntArr.length);
        } else if (rgSortingOption.getCheckedRadioButtonId() == R.id.rbInsertion) {
            sortingUtility.insertionSort(intputIntArr);
        }
        tvOutput.setText(convertToPrintableOutput(intputIntArr));
    }

    // Convert string array to integer array
    private int[] convertToIntergerArray(String[] stArr) {
        int[] num = new int[stArr.length];
        int index = -1;
        for (String c : stArr) {
            if (!c.isEmpty()) {
                num[++index] = Integer.valueOf(c);
            }
        }
        return num;
    }

    // create the output result string
    private String convertToPrintableOutput(int[] inputIntArray) {
        if (inputIntArray == null) return "";
        String outPut = "";
        for (int i : inputIntArray) {
            outPut += i + ",";
        }
        // replace last char
        outPut = outPut.substring(0, outPut.length() - 1);
        return outPut;
    }

    // input validation Number and Comma only
    InputFilter filter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; i++) {
                if (!Character.isDigit(source.charAt(i)) && source.charAt(i) != ',') {
                    return "";
                }
            }
            return null;
        }
    };

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        tvOutput.setText("Sorting Result");
    }
}
