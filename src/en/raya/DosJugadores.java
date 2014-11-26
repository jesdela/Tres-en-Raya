package en.raya;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import en.raya.R;

public class DosJugadores extends Fragment {
	private View dos_jugadores;
	int cont = 0;
	String estado[] = new String[9];
	Boolean turno = true;
	RelativeLayout casilla[] = new RelativeLayout[9];

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		dos_jugadores = inflater.inflate(R.layout.fragment_tablero, container, false);
		LinearLayout contenedor = (LinearLayout) dos_jugadores.findViewById(R.id.ly);
		LinearLayout hor[] = new LinearLayout[4];
		int a = 0;
		for (int j = 1; j <= 3; j++) {
			hor[j] = new LinearLayout(getActivity());
			hor[j].setId(j);
			hor[j].setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT, 1));
			contenedor.addView(hor[j]);
			for (int i = 1; i < 4; i++) {
				casilla[a] = new RelativeLayout(getActivity());
				casilla[a].setBackgroundResource(R.drawable.blanco);
				casilla[a].setLayoutParams(new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.MATCH_PARENT,
						LinearLayout.LayoutParams.MATCH_PARENT, 1));
				casilla[a].setId(a);
				final int i2 = a;
				hor[j].addView(casilla[a]);
				estado[a] = "blanco";
				casilla[a].setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {

						cont++;
						if (turno) {
							estado[casilla[i2].getId()] = "equis";
							casilla[i2].setBackgroundResource(R.drawable.equis);
							casilla[i2].setEnabled(false);
						} else {
							estado[casilla[i2].getId()] = "circulo";
							casilla[i2]
									.setBackgroundResource(R.drawable.circulo);
							casilla[i2].setEnabled(false);
						}
						if(!comprobar(estado, casilla[i2].getId())){
							if (cont >= 9) {
								Toast toast3 = Toast.makeText(dos_jugadores.getContext(),
										getResources().getString(R.string.empate), Toast.LENGTH_LONG);
								toast3.setGravity(Gravity.BOTTOM, Gravity.CENTER_HORIZONTAL, Gravity.BOTTOM);
								toast3.show();
								FragmentManager fragmentManager = getFragmentManager();
		DialogoSeguirJugando seguirJugando = new DialogoSeguirJugando();
		seguirJugando.show(fragmentManager, "seguir");
							} else {
								if (turno) {
									turno = false;
								} else {
									turno = true;
								}
							}
						}
					}

				});
				a++;
			}
		}
		return dos_jugadores;
	}
	public boolean comprobar(String[] estado2, int id) {
		if (id == 0) {
			if (((estado2[id] == "equis") && (estado2[id + 1] == "equis") && (estado2[id + 2] == "equis"))
					|| ((estado2[id] == "equis")
							&& (estado2[id + 3] == "equis") && (estado2[id + 6] == "equis"))
					|| ((estado2[id] == "equis")
							&& (estado2[id + 4] == "equis") && (estado2[id + 8] == "equis"))) {
				ganado();
				return true;
			} else {
				if (((estado2[id] == "circulo")
						&& (estado2[id + 1] == "circulo") && (estado2[id + 2] == "circulo"))
						|| ((estado2[id] == "circulo")
								&& (estado2[id + 3] == "circulo") && (estado2[id + 6] == "circulo"))
						|| ((estado2[id] == "circulo")
								&& (estado2[id + 4] == "circulo") && (estado2[id + 8] == "circulo"))) {
					perdido();
					return true;
				} else {
					return false;
				}
			}
		} else {
			if (id == 1) {
				if (((estado2[id] == "equis") && (estado2[id + 1] == "equis") && (estado2[id - 1] == "equis"))
						|| ((estado2[id] == "equis")
								&& (estado2[id + 3] == "equis") && (estado2[id + 6] == "equis"))) {
					ganado();
					return true;
				} else {
					if (((estado2[id] == "circulo")
							&& (estado2[id + 1] == "circulo") && (estado2[id - 1] == "circulo"))
							|| ((estado2[id] == "circulo")
									&& (estado2[id + 3] == "circulo") && (estado2[id + 6] == "circulo"))) {
						perdido();
						return true;
					} else {
						return false;
					}
				}
			} else {
				if (id == 2) {
					if (((estado2[id] == "equis")
							&& (estado2[id - 1] == "equis") && (estado2[id - 2] == "equis"))
							|| ((estado2[id] == "equis")
									&& (estado2[id + 3] == "equis") && (estado2[id + 6] == "equis"))
							|| ((estado2[id] == "equis")
									&& (estado2[id + 2] == "equis") && (estado2[id + 4] == "equis"))) {
						ganado();
						return true;
					} else {
						if (((estado2[id] == "circulo")
								&& (estado2[id - 1] == "circulo") && (estado2[id - 2] == "circulo"))
								|| ((estado2[id] == "circulo")
										&& (estado2[id + 3] == "circulo") && (estado2[id + 6] == "circulo"))
								|| ((estado2[id] == "circulo")
										&& (estado2[id + 2] == "circulo") && (estado2[id + 4] == "circulo"))) {
							perdido();
							return true;
						} else {
							return false;
						}
					}
				} else {
					if (id == 3) {
						if (((estado2[id] == "equis")
								&& (estado2[id + 1] == "equis") && (estado2[id + 2] == "equis"))
								|| ((estado2[id] == "equis")
										&& (estado2[id - 3] == "equis") && (estado2[id + 3] == "equis"))) {
							ganado();
							return true;
						} else {
							if (((estado2[id] == "circulo")
									&& (estado2[id + 1] == "circulo") && (estado2[id + 2] == "circulo"))
									|| ((estado2[id] == "circulo")
											&& (estado2[id - 3] == "circulo") && (estado2[id + 3] == "circulo"))) {
								perdido();
								return true;
							} else {
								return false;
							}
						}
					} else {
						if (id == 4) {
							if (((estado2[id] == "equis")
									&& (estado2[id - 1] == "equis") && (estado2[id + 1] == "equis"))
									|| ((estado2[id] == "equis")
											&& (estado2[id - 3] == "equis") && (estado2[id + 3] == "equis"))
									|| ((estado2[id] == "equis")
											&& (estado2[id - 4] == "equis") && (estado2[id + 4] == "equis"))
									|| ((estado2[id] == "equis")
											&& (estado2[id - 2] == "equis") && (estado2[id + 2] == "equis"))) {
								ganado();
								return true;
							} else {
								if (((estado2[id] == "circulo")
										&& (estado2[id - 1] == "circulo") && (estado2[id + 1] == "circulo"))
										|| ((estado2[id] == "circulo")
												&& (estado2[id - 3] == "circulo") && (estado2[id + 3] == "circulo"))
										|| ((estado2[id] == "circulo")
												&& (estado2[id - 4] == "circulo") && (estado2[id + 4] == "circulo"))
										|| ((estado2[id] == "circulo")
												&& (estado2[id - 2] == "circulo") && (estado2[id + 2] == "circulo"))) {
									perdido();
									return true;
								} else {
									return false;
								}
							}
						} else {
							if (id == 5) {
								if (((estado2[id] == "equis")
										&& (estado2[id - 1] == "equis") && (estado2[id - 2] == "equis"))
										|| ((estado2[id] == "equis")
												&& (estado2[id - 3] == "equis") && (estado2[id + 3] == "equis"))) {
									ganado();
									return true;
								} else {
									if (((estado2[id] == "circulo")
											&& (estado2[id + 1] == "circulo") && (estado2[id - 2] == "circulo"))
											|| ((estado2[id] == "circulo")
													&& (estado2[id - 3] == "circulo") && (estado2[id + 3] == "circulo"))) {
										perdido();
										return true;
									} else {
										return false;
									}
								}
							} else {
								if (id == 6) {
									if (((estado2[id] == "equis")
											&& (estado2[id + 1] == "equis") && (estado2[id + 2] == "equis"))
											|| ((estado2[id] == "equis")
													&& (estado2[id - 3] == "equis") && (estado2[id - 6] == "equis"))
											|| ((estado2[id] == "equis")
													&& (estado2[id - 2] == "equis") && (estado2[id - 4] == "equis"))) {
										ganado();
										return true;
									} else {
										if (((estado2[id] == "circulo")
												&& (estado2[id + 1] == "circulo") && (estado2[id + 2] == "circulo"))
												|| ((estado2[id] == "circulo")
														&& (estado2[id - 3] == "circulo") && (estado2[id - 6] == "circulo"))
												|| ((estado2[id] == "circulo")
														&& (estado2[id - 2] == "circulo") && (estado2[id - 4] == "circulo"))) {
											perdido();
											return true;
										} else {
											return false;
										}
									}
								} else {
									if (id == 7) {
										if (((estado2[id] == "equis")
												&& (estado2[id + 1] == "equis") && (estado2[id - 1] == "equis"))
												|| ((estado2[id] == "equis")
														&& (estado2[id - 3] == "equis") && (estado2[id - 6] == "equis"))) {
											ganado();
											return true;
										} else {
											if (((estado2[id] == "circulo")
													&& (estado2[id + 1] == "circulo") && (estado2[id - 1] == "circulo"))
													|| ((estado2[id] == "circulo")
															&& (estado2[id - 3] == "circulo") && (estado2[id - 6] == "circulo"))) {
												perdido();
												return true;
											} else {
												return false;
											}
										}
									} else {
										if (id == 8) {
											if (((estado2[id] == "equis")
													&& (estado2[id - 1] == "equis") && (estado2[id - 2] == "equis"))
													|| ((estado2[id] == "equis")
															&& (estado2[id - 3] == "equis") && (estado2[id - 6] == "equis"))
													|| ((estado2[id] == "equis")
															&& (estado2[id - 4] == "equis") && (estado2[id - 8] == "equis"))) {
												ganado();
												return true;
											} else {
												if (((estado2[id] == "circulo")
														&& (estado2[id - 1] == "circulo") && (estado2[id - 2] == "circulo"))
														|| ((estado2[id] == "circulo")
																&& (estado2[id - 3] == "circulo") && (estado2[id - 6] == "circulo"))
														|| ((estado2[id] == "circulo")
																&& (estado2[id - 4] == "circulo") && (estado2[id - 8] == "circulo"))) {
													perdido();
													return true;
												} else {
													return false;
												}
											}
										} else {
											return false;
										}
									}
								}
							}
						}
					}
				}
			}
		}

	}
	public void ganado() {
		Toast ganado = Toast.makeText(dos_jugadores.getContext(),getResources().getString(R.string.j1),
				Toast.LENGTH_LONG);
		ganado.setGravity(Gravity.BOTTOM, Gravity.CENTER_HORIZONTAL, Gravity.BOTTOM);
		ganado.show();
		FragmentManager fragmentManager = getFragmentManager();
		DialogoSeguirJugando seguirJugando = new DialogoSeguirJugando();
		seguirJugando.show(fragmentManager, "seguir");
	}
	public void perdido() {
		Toast perdido = Toast.makeText(dos_jugadores.getContext(),
				getResources().getString(R.string.j2), Toast.LENGTH_LONG);
		perdido.setGravity(Gravity.BOTTOM, Gravity.CENTER_HORIZONTAL, Gravity.BOTTOM);
		perdido.show();
		FragmentManager fragmentManager = getFragmentManager();
		DialogoSeguirJugando seguirJugando = new DialogoSeguirJugando();
		seguirJugando.show(fragmentManager, "seguir");
	}
}
