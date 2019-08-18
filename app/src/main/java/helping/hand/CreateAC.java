package helping.hand;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAC extends AppCompatActivity {
    EditText name,email,address,mob,pass,rpass,camp;
    Button submit;
    private void initialize()
    {
      name=(EditText)findViewById(R.id.name);
      email=(EditText)findViewById(R.id.email);
      address=(EditText)findViewById(R.id.address);
      mob=(EditText)findViewById(R.id.mobile);
      pass=(EditText)findViewById(R.id.password);
      rpass=(EditText)findViewById(R.id.rpassword);
      camp=(EditText)findViewById(R.id.camp);
      submit= (Button) findViewById(R.id.submit);
    }

    private boolean checkError()
    {
        boolean error=false;
        if(name.getText().toString().isEmpty())
        {
            error=true;
            name.setError("This field should not be empty");

        }
        if(address.getText().toString().isEmpty())
        {
            error=true;
            address.setError("This field should not be empty");
        }
        if(email.getText().toString().isEmpty())
        {
            error=true;
            email.setError("This field should not be empty");
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches())
        {
            error=true;
            email.setError("Invalid Email");
        }

        if(mob.getText().toString().isEmpty())
        {
            error=true;
            mob.setError("This field should not be empty");
        }
        else if(mob.getText().toString().length()!=10)
        {
            error=true;
            mob.setError("Invalid Mobile Number");
        }

        if(pass.getText().toString().isEmpty())
        {
            error=true;
            pass.setError("This field cannot be empty");
        }
        else if(pass.getText().toString().length()<8)
        {
            error=true;
            pass.setError("Minimum 8 charactes are required");
        }
        if(rpass.getText().toString().compareTo(pass.getText().toString())!=0)
        {
            error=true;
            rpass.setError("Password doesnot matches");
        }

        return error;
    }
    public void submit(View view)
    {
        if(!checkError())
        {
            Toast.makeText(this, "No Error", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ac);
        setTitle("Create an Account");
        initialize();

    }
}
