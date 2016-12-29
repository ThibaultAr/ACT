import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
				for(int k = i; k < pizza.length; k++) {
					for(int l = j; l < pizza[0].length; l++) {
						Part part = new Part(i, j, k, l);
						if(part.surface() <= taillePart && part.getNbJambon(pizza) >= jambons) 
							this.parts.add(part);
					}
				}
			}
		}
	}
	
	private Part[] holeSolution(int nbJambon, int taillePart, char[][] pizza, long it) {
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
		
		List<Cell> holes = new ArrayList<Cell>();
		int bestScore = this.computeScore(solve);
		
		List<Part> bestSolve = new LinkedList<Part>(solve);
		Random rand = new Random();
		
		this.hole(solve, pizza, holes);
		
		long time = System.currentTimeMillis();
		
		while(!holes.isEmpty()) {
			Cell cell = holes.get(rand.nextInt(holes.size()));
			List<Part> bestPartHole = new LinkedList<Part>();
			
			for(Part part : this.parts) {
				if(part.isCellContainIn(cell))
					bestPartHole.add(part);
			}
			
			for(Part bestPart : bestPartHole) {			
				List<Part> toRemove = new LinkedList<Part>();
				List<Part> allPossibleLocalParts = new LinkedList<Part>();
				
				for(Part part : solve) {
					if(part.isContainsOn(bestPart, pizza)) {
						toRemove.add(part);
					}
				}
				
				solve.removeAll(toRemove);
				solve.add(bestPart);
				
				for(Part removed : toRemove) {
					for(Part part : this.parts) {
						if(part.isStrictlyContainsOn(removed) && !bestPart.isContainsOn(part, pizza)) allPossibleLocalParts.add(part);
					}
				}
				
				if(allPossibleLocalParts.size() <= 20) {					
					Tree tree = new Tree();
					tree.construct(allPossibleLocalParts, pizza);
					solve.addAll(tree.bestList());
				}
				
				if(bestScore < this.computeScore(solve)) {
					bestScore = this.computeScore(solve);
					bestSolve = new LinkedList<Part>(solve);
					holes = new ArrayList<Cell>();
					this.hole(solve, pizza, holes);
				} else {
					solve = new LinkedList<Part>(bestSolve);
				}
				
				if(System.currentTimeMillis() - time >= it * 1000) break;
			}
			
			if(System.currentTimeMillis() - time >= it * 1000) break;
		}
			
		
		Part[] certif = new Part[solve.size()];
		certif = solve.toArray(certif);
		
		return certif;
	}
	
	private void hole(List<Part> certif, char[][] pizza, List<Cell> holes) {
		for(int i = 0; i < pizza.length; i++) {
			for(int j = 0; j < pizza[0].length; j++) {
				Cell cell = new Cell(i, j);
				boolean isHole = true;
				for(Part part : certif) {
					if(cell.containsOnPart(part)) isHole = false;
				}
				
				if(isHole) {
					holes.add(cell);
				}
			}
		}
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
			main.generateAllParts(pizza, taillePart, nbJambon);
			Part[] solve = main.holeSolution(nbJambon, taillePart, pizza, Integer.parseInt(argv[0]) * 60);
			main.printSolution(solve);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
