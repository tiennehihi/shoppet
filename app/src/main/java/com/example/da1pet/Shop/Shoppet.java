package com.example.da1pet.Shop;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import com.example.da1pet.R;
import com.example.da1pet.home.Home;
public class Shoppet extends AppCompatActivity {
   ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppet);



      imageButton = findViewById(R.id.btnbackHome);
      imageButton.setOnClickListener(v -> {
          Intent intent = new Intent(this, Home.class);
             startActivity(intent);
       });

    }
}