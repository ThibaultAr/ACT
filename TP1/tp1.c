#include <stdlib.h>
#include <stdio.h>

struct point_s {
  unsigned long int x;
  unsigned long int y;
};

typedef struct point_s point;

/*l -> largeur
  h -> hauteur
  n -> nombre de points
  points -> tableau de points
*/
void res (int l, int h, int n, point* points){
  point actual;
  point point1, point2;
  unsigned long int surface = 0;

  /* Pour i allant de 0 à n - 2 (du début jusque l'avant dernier) */
  for(int i = 0; i < n - 1; i++){
    unsigned long int minOrd = h;
    actual = points[i];

    /* Pour j allant de i + 1 (voisin direct à droite) à n - 1 (tout les voisin de droites) */
    for(int j = i + 1; j < n; j++){
      unsigned long int surfaceTMP;

      surfaceTMP = (points[j].x - points[i].x) * minOrd;
      if(surface < surfaceTMP) {
        surface = surfaceTMP;
        point1.x = points[i].x;
        point1.y = 0;
        point2.x = points[j].x;
        point2.y = minOrd;
      }

      if(points[j].y < minOrd)
      minOrd = points[j].y;
    }
  }

  printf("surface: %lu, p1: (%lu, %lu), p2: (%lu, %lu)", surface, point1.x, point1.y, point2.x, point2.y);
}

int main (char* argv[], int argc) {
  unsigned long int n, l, h, i;
  point* points;
  point begin, end;

  i = 0;

  scanf("%lu %lu", &l, &h);
  scanf("%lu", &n);

  points = malloc((n + 2) * sizeof(point));
  begin.x = 0;
  begin.y = 0;
  points[i++] = begin;

  for(;i < n + 1; i++) {
    point actual;
    scanf("%lu %lu", &actual.x, &actual.y);
    points[i] = actual;
  }

  end.x = l;
  end.y = 0;
  points[n + 1] = end;

  res(l, h, n + 2, points);

  free(points);

  return 0;
}
