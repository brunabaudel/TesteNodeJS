package com.casa.brunabaudel.testenodejs.gui.OldListAuthors;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.casa.brunabaudel.testenodejs.R;
import com.casa.brunabaudel.testenodejs.model.Author;

import java.util.List;

/**
 * Created by brunabaudel on 02/05/16.
 */
public class OAdapterListAuthors extends BaseAdapter {

    private Activity mContext;
    private List<Author> mList;
    private LayoutInflater mLayoutInflater = null;

    public OAdapterListAuthors(Activity context, List<Author> list) {
        mContext = context;
        mList = list;
        Log.d("DEBUG 2", mList.size() + "");
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ListAuthorsViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = li.inflate(R.layout.list_authors_item, null);

            viewHolder = new ListAuthorsViewHolder(v);

            v.setTag(viewHolder);
        } else {
            viewHolder = (ListAuthorsViewHolder) v.getTag();
        }

        String nameAuthor = mList.get(position).getAuthor();
        viewHolder.mTxtImg.setText(nameAuthor.charAt(0)+"");

        viewHolder.mTxtNameAuthor.setText(nameAuthor);

        Log.d("DEBUG 2","GETVIEW CALLED - " + nameAuthor);

        return v;
    }

    class ListAuthorsViewHolder {
        public TextView mTxtImg;
        public TextView mTxtNameAuthor;

        public ListAuthorsViewHolder(View base) {
            mTxtImg = (TextView) base.findViewById(R.id.img_author);
            mTxtNameAuthor = (TextView) base.findViewById(R.id.name_author);
        }
    }
}
