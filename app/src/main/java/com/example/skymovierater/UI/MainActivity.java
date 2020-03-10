package com.example.skymovierater.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skymovierater.R;
import com.example.skymovierater.helper.InputValidation;
import com.example.skymovierater.models.Movie;
import com.example.skymovierater.models.User;
import com.example.skymovierater.sql.DatabaseHelper;
import com.example.skymovierater.sql.MovieContract;
import com.example.skymovierater.sql.MovieContract.MovieEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class MainActivity extends AppCompatActivity {

    public int dbuserID ;
    public static int userID;

private EditText u_email, u_password;

    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_main);


        databaseHelper = new DatabaseHelper(this);


    }

    public boolean login_validation(String email, String password){

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String[] projection = {
                MovieEntry.COLUMN_USER_ID,
                MovieEntry.COLUMN_USER_EMAIL,
                MovieEntry.COLUMN_USER_PASSWORD
                                            };

            String selection = MovieContract.MovieEntry.COLUMN_USER_EMAIL  + " =?" + " AND " + MovieContract.MovieEntry.COLUMN_USER_PASSWORD + " =?";

            String[] selectionArgs = {email,password};

        Cursor cursor = db.query(
                MovieEntry.TABLE_USER,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        int userIdColumnIndex = cursor.getColumnIndex(MovieEntry.COLUMN_USER_ID);
        int emailColumnIndex = cursor.getColumnIndex(MovieEntry.COLUMN_USER_EMAIL);
        int passColumnIndex = cursor.getColumnIndex(MovieEntry.COLUMN_USER_PASSWORD);
        boolean flag = false;

        try {
            while (cursor.moveToNext()){
                String dbEmail = cursor.getString(emailColumnIndex);
                String dbPass = cursor.getString(passColumnIndex);

                if (! email.equals(dbEmail) || ! password.equals(dbPass) ){

                    flag = false;
                }
                else if (email.equals(dbEmail) || password.equals(dbPass)){
                    dbuserID = cursor.getInt(userIdColumnIndex);
                    flag = true;
                }
            }
        } finally {
            cursor.close();
            db.close();
        }
        return flag;
    }

    public void login(View view){
      u_email=  findViewById(R.id.TIT_email);
      u_password = findViewById(R.id.TIT_password) ;

        String email = u_email.getText().toString().trim();
        String password = u_password.getText().toString().trim();

        if(login_validation(email,  password)){
           userID = dbuserID;
            Intent intent = new Intent(MainActivity.this,  movie_activity.class);
            startActivity(intent);
        }else {
            StyleableToast.makeText(MainActivity.this, "Login Failed!", Toast.LENGTH_SHORT, R.style.failedToast).show();
        }
    }

    public void openSignupPage(View view) {
        startActivity(new Intent(MainActivity.this, signup_activity.class));
    }


}
