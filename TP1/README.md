Thibault ARLOING
Yann DUBOIS

#TP1

##Question 1

###Fichier qui donne le bon résultat :
- ex0point_10000 P1: (0,0) P2: (100,100)
- ex10_24400144 P1: (106,0) P2: (65000, 376)
- ex100_5980 P1: (127,0) P2: (150,260)
- ex500_7616 P1: (1,0) P2: (33,238)
- exCodeChef_49997500 P1: (5,0) P2: (100000,500)
- ex100000_100000 P1: (0,0) P2: (100000,1)
- ex=100000_1000000 P1: (0,0) P2: (100000,100)
- ex=200000_2000000 P1: (0,0) P2: (200000,100)
- exT100000_30011389 P1: (99189,0) P2: (250000,199)
- exT200000_75141975 P1: (198225,0) P2: (500000,249)

###Constats

On constate que l'algo fonctionne bien mais que sur certain jeux de données, l'algo
peut mettre beaucoup de temps.

###Analyse de la complexité

Nous n'allons pas compter les tours de boucle où nous lisons les fichiers pour
en extraire les points.

Nous allons donc simplements nous concentrer sur le nombre de comparaison qu'il y a
dans l'algo, c'est à dire, le nombre de fois où nous comparons deux surfaces de rectangles.

for(int i = 0; i < n - 1; i++) -> (n - 2) fois
  for(int j = i + 1; j < n; j++) -> (n * (n - 1)) / 2 fois

Il y a donc : (n - 2) * ((n * (n - 1)) / 2) comparaisons.
Nous sommes donc en teta(n^3);

###Conclusions

Plus le jeux de données est grand, plus l'algo mettra de temps, notre algo étant en
teta(n^3), il est certain que nous mettrions plus de 1s pour trouver le résultat pour
n = 100 0000.

##Question 2

###Fichier qui donne le bon résultat :
- ex0point_10000 P1: (0,0) P2: (100,100)
- ex10_24400144 P1: (106,0) P2: (65000, 376)
- ex100_5980 P1: (127,0) P2: (150,260)
- ex500_7616 P1: (1,0) P2: (33,238)
- ex100000_100000 P1: (0,0) P2: (100000,1)
- exCodeChef_49997500 P1: (5,0) P2: (100000,500)
- ex=100000_1000000 P1: (0,0) P2: (100000,100)
- ex=200000_2000000 P1: (0,0) P2: (200000,100)

###Fichier qui fonctionne pas encore : (segfault -> algo récursif / pile)
- exT100000_30011389 P1: (99189,0) P2: (250000,99)
- exT200000_75141975 P1: (198225,0) P2: (500000,249)
