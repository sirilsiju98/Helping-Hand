package helping.hand;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Items extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        setTitle("Available items");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Items_Available");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null) {
                    if (objects.size() == 0)
                        Toast.makeText(Items.this, "Sorry no items are Available", Toast.LENGTH_SHORT).show();

                    else

                    {
                        ListView listView = (ListView)findViewById(R.id.avail);
                        final ArrayList<String> list = new ArrayList<String>();
                        for (ParseObject object : objects){
                            String ele=object.get("user")+"\nRice(in Kg): "+object.get("Rice")+"\nWheat(in kg): "+
                                    object.get("Wheat")+"\nDress Material: "+object.get("Dress_Material")+"\nToys:"+object.get("Toys")+
                                    "\nLocation: "+object.get("Location")+"\nContact :"+object.get("contact");
                            list.add(ele);

                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Items.this,android.R.layout.simple_list_item_1,list);
                        listView.setAdapter(arrayAdapter);
                    }
                }
            }
        });
    }
}





