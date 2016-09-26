#include <stdlib.h>
#include <stdio.h>

struct point_s {
  int x;
  int y;
};

typedef struct point_s point;

/*l -> largeur
  h -> hauteur
  n -> nombre de points
  points -> tableau de points
  surface -> adresse d'un entier pour afficher la surface dans le main
  point1 -> adresse d'un point pour l'afficher dans le main
  point2 -> adresse d'un point pour l'afficher dans le main
*/
void resDivide(int l, int h, int n, point* points, int* surface, point* point1, point* point2) {
  if(n == 2) {
    point1->x = points[0].x;
    point1->y = 0;
    point2->x = points[1].x;
    point2->y = h;
    (*surface) = l * h;
  } else {
    int minOrd = h, hidePoint = 0, ordEq = 0;
    int indicePoint, left, right, surfaceTMP;
    point pointLeft1, pointLeft2, pointRight1, pointRight2;

    for(int i = 1; i < n - 1; i++) {
      if(points[i].y < minOrd) {
        minOrd = points[i].y;
        indicePoint = i;
        hidePoint = 0;
        ordEq = 0;
      }
      if(points[i].y == points[indicePoint].y)
        ordEq++;
      if(points[i].x == points[i - 1].x && points[i].y > minOrd)
        hidePoint++;
    }

    if (ordEq)
      ordEq = ordEq - 1;

    resDivide((points[indicePoint].x - points[0].x), h, indicePoint + 1, points, &left, &pointLeft1, &pointLeft2);
    resDivide(points[n - 1].x - (points[indicePoint].x), h, n - indicePoint - hidePoint - (ordEq / 2), points + indicePoint + (ordEq / 2) + hidePoint, &right, &pointRight1, &pointRight2);

    surfaceTMP = l * minOrd;
    point1->x = points[0].x;
    point1->y = 0;
    point2->x = l + points[0].x;
    point2->y = minOrd;

    if(surfaceTMP < left) {
      surfaceTMP = left;
      (*point1) = pointLeft1;
      (*point2) = pointLeft2;
    }
    if(surfaceTMP < right) {
      surfaceTMP = right;
      (*point1) = pointRight1;
      (*point2) = pointRight2;
    }

  (*surface) = surfaceTMP;
  }
}

/*l -> largeur
  h -> hauteur
  n -> nombre de points
  points -> tableau de points
*/
void res (int l, int h, int n, point* points){
  point actual;
  point point1, point2;
  int surface = 0;

  /* Pour i allant de 0 à n - 2 (du début jusque l'avant dernier) */
  for(int i = 0; i < n - 1; i++){
    int minOrd = h;
    actual = points[i];

    /* Pour j allant de i + 1 (voisin direct à droite) à n - 1 (tout les voisin de droites) */
    for(int j = i + 1; j < n; j++){
      int surfaceTMP;

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

  printf("surface: %d, p1: (%d, %d), p2: (%d, %d)", surface, point1.x, point1.y, point2.x, point2.y);
}

int main (char* argv[], int argc) {
  int n, l, h, i, surface;
  point* points;
  point begin, end, p1, p2;

  i = 0;

  scanf("%d %d", &l, &h);
  scanf("%d", &n);

  points = malloc((n + 2) * sizeof(point));
  begin.x = 0;
  begin.y = 0;
  points[i++] = begin;

  for(;i < n + 1; i++) {
    point actual;
    scanf("%d %d", &actual.x, &actual.y);
    points[i] = actual;
  }

  end.x = l;
  end.y = 0;
  points[n + 1] = end;

  /*res(l, h, n + 2, points);*/

  resDivide(l, h, n + 2, points, &surface, &p1, &p2);
  printf("surface: %d, p1: (%d, %d), p2: (%d, %d)\n", surface, p1.x, p1.y, p2.x, p2.y);

  free(points);

  return 0;
}
