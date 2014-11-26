package en.raya;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import en.raya.R;
import en.raya.MainActivity.PlaceholderFragment;

public class DialogoSeguirJugando extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(getResources().getString(R.string.seguir))
				.setCancelable(false)
				.setPositiveButton(
						getResources().getString(R.string.jug_de_nuevo),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								if (MainActivity.jug == 1) {
									getFragmentManager()
											.beginTransaction()
											.add(R.id.container,
													new UnJugador()).commit();
								} else {
									getFragmentManager()
											.beginTransaction()
											.add(R.id.container,
													new DosJugadores())
											.commit();

								}
								dialog.cancel();
							}
						})
				.setNegativeButton(getResources().getString(R.string.salir),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								getFragmentManager()
										.beginTransaction()
										.add(R.id.container,
												new PlaceholderFragment())
										.commit();
								dialog.cancel();
							}
						});

		return builder.create();
	}

}
