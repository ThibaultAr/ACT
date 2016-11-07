#Question 1
##Taille d'un certificat

La taille d'un certificat est la même que le nombre d'objets, car un certificat correspond à une affectation pour chaque objet.
La taille d'un certificat est polynomiale car il est de même taille que l'entrée.

##Algorithme de verification

L'algorithme contient une boucle de taille "nbObjets", il est donc polynomial (O(nbObjets)).

#Question 2
##Génération aléatoire de certificats

L'algorithme génère des certificats de façon uniformes. Pour chaque objet, on choisit un numéro de sac aléatoire, chaque numéro de sac a donc la même probabilité d'apparaître.

##Schéma d'algorithme NP pour le problème
-On tri le tableau dans l'ordre decroissant
-On rempli le premier sac au maximum si possible (i.e. si le premier objet rentre dans un sac, sinon il n'y a pas de solutions)
-Une fois rempli, on mets les objets dans le deuxième
-Ainsi de suite
-Si le nombre de sac est dépassé, il n'y a pas de solutions
