package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Tabuleiro extends Matriz {

	public static final int DEFAULT_LINES = 8;
	public static final int DEFAULT_COLS = 8;

	public Tabuleiro() {
		this(DEFAULT_LINES, DEFAULT_COLS);
	}

	public Tabuleiro(int lines, int cols) {
		super(lines, cols);
	}

	public void inserir(Peca peca) {
		inserir(peca, getProximaCoordenada());
	}

	public void inserir(Peca peca, Coordenada coordenada) {
		if (peca != null && isValid(coordenada)) {
			insert(peca, coordenada);
		}
	}

	private Coordenada getProximaCoordenada() {
		List<Coordenada> coordenadasPossiveis = getPossiveisCoordenadas();
		if ((coordenadasPossiveis != null && !coordenadasPossiveis.isEmpty())) {
			int index = (coordenadasPossiveis.size() > 1)
					? ThreadLocalRandom.current().nextInt(0, coordenadasPossiveis.size() - 1)
					: 0;
			return coordenadasPossiveis.get(index);
		}
		return null;
	}

	private List<Coordenada> getPossiveisCoordenadas() {
		List<Coordenada> retorno = new ArrayList<Coordenada>();
		for (int line = 0; line < getLines(); line++) {
			for (int col = 0; col < getCols(); col++) {
				Coordenada coordenada = new Coordenada(line, col);
				boolean posicaoPreenchida = get(coordenada) != null;
				if (!posicaoPreenchida) {
					boolean atacaOuAtacado = atacaOuAtacado(coordenada);
					if (!atacaOuAtacado) {
						retorno.add(coordenada);
					}
				}
			}
		}
		return retorno;
	}

	private boolean atacaOuAtacado(Coordenada coordenada) {
		if (isValid(coordenada)) {
			Object[] mesmaLinha = getLine(coordenada.getLine());
			int totalNaMesmaLinha = ArrayUtil.totalPreenchidos(mesmaLinha);
			if (totalNaMesmaLinha > 0) {
				return true;
			}

			Object[] mesmaColuna = getCol(coordenada.getColumn());
			int totalNaMesmaColuna = ArrayUtil.totalPreenchidos(mesmaColuna);
			if (totalNaMesmaColuna > 0) {
				return true;
			}

			Object[] diagonalPrincipal = getDiagonalPrincipal(coordenada);
			int totalNaDiagonalPrincipal = ArrayUtil.totalPreenchidos(diagonalPrincipal);
			if (totalNaDiagonalPrincipal > 0) {
				return true;
			}

			Object[] diagonalSecundaria = getDiagonalSecundaria(coordenada);
			int totalNaDiagonalSecundaria = ArrayUtil.totalPreenchidos(diagonalSecundaria);
			if (totalNaDiagonalSecundaria > 0) {
				return true;
			}
			return false;

		}
		throw new RuntimeException("Coordenada inválida");
	}

}