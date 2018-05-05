//package dota2.clicksite.ir.dota2;
//
//import android.content.Intent;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.support.design.widget.NavigationView;
//import android.support.design.widget.TabLayout;
//import android.support.v4.view.ViewPager;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.test.ActivityTestCase;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ImageView;
//
//import java.util.ArrayList;
//
//public class ActivityMain extends AppCompatActivity {
//
//    TabLayout tabLayout;
//    ViewPager viewPager;
//    DrawerLayout drawer;
//    NavigationView navigationView;
//    ImageView imgToolbar;
//
//    public static ArrayList<Heros> herosArrayList=new ArrayList<>();
//    public static ArrayList<Heros> itemArrayList =new ArrayList<>();
//    public static ArrayList<Heros> favArrayList=new ArrayList<>();
//    public static ArrayList<Heros> heroItemArrayList=new ArrayList<>();
//    public static SQLiteDatabase database;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.navigation_menu);
//
//        drawer=(DrawerLayout)findViewById(R.id.drawer);
//        navigationView=(NavigationView)findViewById(R.id.navigation) ;
//
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem item) {
//                int id=item.getItemId();
//                if(id==R.id.serch){
//                    Intent intent=new Intent(G.context,ActivitySearch.class);
//                    startActivity(intent);
//
//                }
//                if(id==R.id.setting){
//                    Intent intent=new Intent(G.context,ActivitySetting.class);
//                    startActivity(intent);
//
//                }if(id==R.id.hero){
//                    //   Intent intent=new Intent(G.context,Test.class);
//                    //  startActivity(intent);
//
//                }
//                return true;
//            }
//        });
//
//        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
//        viewPager=(ViewPager)findViewById(R.id.viewPager);
//        imgToolbar=(ImageView)findViewById(R.id.imgToolbar);
//        imgToolbar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawer.openDrawer(Gravity.RIGHT);
//            }
//        });
//
//        selectHeros();
//        selectItem();
//        selectFav();
//        selectAll();
//
//        for(int i=0;i<herosArrayList.size();i++) {
//            Log.i("LOG","id:"+herosArrayList.get(i).getInt()+"name:"+herosArrayList.get(i).getName() );
//        }
//
//        viewPager.setAdapter(new AdapterFragment(getSupportFragmentManager()));
//
//        tabLayout.setupWithViewPager(viewPager);
//    }
//
//
//
//    public void selectHeros(){
//
//        database=SQLiteDatabase.openOrCreateDatabase(G.direction +"/material_book.sqlite",null);
//        Cursor cursor=database.rawQuery("SELECT * FROM tbl_heros WHERE type='hero'",null);
//
//        while(cursor.moveToNext()){
//            int id=cursor.getInt(cursor.getColumnIndex("id"));
//            String name=cursor.getString(cursor.getColumnIndex("name"));
//            String desc=cursor.getString(cursor.getColumnIndex("desc"));
//            String pic=cursor.getString(cursor.getColumnIndex("pic"));
//            String type=cursor.getString(cursor.getColumnIndex("type"));
//            int fav=cursor.getInt(cursor.getColumnIndex("fav"));
//            Heros heros=new Heros(id,name,desc,pic,fav,type);
//            herosArrayList.add(heros);
//            Log.i("LOG","heros check----id:"+name);
//
//        }
//
//    }
//
//    public static  void selectItem(){
//
//        database=SQLiteDatabase.openOrCreateDatabase(G.direction +"/material_book.sqlite",null);
//        Cursor cursor=database.rawQuery("SELECT * FROM tbl_heros WHERE type='item'",null);
//
//        while(cursor.moveToNext()){
//            int id=cursor.getInt(cursor.getColumnIndex("id"));
//            String name=cursor.getString(cursor.getColumnIndex("name"));
//            String desc=cursor.getString(cursor.getColumnIndex("desc"));
//            String pic=cursor.getString(cursor.getColumnIndex("pic"));
//            String type=cursor.getString(cursor.getColumnIndex("type"));
//            int fav=cursor.getInt(cursor.getColumnIndex("fav"));
//            Heros heros=new Heros(id,name,desc,pic,fav,type);
//            itemArrayList.add(heros);
//
//        }
//
//    }
//
//    public static  void selectFav(){
//
//        database=SQLiteDatabase.openOrCreateDatabase(G.direction +"/material_book.sqlite",null);
//        Cursor cursor=database.rawQuery("SELECT * FROM tbl_heros WHERE fav=1",null);
//
//        while(cursor.moveToNext()){
//            int id=cursor.getInt(cursor.getColumnIndex("id"));
//            String name=cursor.getString(cursor.getColumnIndex("name"));
//            String desc=cursor.getString(cursor.getColumnIndex("desc"));
//            String pic=cursor.getString(cursor.getColumnIndex("pic"));
//            String type=cursor.getString(cursor.getColumnIndex("type"));
//            int fav=cursor.getInt(cursor.getColumnIndex("fav"));
//            Heros heros=new Heros(id,name,desc,pic,fav,type);
//            favArrayList.add(heros);
//
//        }
//
//    }
//
//    public static void selectAll() {
//        database = SQLiteDatabase.openOrCreateDatabase(G.direction + "/material_book.sqlite", null);
//        Cursor cursor = database.rawQuery("SELECT * FROM tbl_heros", null);
//
//        while (cursor.moveToNext()) {
//            int id = cursor.getInt(cursor.getColumnIndex("id"));
//            String name = cursor.getString(cursor.getColumnIndex("name"));
//            String desc = cursor.getString(cursor.getColumnIndex("desc"));
//            String pic = cursor.getString(cursor.getColumnIndex("pic"));
//            String type = cursor.getString(cursor.getColumnIndex("type"));
//            int fav = cursor.getInt(cursor.getColumnIndex("fav"));
//            Heros heros = new Heros(id, name, desc, pic, fav, type);
//            heroItemArrayList.add(heros);
//        }
//    }
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(!herosArrayList.isEmpty()){
//            herosArrayList.clear();
//            selectHeros();
//
//        } if(!itemArrayList.isEmpty()){
//            itemArrayList.clear();
//            selectItem();
//
//        }if(!favArrayList.isEmpty()){
//            favArrayList.clear();
//            selectFav();
//
//        }if(!heroItemArrayList.isEmpty()){
//            heroItemArrayList.clear();
//            selectAll();
//
//        }
//
//
//    }
//
//
//
//
//}
