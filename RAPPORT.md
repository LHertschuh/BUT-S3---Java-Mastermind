DEUXIEME RAPPORT

Pour le deuxième rapport il y a plusieurs choses qui ont changé au niveau conception, j'ai premièrement changé les idées ou j'étais en désaccord sur le premier rapport.

I)Les bugs
J'ai trouvé 2 bugs, l'un est mineur et l'autre est majeur. Mais les deux on un rapport avec la création d'une nouvelle partie après la fin de la première.
Le premier bug mineur est lorsqu'on crée plusieurs parties, les écrans vont se dédoubler, j'avais d'autres problème a régler et je me suis retrouvé à court de temps pour corriger ce problème.
Le deuxième est beaucoup plus important, je l'ai trouvé que quelques heures avant le rendu donc je n'ai pas le temps de le corriger. Si on crée une première partie, ensuite on crée une deuxième partie avec comme paramètre le nombre de pions dans une combinaison plus petit que l'ancienne partie ça plantera a l'affichage de la combinaison secrete. J'ai des pistes pour corriger le problème.

II)Score de la partie : 
Dans l'énoncé, il y a écrit que dans le mode "Classique", on a 4 points en plus dans le score. J'ai donc considéré que même si on a une combinaison nulle on a 4 points d'office.

III)Affichage indice
Malgré le fait que le mode des indices ne est défini que au début de la partie, j'ai tout de même choisi de prendre une méthode Strategie. Dans le cas ou on peut remodifier les indices à n'importe quel moment mon code s'adaptera beaucoup mieux.
J'ai hésité à faire tout de même une enum Mode_Indice , mais j'ai décidé pour les cas ou je dois savoir quel mode d'indice j'ai de comparer des Strings

IV) Mise a jour de la vue : 
J'utilise un observeur pour passer les informations. Chaque element de mon Modele contient ma vue en observeur. 

