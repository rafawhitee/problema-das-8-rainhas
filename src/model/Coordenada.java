package model;

import java.util.Objects;

public class Coordenada {

	private int line;
	private int column;

	public Coordenada(int line, int column) {
		this.line = line;
		this.column = column;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return "Posição[" + line + "][" + column + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(column, line);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordenada other = (Coordenada) obj;
		return column == other.column && line == other.line;
	}

}