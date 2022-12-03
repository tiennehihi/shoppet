package com.example.da1pet.Shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.HoaDon;
import com.example.da1pet.Model.Cart_item;
import com.example.da1pet.Model.Order;
import com.example.da1pet.Model.Order_detail;
import com.example.da1pet.Model.Products;
import com.example.da1pet.NavigationActivity;
import com.example.da1pet.R;
import com.example.da1pet.home.Home;
import com.example.da1pet.innerjoin.InnerCart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Shoppet extends AppCompatActivity {
    ImageButton imageButton;
    RecyclerView rvcart;
    ArrayList<InnerCart> list;
    DbRoom db;
    String TAG = "zzzzzz";
    int totalcheck = 0;
    TextView tvtotalall;
    ArrayList<PR> list1 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppet);
        db = DbRoom.getInstance(this);
        imageButton = findViewById(R.id.btnbackHome);
        imageButton.setOnClickListener(v -> {
            onBackPressed();
        });
        tvtotalall = findViewById(R.id.tvtotalall);
        rvcart = findViewById(R.id.rvcart);
        list = (ArrayList<InnerCart>) db.cartItemDAO().getAllCartItemById(getIntent().getExtras().getString("idcart"));
        RVAdapter adapter = new RVAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvcart.setLayoutManager(linearLayoutManager);
        rvcart.setAdapter(adapter);

    }
    public class PR {
        private Integer id;
        private Integer soluong;

        public PR(Integer id, Integer soluong) {
            this.id = id;
            this.soluong = soluong;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getSoluong() {
            return soluong;
        }

        public void setSoluong(Integer soluong) {
            this.soluong = soluong;
        }
    }
    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.Viewholder> {
        ArrayList<InnerCart> list;

        public RVAdapter(ArrayList<InnerCart> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public RVAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowcart, parent, false);
            return new Viewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RVAdapter.Viewholder holder, int position) {
            holder.innerCart = list.get(position);
            holder.tvnamepro.setText(String.valueOf(holder.innerCart.getName_products()));
            holder.tvid.setText(String.valueOf(holder.innerCart.getId_product()));
            holder.tvinventory.setText(String.valueOf(holder.innerCart.getInventory()));
            holder.tvprice.setText(String.valueOf(holder.innerCart.getPrice()));
            holder.tvtotalprice.setText(String.valueOf(holder.innerCart.getPrice() * Integer.parseInt(holder.tvsoluoong.getText().toString())));
            holder.img.setImageBitmap(BitmapFactory.decodeByteArray(holder.innerCart.getImg_product(), 0, holder.innerCart.getImg_product().length));
            holder.ckbox.setOnClickListener(v -> {
                if (holder.ckbox.isChecked()){
                    list1.add(new PR(holder.innerCart.getId_product(),Integer.parseInt(holder.tvsoluoong.getText().toString())));
                    tvtotalall.setText(String.valueOf(Integer.parseInt(tvtotalall.getText().toString()) + Integer.parseInt(holder.tvtotalprice.getText().toString())));
                    totalcheck = totalcheck + 1;
                    holder.tbtn.setEnabled(false);
                    holder.gbtn.setEnabled(false);
                }else if(!holder.ckbox.isChecked()) {
                    for (int i = 0; i < list1.size(); i++) {
                        PR p = list1.get(i);
                        if (p.getId() == holder.innerCart.getId_product()){
                            list1.remove(i);
                            break;
                        }
                    }
                    tvtotalall.setText(String.valueOf(Integer.parseInt(tvtotalall.getText().toString()) - Integer.parseInt(holder.tvtotalprice.getText().toString())));
                    totalcheck = totalcheck - 1;
                    holder.tbtn.setEnabled(true);
                    holder.gbtn.setEnabled(true);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class Viewholder extends RecyclerView.ViewHolder {
            CheckBox ckbox;
            ImageView img;
            InnerCart innerCart;

            TextView tvnamepro, tvinventory, tvprice, tvsoluoong, tvtotalprice,tvid;
            Button tbtn,gbtn;
            public Viewholder(@NonNull View itemView) {
                super(itemView);
                ckbox = itemView.findViewById(R.id.ckbox);
                img = itemView.findViewById(R.id.img);
                tvid = itemView.findViewById(R.id.tvid);
                tvnamepro = itemView.findViewById(R.id.tvnamepro);
                tvinventory = itemView.findViewById(R.id.tvinventory);
                tvprice = itemView.findViewById(R.id.tvprice);
                tvsoluoong = itemView.findViewById(R.id.tvsoluoong);
                tvtotalprice = itemView.findViewById(R.id.tvtotalprice);
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                tbtn = itemView.findViewById(R.id.tbtn);
                gbtn = itemView.findViewById(R.id.gbtn);
                tbtn.setOnClickListener(v -> {
                    gbtn.setEnabled(true);
                    tvsoluoong.setText(String.valueOf(Integer.parseInt(tvsoluoong.getText().toString()) + 1));
                    tvtotalprice.setText(String.valueOf(Integer.parseInt(tvsoluoong.getText().toString()) * Integer.parseInt(tvprice.getText().toString())));
                    if (ckbox.isChecked()) {
                        tvtotalall.setText(String.valueOf(Integer.parseInt(tvtotalall.getText().toString()) + Integer.parseInt(tvprice.getText().toString())));
                    }
                });
                gbtn.setOnClickListener(v1 -> {
                    if (Integer.parseInt(tvsoluoong.getText().toString()) == 1) {
                        gbtn.setEnabled(false);

                    } else {
                        tvsoluoong.setText(String.valueOf(Integer.parseInt(tvsoluoong.getText().toString()) - 1));
                        tvtotalprice.setText(String.valueOf(Integer.parseInt(tvsoluoong.getText().toString()) * Integer.parseInt(tvprice.getText().toString())));
                        if (ckbox.isChecked()) {
                            tvtotalall.setText(String.valueOf(Integer.parseInt(tvtotalall.getText().toString()) - Integer.parseInt(tvprice.getText().toString())));
                        }
                    }
                });

//                ckbox.setOnClickListener(v -> {
//
//                });

                findViewById(R.id.btnthanhtoan).setOnClickListener(v -> {
                    if (tvtotalall.getText().toString().equals("0")) {
                        Toast.makeText(Shoppet.this, "Bạn hãy chọn sản phẩm muốn mua", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            db.orderDAO().insert(new Order(getIntent().getExtras().getString("idcart"), totalcheck, currentDate, Integer.parseInt(tvtotalall.getText().toString()),"Chưa nhận hàng"));
                            ArrayList<Order> listorder = (ArrayList<Order>) db.orderDAO().getAll();
                            Order order = listorder.get(listorder.size()-1);

                            for (int i = 0 ;i<totalcheck;i++){
                                PR pr = list1.get(i);

                                db.orderDetailDAO().insert(
                                        new Order_detail(order.getId_order()
                                                ,pr.getId()
                                                ,pr.getSoluong()));
                                for (int j = 0; j < list.size(); j++) {
                                    InnerCart innerCart1 = list.get(j);
                                    if (pr.getId() == innerCart1.getId_product()){
                                        db.productsDAO().updateinven(pr.getId(),innerCart1.getInventory()-pr.getSoluong());
                                    }

                                }
                                db.cartItemDAO().deleteitem(pr.getId());
                            }
                            Toast.makeText(Shoppet.this, "Mua hàng thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Shoppet.this, HoaDon.class);
                            intent.putExtra("username",getIntent().getExtras().getString("idcart"));
                            startActivity(intent);
                        }catch (Exception e){
                            Log.e(TAG, "Viewholder: "+e.getMessage() );
                        }
                    }
                });
                this.setIsRecyclable(false);
            }
        }

    }

}