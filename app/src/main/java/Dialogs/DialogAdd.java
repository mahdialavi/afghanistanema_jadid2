package Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.sma_rasanehsoft.phonebook2.App;
import com.sma_rasanehsoft.phonebook2.R;

import Async.AsyncCteate;
import CustomControl.CustomButtonBold;
import CustomControl.CustomEditText;
import CustomControl.CustomTextViewBold;
import Models.Persons;
import StructPerson.StructPerson;

/**
 * Created by alavi on 11/15/2017.
 */

public class DialogAdd extends Dialog {
    private StructPerson person;
    private OnDismissListener listener;

    private AppCompatCheckBox chkFeatured;
    private CustomButtonBold btnSave;
    private CustomEditText edtTitle;
    private CustomEditText edtfulltext;
    private CustomEditText edthits;
    private CustomEditText edtcatid;
    private CustomEditText edtcreated;

    private CustomTextViewBold txtFemale;
    private CustomTextViewBold txtMale;
    private ImageView imgFemale;
    private ImageView imgMale;
    private ViewGroup lytFemale;
    private ViewGroup lytMale;

    private int colorAccent;
    private int colorLightest;

    public DialogAdd(Context context, StructPerson person) {
        super(context);
        this.person = person;
        init();
    }

    public DialogAdd(Context context, int themeResId, StructPerson person) {
        super(context, themeResId);
        this.person = person;
        init();
    }

    private void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add);

        getItems();

        chkFeatured.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                person.featured = isChecked;
            }
        });

//        lytFemale.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                person.male = false;
//                txtFemale.setTextColor(colorAccent);
//                imgFemale.setColorFilter(colorAccent);
//                txtMale.setTextColor(colorLightest);
//                imgMale.setColorFilter(colorLightest);
//            }
//        });
//
//        lytMale.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                person.male = true;
//                txtFemale.setTextColor(colorLightest);
//                imgFemale.setColorFilter(colorLightest);
//                txtMale.setTextColor(colorAccent);
//                imgMale.setColorFilter(colorAccent);
//            }
//        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person.title = edtTitle.getText().toString();
                person.fulltext = edtfulltext.getText().toString();
                person.hits = edthits.getText().toString();
                person.created = edtcreated.getText().toString();
                person.catid= edtcatid.getText().toString();
                person.stored = true;

                if (person.id == 0) {
                    new AsyncCteate()
                            .setOnDataReceivedListener(new Async.AsyncCteate.OnDataReceivedListener() {
                                @Override
                                public void OnDataReceived(int result) {
                                    if (result > 0) {
                                        person.id = result;
                                        if (Persons.save(person)) {
                                            App.toast(App.CONTEXT.getString(R.string.save_success));
                                            dismiss();
                                        } else {
                                            App.toast(App.CONTEXT.getString(R.string.save_error));
                                        }
                                    } else {
                                        App.toast(App.CONTEXT.getString(R.string.save_error));
                                    }
                                }
                            })
                            .execute(person);
                } else {
                    person.stored = false;
                    if (Persons.save(person)) {
                        App.toast(App.CONTEXT.getString(R.string.save_success));
                        dismiss();
                    } else {
                        App.toast(App.CONTEXT.getString(R.string.save_error));
                    }
                }
            }
        });
    }

    private void getItems() {
        colorAccent = ContextCompat.getColor(App.CONTEXT, R.color.colorAccent);
        colorLightest = ContextCompat.getColor(App.CONTEXT, R.color.colorLightest);

        if (person == null) {
            person = new StructPerson();
         //   person.male = true;
        }

        chkFeatured = (AppCompatCheckBox) findViewById(R.id.chkFav);
        chkFeatured.setChecked(person.featured);

        btnSave = (CustomButtonBold) findViewById(R.id.btnSave);

        edtTitle = (CustomEditText) findViewById(R.id.edtTitle);
        edtTitle.setText(person.title);

        edtfulltext = (CustomEditText) findViewById(R.id.edtfulltext);
        edtfulltext.setText(person.fulltext);

        edthits = (CustomEditText) findViewById(R.id.edthits);
        edthits.setText(person.introtext);

        edtcatid = (CustomEditText) findViewById(R.id.edtcatid);
        edtcatid.setText(person.catid);

        edtcreated= (CustomEditText) findViewById(R.id.edtcreated);
        edtcreated.setText(person.created);

//        txtFemale = (CustomTextViewBold) findViewById(R.id.txtFemale);
//        txtMale = (CustomTextViewBold) findViewById(R.id.txtMale);
//        imgFemale = (ImageView) findViewById(R.id.imgFemale);
//        imgMale = (ImageView) findViewById(R.id.imgMale);
//
//        if (person.male) {
//            txtFemale.setTextColor(colorLightest);
//            imgFemale.setColorFilter(colorLightest);
//            txtMale.setTextColor(colorAccent);
//            imgMale.setColorFilter(colorAccent);
//        } else {
//            txtFemale.setTextColor(colorAccent);
//            imgFemale.setColorFilter(colorAccent);
//            txtMale.setTextColor(colorLightest);
//            imgMale.setColorFilter(colorLightest);
        }


//
//        lytFemale = (ViewGroup) findViewById(R.id.lytFemale);
//        lytMale = (ViewGroup) findViewById(R.id.lytMale);
//
//    }

    public DialogAdd setListener(OnDismissListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (listener != null) {
            listener.onDismiss(this);
        }
    }
}
