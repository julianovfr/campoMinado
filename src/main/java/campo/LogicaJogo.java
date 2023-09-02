package campo;

import java.util.Random;

public class LogicaJogo {
	private String[][] tabuleiro;
	private String[][] pontos;
	private int tam = 8;
	private int minas = 10;
	private int[][] localBomba = new int[2][10];
	private boolean vencer;
	Random random = new Random();
	
	public  LogicaJogo()
	{	
		setTabuleiro();
		pontos = new String[tam][tam];
		inicializarTabuleiro();
		inicializarPontos();
		inicializarBombas();
		criaLocalBombas();
	}
	
	public void setVencer(boolean vencer) {
		this.vencer = vencer;
	}
	public boolean getVencer() {
		return this.vencer;
	}
	
	public void setTabuleiro() {
		tabuleiro = new String[tam][tam];
	}
	public String[][] getTabuleiro() {
		return tabuleiro;
	}
	
	private void criaLocalBombas() {
		
		for (int qtd=0;qtd<minas;qtd++) {
			
			boolean vazio = true;
			while(vazio) {
				int[] numeros = numerosAleatorios();
				int col = numeros[0];
				int row = numeros[1];
				if(!verificaMinas(col, row)) {
					localBomba[0][qtd] = col; 
					localBomba[1][qtd] = row;
					
					vazio = false;
				}
			}
			
		}
		
	}

	private void inicializarTabuleiro() {
		for (int i=0;i<tam;i++) {
			for (int j=0;j<tam;j++) {
				tabuleiro[i][j] = "-";
				
			}
		}
	}
	private void inicializarPontos() {
		for (int i=0;i<tam;i++) {
			for (int j=0;j<tam;j++) {
				pontos[i][j] = "-";
				
			}
		}
	}
	
	public void exibirTabuleiro() {
		System.out.println("Tabuleiro\n####################");
		for (int i=0;i<tam;i++) {
			for (int j=0;j<tam;j++) {
				System.out.printf(tabuleiro[i][j]+ " ");
			}
			System.out.println();
		}
	}
	
	private int[] numerosAleatorios() {
		int[] numero = new int[2];
		numero[0] = random.nextInt(8);
		numero[1] = random.nextInt(8);
		
		return numero;
	}
	
	private void inicializarBombas() {
		for(int i=0;i<minas;i++) {
			localBomba[0][i] = -1; 
			localBomba[1][i] = -1;
		}
	}
		
	private boolean verificaMinas(int col, int row) {				
		for (int i=0;i<minas;i++) {
			if(localBomba[0][i] ==col && localBomba[1][i] == row) {
				return true;
			}
		}
		return false;
	}
	
	public void exibeBombas() {
		for(int i=0;i<tam;i++) {
			for (int j=0;j<tam;j++) {
				if(verificaMinas(i,j)) {
					System.out.printf("x ");
				}
				else {
					System.out.printf("- ");
				}
			}
			System.out.println();
		}
		
	}
	
	public void exibePontos() {
		System.out.println("Exibindo Pontos já verificados");
		System.out.println("***************");
		for(int i=0;i<tam;i++) {
			for (int j=0;j<tam;j++) {
				System.out.printf(pontos[i][j]+ " ");
			}
			System.out.println();
		}
		System.out.println("***************");
		
	}

	//aqui mostra as bombas a medida que clicamos no mapa
	private void redesenhaTabuleiro(int col, int row) {
		//exibePontos();
		//System.out.println("Estamos chamando");
		//eu tenho o ponto no espaço 2d
		//tenho que verificar os pontos adjacentes a esse ponto
		pontoVerificado(col, row);
		int total = 0;
		if(col-1 >= 0) {
			//System.out.println("direita");
			//verificar a parte de cima
			if(verificaMinas(col-1,row)) {
				total++;
			}
			
		}
		if(col+1 < tam) {
			//System.out.println("esquerda");
			//verificar a parte de baixo
			if(verificaMinas(col+1,row)) {
				total++;
			}
		}
		if(row-1 >= 0) {
			//System.out.println("cima");
			//verificar a parte da esquerda
			if(verificaMinas(col,row-1)) {
				total++;
			}
		}
		if(row+1 < tam) {
			System.out.println();
			//verificar a parte da direita
			if(verificaMinas(col,row+1)) {
				total++;
			}
		}
		
		if(col-1>=0 && row-1>=0) { //verificar a parte superior esquerda
			if(verificaMinas(col-1,row-1)) {
				total++;
			}
		}
		if(col+1<tam && row-1>=0) { //verificar a parte superior direita
			if(verificaMinas(col+1,row-1)) {
				total++;
			}
		}
		if(col-1>=0&& row+1<tam) { //verificar a parte inferior esquerda
			if(verificaMinas(col-1,row+1)) {
				total++;
			}
		}
		if(col+1<tam && row+1<tam) { //verificar a parte inferior direita
			if(verificaMinas(col+1,row+1)) {
				total++;
			}
		}
		
		if(total == 0) {
			tabuleiro[col][row] = "o";
			if(col-1 >= 0 && pontos[col-1][row] != "x") redesenhaTabuleiro(col-1,row);
			if(col+1 < tam&& pontos[col+1][row] != "x") redesenhaTabuleiro(col+1,row);
			if(row-1 >= 0 && pontos[col][row-1] != "x") redesenhaTabuleiro(col,row-1);
			if(row+1 < tam&& pontos[col][row+1] != "x")redesenhaTabuleiro(col,row+1);
			if(col-1>=0 && row-1>=0&& pontos[col-1][row-1] != "x") redesenhaTabuleiro(col-1,row-1);
			if(col+1<tam && row-1>=0&& pontos[col+1][row-1] != "x")redesenhaTabuleiro(col+1,row-1);
			if(col-1>=0&& row+1<tam&& pontos[col-1][row+1] != "x") redesenhaTabuleiro(col-1,row+1);
			if(col+1<tam && row+1<tam&& pontos[col+1][row+1] != "x")redesenhaTabuleiro(col+1,row+1);
			
		}
		else {
			tabuleiro[col][row] = total+"";
		}
		
	}
	
	public  boolean jogada(String click) {
		//System.out.println("chamando jogada");
        String[] palavras = click.split("-");
        int col = Integer.parseInt(palavras[0]);
        int row = Integer.parseInt(palavras[1]);

		if(!verificaMinas(col, row)) {
			inicializarPontos();
			redesenhaTabuleiro(col, row);
			exibePontos();
			setVencer(totalFalta());
			return true;
		}
		tabuleiro[col][row] = "x";
		return false;
	}
	
	public void pontoVerificado(int col, int row) {
		pontos[col][row] = "x";
	}
	
	public int faltamPontos() {
		int qtd=0;
		for (int i=0;i<tam;i++) {
			for (int j=0;j<tam;j++) {
				if(tabuleiro[i][j]=="-") qtd++;
			}
		}
		return qtd;
	}
	
	public void mostrarBombas(String click) {
		 String[] palavras = click.split("-");
	     int col = Integer.parseInt(palavras[0]);
	     int row = Integer.parseInt(palavras[1]);
	     for(int i=0;i<minas;i++) {
	    	 tabuleiro[localBomba[0][i]][localBomba[1][i]] = "x";
	     }
	     
	}
	
	public boolean totalFalta() {
		int qtd=0;
		for(int i=0;i<tam;i++) {
			for(int j=0;j<tam;j++) {
				if(tabuleiro[i][j] == "-") qtd++;
			}
		}
		
		if(qtd==10) return true;
		return false;
	}
}
