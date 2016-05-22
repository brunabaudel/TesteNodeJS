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
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.casa.brunabaudel.testenodejs.R;
import com.casa.brunabaudel.testenodejs.model.Author;
import com.casa.brunabaudel.testenodejs.model.LetterHeader;

import java.util.ArrayList;
import java.util.List;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;

public class StickyTestAdapter extends RecyclerView.Adapter<StickyTestAdapter.ViewHolder> implements
        StickyHeaderAdapter<StickyTestAdapter.HeaderHolder> {

    private int SIZE_LETTER = 26;
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Author> mListAuthor;
    private LetterHeader[] mListLetter;

    public StickyTestAdapter(Context context, List<Author> listAuthor) {
        mInflater = LayoutInflater.from(context);
        mListAuthor = listAuthor;
        mContext = context;
        mListLetter = new LetterHeader[SIZE_LETTER];

        createListLetter();

    }

    private void createListLetter() {
        String letter = "";
        String[] aLetter = mContext.getResources().getStringArray(R.array.letters);
        int count = 0;
        int countLetter = 0;

        mListLetter[0] = new LetterHeader(aLetter[0], 0);

        for (int i = 1; i < mListLetter.length; i++) {
            mListLetter[i] = new LetterHeader(aLetter[i], i);
            //Log.d("DEBUG", mListLetter[i].getLetter() +" "+ mListLetter[i].getPosition() + " " + i);
        }

        for (int i = 1; i < aLetter.length; i++) {
            count = 0;

            for (Author author: mListAuthor) {
                letter = author.getAuthor().toUpperCase().charAt(0)+"";
                count++;
               // Log.d("DEBUG", " " + countLetter);

                if(aLetter[i].equals(letter)){
                    countLetter++;
                    //mListLetter[countLetter] = new LetterHeader(letter, count);
                    mListLetter[countLetter].setLetter(letter);
                    mListLetter[countLetter].setPosition(count);
                    Log.d("DEBUG", mListLetter[countLetter].getLetter() +" "+ mListLetter[countLetter].getPosition() + " " + countLetter);
                    break;
                } /*else if (count > 0 && !letter.equals(aLetter[i])) {
                    break;
                }*/
            }

        }

        //Log.d("DEBUG", mListLetter[0].getLetter() +" "+ mListLetter[0].getPosition());
        //Log.d("DEBUG", mListLetter[1].getLetter() +" "+ mListLetter[1].getPosition());
/*
        for (int i = 0; i < mListLetter.length-1 ; i++) {
            Log.d("DEBUG", mListLetter[i].getLetter() +" "+ mListLetter[i].getPosition());
        }
*/

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View view = mInflater.inflate(R.layout.item_test, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.item.setText(mListAuthor.get(i).getAuthor());
    }

    @Override
    public int getItemCount() {
        return mListAuthor.size();
    }

    @Override
    public long getHeaderId(int position) {
        Log.d("DEBUG", position+"");
        return (long) mListLetter[position].getPosition();
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
        String header = mListLetter[position].getLetter() + " " /* + getHeaderId(position)*/;
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