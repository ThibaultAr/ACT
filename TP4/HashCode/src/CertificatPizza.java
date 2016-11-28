
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
		int[] jambons = new int[nbPart];
		
		for(int i = 0; i < nbPart; i++) {
			int x1 = this.certif[i][0];
			int y1 = this.certif[i][1];
			int x2 = this.certif[i][2];
			int y2 = this.certif[i][3];
			
			if(((x2 - x1) + 1) * ((y2 - y1) + 1) > taillePart) return false;
			
			for(int x = x1; x < x2; x++) {
				for(int y = y1; y < y2; y++) {
					if(pizza[x][y] == 'H') jambons[i]++;
				}
			}
			
			if(jambons[i] < this.nbJambon) return false;
		}
				
		return true;
	}
	
	public int[][] getPizza() {
		return this.certif;
	}
}
