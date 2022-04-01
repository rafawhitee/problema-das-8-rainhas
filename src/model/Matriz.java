package model;

public class Matriz {

	private final int lines;
	private final int cols;
	private final Object[][] matriz;

	public Matriz(int lines, int cols) {
		if (lines >= 1 && cols >= 1) {
			this.lines = lines;
			this.cols = cols;
			this.matriz = new Object[lines][cols];
			System.out.println("Matriz " + lines + "x" + cols + " criada, começo [0][0] indo até [" + (lines - 1) + "]["
					+ (cols - 1) + "]");
		} else {
			throw new RuntimeException("Lines and Cols must be greather than 0");
		}
	}

	public Object get(Coordenada coordenada) {
		if (isValid(coordenada)) {
			return matriz[coordenada.getLine()][coordenada.getColumn()];
		}
		return null;
	}
	
	public boolean insert(Object object, Coordenada coordenada) {
		if (isValid(coordenada)) {
			matriz[coordenada.getLine()][coordenada.getColumn()] = object;
			return true;
		}
		return false;
	}

	public boolean remove(Coordenada coordenada) {
		if (isValid(coordenada)) {
			matriz[coordenada.getLine()][coordenada.getColumn()] = null;
			return true;
		}
		return false;
	}

	public Object[] getLine(int line) {
		return matriz[line];
	}

	public Object[] getCol(int col) {
		Object[] vetor = new Object[lines];
		for (int currentLine = 0; currentLine < lines; currentLine++) {
			vetor[currentLine] = matriz[currentLine][col];
		}
		return vetor;
	}

	/* DIAGONAL PRINCIPAL */
	public Object[] getDiagonalPrincipal(Coordenada coordenada) {
		if (isValid(coordenada)) {
			Object[] voltando = getDiagonalPrincipalVoltando(coordenada);
			Object[] indo = getDiagonalPrincipalIndo(coordenada);
			return ArrayUtil.copiarComTamanhoExato(ArrayUtil.merge(voltando, indo));
		}
		return null;
	}

	protected Object[] getDiagonalPrincipalIndo(Coordenada coordenada) {
		Object[] vetor = new Object[getVetorSize()];
		if (isValid(coordenada)) {
			int index = 0;
			int currentLine = coordenada.getLine();
			int currentCol = coordenada.getColumn();
			while (currentLine != lines && currentCol != cols) {
				vetor[index] = matriz[currentLine][currentCol];
				currentLine++;
				currentCol++;
				index++;
			}
		}
		return vetor;
	}

	protected Object[] getDiagonalPrincipalVoltando(Coordenada coordenada) {
		Object[] vetor = new Object[getVetorSize()];
		if (isValid(coordenada)) {
			int index = 0;
			int currentLine = coordenada.getLine() - 1;
			int currentCol = coordenada.getColumn() - 1;
			while (currentLine != -1 && currentCol != -1) {
				vetor[index] = matriz[currentLine][currentCol];
				currentLine--;
				currentCol--;
				index++;
			}
		}
		return vetor;
	}

	/* DIAGONAL SECUNDÁRIA */
	public Object[] getDiagonalSecundaria(Coordenada coordenada) {
		if (isValid(coordenada)) {
			Object[] voltando = getDiagonalSecundariaVoltando(coordenada);
			Object[] indo = getDiagonalSecundariaIndo(coordenada);
			return ArrayUtil.copiarComTamanhoExato(ArrayUtil.merge(voltando, indo));
		}
		return null;
	}

	protected Object[] getDiagonalSecundariaIndo(Coordenada coordenada) {
		Object[] vetor = new Object[getVetorSize()];
		if (isValid(coordenada)) {
			int index = 0;
			int currentLine = coordenada.getLine();
			int currentCol = coordenada.getColumn();
			while (currentLine != -1 && currentCol != cols) {
				vetor[index] = matriz[currentLine][currentCol];
				currentLine--;
				currentCol++;
				index++;
			}
		}
		return vetor;
	}

	protected Object[] getDiagonalSecundariaVoltando(Coordenada coordenada) {
		Object[] vetor = new Object[getVetorSize()];
		if (isValid(coordenada)) {
			int index = 0;
			int currentLine = coordenada.getLine() + 1;
			int currentCol = coordenada.getColumn() - 1;
			while (currentLine != lines && currentCol != -1) {
				vetor[index] = matriz[currentLine][currentCol];
				currentLine++;
				currentCol--;
				index++;
			}
		}
		return vetor;
	}

	protected int getVetorSize() {
		return Math.max(lines, cols);
	}

	protected boolean isValid(Coordenada coordenada) {
		return coordenada != null && isValid(coordenada.getLine(), coordenada.getColumn());
	}

	protected boolean isValid(int line, int col) {
		return (line >= 0 && line <= (lines - 1)) && (col >= 0 && col <= (cols - 1));
	}

	public int getLines() {
		return lines;
	}

	public int getCols() {
		return cols;
	}

	public Object[][] getMatriz() {
		return matriz;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Matriz " + lines + "x" + cols + ": ");
		sb.append("\n");
		sb.append(MatrizUtil.imprimir(matriz, lines, cols));
		return sb.toString();
	}

}