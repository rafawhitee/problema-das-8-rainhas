package model;

import java.util.ArrayList;
import java.util.List;

public abstract class MatrizUtil {

	public static List<Coordenada> getCoordenadasPreenchidas(Object[][] matriz, int lines, int cols) {
		List<Coordenada> coordenadas = new ArrayList<Coordenada>();
		for (int line = 0; line < lines; line++) {
			for (int col = 0; col < cols; col++) {
				if(matriz[line][col] != null){
					coordenadas.add(new Coordenada(line, col));
				}
			}
		}
		return coordenadas;
	}
	
	public static String imprimir(Object[][] matriz, int lines, int cols) {
		StringBuilder sb = new StringBuilder();
		for (int line = 0; line < lines; line++) {
			for (int col = 0; col < cols; col++) {
				sb.append("[" + (line + 1) + "][" + (col + 1) + "]: " + matriz[line][col]);
				sb.append("\n");
			}
		}
		return sb.toString();
	}

}