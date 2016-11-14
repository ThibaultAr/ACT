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

#Question 3
##Nombre de valeurs pour le certificat pour un n fixé
Comme on choisit une valeur parmis les k sacs pour chaque objet, on a k^nbObjets possibilités.

##Enumération des certificats
On va choisir l'ordre du phénix ou des Jedi, on aime pas trop Voldemort et Palpatine.

Pour le vrai ordre du tp, on fait du codage k-naires. On incrémente le numéro de sac du premier objet,
On considère le tableau comme un seul nombre et on l'incrémente en base k.

##Algorithme du British Museum

-On génère un certificat
-On le vérifie
-Si il est faux
  -On rappelle avec le successeur si possible sinon renvoyer faux
-Sinon
  -On renvoie vrai car le problème a une solution

On est donc en O(k^nbObjets)
