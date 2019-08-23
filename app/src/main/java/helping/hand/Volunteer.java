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

public class Volunteer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        setTitle("Volunteer");
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.volunteer_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void show(View view)
    {
        Intent intent =new Intent(Volunteer.this,requets.class);
        startActivity(intent);

    }
    public void up(View view)
    {
        Intent intent =new Intent(Volunteer.this,uploadv.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logoutp:
                ParseUser.logOut();
                Intent intent = new Intent(Volunteer.this,SelectActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.notif:
                Intent intent2 = new Intent(Volunteer.this,Notif.class);
                startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }
}
