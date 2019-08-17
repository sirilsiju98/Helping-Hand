package helping.hand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
    }
    public void people(View view)
    {
        Intent login= new Intent(this,Login.class);
        login.putExtra("who",'p');
        startActivity(login);
    }
    public void volunter(View view)
    {
        Intent login= new Intent(this,Login.class);
        login.putExtra("who",'v');
        startActivity(login);
    }
}
