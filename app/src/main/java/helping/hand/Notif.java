package helping.hand;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class Notif extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avilable);
        setTitle("Notifications");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Requests");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null) {
                    if (objects.size() == 0)
                        ;

                    else {
                        ListView listView = (ListView) findViewById(R.id.not);
                        final ArrayList<String> list = new ArrayList<String>();

                        for (ParseObject object : objects) {

                            String ele = "Items required at : "+ object.get("Location").toString();
                            list.add(ele);

                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Notif.this, android.R.layout.simple_list_item_1, list);
                        listView.setAdapter(arrayAdapter);
                    }
                }
            }
        });



    }
}
