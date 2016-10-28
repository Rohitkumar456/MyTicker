package com.wordpress.shloknangia.myticker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddStock extends AppCompatActivity {
    EditText name_of_stock,cost_price,noof_stock,total_cost;
    String symbol,rate;

    DatabaseAdapter databaseadapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        name_of_stock= (EditText)findViewById(R.id.text_stock_name);
        cost_price=(EditText)findViewById(R.id.text_cost_price);
        noof_stock=(EditText)findViewById(R.id.text_noofstock);
        total_cost=(EditText)findViewById(R.id.text_cost);

        Intent intent = getIntent();
        symbol = intent.getStringExtra("symbol");
        rate = intent.getStringExtra("rate");

        name_of_stock.setText(symbol);
        cost_price.setText(rate);
        databaseadapter = new DatabaseAdapter(this);
    }

    public void calculate(View view){
        String r,q;
        float rate,totalprice;
        int quan;
        q = noof_stock.getText().toString();
        quan = Integer.parseInt(q);
        r = cost_price.getText().toString();
        rate =Float.parseFloat(r);
        totalprice = quan*rate;
        total_cost.setText(String.valueOf(totalprice));

    }
    
    public void confirm_add(View view){
        String stockname,s1,s2,s3;
        int numstock;
        float costprice,costrate;

        stockname = name_of_stock.getText().toString();
        s1 = noof_stock.getText().toString();
        numstock = Integer.parseInt(s1);
        s2 = cost_price.getText().toString();
        costrate = Float.parseFloat(s2);
        s3 = total_cost.getText().toString();
        costprice = Float.parseFloat(s3);

        long id = databaseadapter.insertdata(stockname,numstock,costrate,numstock,costprice,0.0f,0,0.0f,0.0f);
        if (id<0){
            Toast.makeText(this, "unsuccessfull",Toast.LENGTH_SHORT).show();
        }else
        {
           Toast.makeText(this,"successfully inserted a stock",Toast.LENGTH_SHORT).show();
        }

        this.finish();
    }

}
