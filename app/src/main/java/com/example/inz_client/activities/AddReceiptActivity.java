package com.example.inz_client.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inz_client.R;
import com.example.inz_client.models.ImageResponse;
import com.example.inz_client.models.Product;
import com.example.inz_client.presenters.AddReceiptPresenter;
import com.example.inz_client.views.IAddReceiptView;
import com.example.inz_client.views.ProductFormRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddReceiptActivity extends AppCompatActivity implements IAddReceiptView {

    Button submit;
    Button addProduct;
    EditText date;
    EditText shop;
    ImageResponse data;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProductFormRecyclerViewAdapter adapter;

    String token;

    AddReceiptPresenter presenter;

    private boolean validateDate(String date){
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
    @Override
    public boolean validateForm() {
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            ProductFormRecyclerViewAdapter.ProductFormViewHolder viewHolder = (ProductFormRecyclerViewAdapter.ProductFormViewHolder)recyclerView.findViewHolderForAdapterPosition(i);
            if(!viewHolder.validateName()) {
                Toast.makeText(AddReceiptActivity.this, "Brak nazwy", Toast.LENGTH_LONG).show();
                return false;
            }
            if(!viewHolder.validateAmount()) {
                Toast.makeText(AddReceiptActivity.this, "Błędna ilość", Toast.LENGTH_LONG).show();
                return false;
            }
            if(!viewHolder.validatePrice()) {
                Toast.makeText(AddReceiptActivity.this, "Błędna cena", Toast.LENGTH_LONG).show();
                return false;
            }
            if(!validateDate(date.getText().toString())){
                Toast.makeText(AddReceiptActivity.this, "Błędny format daty", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
    }

    @Override
    public void success() {
        finish();
    }

    @Override
    public void error() {
        Toast.makeText(AddReceiptActivity.this,
                "Nie udało się dodać paragonu. Spróbuj później", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receipt);

        date = findViewById(R.id.dateEd);
        shop = findViewById(R.id.shopEd);

        addProduct = findViewById(R.id.addProduct);
        submit = findViewById(R.id.submitBtn);

        recyclerView = findViewById(R.id.rvProductForms);

        token = getIntent().getStringExtra("token");
        data = getIntent().getParcelableExtra("response");
        if(data==null)
            data = new ImageResponse("","",new ArrayList<Product>());
        adapter = new ProductFormRecyclerViewAdapter(data.getProducts());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        if(data.getDate().equals("") || data.getDate()==null) {
            data.setDate(getDateFormatted());
        }
        date.setText(data.getDate());
        shop.setText(data.getShop());

        presenter = new AddReceiptPresenter(token,this);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.getProducts().add(0,new Product("",1,0));
                adapter.notifyItemInserted(0);
                recyclerView.smoothScrollToPosition(0);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateForm()) {
                    List<Product> list = new ArrayList<>();
                    for (int i = 0; i < recyclerView.getChildCount(); i++) {
                        ProductFormRecyclerViewAdapter.ProductFormViewHolder viewHolder = (ProductFormRecyclerViewAdapter.ProductFormViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
                        String name = viewHolder.getName().getText().toString();
                        double amount = Float.parseFloat(viewHolder.getAmount().getText().toString());
                        double price = Float.parseFloat(viewHolder.getPrice().getText().toString());
                        list.add(new Product(name,amount,price));
                    }
                    presenter.sendData(shop.getText().toString(), date.getText().toString(),list);
                }
                else
                    error();
            }
        });


    }
    private String getDateFormatted(){
        Calendar cal = Calendar.getInstance();
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String month = cal.get(Calendar.MONTH)+1<10 ? "0"+String.valueOf(cal.get(Calendar.MONTH)+1) : String.valueOf(cal.get(Calendar.MONTH)+1);
        String day = cal.get(Calendar.DAY_OF_MONTH)<10 ? "0"+cal.get(Calendar.DAY_OF_MONTH) : String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        return year+"-"+month+"-"+day;
    }
}
