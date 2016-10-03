#include <stdio.h>

int meilleurGris(int indicePremier, int indiceDernier, int * tab) {
  int res = 0;

  for(int i = indicePremier; i <= indiceDernier; i++)
    res += tab[i];

  return ((float)res / (float)(indiceDernier - indicePremier + 1)) + 0.5;
}

int distanceMin (int indicePremier, int indiceDernier, int * tab) {
  int meilleurGrisLoc = meilleurGris(indicePremier, indiceDernier, tab);
  int res = 0;

  for(int i = indicePremier; i <= indiceDernier; i++)
    res += (meilleurGrisLoc - tab[i]) * (meilleurGrisLoc - tab[i]);

  return res;
}


int main(){

  int tab[25] = {0,0,0,0,0,20,20,20,20,20,100,132,164,164,180,255,255,255,255,255,255,255,255,255,255};
  /* TEST QUESTION 1.1
  printf("%d\n%d\n%d\n", meilleurGris(0, 9,tab),meilleurGris(10, 14,tab), meilleurGris(15, 24,tab));
  */
  /*TEST QUESTION 1.2*/
  printf("%d\n", distanceMin(0, 9,tab) + distanceMin(10, 14,tab) + distanceMin(15, 24,tab));
  return 0;
}
