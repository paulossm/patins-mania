package imd.ufrn.br.patinsmania;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import imd.ufrn.br.patinsmania.data.Patins;
import imd.ufrn.br.patinsmania.data.PatinsDAO;

public class EditPatinsActivity extends AppCompatActivity {

    private PatinsDAO patinsDAO;
    private EditText edtBrand;
    private EditText edtModel;
    private EditText edtSize;
    private Patins p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patins);

        edtBrand = findViewById(R.id.edt_brand);
        edtModel = findViewById(R.id.edt_model);
        edtSize = findViewById(R.id.edt_size);

        patinsDAO = PatinsDAO.getInstance(this);
        p = (Patins) getIntent().getSerializableExtra("patins");

        if(p != null) {
            getSupportActionBar().setTitle("Editar Patins (" + p.getId() + ")");
            edtBrand.setText(p.getBrand());
            edtModel.setText(p.getModel());
            edtSize.setText(String.valueOf(p.getSize()));
        } else {
            getSupportActionBar().setTitle("Cadastrar Patins");
        }
    }

    public void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void submit(View view) {
        String brand = edtBrand.getText().toString();
        String model = edtModel.getText().toString();
        int size = Integer.parseInt(edtSize.getText().toString());

        String message;
        if(p == null) {
            Patins p = new Patins(brand, model, size);
            patinsDAO.save(p);
            message = "Patins Cadastrado. Código ID = " + p.getId();
        } else {
            p.setBrand(brand);
            p.setModel(model);
            p.setSize(size);
            patinsDAO.update(p);
            message = "Informações do Patins atualizadas com sucesso.";
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }
}
