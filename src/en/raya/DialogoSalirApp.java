package en.raya;

import en.raya.R;
import en.raya.MainActivity.PlaceholderFragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class DialogoSalirApp extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(getResources().getString(R.string.salir_sec))
				.setPositiveButton(
						getResources().getString(R.string.pant_princ),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								getFragmentManager()
										.beginTransaction()
										.add(R.id.container,
												new PlaceholderFragment())
										.commit();
								dialog.cancel();
							}
						})
				.setNegativeButton(getResources().getString(R.string.cerrar),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								getActivity().finish();
							}
						});

		return builder.create();
	}

}