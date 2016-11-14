import java.io.*;
import java.util.Random;

/******************************************************************
La classe abstraite des problèmes de décision, pbs de type Oui/non
******************************************************************/

abstract class PblDec {

  public PblDec(){};

  abstract public boolean aUneSolution();
}

/**********************************************************************
****************La classe des problèmes BinPack************************
**********************************************************************/

class PblBinPack extends PblDec {
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
    int[] aff = new int[nbObjets];
    for(int i = 0; i < nbObjets; i++) {
      aff[i] = 0;
    }
    Certificat certificat = new CertificatBinPack(this, aff);
    boolean b = false;
    while(!certificat.estDernier() && !(b = certificat.estCorrect())) {
      certificat.suivant();
    }
    return b;
  }

  //Algo non déterministe
  //si il y aune solution, au moins une exécution doit retourner Vrai
  // sinon, toutes les exécutions doivent retourner Faux
  public boolean aUneSolutionNonDeterministe() {
    //génère alétaoirement un certificat et vérifie si il est correct
    Certificat certificat = new CertificatBinPack(this);
    certificat.alea();
    return certificat.estCorrect();
  }

  // différents accesseurs, fonctions affichage ...
  public int getNbObjets() {
    return this.nbObjets;
  }

  public int getNbSacs() {
    return this.nbSacs;
  }

  public int[] getPoids() {
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

interface Certificat {
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

class CertificatBinPack implements Certificat {

  private PblBinPack pb;
  private int[] aff;
  //	//   A compléter ... votre représenttaion du certificat

  public CertificatBinPack(PblBinPack p) {
    this.pb = p;
    this.aff = new int[p.getNbObjets()];
    for(int i = 0; i < pb.getNbObjets(); i++) {
      aff[i] = -1;
    }
  }

  public CertificatBinPack(PblBinPack p, int[] aff) {
    this.pb = p;
    this.aff = aff;
  }

  //Implémnetation del'interface:

  public boolean estCorrect(){
    int[] poidSacs = new int[this.pb.getNbSacs()];
    for (int i = 0; i < this.aff.length; i++){
      if (aff[i] == -1)
        return false;

      poidSacs[aff[i]] += this.pb.getPoids()[i];

      if (poidSacs[aff[i]] > this.pb.getCap())
        return false;
    }

    this.affiche();

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
    for(int i = 0; i < this.pb.getNbObjets(); i++) {
      System.out.println("objet " + i + " dans sac " + this.aff[i]);
    }
  }

}
/*************************************************************************
********************************Pour Tester ******************************
**************************************************************************/

class testBinPack {

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
