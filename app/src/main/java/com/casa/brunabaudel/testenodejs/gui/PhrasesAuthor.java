package com.casa.brunabaudel.testenodejs.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;

import com.casa.brunabaudel.testenodejs.R;
import com.casa.brunabaudel.testenodejs.model.AuthorParams;
import com.casa.brunabaudel.testenodejs.model.Phrase;
import com.casa.brunabaudel.testenodejs.rest.PhraseRest;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

import java.util.List;

/**
 * Created by brunabaudel on 12/04/16.
 */

@EActivity
public class PhrasesAuthor extends AppCompatActivity {

    @RestService
    protected PhraseRest phraseRest;

    private AdapterPhrase mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private int mScreenWidth = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrasecards);

        AuthorParams authorParams = (AuthorParams) getIntent().getSerializableExtra("NAME_AUTHOR");

        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        this.mScreenWidth = displaymetrics.widthPixels;


        mRecyclerView = (RecyclerView) findViewById(R.id.phrase_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        consult(authorParams);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int firstPos = mLayoutManager.findFirstVisibleItemPosition();
                    int lastPos = mLayoutManager.findLastVisibleItemPosition();

                    int positionXH = recyclerView.computeHorizontalScrollOffset();

                    int diferenceSize = positionXH - (mScreenWidth*firstPos);

                    Log.d("POSITION", positionXH + " - " + mScreenWidth + " * " + firstPos + " = " + diferenceSize);

                    int whenToScroll = mScreenWidth/3;

                    if (diferenceSize > whenToScroll) {
                        mRecyclerView.smoothScrollToPosition(lastPos);
                    } else {
                        mRecyclerView.smoothScrollToPosition(firstPos);
                    }

                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

        });

    }

    @Background
    protected void consult(AuthorParams author) {
        fillList(phraseRest.getPhrases(author), author.author);
    }

    @UiThread
    protected void fillList(List<Phrase> listPhrase, String author) {
        mAdapter = new AdapterPhrase(this, listPhrase, author);
        mRecyclerView.setAdapter(mAdapter);
    }
}

