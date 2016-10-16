Arloing Thibault
Dubois Yann

langage: C

Question 1:

meilleur gris :

$ ./tp2
10
148
255

distanceMin :

$ ./tp2
5096

Question 2 :

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
