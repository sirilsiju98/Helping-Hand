package helping.hand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.parse.ParseUser;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
    }
    public void people(View view)
    {
        if(ParseUser.getCurrentUser()==null) {
            Intent login = new Intent(this, Login.class);
            login.putExtra("who", 'p');
            startActivity(login);
        }
        else
        {
            Intent login = new Intent(this, People.class);
            startActivity(login);
        }
        finish();
    }
    public void volunter(View view)
    {
        if(ParseUser.getCurrentUser()==null) {
            Intent login = new Intent(this, Login.class);
            login.putExtra("who", 'v');
            startActivity(login);
        }
        else
        {
            Intent login = new Intent(this, Volunteer.class);
            startActivity(login);
        }
        finish();

    }
}
