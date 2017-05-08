import java.util.Random;
import java.util.Scanner;

public class LaranjaBanana {

	public static void main(String[] args){
		
		Random rnd = new Random(System.currentTimeMillis());
		
		double amostras[][] = new double[][]{
			{-1.0, -1.0, -1.0, -1.0, -1.0, -1.0}, //bias
			{ 6.1,  6.3, 12.1, 11.9, 10.2,  5.6}, //largura
			{21.2, 20.9, 13.0, 12.9, 9.98, 19.8} //comprimento
		};
		
		int lin = amostras.length, col = amostras[0].length;
		
		double saidas[] = new double[] {1.0, 1.0, 0.0, 0.0, 0.0, 1.0};
		
		double pesos[] = new double[lin];
		
		for(int i = 0; i < pesos.length; i++){
			pesos[i] = 1.0 / (rnd.nextInt(100) + 1.0);
		}
		
		double taxaAprendizado = 0.02;
		
		int epoca = 0;
		
		boolean erro;
		
		do{
			
			erro = false;
			
			for(int i = 0; i < col; i++){
				
				double net = 0.0;
				
				for(int j = 0; j < lin; j++){
					net += pesos[j] * amostras[j][i];
				}
				
				int y = sinal(net);
				
				if(y != saidas[i]){
					
					for(int k = 0; k < pesos.length; k++){
						pesos[k] = pesos[k] +  taxaAprendizado * (saidas[i] - y) * amostras[k][i];
					}

					erro = true;
				}
			}
			
			epoca ++;
			
			System.out.print("Epoca: " + epoca);
			
			if(erro){
				System.out.println(": ERRO");
			}
			else{
				System.out.println("\n");
			}
			
		}while(erro == true);
		
		
		System.out.println("Numero de epocas " + epoca);
		for (int i = 0; i < pesos.length; i++) {
			System.out.println(String.format("Peso final %s = %s", i, pesos[i]));
		}
		
		
		//OPERAÇÃO
		System.out.println("\n\nFase de operação");
		
		while(true){
			
			double[] amostra = new double[3];
			amostra[0] = -1.0;
			
			Scanner in = new Scanner(System.in);
			
			System.out.println("\nDigite a largura da fruta: ");
			amostra[1] = in.nextDouble();
	
			System.out.println("Digite o comprimento da fruta: ");
			amostra[2] = in.nextDouble();
	
			double net = 0;
			for(int i = 0; i < amostra.length; i++){
				net += pesos[i] * amostra[i];
			}
			
			int y = sinal(net);
			
			if(y == 1){
				System.out.println("É banana");
			}
			else if(y == 0){
				System.out.println("É laranja");
			}
		}
		
	}
	
	private static int sinal(double net) {
		return (net > 0.0) ? 1 : 0;
	}
	
}
