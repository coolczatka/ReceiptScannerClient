package com.example.inz_client.activities;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.example.inz_client.models.ImageResponse;
import com.example.inz_client.models.Receipt;
import com.example.inz_client.presenters.IMainPresenter;
import com.example.inz_client.presenters.MainPresenter;
import com.example.inz_client.views.IMainView;
import com.example.inz_client.views.ReceiptsRecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.inz_client.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView {


    Context c;
    IMainPresenter presenter;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String token;
    FloatingActionButton statisticsButton;
    Uri imageUri;


    public Bitmap grabImage()
    {
        this.getContentResolver().notifyChange(imageUri, null);
        ContentResolver cr = this.getContentResolver();
        try
        {
            return android.provider.MediaStore.Images.Media.getBitmap(cr, imageUri);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT).show();
            Log.d("TAG", "Failed to load", e);
            return null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        c = getBaseContext();

        recyclerView = findViewById(R.id.rvReceipts);

        presenter = new MainPresenter(this);
        token = this.getIntent().getStringExtra("token");
        presenter.takeData(token);

        statisticsButton = findViewById(R.id.statistics);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            private File createTemporaryFile(String part, String ext) throws Exception
            {
                File tempDir= Environment.getExternalStorageDirectory();
                tempDir=new File(tempDir.getAbsolutePath()+"/.temp/");
                if(!tempDir.exists())
                {
                    tempDir.mkdirs();
                }
                return File.createTempFile(part, ext, tempDir);
            }
            @Override
            public void onClick(View view) {
                File photo = null;
                try
                {
                    // place where to store camera taken picture
                    photo = createTemporaryFile("picture", ".jpg");
                    photo.delete();
                }
                catch(Exception e)
                {
                    Log.v("tag", "Can't create file to take picture!");
                }
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                imageUri = Uri.fromFile(photo);
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(takePictureIntent,1);
            }
        });
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent statisticIntent = new Intent(c,StatisticsActivity.class);
                statisticIntent.putExtra("token",token);
                startActivity(statisticIntent) ;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Bitmap imageBitmap = grabImage();
        File file = savebitmap(imageBitmap);
        presenter.uploadImage(token,file);

        super.onActivityResult(requestCode,resultCode,data);
    }

    private File savebitmap(Bitmap bmp) {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        OutputStream outStream = null;
        File file = new File(extStorageDirectory, "temp.png");
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, "temp.png");
        }
        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, bos);
            byte[] bytedata = bos.toByteArray();
            outStream.write(bytedata);
            outStream.flush();
            outStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;

    }

    @Override
    public void showData(List<Receipt> list) {
        adapter = new ReceiptsRecyclerViewAdapter(list,this,token);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void startProductFormActivity(ImageResponse response) {
        Intent productFormIntent = new Intent(MainActivity.this,AddReceiptActivity.class);
        productFormIntent.putExtra("response",response);
        startActivity(productFormIntent);
    }
}
