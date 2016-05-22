/*
 * Copyright 2014 Eduardo Barrenechea
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.casa.brunabaudel.testenodejs.gui.ListAuthors;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.casa.brunabaudel.testenodejs.ItemStickHeader;
import com.casa.brunabaudel.testenodejs.R;
import com.casa.brunabaudel.testenodejs.model.Author;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;

public class StickyTestAdapter extends RecyclerView.Adapter<StickyTestAdapter.ViewHolder> implements
        StickyHeaderAdapter<StickyTestAdapter.HeaderHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Author> mListAuthor;
    private List<ItemStickHeader<Character, Author>> mItems;
    private HashMap<Character, Character> mapCharacters;

    public StickyTestAdapter(Context context, List<Author> listAuthor) {
        mInflater = LayoutInflater.from(context);
        mListAuthor = listAuthor;
        mContext = context;
        mapCharacters = new HashMap<>();

        createItems();
    }

    private void createItems(){
        mItems = new ArrayList<>();
        char firstLetter;
        for (Author author : mListAuthor) {
            firstLetter = author.getAuthor().toUpperCase().charAt(0);
            if(!mapCharacters.containsKey(firstLetter)){
                mapCharacters.put(firstLetter, firstLetter);
            }
            ItemStickHeader<Character, Author> item = new ItemStickHeader<>(mapCharacters.get(firstLetter), author);
            mItems.add(item);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View view = mInflater.inflate(R.layout.item_test, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.item.setText(mItems.get(i).getItem().getAuthor());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public long getHeaderId(int position) {
        Log.d("DEBUG", position+"");
        return (long) mItems.get(position).getHeader().charValue();
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        final View view = mInflater.inflate(R.layout.header_test, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewholder, int position) {
        viewholder.header.setBackgroundResource(R.color.header);
        viewholder.header.setTextColor(Color.BLACK);
        viewholder.header.setTypeface(null, Typeface.BOLD);
        String header = mItems.get(position).getHeader().toString();
        viewholder.header.setText(header);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView item;

        public ViewHolder(View itemView) {
            super(itemView);

            item = (TextView) itemView;
        }
    }

    static class HeaderHolder extends RecyclerView.ViewHolder {
        public TextView header;

        public HeaderHolder(View itemView) {
            super(itemView);

            header = (TextView) itemView;
        }
    }
}