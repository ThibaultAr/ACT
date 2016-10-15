#include <stdio.h>
#include <stdlib.h>

struct palette_s {
  int *pixels;
  int *weight;
};

int meilleurGris(int indicePremier, int indiceDernier, struct palette_s palette) {
  int res = 0, totalWeight = 0;

  for(int i = indicePremier; i <= indiceDernier; i++) {
    res += palette.pixels[i] * palette.weight[i];
    totalWeight += palette.weight[i];
  }

  return (float)res / (float)(totalWeight) + 0.5;
}

int distanceMin (int indicePremier, int indiceDernier, struct palette_s palette) {
  int meilleurGrisLoc = meilleurGris(indicePremier, indiceDernier, palette);
  int res = 0;

  for(int i = indicePremier; i <= indiceDernier; i++)
    res += (meilleurGrisLoc - palette.pixels[i]) * (meilleurGrisLoc - palette.pixels[i]) * palette.weight[i];

  return res;
}

int reduction_naive(int k, struct palette_s palette, int n) {
  if(k == 1) {
    return distanceMin(0, n, palette);
  } else {
    int dst = 0;

    for(int i = 0; i < n; i++) {
      int tmp;
      struct palette_s tmpPalette;

      tmpPalette.pixels = palette.pixels + i + 1;
      tmpPalette.weight = palette.weight + i + 1;

      tmp = distanceMin(0, i, palette) + reduction_naive(k - 1, tmpPalette, n - i - 1);

      if(dst == 0 || dst > tmp)
        dst = tmp;
    }

    return dst;
  }
}

int reduction(int k, struct palette_s palette, int n) {
  int *tab = malloc(k * n * sizeof(int));
  int res;

  for(int j = 0; j < n; j++) {
    tab[j + 0 * n] = distanceMin(j, n - 1, palette);
  }

  for(int i = 1; i < k; i++) {
    for(int j = 0; j < n; j++) {
      int min = 0;
      for(int l = j + 1; l < n; l++) {
        int tmp, checkDistance;
        if(l == n - 1) {
          checkDistance = tab[j + 0 * n];
        } else {
          checkDistance = distanceMin(j, l, palette);
        }
        tmp = checkDistance + tab[l + 1 + (i - 1) * n];
        if(min > tmp || min == 0) min = tmp;
      }
      tab[j + i * n] = min;
    }
  }

  /* Render the array */
  /*
  for(int i = 0; i < k; i++) {
    for(int j = 0; j < n; j++) {
      printf("%d\t|\t", tab[j + i * n]);
    }
    printf("\n");
  }
  */

  res = tab[0 + (k - 1) * n];
  free(tab);

  return res;
}

int main(){
  int pixels[7] = {0,20,100,132,164,180,255};
  int weight[7] = {5,5,1,1,2,1,10};

  struct palette_s palette;
  palette.pixels = pixels;
  palette.weight = weight;
  /* TEST QUESTION 1.1*/
  /*printf("%d\n%d\n%d\n", meilleurGris(0, 1,palette),meilleurGris(2, 5,palette), meilleurGris(6, 6,palette));*/

  /*TEST QUESTION 1.2*/
  /*printf("%d\n", distanceMin(0, 1,palette) + distanceMin(2, 5,palette) + distanceMin(6, 6,palette));*/

  /*TEST QUESTION 2*/
  /*printf("%d\n", reduction_naive(3, palette, 7));*/

  printf("%d\n", reduction(3, palette, 7));

  return 0;
}
