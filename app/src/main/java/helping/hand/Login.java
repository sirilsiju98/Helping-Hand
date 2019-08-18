package helping.hand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends AppCompatActivity {
    public static char who;
    EditText username;
    EditText password;
    ProgressBar logbar;
    Button loginb;
    public void createAc(View view)
    {
      Intent cac = new Intent(this,CreateAC.class);
      startActivity(cac);
      finish();
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void login(View view)
    {
        if(!isNetworkAvailable())
            Toast.makeText(this, "An Internet Connection is Required", Toast.LENGTH_SHORT).show();
        else
        {
            String us=username.getText().toString();
            String ps=password.getText().toString();
            boolean error=false;
            if(us.isEmpty())
            {
                error=true;
                username.setError("Field cannot be empty");
            }
            if(ps.isEmpty())
            {
                error=true;
                password.setError("Field cannot be empty");
            }
            if(!error)
            {
                username.setEnabled(false);
                password.setEnabled(false);
                loginb.setEnabled(false);
                logbar.setVisibility(View.VISIBLE);
                ParseUser.logInInBackground(us, ps, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user!=null) {
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                            if(who=='v')
                            {
                                Intent intent = new Intent(Login.this,Volunteer.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                              Intent intent = new Intent(Login.this,People.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                        else {
                            username.setEnabled(true);
                            password.setEnabled(true);
                            loginb.setEnabled(true);
                            Toast.makeText(Login.this,"Invalid username/password",Toast.LENGTH_SHORT).show();
                        }
                        logbar.setVisibility(View.INVISIBLE);
                    }

                });
            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent login = getIntent();
        setTitle("Login");
        who=login.getCharExtra("who",'p');
        username=(EditText)findViewById(R.id.usernamel);
        password=(EditText)findViewById(R.id.passwordl);
        logbar=(ProgressBar)findViewById(R.id.loading);
        loginb=(Button)findViewById(R.id.login);
         if(!isNetworkAvailable())
             Toast.makeText(this, "An Internet Connection is Required", Toast.LENGTH_SHORT).show();


    }
}
