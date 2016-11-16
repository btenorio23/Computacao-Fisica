package safetyfloat.sf_safetyfloat;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {

    RelativeLayout mainContentLayout;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Adiciona novo Layout no main_content
        mainContentLayout = (RelativeLayout) findViewById(R.id.content_main);
        loadMonitores();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.monitores) {
            loadMonitores();
        } else if (id == R.id.leituras) {
            loadLeituras();
        } else if (id == R.id.alertas) {
            loadAlertas();
        } else if (id == R.id.configuracoes) {
            loadConfiguracoes();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loadGraph(DataPoint[] points) {
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4.8, 6)
        });
        graph.addSeries(series);
    }

    public void loadSpinner(String[] floats) {
        floats = new String[]{"boia1", "boia2", "boia3"};
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, floats);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String boiaSelecionada = (String) parent.getItemAtPosition(pos);
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, boiaSelecionada, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void loadContentLayout(int layout, int id) {
        mainContentLayout.removeAllViews();
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentLayout = layoutInflater.inflate(layout, (ViewGroup) findViewById(id));
        mainContentLayout.addView(contentLayout);
    }

    public void loadMonitores() {
        loadContentLayout(R.layout.monitores, R.id.monitores);
        loadGraph(null);//carrega o grafico com um Array de DataPoint
        loadSpinner(null);//carrega o spinner com um array de String
    }

    public void loadLeituras() {
        loadContentLayout(R.layout.leituras, R.id.leituras);
        String[][] testes = new String[3][3];
        testes[0] = new String[]{"1", "2", "3"};
        testes[1] = new String[]{"4", "5", "6"};
        testes[2] = new String[]{"7", "8", "9"};
        loadLeiturasTable(testes);
        //loadTable();
    }

    public void loadAlertas() {
        loadContentLayout(R.layout.alertas, R.id.alertas);
    }

    public void loadConfiguracoes() {
        loadContentLayout(R.layout.configuracoes, R.id.configuracoes);
    }

    public void loadTable(){
        TableLayout t1;

        TableLayout tl = (TableLayout) findViewById(R.id.tb_leituras);
        TableRow tr_head = new TableRow(this);
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        Integer count=0;
            String date = "data";
            Double weight_kg = 20.0;
// Create the table row
            TableRow tr = new TableRow(this);
            if(count%2!=0) tr.setBackgroundColor(Color.GRAY);
            tr.setId(100+count);
            tr.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

//Create two columns to add as table data
            // Create a TextView to add date
            TextView labelDATE = new TextView(this);
            labelDATE.setId(200+count);
            labelDATE.setText(date);
            labelDATE.setPadding(2, 0, 5, 0);
            labelDATE.setTextColor(Color.WHITE);
            tr.addView(labelDATE);
            TextView labelWEIGHT = new TextView(this);
            labelWEIGHT.setId(200+count);
            labelWEIGHT.setText(weight_kg.toString());
            labelWEIGHT.setTextColor(Color.WHITE);
            tr.addView(labelWEIGHT);

// finally add this to the table row
            tl.addView(tr, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            count++;
    }

    public void loadLeiturasTable(String[][] content) {
        TableLayout tl = (TableLayout) findViewById(R.id.tb_leituras);
        //Carrega valores dos resources
        int padding = (int) getResources().getDimension(R.dimen.tb_leituras_padding);
        int horarioWidth = (int) getResources().getDimension(R.dimen.tb_header_horario_width);
        int medicaoWidth = (int) getResources().getDimension(R.dimen.tb_header_medicao_width);
        int alertaWidth = (int) getResources().getDimension(R.dimen.tb_header_alerta_width);
        int marginRight = (int) getResources().getDimension(R.dimen.tb_cells_margin_right);
        int marginBottom = (int) getResources().getDimension(R.dimen.tb_cells_margin_bottom);
        Drawable backgroudShape = getResources().getDrawable(R.drawable.cell_shape);
        //Cria layout a ser utilizado pelas celulas da tabela
        ViewGroup.MarginLayoutParams layout = new ViewGroup.MarginLayoutParams(horarioWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.setMargins(0, 0, marginRight, marginBottom);

        for (int i = 0; i < content.length; i++) {
            TableRow tr = new TableRow(getApplicationContext());
            tr.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            //Cria tres colunas (horario, medicao e alerta) e envia para a linha da tabela
            //Cria coluna horario
            TextView horario = new TextView(this);
            horario.setLayoutParams(layout);
            horario.setText("10");
            horario.setTextColor(Color.BLACK);
            horario.setPadding(padding, padding, padding, padding);
            horario.setBackground(backgroudShape);
            tr.addView(horario);

            //Cria coluna horario
            TextView medicao = new TextView(this);
            medicao.setLayoutParams(layout);
            medicao.setWidth(medicaoWidth);
            medicao.setText("20");
            medicao.setTextColor(Color.BLACK);
            medicao.setPadding(padding, padding, padding, padding);
            medicao.setBackground(backgroudShape);
            tr.addView(medicao);

            //Cria coluna horario
            TextView alerta = new TextView(this);
            alerta.setLayoutParams(layout);
            alerta.setWidth(alertaWidth);
            alerta.setText("30");
            alerta.setTextColor(Color.BLACK);
            alerta.setPadding(padding, padding, padding, padding);
            alerta.setBackground(backgroudShape);
            tr.addView(alerta);
            tl.addView(tr, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
