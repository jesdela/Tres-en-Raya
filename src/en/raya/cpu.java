package en.raya;

import java.util.Random;

import android.util.Log;

public class cpu {
	public int calcular(String[] estado) {
		String[] ganar ={"circulo","equis"};
		for(int i=0;i<2;i++){
			if (((estado[0] == "blanco") && (estado[1] == ganar[i]) && (estado[2] == ganar[i]))
					|| ((estado[0] == "blanco") && (estado[4] == ganar[i]) && (estado[8] == ganar[i]))
					|| ((estado[0] == "blanco") && (estado[3] == ganar[i]) && (estado[6] == ganar[i]))) {
				return 0;
			} else {
				if (((estado[2] == "blanco") && (estado[1] == ganar[i]) && (estado[0] == ganar[i]))
						|| ((estado[2] == "blanco") && (estado[4] == ganar[i]) && (estado[6] == ganar[i]))
						|| ((estado[2] == "blanco") && (estado[5] == ganar[i]) && (estado[8] == ganar[i]))) {
					return 2;
				} else {
					if (((estado[6] == "blanco") && (estado[0] == ganar[i]) && (estado[3] == ganar[i]))
							|| ((estado[6] == "blanco") && (estado[4] == ganar[i]) && (estado[2] == ganar[i]))
							|| ((estado[6] == "blanco") && (estado[7] == ganar[i]) && (estado[8] == ganar[i]))) {
						return 6;
					} else {
						if (((estado[8] == "blanco") && (estado[0] == ganar[i]) && (estado[4] == ganar[i]))
								|| ((estado[8] == "blanco")
										&& (estado[5] == ganar[i]) && (estado[2] == ganar[i]))
								|| ((estado[8] == "blanco")
										&& (estado[7] == ganar[i]) && (estado[6] == ganar[i]))) {
							return 8;
						} else {
							if (((estado[4] == "blanco") && (estado[0] == ganar[i]) && (estado[8] == ganar[i]))
									|| ((estado[4] == "blanco")
											&& (estado[1] == ganar[i]) && (estado[7] == ganar[i]))
									|| ((estado[4] == "blanco")
											&& (estado[3] == ganar[i]) && (estado[5] == ganar[i]))
									|| ((estado[4] == "blanco")
											&& (estado[2] == ganar[i]) && (estado[6] == ganar[i]))) {
								return 4;
							} else {
								if (((estado[1] == "blanco") && (estado[0] == ganar[i]) && (estado[2] == ganar[i]))
										|| ((estado[1] == "blanco")
												&& (estado[4] == ganar[i]) && (estado[7] == ganar[i]))) {
									return 1;
								} else {
									if (((estado[3] == "blanco") && (estado[0] == ganar[i]) && (estado[6] == ganar[i]))
											|| ((estado[3] == "blanco")
													&& (estado[4] == ganar[i]) && (estado[5] == ganar[i]))) {
										return 3;
									} else {
										if (((estado[5] == "blanco") && (estado[8] == ganar[i]) && (estado[2] == ganar[i]))
												|| ((estado[5] == "blanco")
														&& (estado[4] == ganar[i]) && (estado[3] == ganar[i]))) {
											return 5;
										} else {
											if (((estado[7] == "blanco") && (estado[6] == ganar[i]) && (estado[8] == ganar[i]))
													|| ((estado[7] == "blanco")
															&& (estado[4] == ganar[i]) && (estado[1] == ganar[i]))) {
												return 7;
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
		return aleatorio(estado);
	}

	public int aleatorio(String[] estado) {
		int id;
		Random ale = new Random();
		do{
			id=ale.nextInt(9);
		}while (estado[id] != "blanco");
		Log.d("estado","estado["+id+"]: "+estado[id]);
		return id;
	}
}
