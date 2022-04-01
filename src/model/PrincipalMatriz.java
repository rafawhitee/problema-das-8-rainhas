package model;

public class PrincipalMatriz {

	public static void main(String[] args) {
		int lines = 8;
		int cols = 8;

		Matriz matriz = new Matriz(lines, cols);

		for (int line = 0; line < lines; line++) {
			for (int col = 0; col < cols; col++) {
				String name = String.valueOf(line) + String.valueOf(col);
				matriz.insert(name, new Coordenada(line, col));
			}
		}

		System.out.println("************* DIAGONAIS PRINCIPAIS *************");
		
		System.out.println(" DIAGONAL PRINCIPAL do (0,0) ");
		Object[] diagonalPrincipal1 = matriz.getDiagonalPrincipal(new Coordenada(0, 0));
		System.out.println(ArrayUtil.imprimir(diagonalPrincipal1));
		
		System.out.println(" DIAGONAL PRINCIPAL (1,1) ");
		Object[] diagonalPrincipal2 = matriz.getDiagonalPrincipal(new Coordenada(1, 1));
		System.out.println(ArrayUtil.imprimir(diagonalPrincipal2));
		
		System.out.println(" DIAGONAL PRINCIPAL (6,4) ");
		Object[] diagonalPrincipal3 = matriz.getDiagonalPrincipal(new Coordenada(6, 4));
		System.out.println(ArrayUtil.imprimir(diagonalPrincipal3));
		
		System.out.println(" DIAGONAL PRINCIPAL (7,0) ");
		Object[] diagonalPrincipal4 = matriz.getDiagonalPrincipal(new Coordenada(7, 0));
		System.out.println(ArrayUtil.imprimir(diagonalPrincipal4));
		
		
		System.out.println("************* DIAGONAIS SECUNDARIAS *************");
		
		System.out.println(" DIAGONAL SECUNDARIA do (0,0) ");
		Object[] diagonalSecundaria1 = matriz.getDiagonalSecundaria(new Coordenada(0, 0));
		System.out.println(ArrayUtil.imprimir(diagonalSecundaria1));
		
		System.out.println(" DIAGONAL SECUNDARIA do (6,2) ");
		Object[] diagonalSecundaria2 = matriz.getDiagonalSecundaria(new Coordenada(6, 2));
		System.out.println(ArrayUtil.imprimir(diagonalSecundaria2));
		
		System.out.println(" DIAGONAL SECUNDARIA do (7,6) ");
		Object[] diagonalSecundaria3 = matriz.getDiagonalSecundaria(new Coordenada(7, 6));
		System.out.println(ArrayUtil.imprimir(diagonalSecundaria3));
		
		System.out.println(" DIAGONAL SECUNDARIA do (2,1) ");
		Object[] diagonalSecundaria4 = matriz.getDiagonalSecundaria(new Coordenada(2, 1));
		System.out.println(ArrayUtil.imprimir(diagonalSecundaria4));
		
		System.out.println(" DIAGONAL SECUNDARIA do (7,0) ");
		Object[] diagonalSecundaria5 = matriz.getDiagonalSecundaria(new Coordenada(7, 0));
		System.out.println(ArrayUtil.imprimir(diagonalSecundaria5));
		
		System.out.println(" DIAGONAL SECUNDARIA do (3,4) ");
		Object[] diagonalSecundaria6 = matriz.getDiagonalSecundaria(new Coordenada(3, 4));
		System.out.println(ArrayUtil.imprimir(diagonalSecundaria6));

	}

}