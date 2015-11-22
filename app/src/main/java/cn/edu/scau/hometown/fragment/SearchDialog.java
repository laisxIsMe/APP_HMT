package cn.edu.scau.hometown.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.activities.ResultOfSearchActivity;

/**
 * Created by Admin on 2015/11/19.
 */
public class SearchDialog extends Dialog{
    private EditText et_search;
    private ImageView iv_delete;
    private String colorOfText ;

    public SearchDialog(Context context, String str){
        super(context);
        colorOfText = str;
        InitDialog(context);
    }

    private void InitView(){
        et_search = (EditText) findViewById(R.id.et_search);
        iv_delete = (ImageView) findViewById(R.id.iv_delete);
    }

    public void InitDialog(Context context){
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_search);
        InitView();
        setCanceledOnTouchOutside(true);

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.TOP;
        layoutParams.width = wm.getDefaultDisplay().getWidth();
        layoutParams.alpha = 0.9f;
        getWindow().setAttributes(layoutParams);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        et_search.setSelection(et_search.getText().length());
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0)
                    iv_delete.setVisibility(View.GONE);
                else
                    iv_delete.setVisibility(View.VISIBLE);
            }
        });
        et_search.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_SEARCH){
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    //添加搜索关键字的方法
                    String keyWord = et_search.getText().toString().trim();
                    if(!TextUtils.isEmpty(keyWord)){
                        Intent intent = new Intent(getContext(), ResultOfSearchActivity.class);
                        intent.putExtra("keyWord", keyWord);
                        getContext().startActivity(intent);
                    }
                    //Toast.makeText(getContext(),"搜索",Toast.LENGTH_SHORT).show();
                    SearchDialog.this.cancel();
                }
                return false;
            }
        });
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search.setText("");
            }
        });



        Resources resource = (Resources)getContext().getResources();
        ColorStateList csl;
        switch(colorOfText){
            case "Tab_green":csl = (ColorStateList) resource.getColorStateList(R.color.tab_green);break;
            case "Tab_blue":csl =(ColorStateList)  resource.getColorStateList(R.color.tab_blue);break;
            case "Tab_purple":
                csl = (ColorStateList) resource.getColorStateList(R.color.tab_purple);break;
            case "Tab_pink":csl = (ColorStateList) resource.getColorStateList(R.color.tab_pink);break;
            default :csl=(ColorStateList) resource.getColorStateList(R.color.tab_green);
        }
    if(csl!=null)
        et_search.setTextColor(csl);
        this.show();
    }

}
