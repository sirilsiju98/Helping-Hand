package helping.hand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    char who;
    public void createAc(View view)
    {
      Intent cac = new Intent(this,CreateAC.class);
      startActivity(cac);
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent login = getIntent();
        setTitle("Login");
        who=login.getCharExtra("who",'p');
         if(!isNetworkAvailable())
             Toast.makeText(this, "An Internet Connection is Required", Toast.LENGTH_SHORT).show();
    }
}
