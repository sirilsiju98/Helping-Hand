package helping.hand;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class RequestItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_item);
        setTitle("Enter items you need");
        initialize();
        setAdapters();
        if(ParseUser.getCurrentUser().get("Camp").toString().isEmpty())
        {
            loc.setEnabled(true);
        }
        else
        {
            loc.setText(ParseUser.getCurrentUser().get("Camp").toString());
            loc.setEnabled(false);
        }
    }



    Spinner[]sp;
    EditText loc;
    ArrayAdapter<CharSequence> arrayAdapterItems,arrayAdapterQty;
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    private void initialize()
    {
        sp=new Spinner[4];
        sp[0]=(Spinner)findViewById(R.id.spinneri1);
        sp[1]=(Spinner)findViewById(R.id.spinneri2);
        sp[2]=(Spinner)findViewById(R.id.spinneri3);
        sp[3]=(Spinner)findViewById(R.id.spinneri4);
        loc = (EditText)findViewById(R.id.editTextil);


    }
    private void setAdapters()
    {

        arrayAdapterQty = ArrayAdapter.createFromResource(this, R.array.itemqty, R.layout.support_simple_spinner_dropdown_item);
        arrayAdapterQty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        for(int i=0;i<4;i++)
            sp[i].setAdapter(arrayAdapterQty);

    }
    public void request(View view)
    {
        if(!isNetworkAvailable())
            Toast.makeText(this, "An Internet Connection is Required", Toast.LENGTH_SHORT).show();
        else {

            if(loc.getText().toString().isEmpty())
                loc.setError("This field cannot be empty");
            else {
                final ProgressDialog dialog = ProgressDialog.show(RequestItem.this, "",
                        "Loading. Please wait...", true);
                ParseObject itemsUpload = new ParseObject("Requests");
                itemsUpload.put("user", ParseUser.getCurrentUser().get("name"));
                itemsUpload.put("Rice", sp[0].getSelectedItem().toString());
                itemsUpload.put("Wheat", sp[1].getSelectedItem().toString());
                itemsUpload.put("Dress_Material", sp[2].getSelectedItem().toString());
                itemsUpload.put("Toys", sp[3].getSelectedItem().toString());
                itemsUpload.put("contact", ParseUser.getCurrentUser().get("mobile"));
                itemsUpload.put("Location",loc.getText().toString());

                itemsUpload.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            Toast.makeText(RequestItem.this, e.toString(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        else {
                            Intent intent = new Intent(RequestItem.this, Volunteer.class);
                            startActivity(intent);
                            finish();
                        }
                    }


                });
            }

        }
    }
}
