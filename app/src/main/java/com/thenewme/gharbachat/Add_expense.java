package com.thenewme.gharbachat;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Add_expense extends AppCompatActivity {

    EditText amount,description;
    int id,Id;

    private ArrayList<Categories_item> categorylist;
    private Categories_adapter Adapter;

    Button add,cancel;

    DatabaseHelper databaseHelper;

    Spinner income_spinner, spinnercategories;

    @Override
    protected void onCreate(Bundle savedInstanceStste){
        super.onCreate(savedInstanceStste);
        setContentView(R.layout.add_expense);

        databaseHelper = new DatabaseHelper(this);

        add=findViewById(R.id.add);
        cancel = findViewById(R.id.cancel);
        amount=findViewById(R.id.amount);
        description=findViewById(R.id.description);
        income_spinner = findViewById(R.id.cashcredit);
        spinnercategories = findViewById(R.id.categories);

        id=getIntent().getIntExtra("id",0);

        income_spinner.setAdapter(new Income_spinner(this, getSpinner()));
        initlist();


        Adapter = new Categories_adapter(this,categorylist);
        spinnercategories.setAdapter(Adapter);

        if(id!=0){
            ExpenseInfo info = databaseHelper.getexpenseinfo(id);
            amount.setText(String.valueOf(info.amount));
            description.setText(info.description);
            int spinnerPosition = ((ArrayAdapter<String>)income_spinner.getAdapter()).getPosition(info.cashcredit);
            income_spinner.setSelection(spinnerPosition);

            spinnercategories.setSelection(findIndex(info.category));

            add.setText("Update");
        }


        add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(isAmountEmpty(amount)) {

                        Categories_item categoryval = (Categories_item) spinnercategories.getSelectedItem();
                        String categoryVal = categoryval.getName().toString();

                        //final String[] categoryval = new String[1];

                /*spinnercatergories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Categories_item clickeditem = (Categories_item)parent.getItemAtPosition(position);
                        String categoryval = clickeditem.getName();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });*/

                        final String spinnerval = income_spinner.getSelectedItem().toString();

                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        String date = df.format(Calendar.getInstance().getTime());

                        String a = amount.getText().toString();
                        int amountVal = Integer.parseInt(a);

                        String descriptionVal = description.getText().toString();

                        ContentValues contentValues = new ContentValues();
                        contentValues.put("date", date);
                        contentValues.put("amount", amountVal);
                        contentValues.put("description", descriptionVal);
                        contentValues.put("category", categoryVal);
                        contentValues.put("cashcredit",spinnerval);

                        if(id==0){
                            databaseHelper.insertexpense(contentValues);

                            Intent intent = new Intent(Add_expense.this, Expense.class);
                            startActivity(intent);
                            finish();
                        }else{
                            databaseHelper.updateexpense(id,contentValues);
                            Toast.makeText(Add_expense.this,"Expense updated",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id==0){
                    Intent intent = new Intent(Add_expense.this, Navigation_drawer.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(Add_expense.this, Expense.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    ArrayList<String> spinner = new ArrayList<>();

    public ArrayList<String> getSpinner(){

        spinner.add("Choose category");
        spinner.add("Cash");
        spinner.add("Credit Card");

        return spinner;
    }

    private int findIndex(String cateogry) {
        int selection = 0;
        for(int i = 0; i < categorylist.size(); i++)  {
            if (categorylist.get(i).getName().equals(cateogry)) {
                selection = i;
                break;
            }
        }
        return selection;
    }

    private void initlist(){
        categorylist = new ArrayList<>();
        categorylist.add(new Categories_item("Household",R.drawable.ic_household));
        categorylist.add(new Categories_item("Eating-out",R.drawable.ic_eating_out));
        categorylist.add(new Categories_item("Grocery",R.drawable.ic_grocery));
        categorylist.add(new Categories_item("Personal",R.drawable.ic_personal));
        categorylist.add(new Categories_item("Utilities",R.drawable.ic_utilities));
        categorylist.add(new Categories_item("Medical",R.drawable.ic_medical));
        categorylist.add(new Categories_item("Education",R.drawable.ic_education));
        categorylist.add(new Categories_item("Entertainment",R.drawable.ic_entertainment));
        categorylist.add(new Categories_item("Clothing",R.drawable.ic_clothing));
        categorylist.add(new Categories_item("Transportation",R.drawable.ic_transportation));
        categorylist.add(new Categories_item("Shopping",R.drawable.ic_shopping));
        categorylist.add(new Categories_item("Others",R.drawable.savings));

    }

    public boolean isAmountEmpty(EditText view) {
        if (view.getText().toString().length() > 0) {
            return true;
        } else {
            view.setError("This field cannot be empty");
            //password.setError("Enter Password");
            return false;
        }
    }

}
