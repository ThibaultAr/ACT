
public class CertificatPizza {
	
	protected int[][] certif;
	protected int nbJambon;
	protected int taillePart;
	
	/*
	 * Le certificat est une matrice les colonnes représente les
	 * coordonnées du point en haut gauche de la part et du point
	 * en bas à gauche de la part (x1 y1 x2 y2). Les lignes représentent 
	 * une part.
	 * Le certificat est de taille 4 * taillePizza / taillePart
	 */
	public CertificatPizza(int[][] certif, int nbJambon, int taillePart) {
		this.certif = certif;
		this.nbJambon = nbJambon;
		this.taillePart = taillePart;
	}
	
	/*
	 * Vérification faites :
	 * - Vérifier que la taille d'une part est plus petite que la limite
	 * - Vérifier que les parts ne se chevauchent pas
	 * - Vérifier qu'il y a au moins autant de jambon que la limite qui est donnée
	 * Complexité : O(l * h) car on parcours une seule fois toute les parts
	 * On peut en déduire que la classe de complexité du problème est NP, car :
	 * - On s'est ramené à un problème de décision (Le découpage en parts de la pizza est-il bon ?)
	 * - On a trouvé un certificat polynomial en donnée par rapport à l'entrée
	 * - L'algo de vérification est polynomila en temps
	 */
	public boolean verif(char[][] pizza) {
		int nbPart = this.certif.length;
		int[] jambons = new int[nbPart];
		boolean[][] pizzaTmp = new boolean[pizza.length][pizza[0].length];
		
		for(int i = 0; i < nbPart; i++) {
			int x1 = this.certif[i][0];
			int y1 = this.certif[i][1];
			int x2 = this.certif[i][2];
			int y2 = this.certif[i][3];
			
			if(((x2 - x1) + 1) * ((y2 - y1) + 1) > taillePart) return false;
			
			for(int x = x1; x < x2; x++) {
				for(int y = y1; y < y2; y++) {
					if(pizzaTmp[x][y]) return false;
					pizzaTmp[x][y] = true;
					if(pizza[x][y] == 'H') jambons[i]++;
				}
			}
			
			if(jambons[i] < this.nbJambon) return false;
		}
				
		return true;
	}
	
	public int[][] getCertificat() {
		return this.certif;
	}
}
