package com.casa.brunabaudel.testenodejs.gui.OldListAuthors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.casa.brunabaudel.testenodejs.R;
import com.casa.brunabaudel.testenodejs.gui.PhrasesAuthor_;
import com.casa.brunabaudel.testenodejs.model.Author;
import com.casa.brunabaudel.testenodejs.model.AuthorParams;

import java.util.List;

/**
 * Created by brunabaudel on 29/04/16.
 */
public class OAdapterAuthor extends BaseAdapter {

    private Activity mContext;
    private List<Author> mList;
    private LayoutInflater mLayoutInflater = null;
    private String[] arrayLetters;

    public OAdapterAuthor(Activity context, List<Author> list) {
        mContext = context;
        mList = list;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arrayLetters = mContext.getResources().getStringArray(R.array.letters);
    }
        @Override
    public int getCount() {
        return arrayLetters.length;
    }

    @Override
    public Object getItem(int position) {
        return arrayLetters[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        AuthorViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = li.inflate(R.layout.item_author, null);

            viewHolder = new AuthorViewHolder(v);

            v.setTag(viewHolder);
        } else {
            viewHolder = (AuthorViewHolder) v.getTag();
        }

        String curLetter = arrayLetters[position];

        viewHolder.mTxtLetter.setText(curLetter);

        for (Author author: mList) {
            String nameAuthor = author.getAuthor();

            String fisrtLetter = nameAuthor.toUpperCase().charAt(0)+"";
            Log.d("DEBUG", curLetter + " - " + fisrtLetter);
            //if(fisrtLetter.equals(curLetter)){
               // viewHolder.linearListAuthor.addView(createLayoutItemsAuthor(author));
            //}
        }

        return v;
    }

    private LinearLayout createLayoutItemsAuthor(final Author author){
        LayoutParams lpViewW = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        LayoutParams lpViewMM = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        LayoutParams lpViewM = new LayoutParams(130, 130);

        final String nameAuthor = author.getAuthor();

        LinearLayout linearItem = new LinearLayout(mContext);
        linearItem.setLayoutParams(lpViewMM);
        linearItem.setOrientation(LinearLayout.HORIZONTAL);
        linearItem.setPadding(20, 20, 20, 20);
        linearItem.setClickable(true);
        linearItem.setFocusable(true);

        //linearItem.setBackgroundResource(android.R.drawable.list_selector_background);
        //linearItem.setBackgroundResource(android.R.drawable.btn_default);

        int[] attrs = new int[]{R.attr.selectableItemBackground};
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs);
        int backgroundResource = typedArray.getResourceId(0, 0);
        linearItem.setBackgroundResource(backgroundResource);
        typedArray.recycle();

        linearItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AuthorParams authorParams = new AuthorParams(nameAuthor);

                Intent intent = new Intent(mContext, PhrasesAuthor_.class);
                intent.putExtra("NAME_AUTHOR", authorParams);

                mContext.startActivity(intent);
            }
        });


        TextView img = new TextView(mContext);
        img.setBackgroundResource(R.drawable.shape);
        img.setLayoutParams(lpViewM);
        img.setPadding(20, 20, 20, 20);
        img.setTextSize(24);
        img.setText(nameAuthor.charAt(0) + "");
        img.setGravity(Gravity.CENTER);
        img.setTextColor(Color.WHITE);
        linearItem.addView(img);

        TextView tv = new TextView(mContext);
        tv.setText(nameAuthor);
        tv.setLayoutParams(lpViewW);
        tv.setPadding(30, 20, 20, 20);
        tv.setTextSize(18);
        linearItem.addView(tv);

        return linearItem;

    }

    class AuthorViewHolder {
        public TextView mTxtLetter;
        public ListView mListAuthor;

        public AuthorViewHolder(View base) {
            mTxtLetter = (TextView) base.findViewById(R.id.letter);
            mListAuthor = (ListView) base.findViewById(R.id.list_author);
        }
    }
}
