import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
	
	private List<int[]> parts = new ArrayList<int[]>();
	
	private void generateAllParts(char[][] pizza) {
		//On sélectionne une cellule précise
		for(int i = 0; i < pizza.length; i++) {
			for(int j = 0; j < pizza[0].length; j++) {
				//Générer toute les parts possibles à partir de la celllule
				for(int k = i; k < pizza.length; k++) {
					for(int l = j; l < pizza[0].length; l++) {
						int[] partsArray = new int[4];
						partsArray[0] = i;
						partsArray[1] = j;
						partsArray[2] = l;
						partsArray[3] = k;
						this.parts.add(partsArray);
					}
				}
			}
		}
	}
	
	private void randomSolution(int nbJambon, int taillePart, char[][] pizza) {
		List<int[]> solve = new ArrayList<int[]>();
		List<int[]> partsCopy = new ArrayList<int[]>(parts);
		Random random = new Random();
		int[][] certif = null;
		
		while(!partsCopy.isEmpty()) {
			int index = random.nextInt(partsCopy.size());
			int[] part = partsCopy.get(index);
			int[][] certifTmp = new int[solve.size()][4];
			
			solve.add(part);
			partsCopy.remove(index);
			solve.toArray(certifTmp);
			CertificatPizza c = new CertificatPizza(certifTmp, nbJambon, taillePart);
			if(!c.verif(pizza)) {
				solve.remove(part);
			}
			certif = certifTmp;
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
			main.generateAllParts(pizza);
			main.randomSolution(nbJambon, taillePart, pizza);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
