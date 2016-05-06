package lab2apprun.gr2.compumovil.udea.edu.co.lab2apprun;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NavigationDrawer extends AppCompatActivity {


    private CharSequence tituloSec;
    private CharSequence tituloApp;

    private String[] options;
    private DrawerLayout mDrawerLayout;
    private ListView mListView;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView carreras;
    private ArrayList listaCarreras;
    private DBD data;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        options = new String[] {"opción menú 1","opción menú 2","opción menú 3","opción menú 4"};

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        mListView = (ListView)findViewById(R.id.left_drawer);


        mListView.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, options));

        //Listado de las carreras
        data = new DBD(this);
        listaCarreras = data.consultar();
        carreras = (ListView)findViewById(R.id.carreras);
        if(listaCarreras.size() != 0) {
            carreras.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                    android.R.layout.simple_list_item_1, listaCarreras));
        }

        carreras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NavigationDrawer.this, DetalleCarrera.class);
                startActivity(intent);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = null;

                switch (position) {
                    case 0:
                        intent = new Intent(NavigationDrawer.this, LoggIN.class);
                        break;
                    case 1:
                        intent = new Intent(NavigationDrawer.this, AdicionUser.class);
                        break;
                    case 2:
                        //fragment = new fragment3();
                        break;
                    case 3:
                        //fragment = new fragment4();
                        break;
                }
                startActivity(intent);
                mListView.setItemChecked(position, true);
                tituloSec = options[position];
                getSupportActionBar().setTitle(tituloSec);
                mDrawerLayout.closeDrawer(mListView);
            }
        });

        tituloSec=getTitle();
        tituloApp=getTitle();

        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(tituloSec);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(tituloApp);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }
}
