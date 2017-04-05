/**
*Pg
*4ºTP
*aluno: Diogo Ferreira
*nº33438
*/
public class AlgoritmoSudoku
{		
	//é este que vai retornar o array unidimensional de hipoteses
	//este método é chamado porque o método que chama este, parou numa posição com 0.
	//esta posição pode ser preenchida,e é isso que este método vai guardar todos os numeros possiveis num array h.
	//ou seja, todas os numero possiveis para essa posição		
	public static int[] hipoteses (int[][]tabuleiro,boolean[][]tabuleiroInicial, int linha, int coluna, int[]h)//numeros possiveis
	{	
		boolean pode = false;// inicialização da variável
		int numeroPossivel = 1;
		for (int i = 0; i<9;++i)
		{
			pode = verificaSePodePorNumeroNaPosicaoIndicada(numeroPossivel, tabuleiro, tabuleiroInicial, linha, coluna);	
			if (pode == true)
			{
				h[i] = numeroPossivel;
				++numeroPossivel;
			}
			else if (pode == false)
				++numeroPossivel;	
		}
		return h;
	}
	public static boolean verificaSePodePorNumeroNaPosicaoIndicada(int numero,int[][] tabuleiro,boolean[][]tabuleiroInicial, int linha, int coluna)
	{
		boolean pode = false;
		boolean[][]aux = new boolean[9][9];
		aux = consultaPossibilidadesParaEsteNumero(numero, tabuleiro, tabuleiroInicial);//vai por no array aux todos os indices que não pode por o numero a false
		pode = aux[linha][coluna];	//de seguida a variável pode, fica a false ou a true na linha e coluna passada como parâmetro, na assinatura do método.	
		return pode;//retorna a condicao encontrada
	}
	//retorna um tabuleiro de boolean preenchido com as posicoes que nao pode preencher la a false.
	public static boolean[][]consultaPossibilidadesParaEsteNumero(int numero,int[][]tabuleiro, boolean[][]tabuleiroInicial)
	{
		boolean[][]aux = new boolean[9][9];
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
			{//se o numero 1, por exemplo, é encontrado no tabuleiro,o array aux vai ficar com a linha, coluna e quadrado correspondente a false.
			//a ilustração desta implementação segue em anexo a este trabalho.
				if (numero == tabuleiro[i][j])
				{
					aux = fixaQuadrados(aux, i, j, tabuleiroInicial);//os quadrados tem 3				
					aux = fixaLinhas(aux, i, tabuleiroInicial);
					aux = fixaColunas(aux, j, tabuleiroInicial);
				}
			}
		aux = inverteCondicoes(aux);
		aux = juntaTabuleiroInicialComAux(aux,tabuleiroInicial);		
		return aux;	
	}	
	//sem este método os 9 arrays possiveis para cada numero aparecem correctos mas com as condicoes trocadas
	public static boolean[][]inverteCondicoes(boolean[][]aux)
	{
		for (int i = 0; i < 9;i++)
			for (int j = 0; j < 9; j++)
			{
				if (aux[i][j]==false)
					aux[i][j]=true;
				else if (aux[i][j]==true)
					aux[i][j]=false;
			}		
		return aux;
	}
	//temos de juntar as posições possiveis para cada numero com a tabela inicial que contem os numeros inicias do jogo que se mantem inalterados até ao fim 
	public static boolean[][]juntaTabuleiroInicialComAux(boolean[][]aux, boolean[][]tabuleiroInicial)
	{
		for (int i = 0; i < 9;i++)
			for (int j = 0; j < 9; j++)
				if (tabuleiroInicial[i][j]==false)
					aux[i][j]=false;		
		return aux;
	}	
	//metodo do fixalinhas, preenche as colunas
	public static boolean[][] fixaLinhas(boolean[][]tabuleiro, int linha, boolean[][]tabuleiroInicial)
	{
		for (int i = 0; i < 9; ++i)
			tabuleiro[linha][i] = true;
		return tabuleiro;	
	}
	//metodo do fixacolunas, preenche as linhas
	public static boolean[][] fixaColunas(boolean[][]tabuleiro, int coluna, boolean[][]tabuleiroInicial)
	{
		for (int i = 0; i < 9; ++i)
			tabuleiro[i][coluna] = true;	
		return tabuleiro;
	}		
	/*metodo do fixaquadrados preenche o quadrado respectivo a false para um dado numero encontrado numa posição. 
	  0 1 2 3 4 5 6 7 8
	0 . . . . . . . . . 
	1 . 1 . . 2 . . 3 .
	2 . . . . . . . . . 
	3 . . . . . . . . .
	4 . 4 . . 5 . . 6 . 
	5 . . . . . . . . .
	6 . . . . . . . . . 
	7 . 7 . . 8 . . 9 .
	8 . . . . . . . . . 		
	fig. acima:
	quadrado 1, 2, 3, etc.
	
	*/		
	public static boolean[][] fixaQuadrados(boolean[][]tabuleiro, int linha, int coluna, boolean[][]tabuleiroInicial)
	{
		int quadrado = encontraQuadrado(linha,coluna);
		tabuleiro = fixaQuadradoAuxiliar(quadrado, tabuleiro, tabuleiroInicial);	
		return tabuleiro;
	}
	public static int encontraQuadrado(int linha, int coluna)
	{
		int quadrado = 0;//inicialização da variável
		if (linha>=0 && linha <3)
		{
			if (coluna>=0&&coluna<3)//é o quadrado 1
				quadrado = 1;
			else if (coluna>=3&&coluna<6)//é o quadrado 2
				quadrado = 2;	
			else if (coluna>=6&&coluna<9)//é o quadrado 3
				quadrado = 3;					
		}
		if (linha>=3 && linha <6)
		{
			if (coluna>=0&&coluna<3)//é o quadrado 4
				quadrado = 4;
			else if (coluna>=3&&coluna<6)//é o quadrado 5
				quadrado = 5;	
			else if (coluna>=6&&coluna<9)//é o quadrado 6
				quadrado = 6;					
		}			
		if (linha>=6 && linha <9)
		{
			if (coluna>=0&&coluna<3)//é o quadrado 7
				quadrado = 7;
			else if (coluna>=3&&coluna<6)//é o quadrado 8
				quadrado = 8;	
			else if (coluna>=6&&coluna<9)//é o quadrado 9
				quadrado = 9;					
		}
		return quadrado;
	}
	public static boolean[][]fixaQuadradoAuxiliar(int quadrado, boolean[][] tabuleiro, boolean[][]tabuleiroInicial)
	{	
		if (quadrado == 1)
		{
			tabuleiro = poeQuadradoAFalse(tabuleiro,tabuleiroInicial,0, 3, 0, 3);//tabuleiro, linha inicial, linha final, coluna inicial, coluna final
		}
		else if (quadrado == 2)
		{
			tabuleiro = poeQuadradoAFalse(tabuleiro, tabuleiroInicial, 0, 3, 3, 6);			
		} 
		else if (quadrado == 3)
		{
			tabuleiro = poeQuadradoAFalse(tabuleiro, tabuleiroInicial, 0, 3, 6, 9);			
		}
		else if (quadrado == 4)
		{
			tabuleiro = poeQuadradoAFalse(tabuleiro, tabuleiroInicial, 3, 6, 0, 3);			
		}
		else if (quadrado == 5)
		{
			tabuleiro = poeQuadradoAFalse(tabuleiro, tabuleiroInicial, 3, 6, 3, 6);			
		}
		else if (quadrado == 6)
		{
			tabuleiro = poeQuadradoAFalse(tabuleiro, tabuleiroInicial, 3, 6, 6, 9);			
		}
		else if (quadrado == 7)
		{
			tabuleiro = poeQuadradoAFalse(tabuleiro, tabuleiroInicial, 6, 9, 0, 3);			
		}
		else if (quadrado == 8)
		{
			tabuleiro = poeQuadradoAFalse(tabuleiro, tabuleiroInicial, 6, 9, 3, 6);			
		}			
		else if (quadrado == 9)
		{
			tabuleiro = poeQuadradoAFalse(tabuleiro, tabuleiroInicial, 6, 9, 6, 9);			
		}
		return tabuleiro;
	}
	public static boolean[][]poeQuadradoAFalse(boolean [][]tabuleiro,boolean[][]tabuleiroInicial, int linhaInicial, int linhaFinal, int colunaInicial, int colunaFinal)
	{ // o quadrado tem sempre 3 x 3, no entanto para por uma matriz de 3 x 3 numa de 9 x9 optei por usar o método quadrado primeiro, que preenche na matriz 
		//entre os indices encontrados tudo a false
		for (int i = linhaInicial; i < linhaFinal;i++)
			for (int j = colunaInicial; j < colunaFinal; j++)
				tabuleiro[i][j]=true;	
		return	tabuleiro;			
	}	
}	