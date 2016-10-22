Arloing Thibault
Dubois Yann

langage: C

## Question 1:

meilleur gris :

$ ./tp2
10
148
255

distanceMin :

$ ./tp2
5096

## Question 2 :

reduction_naive: O(n^n)

$ ./tp2
5096

reduction (dynamique): O(k * n^2)

$ ./tp2
tableau des additions k\\pixels
306186	|	191266	|	42261	|	27002	|	17107	|	5114	|	0	|
35366	|	30786	|	4096	|	1216	|	171	|	40480	|	0	|
5096	|	6550	|	683	|	1216	|	171	|	10210	|	0	|

tableau des indices pour remonter, on commence par l'indice le plus en bas à gauche et
monte chaque ligne par l'indice indiqué par la case précdente et on coupe à l'indice -1
0	|	0	|	0	|	0	|	0	|	0	|	0	|
3	|	4	|	6	|	6	|	6	|	7	|	0	|
2	|	3	|	4	|	6	|	6	|	7	|	0	|

5096

## Question 5 :

Imaginons que l'on fusionne 2 couleurs extrême d'un palette (c'est à dire la
couleur au premier indice et celle du dernier indice). Alors si on fusionne ces 2
couleurs, on se retrouvera avec une moyenne dont l'écart entre les 2 couleurs et
très grande. De manière récursive, si on prend la couleur qui précéde la dernière couleur
choisit, on fera descendre la distance minimal qui est proportionelle à la moyenne.
Donc lorsque l'on fusionne 2 couleurs voisines, on obtient automatiquement la
distance minimal.
