/******************************************************************
La classe abstraite des problèmes de décision, pbs de type Oui/non
******************************************************************/

public abstract class PblDec {

  public PblDec(){};

  abstract public boolean aUneSolution();
}

/**********************************************************************
****************La classe des problèmes BinPack************************
**********************************************************************/

public class PblBinPack extends PblDec {
  private int nbObjets;
  private int poids[];
  private int cap;
  private int nbSacs;

  public PblBinPack(int n, int p[], int nbs, int c){
    nbObjets=n;
    poids=p;
    cap=c;
    nbSacs=nbs;
  }

  //retourne Vrai SSi le pb a une solution
  public boolean aUneSolution() {
    //  A compléter
    // essaie tous les certificats un à un jusqu'à en trouver un correct -si il existe	....
  }

  //Algo non déterministe
  //si il y aune solution, au moins une exécution doit retourner Vrai
  // sinon, toutes les exécutions doivent retourner Faux
  public boolean aUneSolutionNonDeterministe() {
    //   A compléter
    //génère alétaoirement un certificat et vérifie si il est correct
  }

  // différents accesseurs, fonctions affichage ...
  public int getNbObjets() {
    return this.nbObjets;
  }

  public int getNbSacs() {
    return this.nbSacs
  }

  public int getPoids() {
    return this.poids;
  }

  public int getCap() {
    return this.cap;
  }
}

/**********************************************************************
****************l'interface Certificat*********************************
Un certificat est associé à un problème.
la taille d'un certificat doit être bornée polynomialement par rapport au problème
**********************************************************************/

public interface Certificat {
  /*retournera vrai SSi le certificat est bien correct pour le pb auquel il est associé - l'algorithme A du cours  */

  public boolean estCorrect();

  //pour pouvoir enumérer les certificats, on définit un ordre sur les certificats
  //le certificat passe au suivant dans l'ordre choisi
  public void suivant();
  //le certificat est le dernier dans l'ordre choisi
  public boolean estDernier();

  //le certificat prend une valeur alétaoire
  public void alea();

  public void affiche();
}


/************************************************************************
**************** Les certificats pour BinPack****************************
************************************************************************/

public class CertificatBinPack implements Certificat {

  private PblBinPack pb;
  private int[] aff;
  //	//   A compléter ... votre représenttaion du certificat

  public CertificatBinPack(PblBinPack p) {
    this.pb = p;
    this.aff = new int[p.getNbObjets()];
  }

  public CertificatBinPack(PblBinPack p, int[] aff) {
    this.pb = p;
    this.aff = aff;
  }

  //Implémnetation del'interface:

  public boolean estCorrect(){
    int[] poidSacs = new int[this.pb.getNbSacs()];
    for (int i = 0; i < this.aff.length; i++){
      if (aff[i] == NULL)
        return false;

      poidSacs[aff[i]] += this.pb.getPoids()[i];

      if (poidsSacs[aff[i]] > this.pb.getCap)
        return false;
    }

    return true;
  }

  public void suivant() {
    int i = 0;
    while((this.aff[i] = (this.aff[i] + 1) % this.pb.getNbSacs()) == 0)
      i++;
  }

  public boolean estDernier() {
    for(int i = 0; i < this.aff.length; i++)
      if(aff[i] != this.pb.getNbSacs() - 1)
        return false;
    return true;
  }

  public void alea() {
    Random rand = new Random();

    for (int i = 0; i < this.aff.length; i++) {
      int nsac = rand.nextInt(this.pb.getNbSacs());
      aff[i] = nsac;
    }
  }

  public void affiche() {
    //   A compléter
  }

}
/*************************************************************************
********************************Pour Tester ******************************
**************************************************************************/

.... class ... {
  public static void main(String[] args) throws Exception {
    if (args.length < 3){
      System.out.println("Usage: java testBinPack <file> <mode> <nbsacs>");
      System.out.println("where modes include: -ver (verif), -nd (non déterministe), -exh (exhaustif)");}
      else {
        BufferedReader donnee  //le fichier qui contient les données du pb
        = new BufferedReader (new FileReader(args[0]));
        int cap=Integer.parseInt(donnee.readLine());
        int nbObjets=Integer.parseInt(donnee.readLine());
        int poids[]=new int[nbObjets];
        for (int i=0;i< nbObjets;i++) poids[i]=Integer.parseInt(donnee.readLine());
        int nbSacs=Integer.parseInt(args[2]);
        PblBinPack pb=new PblBinPack(nbObjets,poids,nbSacs,cap);
        if (args[1].equals("-exh")) System.out.println(pb.aUneSolution());
        else if (args[1].equals("-nd")) System.out.println(pb.aUneSolutionNonDeterministe());
        else if (args[1].equals("-ver")) {
          BufferedReader entree = new BufferedReader (new InputStreamReader(System.in));
          int aff[]=new int[nbObjets];
          for (int i=0;i < nbObjets; i++) {
            System.out.print("donnez un no de sac de 1 a "); System.out.println(nbSacs);
            System.out.print("pour l'objet "); System.out.println(i);
            aff[i]=Integer.parseInt(entree.readLine());
            if (aff[i]<0 || aff[i]>=nbSacs) throw new Exception("valeur non autorisee");}
            Certificat cert =new CertificatBinPack(pb,aff); //constructeur qui affecte aux sacs les valeurs de aff;
            System.out.println(cert.estCorrect());
          }
          else {
            System.out.println("Usage: java  testBinPack <file>  <mode> <nbsacs>");
            System.out.println("where modes include: -ver (verif), -nd (non déterministe), -exh (exhaustif)");}

          }
        }
      }
