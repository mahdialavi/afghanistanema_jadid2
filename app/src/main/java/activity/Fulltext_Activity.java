package activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sma_rasanehsoft.phonebook2.ActivityEnhanced;
import com.sma_rasanehsoft.phonebook2.App;
import com.sma_rasanehsoft.phonebook2.R;

import CustomControl.CustomTextView;
import CustomControl.CustomTextViewBold;
import StructPerson.StructPerson;

public class Fulltext_Activity extends ActivityEnhanced {
    String id = "";
    String title = "";
    String fulltext = "";
    String image = "";
    String featured = "";

    public static CustomTextViewBold txtTitle;
    public static CustomTextView txtFultext;
    Toolbar toolbar;
    public static ImageView img_fulltext;
    ImageView imgback;
    ImageView back;
    ImageView sharebtn;
    ImageView sharebtn2;
   // SliderLayout sliderShow;
    public String name;
    public String desc;
    Context context;
    AppBarLayout appBarLayout;
    public  StructPerson person;


    ImageView imgfulltext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fulltext_);

        Intent intent = getIntent();
        if (null != intent) {
            Bundle bundle = getIntent().getExtras();
            id = bundle.getString("id");
            title = bundle.getString("title");
            fulltext = bundle.getString("fulltext");
            image = bundle.getString("image");
//            boolean myBooleanVariable = bundle.getBoolean("featured");
        }

         txtTitle = (CustomTextViewBold) findViewById(R.id.txtTitle);
        txtTitle.setText(title);
        txtFultext= (CustomTextView) findViewById(R.id.txtFulltext);
        txtFultext.setText(Html.fromHtml(fulltext));

        ImageView imagefulltext= (ImageView) findViewById(R.id.imgnews);
        Glide
                .with(App.CONTEXT)
                .load("http://192.168.1.201/joomla/" +image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache( true )
                .into(imagefulltext);
        // sliderShow = (SliderLayout) findViewById(R.id.sliderShow);
            appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);

            sharebtn = (ImageView) findViewById(R.id.share);

            findViewById(R.id.imgback).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://afghanistanema.com/"+id;
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,url);
                intent.putExtra(Intent.EXTRA_SUBJECT,txtTitle.getText().toString());
                startActivity(Intent.createChooser(intent,"اشتراک گذاری"));
//
            }
        });


    }
}
