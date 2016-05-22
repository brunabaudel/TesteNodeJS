package com.casa.brunabaudel.testenodejs;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.casa.brunabaudel.testenodejs.gui.ListAuthors.StickyTestAdapter;
import com.casa.brunabaudel.testenodejs.gui.OldListAuthors.OAdapterAuthors;
import com.casa.brunabaudel.testenodejs.model.Author;
import com.casa.brunabaudel.testenodejs.rest.PhraseRest;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

import java.util.ArrayList;
import java.util.List;

import ca.barrenechea.widget.recyclerview.decoration.DividerDecoration;
import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;

@EActivity
public class MainActivity extends AppCompatActivity /*implements RecyclerView.OnItemTouchListener */ {

    private RecyclerView mList;
    @RestService
    protected PhraseRest phraseRest;

    private DividerDecoration divider;
    private StickyHeaderDecoration decor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        mList = (RecyclerView) findViewById(R.id.author_recyclerview);

        divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.default_divider_height)
                .setPadding(R.dimen.default_divider_padding)
                .setColorResource(R.color.default_header_color)
                .build();

        consult();

    }

    @Background
    protected void consult() {
       // List<Author> listAuthor = phraseRest.getAuthors();
        List<Author> listAuthor = new ArrayList<>();

        Author a = new Author();
        a.setAuthor("Albert");

        Author a_02 = new Author();
        a_02.setAuthor("Alfredo");

        Author a_03 = new Author();
        a_03.setAuthor("Ana Maria");

        Author a_04 = new Author();
        a_04.setAuthor("Alfajó");

        Author a1 = new Author();
        a1.setAuthor("Bruna");

        Author a1_01 = new Author();
        a1_01.setAuthor("Bruno");

        Author a1_02 = new Author();
        a1_02.setAuthor("Breno");

        Author a1_03 = new Author();
        a1_03.setAuthor("Bárbara");

        Author a2 = new Author();
        a2.setAuthor("Clarice");

        Author a3 = new Author();
        a3.setAuthor("Fernando");

        Author a4 = new Author();
        a4.setAuthor("Lais");

        Author a5 = new Author();
        a5.setAuthor("Mozart");

        Author a6 = new Author();
        a6.setAuthor("Natalia");

        Author a7 = new Author();
        a7.setAuthor("Paula");

        Author a8 = new Author();
        a8.setAuthor("Rafael");

        listAuthor.add(a);
        listAuthor.add(a_02);
        listAuthor.add(a_03);
        listAuthor.add(a_04);
        listAuthor.add(a1);
        listAuthor.add(a1_01);
        listAuthor.add(a1_02);
        listAuthor.add(a1_03);
        listAuthor.add(a2);
        listAuthor.add(a3);
        listAuthor.add(a4);
        listAuthor.add(a5);
        listAuthor.add(a6);
        listAuthor.add(a7);
        listAuthor.add(a8);



        fillList(listAuthor);
    }

    @UiThread
    protected void fillList(List<Author> listAuthor) {

        mList.setHasFixedSize(true);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.addItemDecoration(divider);

        setAdapterAndDecor(mList, listAuthor);
    }
    protected void setAdapterAndDecor(RecyclerView list, List<Author> listAuthor) {

        Log.d("DEBUG","TAMANHO LISTA: " + listAuthor.size()+" " + listAuthor.get(0).getAuthor());

        final StickyTestAdapter adapter = new StickyTestAdapter(this, listAuthor);
        decor = new StickyHeaderDecoration(adapter);

        list.setAdapter(adapter);
        list.addItemDecoration(decor, 1);
       // list.addOnItemTouchListener(this);
    }

/*
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        // really bad click detection just for demonstration purposes
        // it will not allow the list to scroll if the swipe motion starts
        // on top of a header
        View v = rv.findChildViewUnder(e.getX(), e.getY());
        return v == null;
//        return rv.findChildViewUnder(e.getX(), e.getY()) != null;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        // only use the "UP" motion event, discard all others
        if (e.getAction() != MotionEvent.ACTION_UP) {
            return;
        }

        // find the header that was clicked
        View view = decor.findHeaderViewUnder(e.getX(), e.getY());

        if (view instanceof TextView) {
            Toast.makeText(this, ((TextView) view).getText() + " clicked", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        // do nothing

    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_search:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);


    }
}
