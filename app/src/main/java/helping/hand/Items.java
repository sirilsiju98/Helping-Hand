package helping.hand;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class Items extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        setTitle("Available items");

        ParseQuery<ParseObject> query =ParseQuery.getQuery("Items_Available");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                    if(objects.toString().equals("[]"))
                        Toast.makeText(Items.this, "Sorry no items are Available", Toast.LENGTH_SHORT).show();

            }
        });


    }



}
