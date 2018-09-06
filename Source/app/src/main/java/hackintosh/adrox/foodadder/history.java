package hackintosh.adrox.foodadder;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import java.util.ArrayList;


public class history extends ListActivity {
    Button scancode_button;
    IntentResult result;
    ListView listview;

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_history);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        setListAdapter(adapter);

        scancode_button = (Button) findViewById(R.id.hist_button);
        listview = (ListView) findViewById(android.R.id.list);

        scancode_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                IntentIntegrator integrator = new IntentIntegrator(history.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Zbliż skaner do kodu produktu");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Anulowałeś skanowanie", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();

                listItems.add(result.getContents());
                adapter.notifyDataSetChanged();

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Toast.makeText(history.this, "Clicked at" + position, Toast.LENGTH_LONG).show();

                        String url = "https://www.barcodelookup.com/" + result.getContents().toString();
                        Intent internetopen = new Intent(Intent.ACTION_VIEW);
                        internetopen.setData(Uri.parse(url));
                        startActivity(internetopen);

                    }
                });

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
