package hackintosh.adrox.foodadder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button scancode_button;
    Button add_button;
    Button history_button;

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
                Intent i = new Intent(getApplicationContext(),scancode.class);
                startActivity(i);
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


}
