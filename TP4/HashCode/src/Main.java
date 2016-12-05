import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
	
	private List<Part> parts = new LinkedList<Part>();
	
	private int maxNbPart(char[][] pizza) {
		int sum = 0;
		for(int i = pizza.length; i > 0; i--) {
			for(int j = pizza[0].length; j > 0; j--) {
				sum += i * j;
			}
		}
		return sum;
	}
	
	private void generateAllParts(char[][] pizza, int taillePart, int jambons) {
		//On sélectionne une cellule précise
		for(int i = 0; i < pizza.length; i++) {
			for(int j = 0; j < pizza[0].length; j++) {
				//Générer toute les parts possibles à partir de la celllule
				int nbJambon = 0;
				for(int k = i; k < pizza.length; k++) {
					for(int l = j; l < pizza[0].length; l++) {
						if(pizza[k][l] == 'H') nbJambon++;
						if(((k - i) + 1) * ((l - j) + 1) <= taillePart && nbJambon >= jambons) 
							this.parts.add(new Part(i, j, k, l));
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
	
	private Part[] randomSolution(int nbJambon, int taillePart, char[][] pizza) {
		List<Part> solve = new LinkedList<Part>();
		List<Part> partsCopy = new LinkedList<Part>(this.parts);
		Part[] certif = null;
		
		Collections.shuffle(partsCopy);
		
		for(Part part : partsCopy) {
			Part[] certifTmp = new Part[solve.size()];
			
			solve.add(part);
			certifTmp = solve.toArray(certifTmp);
			CertificatPizza c = new CertificatPizza(certifTmp, nbJambon, taillePart);
			if(!c.verif(pizza)) {
				solve.remove(part);
			} else {				
				certif = certifTmp;
			}
		}
		
		return certif;
	}
	
	private Part[] gloutonSolution(int nbJambon, int taillePart, char[][] pizza) {
		List<Part> solve = new LinkedList<Part>();
		List<Part> partsCopy = new LinkedList<Part>(this.parts);
		Part[] certif = null;
		
		for(Part part : partsCopy) {
			Part[] certifTmp = new Part[solve.size()];
			
			solve.add(part);
			certifTmp = solve.toArray(certifTmp);
			CertificatPizza c = new CertificatPizza(certifTmp, nbJambon, taillePart);
			if(!c.verif(pizza)) {
				solve.remove(part);
			} else {				
				certif = certifTmp;
			}
		}
		
		return certif;
	}
	
	private Part[] holeSolution(int nbJambon, int taillePart, char[][] pizza) {
		List<Part> solve = new LinkedList<Part>();
		Part[] certif = null;
		
		//glouton solution
		for(Part part : this.parts) {
			solve.add(part);
			CertificatPizza c = new CertificatPizza(solve, nbJambon, taillePart);
			if(!c.verif(pizza)) {
				solve.remove(part);
			}
		}
		
		Cell hole = this.arrayHole(solve, pizza);
		if(hole != null) {
			List<Part> partOfHole = new LinkedList<Part>();
			for(Part part : this.parts) {
				if(hole.containsOnPart(part)) {
					partOfHole.add(part);
				}
			}
		}
		
		return certif;
	}
	
	private Cell arrayHole(List<Part> certif, char[][] pizza) {
		for(int i = 0; i < pizza.length; i++) {
			for(int j = 0; j < pizza[0].length; j++) {
				Cell cell = new Cell(i, j);
				boolean isHole = false;
				for(Part part : certif) {
					isHole |= !cell.containsOnPart(part);
				}
				if(isHole) return cell;
			}
		}
		return null;
	}
	
	private void printSolution(Part[] solve) {
		System.out.println(solve.length);
		for(int i = 0; i < solve.length; i++) {
			System.out.println(solve[i]);
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
			main.generateAllParts(pizza, taillePart, nbJambon);
			Part[] solve = main.gloutonSolution(nbJambon, taillePart, pizza);
			main.printSolution(solve);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
