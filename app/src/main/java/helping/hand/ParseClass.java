package helping.hand;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import android.app.Application;
import android.widget.Toast;

public class ParseClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("28d095343ba0ef9e3372bb26dad98e4e60311bf2")
                // if desired
                .clientKey("132bdd9f84937f6c2267583dc8de01b5a769a303")
                .server("http://52.14.127.170:80/parse/")
                .build()
        );

        ParseObject object = new ParseObject("Hello");
        object.put("eg","1");
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null)
                Toast.makeText(ParseClass.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}