package model;

import java.util.ArrayList;
import java.util.List;

public class PrincipalProblema {

	public static void main(String[] args) {
		List<Tabuleiro> solucoes = new ArrayList<Tabuleiro>();
		int qtdRainhas = 8;
		while (true) {
			Tabuleiro tabuleiro = new Tabuleiro();
			for (int i = 0; i < qtdRainhas; i++) {
				tabuleiro.inserir(new Rainha());
			}
			List<Coordenada> coordenadasPreenchidas = MatrizUtil.getCoordenadasPreenchidas(tabuleiro.getMatriz(),
					tabuleiro.getLines(), tabuleiro.getCols());
			if (coordenadasPreenchidas.size() == qtdRainhas) {
				solucoes.add(tabuleiro);
				System.out.println("Problema resolvido, rainhas inseridas: " + qtdRainhas);
				System.out.println(tabuleiro);
				break;
			}
		}
		

	}

}