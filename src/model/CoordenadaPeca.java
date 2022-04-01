package model;

import java.util.Objects;

public class CoordenadaPeca {
	
	private Coordenada coordenada;
	private Peca peca;
	
	public CoordenadaPeca(Coordenada coordenada, Peca peca) {
		this.coordenada = coordenada;
		this.peca = peca;
	}
	
	public Coordenada getCoordenada() {
		return coordenada;
	}
	
	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}
	
	public Peca getPeca() {
		return peca;
	}
	
	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	@Override
	public String toString() {
		return coordenada.toString() + ": " + peca.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(coordenada, peca);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoordenadaPeca other = (CoordenadaPeca) obj;
		return Objects.equals(coordenada, other.coordenada) && Objects.equals(peca, other.peca);
	}

}