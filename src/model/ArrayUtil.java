package model;

import java.util.ArrayList;
import java.util.List;

public abstract class ArrayUtil {

	/* VETOR */
	public static Object[] copiarComTamanhoExato(Object[] vetor) {
		List<Object> preenchidos = preenchidos(vetor);
		return preenchidos.toArray();
	}

	public static int totalPreenchidos(Object[] vetor) {
		return preenchidos(vetor).size();
	}

	public static List<Object> preenchidos(Object[] vetor) {
		List<Object> list = new ArrayList<Object>();
		for (int line = 0; line < vetor.length; line++) {
			if (vetor[line] != null) {
				list.add(vetor[line]);
			}
		}
		return list;
	}

	public static String imprimir(Object[] vetor) {
		StringBuilder sb = new StringBuilder();
		for (int line = 0; line < vetor.length; line++) {
			sb.append("[" + line + "]: " + vetor[line]);
			sb.append("\n");
		}
		return sb.toString();
	}

	public static Object[] merge(Object[] array1, Object[] array2) {
		Object[] retorno = new Object[array1.length + array2.length];
		int lastIndexInserted = 0;
		for (int i = 0; i < array1.length; i++) {
			retorno[lastIndexInserted] = array1[i];
			lastIndexInserted++;
		}
		for (int j = 0; j < array2.length; j++) {
			retorno[lastIndexInserted] = array2[j];
			lastIndexInserted++;
		}
		return retorno;
	}

}