package hackintosh.adrox.foodadder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    Button scancode_button;
    Button add_button;
    Button history_button;

    IntentResult result;

    public String[] history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scancode_button = (Button) findViewById(R.id.home_scan);
        add_button = (Button) findViewById(R.id.home_add);
        history_button = (Button) findViewById(R.id.home_history);

        scancode_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Zbliż skaner do kodu produktu");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(),add.class);
                startActivity(i);

            }
        });

        history_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                Intent i = new Intent(getApplicationContext(),history.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "Anulowałeś skanowanie", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();


            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
