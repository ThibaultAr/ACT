import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
	
	private List<int[]> parts = new LinkedList<int[]>();
	
	private int maxNbPart(char[][] pizza) {
		int sum = 0;
		for(int i = pizza.length; i > 0; i--) {
			for(int j = pizza[0].length; j > 0; j--) {
				sum += i * j;
			}
		}
		return sum;
	}
	
	private void generateAllParts(char[][] pizza, int taillePart) {
		//On sélectionne une cellule précise
		int index = 0;
		
		for(int i = 0; i < pizza.length; i++) {
			for(int j = 0; j < pizza[0].length; j++) {
				//Générer toute les parts possibles à partir de la celllule
				for(int k = i; k < pizza.length; k++) {
					for(int l = j; l < pizza[0].length; l++) {
						if(((k - i) + 1) * ((l - j) + 1) <= taillePart) 
							this.parts.add(new int[] {i, j, k, l});
					}
				}
			}
		}
	}
	
//	private int[][] shuffle() {
//		int [][] shuffleParts = this.parts.clone();
//		Random random = new Random();
//		for(int i = this.parts.length - 1; i > 1; i--) {
//			int[] tmp = shuffleParts[i];
//			int j = random.nextInt(i);
//			shuffleParts[i] = shuffleParts[j];
//			shuffleParts[j] = tmp;
//		}
//		return shuffleParts;
//	}
	
	private void randomSolution(int nbJambon, int taillePart, char[][] pizza) {
		List<int[]> solve = new LinkedList<int[]>();
		List<int[]> partsCopy = new LinkedList<int[]>(this.parts);
		int[][] certif = null;
		
		Collections.shuffle(partsCopy);
		
		for(int[] part : partsCopy) {
			int[][] certifTmp = new int[solve.size()][4];
			
			solve.add(part);
			certifTmp = solve.toArray(certifTmp);
			CertificatPizza c = new CertificatPizza(certifTmp, nbJambon, taillePart);
			if(!c.verif(pizza)) {
				solve.remove(part);
			} else {				
				certif = certifTmp;
			}
		}
		
		this.printSolution(certif);
	}
	
	private void printSolution(int[][] solve) {
		System.out.println(solve.length);
		for(int i = 0; i < solve.length; i++) {
			System.out.println(solve[i][0] + " " + solve[i][1] + " " + solve[i][2] + " " + solve[i][3]);
		}
	}
	
	private void printPizza(char[][] pizza) {
		for(int i = 0; i < pizza.length; i++) {
			for(int j = 0; j < pizza[0].length; j++) {
				System.out.print(pizza[i][j]);
			}
			System.out.println("");
		}
	}
	
	public static void main(String[] argv) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String[] firstLine = reader.readLine().split(" ");
			int l = Integer.parseInt(firstLine[0]);
			int h = Integer.parseInt(firstLine[1]);
			int nbJambon = Integer.parseInt(firstLine[2]);
			int taillePart = Integer.parseInt(firstLine[3]);
			
			char[][] pizza = new char[l][h];
			
			String line;
			int lineNumber = 0;
			while((line = reader.readLine()) != null) {
				for(int i = 0; i < h; i++) {
					pizza[lineNumber][i] = line.charAt(i);
				}
				lineNumber++;
			}
			
			Main main = new Main();
//			System.out.println(main.maxNbPart(pizza));
			main.generateAllParts(pizza, taillePart);
			main.randomSolution(nbJambon, taillePart, pizza);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
