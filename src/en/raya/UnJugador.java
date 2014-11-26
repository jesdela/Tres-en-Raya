package en.raya;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import en.raya.R;

public class UnJugador extends Fragment {
	int cont = 0;
	String estado[] = new String[9];
	private View un_jugador;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		un_jugador = inflater.inflate(R.layout.fragment_tablero, container,
				false);
		LinearLayout contenedor = (LinearLayout) un_jugador
				.findViewById(R.id.ly);
		LinearLayout hor[] = new LinearLayout[4];
		final RelativeLayout casilla[] = new RelativeLayout[9];
		int a = 0;
		for (int j = 1; j <= 3; j++) {
			hor[j] = new LinearLayout(un_jugador.getContext());
			hor[j].setId(j);
			hor[j].setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT, 1));
			contenedor.addView(hor[j]);
			for (int i = 1; i < 4; i++) {
				casilla[a] = new RelativeLayout(un_jugador.getContext());
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
						casilla[i2].setBackgroundResource(R.drawable.equis);
						cont++;
						estado[casilla[i2].getId()] = "equis";
						casilla[i2].setEnabled(false);
						if (!comprobar(estado, casilla[i2].getId())) {
							if (cont < 5) {
								ord(cont, estado, casilla);
							} else {
								Toast toast3 = Toast.makeText(un_jugador
										.getContext(), getResources()
										.getString(R.string.empate),
										Toast.LENGTH_LONG);
								toast3.setGravity(Gravity.BOTTOM,
										Gravity.CENTER_HORIZONTAL, Gravity.BOTTOM);
								toast3.show();
								FragmentManager fragmentManager = getFragmentManager();
								DialogoSeguirJugando seguirJugando = new DialogoSeguirJugando();
								seguirJugando.show(fragmentManager, "seguir");
							}
						}
					}

				});
				a++;
			}
		}
		return un_jugador;
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

	public void ord(int cont, String[] estado2, RelativeLayout[] casilla) {
		cpu id = new cpu();
		int ide = id.calcular(estado);
		casilla[ide].setBackgroundResource(R.drawable.circulo);
		estado[ide] = "circulo";
		casilla[ide].setEnabled(false);
		cont++;
		if (!comprobar(estado, casilla[ide].getId())) {
		}
	}

	public void ganado() {
		Toast ganado = Toast.makeText(un_jugador.getContext(), getResources()
				.getString(R.string.ganado), Toast.LENGTH_LONG);
		ganado.setGravity(Gravity.BOTTOM, Gravity.CENTER_HORIZONTAL, Gravity.BOTTOM);
		ganado.show();
		FragmentManager fragmentManager = getFragmentManager();
		DialogoSeguirJugando seguirJugando = new DialogoSeguirJugando();
		seguirJugando.show(fragmentManager, "seguir");
	}

	public void perdido() {
		Toast perdido = Toast.makeText(un_jugador.getContext(), getResources()
				.getString(R.string.perdido), Toast.LENGTH_LONG);
		perdido.setGravity(Gravity.BOTTOM, Gravity.CENTER_HORIZONTAL, Gravity.BOTTOM);
		perdido.show();
		FragmentManager fragmentManager = getFragmentManager();
		DialogoSeguirJugando seguirJugando = new DialogoSeguirJugando();
		seguirJugando.show(fragmentManager, "seguir");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == android.R.id.home) {
			// showDialog(1);
		}
		return super.onOptionsItemSelected(item);
	}

}
