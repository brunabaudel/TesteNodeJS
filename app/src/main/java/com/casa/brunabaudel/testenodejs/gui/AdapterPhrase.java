package com.casa.brunabaudel.testenodejs.gui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.casa.brunabaudel.testenodejs.R;
import com.casa.brunabaudel.testenodejs.model.Phrase;

import java.util.Collections;
import java.util.List;

public class AdapterPhrase extends RecyclerView.Adapter<AdapterPhrase.PhraseViewHolder> {

    private final LayoutInflater inflater;
    private List<Phrase> phraseList = Collections.EMPTY_LIST;
    private Context context;
    private String author;

    private final static int FADE_DURATION = 1000; // in milliseconds

    public AdapterPhrase(Context context, List<Phrase> phraseList, String author) {
        inflater = LayoutInflater.from(context);
        this.phraseList = phraseList;
        this.context = context;
        this.author = author;
    }

    @Override
    public PhraseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cardview_phrase, parent, false);
        PhraseViewHolder phraseViewHolder = new PhraseViewHolder(view);

        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels-200;
        view.setLayoutParams(new RecyclerView.LayoutParams(width, height));

        return phraseViewHolder;
    }

    @Override
    public void onBindViewHolder(PhraseViewHolder holder, int position) {
        Phrase currentCard = phraseList.get(position);
        holder.phrase.setText(currentCard.getId() + " - " + currentCard.getPhrase());
        holder.author.setText(author);
        //setScaleAnimation(holder.itemView);

    }

    @Override
    public int getItemCount() {
        return phraseList.size();
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    class PhraseViewHolder extends RecyclerView.ViewHolder {

        TextView phrase;
        TextView author;

        public PhraseViewHolder(View itemView) {
            super(itemView);

            phrase = (TextView) itemView.findViewById(R.id.text_phrase);
            author = (TextView) itemView.findViewById(R.id.text_author);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}