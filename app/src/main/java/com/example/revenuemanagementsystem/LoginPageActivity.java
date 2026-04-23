package com.example.revenuemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class LoginPageActivity extends AppCompatActivity {
    TextView tvName,tvPassword;
    EditText edName,edPassword;
    Button btn;
    public  boolean isValidPassword(String password){
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
        setContentView(R.layout.activity_login_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvName=findViewById(R.id.tvUser);
        tvPassword=findViewById(R.id.tvPassword);
        edName=findViewById(R.id.edName);
        edPassword=findViewById(R.id.edPassword);
        btn=findViewById(R.id.btnLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edName.getText().toString().trim();
                String password=edPassword.getText().toString().trim();
                if(username.isEmpty()){
                    edName.setError("Username Required");
                    return;
                }
                if(password.isEmpty()){

                    edPassword.setError("Password Required");
                    return;
                }
                if(!isValidPassword(password)){
                    edPassword.setError("Password must contain uppercase, lowercase, number & special character");

                }
                else{
                    Toast.makeText(LoginPageActivity.this,"login successful",Toast.LENGTH_SHORT).show();
                }

                Intent intent=new Intent(LoginPageActivity.this,HomeScreenActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
    }
}
