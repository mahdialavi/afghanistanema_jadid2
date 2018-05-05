package Adaptors;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sma_rasanehsoft.phonebook2.App;
import com.sma_rasanehsoft.phonebook2.R;

import java.util.ArrayList;

import CustomControl.CustomButtonBold;
import CustomControl.CustomTextView;
import Models.PersonsLocal;
import StructPerson.StructPerson;
import activity.Fulltext_Activity;

/**
 * Created by alavi on 11/14/2017.
 */

public class AdapterPerson extends RecyclerView.Adapter<AdapterPerson.ViewHolder>{
    public static ArrayList<StructPerson> persons;

    public AdapterPerson(ArrayList<StructPerson> persons) {
        this.persons = persons;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = App.INFLATER.inflate(R.layout.adopter_person, parent, false);
        return new ViewHolder(v);
    }
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final StructPerson person = persons.get(position);



        holder.imgAvatar.setOnClickListener(new View.OnClickListener() {

                      @Override
            public void onClick(View view) {
//                          String id = person.id+"";
//                          App.toast(id);
//                          if (PersonsLocal.selectFavState(id)) {
//
//                    Toast.makeText(App.CONTEXT,"true" +"",Toast.LENGTH_SHORT).show();
//
//                          } else {
//
//                              Toast.makeText(App.CONTEXT, "false", Toast.LENGTH_SHORT).show();
//                          }
                      }

        });;
        holder.txttitle.setText(person.title);
        Glide
                .with(App.CONTEXT)
                .load("http://192.168.1.201/joomla/" + person.introtext)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache( true )
                .into(holder.imgAvatar);
        holder.txthits.setText(person.hits);
        holder.btncreated.setText(person.created);
//-----------------------------------------------------------------------------------
        final int personid= Integer.parseInt(person.id+"");


        holder.imgFeatured.setOnClickListener(new View.OnClickListener() {
                 int id = Integer.valueOf(person.id+"");

            @Override
            public void onClick(View view) {

                if (PersonsLocal.exists(personid)) {

                  PersonsLocal.delete(id);
                    holder.imgFeatured.setImageResource(R.drawable.favheard);

                }else {
                    PersonsLocal.insert_db_local(person);
                    holder.imgFeatured.setImageResource(R.drawable.favheard1);
                }
            }
        });

        if (PersonsLocal.exists(personid)) {
            holder.imgFeatured.setImageResource(R.drawable.favheard1);
//            App.toast();
          } else {
            holder.imgFeatured.setImageResource(R.drawable.favheard);
        }

        holder.linearNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = person.id+"";
                String title = person.title+"";
                String fulltext = person.fulltext+"";
                String image = person.introtext+"";

                Intent intent = new Intent(App.CONTEXT, Fulltext_Activity.class);
                intent.putExtra("id", id);
                intent.putExtra("title", title);
                intent.putExtra("fulltext", fulltext);
                intent.putExtra("image", person.introtext);

                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                App.CONTEXT.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CustomTextView txttitle;
        public CustomTextView txtfulltext;
        public CustomTextView btncreated;
        public CustomButtonBold btnEdit;
        public CustomTextView txthits;
        public ImageView imgFeatured;
        public ImageView imgAvatar;
        public LinearLayout linearNews;

        public ViewHolder(View itemView) {
            super(itemView);
            txttitle = (CustomTextView) itemView.findViewById(R.id.txtTitle);
            btncreated = (CustomTextView) itemView.findViewById(R.id.btncreated);
            txthits = (CustomTextView) itemView.findViewById(R.id.txthits);
            imgFeatured = (ImageView) itemView.findViewById(R.id.imgfav);
            imgAvatar = (ImageView) itemView.findViewById(R.id.imgAvatar);
            linearNews = (LinearLayout) itemView.findViewById(R.id.linearNews);

        }
    }
}
