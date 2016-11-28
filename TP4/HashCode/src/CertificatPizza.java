
public class CertificatPizza {
	
	protected int[][] certif;
	protected int nbJambon;
	protected int taillePart;
	
	public CertificatPizza(int[][] pizza, int nbJambon, int taillePart) {
		this.certif = pizza;
		this.nbJambon = nbJambon;
		this.taillePart = taillePart;
	}
	
	public boolean verif(char[][] pizza) {
		int nbPart = this.certif.length;
		int[] cases = new int[nbPart];
		int[] jambons = new int[nbPart];
		int[][] pizzaTmp = new int[pizza.length][pizza[0].length];
		int part = 1;
		
		for(int i = 0; i < nbPart; i++) {
			int x1 = this.certif[i][0];
			int y1 = this.certif[i][1];
			int x2 = this.certif[i][2];
			int y2 = this.certif[i][3];
			
			for(int j = x1; j < x2; j++) {
				for(int k = y1; k < y2; k++) {
					pizzaTmp[j][k] = part;
				}
			}
			
			part++;
		}
		
		for(int i = 0; i < pizzaTmp.length; i++) {
			for(int j = 0; j < pizzaTmp[i].length; j++) {
				if(pizzaTmp[i][j] != 0) {
					cases[pizzaTmp[i][j]]++;
					if(pizza[i][j] == 'H') jambons[pizza[i][j]]++;
				}
				if(cases[pizzaTmp[i][j]] > taillePart) return false;
			}
		}
		
		for(int i = 1; i < jambons.length; i++) {
			if(jambons[i] < this.nbJambon) return false;
		}
		
		return true;
	}
	
	public int[][] getPizza() {
		return this.certif;
	}
}
