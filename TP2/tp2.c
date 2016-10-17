#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

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

int totalPoid (int indicePremier, int indiceDernier, struct palette_s palette) {
  int total = 0;

  for(int i = indicePremier; i <= indiceDernier; i ++)
    total += palette.weight[i];

  return total;
}

int reduction_naive(int k, struct palette_s palette, int n) {
  if(k == 1) {
    return distanceMin(0, n - 1, palette);
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

int reduction(int k, struct palette_s palette, int n, struct palette_s * new_palette) {
  int *tab = malloc(k * n * sizeof(int));
  int *dec = malloc(k * n * sizeof(int));
  int res;

  for(int j = 0; j < n; j++) {
    tab[j + 0 * n] = distanceMin(j, n - 1, palette);
  }

  for(int i = 1; i < k; i++) {
    for(int j = 0; j < n; j++) {
      int min = 0;
      for(int l = j + 1; l < n; l++) {
        int tmp = distanceMin(j, l, palette) + tab[l + 1 + (i - 1) * n];
        if(min > tmp || min == 0) {
          min = tmp;
          dec[j + i * n] = l + 1;
        }
      }
      tab[j + i * n] = min;
    }
  }

  new_palette->pixels = malloc(k * sizeof(int));
  new_palette->weight = malloc(k * sizeof(int));

  int i = 0;
  for(int j = k - 1; j > 0; j--){
    new_palette->pixels[k - j - 1] = meilleurGris(i, dec[i + j * n]-1, palette);
    new_palette->weight[k - j - 1] = totalPoid(i, dec[i + j * n]-1, palette);
    i = dec[i + j * n];
  }

  new_palette->pixels[k - 1] = meilleurGris(i, n, palette);
  new_palette->weight[k - 1] = totalPoid(i, n, palette);

  /* Render of arrays */

/*
  for(int i = 0; i < k; i++) {
    for(int j = 0; j < n; j++) {
      printf("%d\t|\t", tab[j + i * n]);
    }
    printf("\n");
  }

  printf("\n");

  for(int i = 0; i < k; i++) {
    for(int j = 0; j < n; j++) {
      printf("%d\t|\t", dec[j + i * n]);
    }
    printf("\n");
  }

  printf("\n");
*/
  res = tab[0 + (k - 1) * n];
  free(tab);
  free(dec);

  return res;
}

int plusProche(int pixel, int * palette, int n){
  int actual = INT_MAX;
  int i = 0;
  while(actual > (pixel - palette[i]) * (pixel - palette[i]) && i < n) {
    actual = (pixel - palette[i]) * (pixel - palette[i]);
    i++;
  }
  return palette[i - 1];
}

void transImage(int pixels[], int n, int k, int width) {
  int weight[256];
  int nbColors = 0, acc = 0;
  struct palette_s palette, new_palette;
  int * new_pixels = malloc(n * sizeof(int));

  for(int i = 0; i < 256; i++)
    weight[i] = 0;

  for(int i = 0; i < n; i++){
    weight[pixels[i]]++;
    if(weight[pixels[i]] == 1)
      nbColors++;
  }

  palette.pixels = malloc(nbColors * sizeof(int));
  palette.weight = malloc(nbColors * sizeof(int));

  for(int i = 0; i < 256; i++){
    if(weight[i]) {
      palette.pixels[acc] = i;
      palette.weight[acc] = weight[i];
      acc++;
    }
  }

  reduction(k, palette, nbColors, &new_palette);

  for(int i = 0; i < n; i++) {
    new_pixels[i] = plusProche(pixels[i], new_palette.pixels, k);
  }

  printf("%d", new_palette.pixels[k - 1]);

  for(int i = 0; i < n; i++) {
    if(i % width == 0)
      printf("\n");
    printf("%d ", new_pixels[i]);
  }
}

void readImage(int *pixels, int n) {
  int tmp;

  scanf("%d\n", &tmp);
  for(int i = 0; i < n; i++) {
    scanf("%d", &pixels[i]);
  }
}

int main(){

  int *pixels, width, height;
/*
  int pixels[7] = {0,20,100,132,164,180,255};
  int weight[7] = {5,5,1,1,2,1,10};

  struct palette_s palette;
  palette.pixels = pixels;
  palette.weight = weight;
*/

  /* TEST QUESTION 1.1*/
  /*printf("%d\n%d\n%d\n", meilleurGris(0, 1,palette),meilleurGris(2, 5,palette), meilleurGris(6, 6,palette));*/

  /*TEST QUESTION 1.2*/
  /*printf("%d\n", distanceMin(0, 1,palette) + distanceMin(2, 5,palette) + distanceMin(6, 6,palette));*/

  /*TEST QUESTION 2*/
  /*printf("%d\n", reduction_naive(3, palette, 7));*/

  scanf("P2\n%d %d", &width, &height);

  pixels = malloc(width * height * sizeof(int));

  readImage(pixels, width * height);

  /*for(int i = 0; i < width * height; i++) {
    printf("%d ", pixels[i]);
  }*/


  printf("P2\n");
  printf("%d %d\n", width, height);
  transImage(pixels, width * height, 3, width);

  return 0;
}
