package helping.hand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class requets extends AppCompatActivity {
    public void call(long ph) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + ph));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
        }
        startActivity(callIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requets);
        setTitle("Items requested");
        final ProgressDialog dialog = ProgressDialog.show(requets.this, "",
                "Loading. Please wait...", true);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Requests");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null) {
                    if (objects.size() == 0)
                        Toast.makeText(requets.this, "No Requets", Toast.LENGTH_SHORT).show();

                    else {
                        final long phno[]=new long[objects.size()];
                        int i=0;
                        ListView listView = (ListView) findViewById(R.id.res);
                        final ArrayList<String> list = new ArrayList<String>();

                        for (ParseObject object : objects) {

                            String ele = object.get("user") + "\nRice(in Kg): " + object.get("Rice") + "\nWheat(in kg): " +
                                    object.get("Wheat") + "\nDress Material: " + object.get("Dress_Material") + "\nToys:" + object.get("Toys") +
                                    "\nCamp: " + object.get("Location") + "\nContact :" + object.get("contact");
                            list.add(ele);

                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requets.this, android.R.layout.simple_list_item_1, list);
                        listView.setAdapter(arrayAdapter);
                        dialog.dismiss();




                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                if(Build.VERSION.SDK_INT<23)
                                {
                                    call(phno[i]);
                                }
                                else
                                {
                                    if(ContextCompat.checkSelfPermission(requets.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                                    {
                                        ActivityCompat.requestPermissions(requets.this, new String[]{Manifest.permission.CALL_PHONE},1);
                                        Toast.makeText(requets.this, "Permission required", Toast.LENGTH_SHORT).show();
                                    }

                                    else
                                    {
                                        call(phno[i]);
                                    }
                                }

                            }
                        });




                    }
                }
            }
        });
    }
}





