package imd.ufrn.br.patinsmania;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.List;

import imd.ufrn.br.patinsmania.adapter.PatinsAdapter;
import imd.ufrn.br.patinsmania.data.Patins;
import imd.ufrn.br.patinsmania.data.PatinsDAO;
import imd.ufrn.br.patinsmania.dialog.DeleteDialog;

public class AdministrativeActivity extends AppCompatActivity implements DeleteDialog.OnDeleteListener  {
    private static final int REQ_EDIT = 100;

    private PatinsDAO patinsDAO;
    private PatinsAdapter adapter;
    private Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrative);

        listView = (ListView) findViewById(R.id.my_list);
        adapter = new PatinsAdapter(getApplicationContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), EditPatinsActivity.class);
                intent.putExtra("patins", adapter.getItem(position));
                startActivityForResult(intent, REQ_EDIT);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Patins p = adapter.getItem(position);
                DeleteDialog dialog = new DeleteDialog();
                dialog.setPatins(p);
                dialog.show(getFragmentManager(), "deleteDialog");
                return true;
            }
        });
        patinsDAO = PatinsDAO.getInstance(this);
        updateList();
    }

    private void updateList() {
        List<Patins> patins = patinsDAO.list();
        adapter.setItems(patins);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_listpatins, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_add) {
            Intent intent = new Intent(getApplicationContext(), EditPatinsActivity.class);
            startActivityForResult(intent, REQ_EDIT);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQ_EDIT && resultCode == RESULT_OK) {
            updateList();
        }
    }

    @Override
    public void onDelete(Patins p) {
        patinsDAO.delete(p);
        updateList();
    }
}