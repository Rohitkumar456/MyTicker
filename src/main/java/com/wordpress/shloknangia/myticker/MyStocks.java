package com.wordpress.shloknangia.myticker;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MyStocks extends AppCompatActivity {
    DatabaseAdapter databaseadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stocks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        databaseadapter = new DatabaseAdapter(this);
        Cursor cursor = databaseadapter.viewall();
        String fieldnames[] = new String[]{databaseadapter.retcolstockname()};
        int[] toids = new int[]{R.id.custom_text_view};
        SimpleCursorAdapter simpleCursorAdapter;
        simpleCursorAdapter = new SimpleCursorAdapter(getBaseContext(),R.layout.custom,cursor,fieldnames,toids,0);
        ListView mylist = (ListView)findViewById(R.id.viewlist);
        mylist.setAdapter(simpleCursorAdapter);

        mylist.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String data = String.valueOf(adapterView.getItemIdAtPosition(i));
                        Intent intent = new Intent(MyStocks.this, Details.class);
                        String[] args = databaseadapter.setvalue(data);
                        String message = args[0];
                        String message1 = args[1];
                        String message2 = args[2];
                        String message3 = args[3];
                        String message4 = args[4];
                        String message5 = args[5];
                        String message6 = args[6];
                        String message7 = args[7];
                        String message8 = args[8];

                        intent.putExtra("stockname", message);
                        intent.putExtra("noofstocks", message1);
                        intent.putExtra("costrate",message2);
                        intent.putExtra("stocksbought", message3);
                        intent.putExtra("costprice",message4);
                        intent.putExtra("sellrate", message5);
                        intent.putExtra("stockssold", message6);
                        intent.putExtra("sellprice",message7);
                        intent.putExtra("payoff", message8);

                        startActivity(intent);
                    }
                }
        );

    }


}
