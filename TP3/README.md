# Question 1
## Taille d'un certificat

La taille d'un certificat est la même que le nombre d'objets, car un certificat correspond à une affectation pour chaque objet.
La taille d'un certificat est polynomiale car il est de même taille que l'entrée.

## Algorithme de verification

L'algorithme contient une boucle de taille "nbObjets", il est donc polynomial (O(nbObjets)).

# Question 2
## Génération aléatoire de certificats

L'algorithme génère des certificats de façon uniformes. Pour chaque objet, on choisit un numéro de sac aléatoire, chaque numéro de sac a donc la même probabilité d'apparaître.

## Schéma d'algorithme NP pour le problème
```
-On tri le tableau dans l'ordre decroissant
-On rempli le premier sac au maximum si possible (i.e. si le premier objet rentre dans un sac, sinon il n'y a pas de solutions)
-Une fois rempli, on mets les objets dans le deuxième
-Ainsi de suite
-Si le nombre de sac est dépassé, il n'y a pas de solutions
```
# Question 3
## Nombre de valeurs pour le certificat pour un n fixé
Comme on choisit une valeur parmis les k sacs pour chaque objet, on a k^nbObjets possibilités.

## Enumération des certificats
On va choisir l'ordre du phénix ou des Jedi, on aime pas trop Voldemort et Palpatine.

Pour le vrai ordre du tp, on fait du codage k-naires. On incrémente le numéro de sac du premier objet,
On considère le tableau comme un seul nombre et on l'incrémente en base k.

## Algorithme du British Museum
```
- On génère un certificat
- On le vérifie
- Si il est faux
  - On rappelle avec le successeur si possible sinon renvoyer faux
- Sinon
  - On renvoie vrai car le problème a une solution
```
On est donc en O(k^nbObjets)

# Question 4
## exPBeq1

### Non deterministe
```
$ java testBinPack DonTPNP/BinPack/exBPeq1 -nd 3
objet 0 dans sac 1
objet 1 dans sac 0
objet 2 dans sac 2
objet 3 dans sac 0
objet 4 dans sac 2
true
```
### Verification
```
$ java testBinPack DonTPNP/BinPack/exBPeq1 -ver 3
donnez un no de sac de 1 a 3
pour l'objet 0
1
donnez un no de sac de 1 a 3
pour l'objet 1
0
donnez un no de sac de 1 a 3
pour l'objet 2
2
donnez un no de sac de 1 a 3
pour l'objet 3
0
donnez un no de sac de 1 a 3
pour l'objet 4
2
objet 0 dans sac 1
objet 1 dans sac 0
objet 2 dans sac 2
objet 3 dans sac 0
objet 4 dans sac 2
```
### Exhaustif
```
$ java testBinPack DonTPNP/BinPack/exBPeq1 -exh 3
objet 0 dans sac 0
objet 1 dans sac 0
objet 2 dans sac 1
objet 3 dans sac 2
objet 4 dans sac 0
true
```
## exPBeq2

### Exhaustif
```
$ java testBinPack DonTPNP/BinPack/exPBeq2 -exh 3
false
```
# Question 5
## Reduction de Partition dans BinPack
```
n := le nombre d'objets</br>
x1 .. xn := le poids des objets</br>
c := somme(i = 1 -> n)(xi) / 2
k = 2 (le nombre de sacs)
```
##Implémentation

### exPart1
```
$ java testPartition DonTPNP/exPart1 -exh
objet 0 dans sac 0
objet 1 dans sac 1
objet 2 dans sac 0
objet 3 dans sac 1
objet 4 dans sac 1
true
```
12 + 4 = 7 + 4 + 5

### exPart2
```
$ java testPartition DonTPNP/exPart2 -exh
false
```

### exPart3
```
$ java testPartition DonTPNP/exPart3 -exh
objet 0 dans sac 0
objet 1 dans sac 1
objet 2 dans sac 0
objet 3 dans sac 1
objet 4 dans sac 1
objet 5 dans sac 1
objet 6 dans sac 0
true
```
11 + 19 + 3 = 17 + 7 + 8 + 1

### exPart5
```
$ java testPartition DonTPNP/exPart5 -exh
objet 0 dans sac 0
objet 1 dans sac 0
objet 2 dans sac 1
objet 3 dans sac 0
objet 4 dans sac 0
objet 5 dans sac 1
objet 6 dans sac 1
objet 7 dans sac 1
objet 8 dans sac 1
true
```
12 + 12 + 4 + 6 = 8 + 16 + 4 + 7 + 5

## BinPack NP-Complet ?

Oui, forcément. On sait que Partition peut se réduire polynomialement en BinPack. De
plus on sait que BinPack appartient à la classe NP. Partition est NP-complet, ce
qui signifie que tout les problèmes NP sont réductible en lui. Ce qui veut dire que
BinPack est réductible dans Partition. Or là, on fait l'inverse, on a prouvé que
Partition est réductible en BinPack, Donc BinPack est NP-complet, car tout les problèmes
sont réductible dans Partition et par conséquent à BinPack.

## BinPack peut être réduit à Partition ?

BinPack peut se réduire polynomialement dans Partition car Partition est NP-Complet.
De ce fait, Tout problème NP est réductible dans Partition. BinPack est un problème
NP, il peut donc être réductible dans Partition.
