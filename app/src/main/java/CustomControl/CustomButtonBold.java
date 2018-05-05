package CustomControl;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.sma_rasanehsoft.phonebook2.App;

/**
 * Created by alavi on 11/14/2017.
 */

public class CustomButtonBold extends android.support.v7.widget.AppCompatButton {
    public CustomButtonBold(Context context) {
        super(context);
        init();

    }

    public CustomButtonBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomButtonBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setTypeface(App.FONT_NORMAL, Typeface.BOLD);

    }


}

