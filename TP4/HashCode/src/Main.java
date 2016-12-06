import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
		
		Collections.sort(partsCopy);
		
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
	
	private Part[] holeSolution(int nbJambon, int taillePart, char[][] pizza, int it) {
		List<Part> solve = new LinkedList<Part>();
		Collections.sort(parts);
		
		//glouton solution
		for(Part part : this.parts) {
			Part[] certifTmp = new Part[solve.size()];
			solve.add(part);
			certifTmp = solve.toArray(certifTmp);
			CertificatPizza c = new CertificatPizza(certifTmp, nbJambon, taillePart);
			if(!c.verif(pizza)) {
				solve.remove(part);
			}
		}
		
		Collections.reverse(parts);
		
		List<Part> betterSolve = new LinkedList<Part>();
		
		for(int i = 1; i <= it; i++) {	
			Cell hole = this.hole(solve, pizza, i);
			List<Part> toRemove = new LinkedList<Part>();
			List<Part> onHole = new LinkedList<Part>();
			
			for(Part part : parts) {
				this.addRemovedPart(new Cell(hole.getX() - 1, hole.getY()), toRemove, part);
				this.addRemovedPart(new Cell(hole.getX() + 1, hole.getY()), toRemove, part);
				this.addRemovedPart(new Cell(hole.getX(), hole.getY() + 1), toRemove, part);
				this.addRemovedPart(new Cell(hole.getX(), hole.getY() - 1), toRemove, part);
				this.addRemovedPart(new Cell(hole.getX() - 1, hole.getY() + 1), toRemove, part);
				this.addRemovedPart(new Cell(hole.getX() + 1, hole.getY() - 1), toRemove, part);
				this.addRemovedPart(new Cell(hole.getX() + 1, hole.getY() + 1), toRemove, part);
				this.addRemovedPart(new Cell(hole.getX() - 1, hole.getY() - 1), toRemove, part);
			
				if(hole.containsOnPart(part) && !onHole.contains(part)) onHole.add(part);
			}
			
			solve.removeAll(toRemove);
			
			int surface = 0;
			Part partToAdd = null;
			for(Part part : onHole) {
				if(hole.containsOnPart(part)) {
					if(surface < part.surface()) {
						Part[] certifTmp = new Part[solve.size()];
						solve.add(part);
						certifTmp = solve.toArray(certifTmp);
						CertificatPizza c = new CertificatPizza(certifTmp, nbJambon, taillePart);
						if(!c.verif(pizza)) {
							if(partToAdd != null) solve.remove(partToAdd);
							surface = part.surface();
							partToAdd = part;
							solve.remove(part);
						}
					}
				}
				
			}
		
			for(Part part : this.parts) {
				if(!solve.contains(part)) {				
					Part[] certifTmp = new Part[solve.size()];
					solve.add(part);
					certifTmp = solve.toArray(certifTmp);
					CertificatPizza c = new CertificatPizza(certifTmp, nbJambon, taillePart);
					if(!c.verif(pizza)) {
						solve.remove(part);
					}
				}
			}
			
			if(this.computeScore(betterSolve) < this.computeScore(solve))
				betterSolve = new LinkedList<Part>(solve);
			else
				solve = new LinkedList<Part>(betterSolve);
		}
		
		Part[] certif = new Part[betterSolve.size()];
		certif = betterSolve.toArray(certif);
		
		return certif;
	}
	
	private void addRemovedPart(Cell cell, List<Part> toRemove, Part part) {
		if(cell.containsOnPart(part)) {
			if(!toRemove.contains(part))toRemove.add(part);
		}
	}
	
	private Cell hole(List<Part> certif, char[][] pizza, int it) {
		for(int i = 0; i < pizza.length; i++) {
			for(int j = 0; j < pizza[0].length; j++) {
				Cell cell = new Cell(j, i);
				boolean isHole = true;
				for(Part part : certif) {
					if(cell.containsOnPart(part)) isHole = false;
				}
				
				if(isHole) {
					if(--it == 0) return cell;
				}
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
	
	private int computeScore(List<Part> solve) {
		int score = 0;
		for(Part part : solve) {
			score += part.surface();
		}
		return score;
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
			Part[] solve = main.holeSolution(nbJambon, taillePart, pizza, 10);
			main.printSolution(solve);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
