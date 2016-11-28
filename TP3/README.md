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
n := le nombre d'objets
x1 .. xn := le poids des objets
c := somme(i = 1 -> n)(xi) / 2
k = 2 (le nombre de sacs)
```
Nous avons choisit cette réduction car elle s'accorde bien avec le problème partition.</br>
n appartient à O(1),</br>
x1...xn appartient à O(n),</br>
c appartient à O(1),</br>
k appartient à O(1).</br>
La réduction est donc en O(n), donc polynomial.

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

On sait que BinPack est NP. On sait que partition est NP-dur et qu'il se réduit en BinPack, donc Binpack et NP-dur.
Sachant que Binpack est NP et NP-dur, alors il est NP-Complet.

## BinPack peut être réduit à Partition ?

BinPack peut se réduire polynomialement dans Partition car Partition est NP-Complet.
De ce fait, Tout problème NP est réductible dans Partition. BinPack est un problème
NP, il peut donc être réductible dans Partition.

# Une réduction un peu moins évidente
## Question 6
Partition est un cas particulier de Sum.
Cela veut dire que l'on doit réduire Sum en Partition.

## Question 7
On ajoute un entier qui est la différence entre deux fois la capacité et la somme des entiers

On réduit Sum en Partition
```
n := le nombre d'entier
x1...xn := x1...xn x(n + 1)
x(n + 1) := Différence entre 2 * c et la somme des x1..xn
```

```
Sum
xi = (1, 4, 5, 3)
c = 7
=>
Partition
xi = (1, 4, 5, 3, 1)
```

### exSum1
```
$ java testSum DonTPNP/exSum1
false
```

### exSum2
```
$ java testSum DonTPNP/exSum2
objet 0 dans sac 0
objet 1 dans sac 0
objet 2 dans sac 0
objet 3 dans sac 0
objet 4 dans sac 0
objet 5 dans sac 1
objet 6 dans sac 0
objet 7 dans sac 1
true
```
2 + 12 + 8 + 4 + 6 + 4 = 36

### exSum3
```
$ java testSum DonTPNP/exSum3
objet 0 dans sac 0
objet 1 dans sac 0
objet 2 dans sac 1
objet 3 dans sac 1
objet 4 dans sac 0
objet 5 dans sac 1
objet 6 dans sac 1
objet 7 dans sac 1
true
```

8 + 4 + 10 + 4 = 26 (L'objet 7 étant l'objet qu'on a ajouté)

### exSum4
```
$ java testSum DonTPNP/exSum4
false
```

## Question 8

Soit x1,..,xn  
c = cible

A partir des réponses précédentes, on remarque que la capacitéde BinPack peut être
calculé avec cette formule
```
(x1 + x2 + x3 + ... + (2 * c - sum)) / 2
```
Qu'on peut simplifier en
```
cap = (x1 + x2 + x3 + ... + xn) / 2 + (2 * c - sum) / 2

Si (2 * c - sum) >= 0
  cap = (x1 + x2 + x3 + ... + xn) / 2 + (2 * c) / 2 - sum / 2
  Or (x1 + x2 + ... + xn) = sum
  cap = (2 * c) / 2
  cap = c

Si (2 * c - sum) < 0
  On va rendre 2 * c - sum positif
  cap = (x1 + x2 + ... + xn - 2 * c + sum) / 2
      = (2 * sum - 2 * c) / 2
      = sum - c
```

On a donc :
BinPack <- Sum  
nbObjet = nbElement  
x1,..,xn = x1,..,xn  
cap = c si 2 * c - sum >= 0 sinon sum - c  
k = 2  


### exSum2
```
$ java testSumBinPack DonTPNP/exSum3
objet 0 dans sac 0
objet 1 dans sac 0
objet 2 dans sac 1
objet 3 dans sac 0
objet 4 dans sac 1
objet 5 dans sac 1
objet 6 dans sac 1
true
```
### exSum3
```
dubois@a11p15:~/Documents/M1S1/ACT/TP3$ java testSumBinPack DonTPNP/exSum2
objet 0 dans sac 0
objet 1 dans sac 0
objet 2 dans sac 0
objet 3 dans sac 0
objet 4 dans sac 0
objet 5 dans sac 1
objet 6 dans sac 0
true
```
