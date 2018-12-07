package imd.ufrn.br.patinsmania.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.app.DialogFragment;
import android.content.DialogInterface;

import imd.ufrn.br.patinsmania.data.Patins;

public class DeleteDialog extends DialogFragment implements DialogInterface.OnClickListener {
    private OnDeleteListener listener;
    private Patins p;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (!(activity instanceof OnDeleteListener)) {
            throw new RuntimeException("A activity deve implementar DialogFragment.OnDeleteListener");
        }

        this.listener = (OnDeleteListener) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Deseja excluir o patins " + p.getModel() + " - " + p.getBrand() + "?");
        builder.setPositiveButton("Sim", this);
        builder.setNegativeButton("Não", this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE && listener != null) {
            listener.onDelete(p);
        }
    }

    public void setPatins(Patins p) {
        this.p = p;
    }

    public interface OnDeleteListener {
        public void onDelete(Patins p);
    }
}
