/**
*Pg
*4ºTP
*aluno: Diogo Ferreira
*nº33438
*/
public class ResolveSudoku
{
	public static void mostraSudokuResolvido(int[][]c)
	{		
		Teste.escreverTabuleiro(c);
	}
	//o que este metodo faz, é analisar todos os 9 arrays extraidos do sudoku, e se para um dado numero ve que só ha uma possibilidade preenche e actuliza o tabuleiro	
	//a - tabela dos numeros iniciais
	//b - fica com a tabela especifica para cada numero
	//c - sudoku com numeros
	public static int[][] metodoDosQuadradosIsolados(int[][]c, boolean[][]a)
	{
		boolean b[][] = new boolean[9][9];
		for (int i = 1;i<10;++i)//vamos ver os 9 numeros
		{ 
			b = AlgoritmoSudoku.consultaPossibilidadesParaEsteNumero(i,c, a);	
			c = alteraC(i, a, b, c);		
			a = Teste.tabelaDaVerdadeInicial(c, a);		
		}		
			return c;		
	}
	//este método altera C se a posição da possibilidade for igual a A e dentro do mesmo quadrado só houver uma possibilidade
	public static int[][]alteraC(int numeroTeste, boolean[][]a, boolean[][]b, int[][]c)
	{	
		boolean sozinho = false;
		for (int i = 0; i < 9; ++i)
			for (int j = 0; j < 9; j++)
			{
				if (a[i][j]==b[i][j] && b[i][j] == true)//so nos interessa as posições em que a e b tem true
				{			
					sozinho = metodoQ(b,i,j);//aqui ficamos a saber se esta sozinho ou nao
					if (sozinho == true)
						c[i][j] = numeroTeste;//se estiver fica com o numero
				}
			}
		return c;	
	}
	public static boolean metodoQ(boolean[][]b, int linha, int coluna)
	{
		boolean sozinho = false;
		//primeiro tem de saber onde esta o quadrado
		int quadrado = AlgoritmoSudoku.encontraQuadrado(linha,coluna);		
		//depois tem de saber se esta sozinho ou nao
		sozinho = resolveQuadrado(b,quadrado);	
		return sozinho;
	}	
	public static boolean resolveQuadrado(boolean[][]b, int quadrado)
	{
		boolean sozinho = false;
		int linhaInicial = 0;
		int linhaFinal = 0;
		int colunaInicial = 0;
		int colunaFinal = 0;
		if (quadrado == 1)
		{
			linhaInicial = 0;
			linhaFinal = 3;
			colunaInicial = 0;
			colunaFinal = 3;	
			sozinho = resolveQuadradoAux(b, linhaInicial, linhaFinal, colunaInicial, colunaFinal);
		}
		else if (quadrado == 2)
		{
			linhaInicial = 0;
			linhaFinal = 3;
			colunaInicial = 3;
			colunaFinal = 6;	
			sozinho = resolveQuadradoAux(b, linhaInicial, linhaFinal, colunaInicial, colunaFinal);		
		}
		else if (quadrado == 3)
		{
			linhaInicial = 0;
			linhaFinal = 3;
			colunaInicial = 6;
			colunaFinal = 9;
			sozinho = resolveQuadradoAux(b, linhaInicial, linhaFinal, colunaInicial, colunaFinal);			
		}
		else if (quadrado == 4)
		{
			linhaInicial = 3;
			linhaFinal = 6;
			colunaInicial = 0;
			colunaFinal = 3;	
			sozinho = resolveQuadradoAux(b, linhaInicial, linhaFinal, colunaInicial, colunaFinal);			
		}
		else if (quadrado == 5)
		{
			linhaInicial = 3;
			linhaFinal = 6;
			colunaInicial = 3;
			colunaFinal = 6;
			sozinho = resolveQuadradoAux(b, linhaInicial, linhaFinal, colunaInicial, colunaFinal);		
		}
		else if (quadrado == 6)
		{
			linhaInicial = 3;
			linhaFinal = 6;
			colunaInicial = 6;
			colunaFinal = 9;	
			sozinho = resolveQuadradoAux(b, linhaInicial, linhaFinal, colunaInicial, colunaFinal);			
		}
		else if (quadrado == 7)
		{
			linhaInicial = 6;
			linhaFinal = 9;
			colunaInicial = 0;
			colunaFinal = 3;
			sozinho = resolveQuadradoAux(b, linhaInicial, linhaFinal, colunaInicial, colunaFinal);			
		}
		else if (quadrado == 8)
		{
			linhaInicial = 6;
			linhaFinal = 9;
			colunaInicial = 3;
			colunaFinal = 6;	
			sozinho = resolveQuadradoAux(b, linhaInicial, linhaFinal, colunaInicial, colunaFinal);			
		}
		else if (quadrado == 9)
		{
			linhaInicial = 6;
			linhaFinal = 9;
			colunaInicial = 6;
			colunaFinal = 9;
			sozinho = resolveQuadradoAux(b, linhaInicial, linhaFinal, colunaInicial, colunaFinal);			
		}	
		return sozinho;
	}	
	public static boolean resolveQuadradoAux(boolean[][]tabuleiro, int linhaInicial, int linhaFinal, int colunaInicial, int colunaFinal)
	{
		boolean sozinho = false;
		int contaVerdadeiras = 0;
		for (int i = linhaInicial;i < linhaFinal;++i)
			for (int j = colunaInicial;j < colunaFinal;++j)
				if (tabuleiro[i][j]==true)
					++contaVerdadeiras;				
		if (contaVerdadeiras == 1)
			sozinho = true;		
		return sozinho;
	}
	//vou procurar pontos vazios numa linha
	public static int tentaLinhas(int[][]tabuleiro, int linha)
	{
		for (int i = 0; i < 9; ++i)
			tabuleiro[linha][i] = 0;
		return 0;	
	}
	//metodos para verificar as linhas e as colunas
	public static boolean fixaColunas(int coluna, int numeroARetornar, int[][]tabuleiroInteirosFixo)
	{
		boolean podeOuNao = true;
		for (int i = 0; i < 9; ++i)
			if (tabuleiroInteirosFixo[i][coluna] ==	numeroARetornar)
				podeOuNao = false;			
		return podeOuNao;
	}
	public static boolean fixaLinhas(int linha, int numeroARetornar, int[][]tabuleiroInteirosFixo)
	{	
		boolean podeOuNao = true;	
		for (int i = 0; i < 9; ++i)
			if (tabuleiroInteirosFixo[linha][i] == numeroARetornar)
				podeOuNao = false;
		return podeOuNao;
	}
	public static int tentaIndicesK(int[][][]c, int[][]tabuleiro,int[][]tabuleiroInteirosFixo)
	{
		int contaNumeros = 0; //se houver um valor apenas no array k, é essa a hipotese
		int linha = 0; 
		int coluna = 0;
		int gaveta = 0;//e-me mais facil pensar num array tridimensional como um armário com altura, largura, e gavetas em cada posicao com coisas la dentro
		int numeroARetornar = 0;	
		int i = 0, j = 0, k = 0;
		for (i = 0; i < 9; i++)
		{
			for (j = 0; j < 9; j++)
			{
				for (k = 0; k < 9; k++)
				{
					if (c[i][j][k]!=0)
					{
						++contaNumeros;
						linha = i;
						coluna = j;
						gaveta = k;					
					}	
				}
				if (contaNumeros == 1)
				{	
					if (tabuleiro[i][j] ==0)
					{
						numeroARetornar  = c[linha][coluna][gaveta]; 
						i = 10; 
						j = 10;
						k = 10;												
					}	
				}
				else
				{
					contaNumeros = 0;
					linha = 0;
					coluna = 0;
					gaveta = 0;	
				}
			}	
		}
		return numeroARetornar;
	}
}