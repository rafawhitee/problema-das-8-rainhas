package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TabuleiroBackup {

	public static final int DEFAULT_LINE_SIZE = 8;
	public static final int DEFAULT_COL_SIZE = 8;

	private final Integer lineSize;
	private final Integer colSize;
	private final List<CoordenadaPeca> coordenadaPecas = new ArrayList<CoordenadaPeca>();

	public TabuleiroBackup() {
		this(DEFAULT_LINE_SIZE, DEFAULT_COL_SIZE);
	}

	public TabuleiroBackup(Integer lineSize, Integer colSize) {
		if (lineSize >= 4 && colSize >= 4) {
			this.lineSize = lineSize;
			this.colSize = colSize;
		} else {
			throw new RuntimeException("LineSize and ColSize min is 4");
		}
	}

	public boolean inserir(Peca peca) {
		boolean inserido = false;
		List<Coordenada> coordenadasPossiveis = getCoordenadasPossiveis();
		if(isValid(coordenadasPossiveis)) {
			inserir(coordenadasPossiveis.get(0), peca);
			inserido = true;
		}
		return inserido;
	}

	private void inserir(Coordenada coordenada, Peca peca) {
		inserir(new CoordenadaPeca(coordenada, peca));
	}

	private void inserir(CoordenadaPeca coordenadaPeca) {
		if (isValid(coordenadaPeca)) {
			coordenadaPecas.add(coordenadaPeca);
		}
	}

	private void remove(Coordenada coordenada, Peca peca) {
		remove(new CoordenadaPeca(coordenada, peca));
	}

	private void remove(CoordenadaPeca coordenadaPeca) {
		if (isValid(coordenadaPeca)) {
			coordenadaPecas.remove(coordenadaPeca);
		}
	}

	public boolean podeAtacarAlguem(Peca peca) {
		CoordenadaPeca coordenadaPeca = find(peca);
		return isValid(coordenadaPeca) && podeAtacarAlguem(coordenadaPeca.getCoordenada());
	}

	public boolean podeAtacarAlguem(Coordenada coordenada) {
		if (isValid(coordenada)) {
			return find(coordenada) != null || isValid(getCoordenadasPecasInSameLine(coordenada))
					|| isValid(getCoordenadasPecasInSameColumn(coordenada))
					|| isValid(getCoordenadasPecasInSameDiagonalPrincipal(coordenada))
					|| isValid(getCoordenadasPecasInSameDiagonalSecundaria(coordenada));
		}
		return false;
	}

	public void imprimir() {
		for (int i = 0; i < lineSize; i++) {
			for (int k = 0; k < colSize; k++) {
				CoordenadaPeca coordenadaPeca = find(new Coordenada(i, k));
				String coordenadaPecaStr = (coordenadaPeca != null) ? coordenadaPeca.getPeca().toString() : null;
				System.out.println("[" + i + "][" + k + "]: " + coordenadaPecaStr);
			}
		}
	}

	/* PRIVATE UTIL'S */
	private boolean isValid(List<?> list) {
		return list != null && !list.isEmpty();
	}

	private boolean isValid(CoordenadaPeca coordenadaPeca) {
		return (coordenadaPeca != null && isValid(coordenadaPeca.getCoordenada()));
	}

	private boolean isValid(Coordenada coordenada) {
		return (coordenada.getLine() >= 0 && coordenada.getLine() <= (lineSize - 1))
				&& (coordenada.getColumn() >= 0 && coordenada.getColumn() <= (colSize - 1));
	}

	private List<Coordenada> getCoordenadasPossiveis() {
		List<Coordenada> fullCordenadas = getFullCoordenadas();
		if (isValid(coordenadaPecas)) {
			return filtrarPossiveis(fullCordenadas);
		}
		return fullCordenadas;
	}

	private List<Coordenada> filtrarPossiveis(List<Coordenada> fullCoordenadas) {
		List<Coordenada> list = new ArrayList<Coordenada>();
		for (int i = 0; i < lineSize; i++) {
			for (int k = 0; k < colSize; k++) {
				Coordenada coordenada = new Coordenada(i, k);
				if (find(coordenada) == null && !podeAtacarAlguem(coordenada)) {
					list.add(coordenada);
				}
			}
		}
		return list;
	}

	private List<Coordenada> getFullCoordenadas() {
		List<Coordenada> list = new ArrayList<Coordenada>();
		for (int i = 0; i < lineSize; i++) {
			for (int k = 0; k < colSize; k++) {
				list.add(new Coordenada(i, k));
			}
		}
		return list;
	}

	/* DIAGONAL */
	private List<CoordenadaPeca> getCoordenadasPecasInSameDiagonalPrincipal(Coordenada coordenada) {
		List<CoordenadaPeca> list = getCoordenadasPecasDiagonalPrincipal(coordenada);
		return list.stream().filter(cp -> !cp.getCoordenada().equals(coordenada)).collect(Collectors.toList());
	}

	private List<CoordenadaPeca> getCoordenadasPecasDiagonalPrincipal(Coordenada coordenada) {
		List<CoordenadaPeca> retorno = new ArrayList<>();
		if (isValid(coordenada)) {
			int currentLine = coordenada.getLine();
			int currentColumn = coordenada.getColumn();
			while (currentLine != lineSize || currentColumn != colSize) {
				CoordenadaPeca cp = find(new Coordenada(currentLine, currentColumn));
				if (isValid(cp)) {
					retorno.add(cp);
				}
				currentLine++;
				currentColumn++;
			}
		}
		return retorno;
	}

	private List<CoordenadaPeca> getCoordenadasPecasInSameDiagonalSecundaria(Coordenada coordenada) {
		List<CoordenadaPeca> list = getCoordenadasPecasDiagonalSecundaria(coordenada);
		return list.stream().filter(cp -> !cp.getCoordenada().equals(coordenada)).collect(Collectors.toList());
	}

	private List<CoordenadaPeca> getCoordenadasPecasDiagonalSecundaria(Coordenada coordenada) {
		List<CoordenadaPeca> retorno = new ArrayList<>();
		if (isValid(coordenada)) {
			int currentLine = coordenada.getLine();
			int currentColumn = coordenada.getColumn();
			while (currentLine != 0 || currentColumn != 0) {
				CoordenadaPeca cp = find(new Coordenada(currentLine, currentColumn));
				if (isValid(cp)) {
					retorno.add(cp);
				}
				currentLine--;
				currentColumn--;
			}
		}
		return retorno;
	}

	/* LINE */
	private List<CoordenadaPeca> getCoordenadasPecasInSameLine(Coordenada coordenada) {
		List<CoordenadaPeca> list = getCoordenadasPecasInLine(coordenada.getLine());
		return list.stream().filter(cp -> !cp.getCoordenada().equals(coordenada)).collect(Collectors.toList());
	}

	private List<CoordenadaPeca> getCoordenadasPecasInLine(int line) {
		List<CoordenadaPeca> retorno = new ArrayList<>();
		getCoordenadasInLine(line).forEach(c -> {
			CoordenadaPeca cp = find(c);
			if (cp != null) {
				retorno.add(cp);
			}
		});
		return retorno;
	}

	private List<Coordenada> getCoordenadasInLine(int line) {
		List<Coordenada> list = new ArrayList<Coordenada>();
		for (int col = 0; col < colSize; col++) {
			list.add(new Coordenada(line, col));
		}
		return list;
	}

	/* COLUMN */
	private List<CoordenadaPeca> getCoordenadasPecasInSameColumn(Coordenada coordenada) {
		List<CoordenadaPeca> list = getCoordenadasPecasInColumn(coordenada.getColumn());
		return list.stream().filter(cp -> !cp.getCoordenada().equals(coordenada)).collect(Collectors.toList());
	}

	private List<CoordenadaPeca> getCoordenadasPecasInColumn(int column) {
		List<CoordenadaPeca> retorno = new ArrayList<>();
		getCoordenadasInColumn(column).forEach(c -> {
			CoordenadaPeca cp = find(c);
			if (cp != null) {
				retorno.add(cp);
			}
		});
		return retorno;
	}

	private List<Coordenada> getCoordenadasInColumn(int column) {
		List<Coordenada> list = new ArrayList<Coordenada>();
		for (int line = 0; line < lineSize; line++) {
			list.add(new Coordenada(line, column));
		}
		return list;
	}

	/* GETTERS AND SETTERS */

	public CoordenadaPeca find(Peca peca) {
		Optional<CoordenadaPeca> opt = coordenadaPecas.stream().filter(cp -> cp.getPeca().equals(peca)).findFirst();
		return (opt.isPresent()) ? opt.get() : null;
	}

	public CoordenadaPeca find(Coordenada coordenada) {
		Optional<CoordenadaPeca> opt = coordenadaPecas.stream().filter(cp -> cp.getCoordenada().equals(coordenada))
				.findFirst();
		return (opt.isPresent()) ? opt.get() : null;
	}

	public List<Peca> getPecas() {
		return coordenadaPecas.stream().map(cp -> cp.getPeca()).collect(Collectors.toList());
	}

	public List<Coordenada> getCoordenadas() {
		return coordenadaPecas.stream().map(cp -> cp.getCoordenada()).collect(Collectors.toList());
	}

	public Integer getLineSize() {
		return lineSize;
	}

	public Integer getColSize() {
		return colSize;
	}

	public List<CoordenadaPeca> getCoordenadaPecas() {
		return coordenadaPecas;
	}

}