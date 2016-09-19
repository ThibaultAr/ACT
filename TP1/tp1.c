#include <stdlib.h>
#include <stdio.h>

struct point_s {
  int x;
  int y;
};

typedef struct point_s* point;

/*l -> largeur
  h -> hauteur
  n -> nombre de points
  points -> tableau de points
*/
void res (int l, int h, int n, point* points){
  point actual;
  point point1, point2;
  int surface = 0;
  int minOrd = h;

  for(int i = 0; i<n-1; i++){
    actual = points[i];
    for(int j = i + 1; j<n; j++){
      int surfaceTMP;
      for(int k = i + 1; k<j; k++){
        if(points[k]->y < minOrd)
          minOrd = points[k]->y;
      }
      surfaceTMP = (points[j]->x - points[i]->x) * minOrd;
      if(surface < surfaceTMP) {
        surface = surfaceTMP;
        point1 =points[i];
        point1->y = 0;
        point2 = points[j];
        point2->y = minOrd;
      }
    }
  }
  printf("surface: %d, p1: (%d, %d), p2: (%d, %d)", surface, point1->x, point1->y, point2->x, point2->y);
}

int main (char* argv[], int argc) {
/*ajout (0,0) (l,0) */
return 0;
}
