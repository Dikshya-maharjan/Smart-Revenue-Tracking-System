package com.example.revenuemanagementsystem;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupPageActivity extends AppCompatActivity {
    TextView tvname,tvemail,tvpassword,tvconfirmpw;
    EditText edname,edemail,edpassword,edconfirmpw;
    Button signupbtn;
    public  boolean isValid(String password){
        String passwordStyle=
                "^(?=.*[0-9])" +         // at least 1 digit
                        "(?=.*[a-z])" +          // at least 1 lowercase letter
                        "(?=.*[A-Z])" +          // at least 1 uppercase letter
                        "(?=.*[@#$%^&+=!])" +    // at least 1 special character
                        "(?=\\S+$).{8,}$";       // no spaces & min 8 chars
        return password.matches(passwordStyle);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvname=findViewById(R.id.tvUser);
        tvemail=findViewById(R.id.tvEmail);
        tvpassword=findViewById(R.id.tvPassword);
        tvconfirmpw=findViewById(R.id.tvConfirmpw);
        edname=findViewById(R.id.edName);
        edemail=findViewById(R.id.edEmail);
        edpassword=findViewById(R.id.edPassword);
        edconfirmpw=findViewById(R.id.edConfirmpw);
        signupbtn=findViewById(R.id.signupBtn);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edname.getText().toString().trim();
                String email=edemail.getText().toString().trim();
                String password=edpassword.getText().toString().trim();
                String confrimpw=edconfirmpw.getText().toString().trim();

                if(name.isEmpty()){
                    edname.setError("Please Enter Business Name");
                    return;
                }
                if(email.isEmpty()){
                    edemail.setError("Email Required");
                    return;
                }
                if(password.isEmpty()){
                    edpassword.setError("Please Enter Password");
                    return;
                }
                if(confrimpw.isEmpty()){
                    edname.setError("Please Enter Password");
                    return;
                }
                if(!isValid(password)){
                    edpassword.setText("Password must contain uppercase, lowercase, number & special character");

                }

                if (!password.equals(confrimpw)){
                    edconfirmpw.setError("Passwords do not match");
                    return;
                }
                Intent intent=new Intent(SignupPageActivity.this,LoginPageActivity.class);
                startActivity(intent);
            }
        });
//        DatabaseActivity databaseActivity=new DatabaseActivity(this);//linking db
//        String username=edname.getText().toString();
//        String email=edemail.getText().toString();
//        String password=edpassword.getText().toString();
//        String confirmpw=edconfirmpw.getText().toString();
//
//        SQLiteDatabase sqLiteDatabase=databaseActivity.getWritableDatabase();

    }
}
