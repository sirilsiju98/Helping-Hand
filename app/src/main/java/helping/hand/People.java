package helping.hand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.parse.ParseUser;

public class People extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        setTitle("Enter Available");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.people_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logoutp:
                ParseUser.logOut();
                Intent intent = new Intent(People.this,SelectActivity.class);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);


    }


    public void vi(View view)
    {
       Intent intent = new Intent(People.this,Items.class);
       startActivity(intent);
    }

    public void rq(View view)
    {
        Intent intent = new Intent(People.this,RequestItem.class);
        startActivity(intent);
    }
}
