package com.example.skymovierater.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skymovierater.R;
import com.example.skymovierater.helper.InputValidation;
import com.example.skymovierater.models.User;
import com.example.skymovierater.sql.DatabaseHelper;
import com.example.skymovierater.sql.MovieContract;
import com.example.skymovierater.sql.MovieContract.MovieEntry;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class signup_activity extends AppCompatActivity{

//    private final AppCompatActivity activity = signup_activity.this;
//
//    private ConstraintLayout constraintLayout;
//

    private EditText u_name, u_email, u_password,u_confirmPassword;

//
//    private AppCompatButton appCompatButtonSignUp;
//
//    private TextView appCompatTextViewLinkLogin;
//
//    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
//    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_signup_activity);

        databaseHelper = new DatabaseHelper(this);
    }


   private boolean validate_input(String u_input, String email_input, String pass_input){
        if (u_input.equals("") && email_input.equals("") && pass_input.equals("")) {
            StyleableToast.makeText(signup_activity.this, "All the fields are Empty", Toast.LENGTH_SHORT, R.style.emptyToast).show();
            return  false;
        }else if (u_input.equals("") || email_input.equals("") || pass_input.equals("")){
            StyleableToast.makeText(signup_activity.this, "One or more are Empty", Toast.LENGTH_SHORT, R.style.emptyToast).show();
            return  false;
        }
        else{
            return  true;
        }

   }
    private boolean validate_password(String password, String con_password){
        if (! password.equals(con_password)){
            StyleableToast.makeText(signup_activity.this, "Password does not match", Toast.LENGTH_SHORT, R.style.password_not_matchToast).show();
            return false;
        }else {
            return  true;
        }

    }
    public void signIn(View view){
        u_name =  findViewById(R.id.TIT_name);
        u_email =  findViewById(R.id.TIT_email);
        u_password = findViewById(R.id.TIT_password) ;
        u_confirmPassword =  findViewById(R.id.TIT_confirmPassword) ;

        String userfield = u_name.getText().toString().trim();
        String emailfield = u_email.getText().toString().trim();
        String passwordfield = u_password.getText().toString().trim();
        String confirmpassfield = u_confirmPassword.getText().toString().trim();

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        if (validate_input(userfield,emailfield,passwordfield) == true && validate_password(passwordfield, confirmpassfield) == true){
            ContentValues content = new ContentValues();
            content.put(MovieContract.MovieEntry.COLUMN_USER_NAME, userfield);
            content.put(MovieContract.MovieEntry.COLUMN_USER_EMAIL, emailfield);
            content.put(MovieContract.MovieEntry.COLUMN_USER_PASSWORD, passwordfield);

            long insert = db.insert(MovieContract.MovieEntry.TABLE_USER, null, content);

            if (insert == -1) {
                Toast.makeText(signup_activity.this,  "Not Connected", Toast.LENGTH_SHORT).show();

            }else if (insert >= 0){
                StyleableToast.makeText(signup_activity.this,  "Registration Successful", Toast.LENGTH_SHORT, R.style.successfulToast).show();
            }

        } else if (validate_input(userfield,emailfield,passwordfield)  == false && validate_password(passwordfield, confirmpassfield) == false){

        }
    }

//    private void initObjects() {
//        inputValidation= new InputValidation(activity);
//        databaseHelper = new DatabaseHelper(activity);
//        user = new User();
//    }
//
//    private void initListeners() {
//        appCompatButtonSignUp.setOnClickListener(this);
//       appCompatTextViewLinkLogin.setOnClickListener(this);
//    }
//
//    private void initViews() {
//        constraintLayout = (ConstraintLayout) findViewById(R.id.signinParent_layout);
//
//        textInputLayoutName= (TextInputLayout) findViewById(R.id.TIL_name);
//        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.TIL_email);
//        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.TIL_password);
//        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.TIL_confirmPassword);
//

//
//
//        appCompatButtonSignUp= (AppCompatButton) findViewById(R.id.button_Signup);
//
//        appCompatTextViewLinkLogin = (TextView) findViewById(R.id. registerLink);
//    }

//    @Override
//    public void onClick(View v){
//        switch (v.getId()){
//            case R.id.button_Signup:
//                postDataIntoSQLite();
//                break;
//            case R.id.registerLink:
//               finish();
//                break;
//        }
//    }

//    private void postDataIntoSQLite() {
//        if (! inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message))){
//            return;
//        }
//        if (! inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message))){
//            return;
//        }
//        if (! inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message))){
//            return;
//        }
//        if (! inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message))){
//            return;
//        }
//        if (! inputValidation.isInputEditTextMatches(textInputEditTextPassword , textInputEditTextConfirmPassword,
//                textInputLayoutConfirmPassword, getString(R.string.error_message))){
//                return;
//        }
//        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())){
//            user.setName(textInputEditTextName.getText().toString().trim());
//            user.setEmail(textInputEditTextEmail.getText().toString().trim());
//            user.setPassword(textInputEditTextPassword.getText().toString().trim());
//
//            databaseHelper.addUser(user);
//
//            Snackbar.make(constraintLayout, getString(R.string. success_message), Snackbar.LENGTH_LONG).show();
//            emptyInputEditText();
//        }else{
//            Snackbar.make(constraintLayout, getString(R.string. mail_exists), Snackbar.LENGTH_LONG).show();
//        }
//    }
//
//    private void emptyInputEditText() {
//
//        textInputEditTextName.setText(null);
//        textInputEditTextEmail.setText(null);
//        textInputEditTextPassword.setText(null);
//        textInputEditTextConfirmPassword.setText(null);
//    }

    private void hideKeyboardFrom(View view){
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    public void openSigninPage(View view) {
        startActivity(new Intent(signup_activity.this, MainActivity.class));
    }
}
