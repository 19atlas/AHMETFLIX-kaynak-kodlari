package com.dtw.ahmetflix;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dtw.ahmetflix.adapter.MainRecyclerAdapter;
import com.dtw.ahmetflix.model.AllCategory;
import com.dtw.ahmetflix.model.CategoryItem;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    ImageView settings,aramaDume;
    FirebaseAuth auth;
    FirebaseAnalytics anal;
    int versionCode = BuildConfig.VERSION_CODE;
    String versionName = BuildConfig.VERSION_NAME;
    MainRecyclerAdapter mainRecyclerAdapter;
    RecyclerView mainRecycler;
    List<AllCategory> allCategoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = findViewById(R.id.settings);
        aramaDume = findViewById(R.id.aramaDume);
        anal= FirebaseAnalytics.getInstance(this);
        auth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        settings.setOnClickListener(view -> {
            if (auth.getCurrentUser()!=null){
                Intent git = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(git);
            }else {
                Toast.makeText(this, "giriş hakkın yok", Toast.LENGTH_SHORT).show();
            }
        });
        settings.setOnLongClickListener(view -> {
            Toast.makeText(MainActivity.this,versionName+" kod:"+versionCode , Toast.LENGTH_SHORT).show();
            return false;
        });
        List<CategoryItem> homeCatListItem1 = new ArrayList<>();
        homeCatListItem1.add(new CategoryItem(1,"AYNALI TAHİR REMAKE 1.BÖLÜM","https://firebasestorage.googleapis.com/v0/b/ahmetimg-ser.appspot.com/o/aynali%20tahir%201.jpg?alt=media&token=8e371f46-f4f1-4d3a-8f6d-660f61af38a3","https://firebasestorage.googleapis.com/v0/b/ahmetstorage1.appspot.com/o/AYNALI%20TAH%C4%B0R%20REMAKE%201.B%C3%96L%C3%9CM.mp4?alt=media&token=d7592dcf-e8ba-463a-b6d3-037f57bb43fe"));
        homeCatListItem1.add(new CategoryItem(2,"AYNALI TAHİR REMAKE 2.BÖLÜM","https://firebasestorage.googleapis.com/v0/b/ahmetimg-ser.appspot.com/o/aynali%20tahir2.jpg?alt=media&token=ff736c40-ab2d-4221-9fee-cee7ec908d59","https://firebasestorage.googleapis.com/v0/b/ahmetstorage1.appspot.com/o/AYNALI%20%20TAH%C4%B0R%20REMAKE%202.%20B%C3%96L%C3%9CM.mp4?alt=media&token=9cae76ed-f5c9-4315-8a96-454856b0d737"));
        homeCatListItem1.add(new CategoryItem(3,"DOĞUŞ  KONSERLERİ","https://firebasestorage.googleapis.com/v0/b/ahmetimg-ser.appspot.com/o/dogus.jpg?alt=media&token=d986b43c-d894-4f7c-9fb8-3f950ecf57e4","https://firebasestorage.googleapis.com/v0/b/ahmetstorage1.appspot.com/o/DO%C4%9EU%C5%9E%20%20KONSERLER%C4%B0.mp4?alt=media&token=81b5e816-a214-4c88-9751-af601ab1a94c"));
        homeCatListItem1.add(new CategoryItem(4,"DOĞUŞ BOZUK KLİBİ","https://firebasestorage.googleapis.com/v0/b/ahmetimg-ser.appspot.com/o/bozukklip.jpg?alt=media&token=37241c29-c8a1-40e5-bf06-a6dd13d2c819","https://firebasestorage.googleapis.com/v0/b/ahmetstorage1.appspot.com/o/DO%C4%9EU%C5%9E%20BOZUK%20KL%C4%B0B%C4%B0.mp4?alt=media&token=a4e8f521-34ef-446f-a5ff-ee4e88dd6e3c"));
        homeCatListItem1.add(new CategoryItem(5,"MERO KONSERLERİ","https://firebasestorage.googleapis.com/v0/b/ahmetimg-ser.appspot.com/o/mero.jpg?alt=media&token=97d4df1f-670c-48f5-80bf-daaf9bd4da86","https://firebasestorage.googleapis.com/v0/b/ahmetstorage1.appspot.com/o/MERO%20KONSERLER%C4%B0.mp4?alt=media&token=eef1e94a-de27-4739-90f1-4577701ce1ba"));
        homeCatListItem1.add(new CategoryItem(6,"BARON","https://firebasestorage.googleapis.com/v0/b/ahmetimg-ser.appspot.com/o/barfedai.jpg?alt=media&token=88eb29b7-68c1-40a7-88c5-db5b84598c02","https://firebasestorage.googleapis.com/v0/b/ahmetstorage1.appspot.com/o/MERO%20KONSERLER%C4%B0.mp4?alt=media&token=eef1e94a-de27-4739-90f1-4577701ce1ba"));
        List<CategoryItem> homeCatListItem2 = new ArrayList<>();
        homeCatListItem2.add(new CategoryItem(1,"","",""));
        List<CategoryItem> homeCatListItem3 = new ArrayList<>();
        homeCatListItem3.add(new CategoryItem(1,"","",""));
        List<CategoryItem> homeCatListItem4 = new ArrayList<>();
        homeCatListItem4.add(new CategoryItem(1,"","",""));
        allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory(1,"AHMETFLIX Originals",homeCatListItem1));
        allCategoryList.add(new AllCategory(2,"Burdurun Fatihi",homeCatListItem2));
        allCategoryList.add(new AllCategory(3,"ama o zamanda netflix gibi olmaz",homeCatListItem3));
        allCategoryList.add(new AllCategory(4,"Burdur yöresel yemekleri \uD83D\uDE1B",homeCatListItem4));
        setMainRecycler(allCategoryList);
        aramaDume.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,SearchActivity.class);
            //intent.put
            startActivity(intent);
        });
    }
    boolean zorlayici = false;
    @Override
    public void onBackPressed() {
        if (zorlayici) {super.onBackPressed();return;}
        this.zorlayici = true;
        Toast.makeText(this, "Çıkmak için tekrar basın", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(() -> zorlayici =false, 2000);
    }
    public void setMainRecycler(List<AllCategory> allCategoryList){
        mainRecycler = findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this,allCategoryList);
        mainRecycler.setAdapter(mainRecyclerAdapter);
    }
}