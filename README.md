# analyses-medicaux
```
On souhaite créer une application web basée sur les micro-services qui permet de gérer des Analyses médicaux concernant des clients. Chaque Analyse est définie par son identifiant, son nom, sa date, son prix, son résultat de type String et un attribut qui indique si les résultats de l’analyse sont connus ou non. Un Client est défini par son code, son nom, son prénom et sa photo. Toutes les opérations des consultations des analyses et des clients sont accessibles après authentification avec le rôle « USER » alors que les autres opérations d’ajout de modification, mise à jour et de suppression nécessitent d’être authentifié avec le rôle ANALYSE_MANAGER. La gestion des utilisateurs et des rôles sont réservées aux utilisateurs dont le rôle est ADMIN.
La partie back end de l’application est basée sur :
      • Spring IOC, Spring Data, Hibernate
      • SGBD NoSQL MongoDB
 ```    
# screenshots

![technologie](https://github.com/Ammarke/analyses-medicaux/blob/master/img/technologie.png)
![login](https://github.com/Ammarke/analyses-medicaux/blob/master/img/login.png)
![client](https://github.com/Ammarke/analyses-medicaux/blob/master/img/clients.png)
![add-client](https://github.com/Ammarke/analyses-medicaux/blob/master/img/addClient.png)
![alert](https://github.com/Ammarke/analyses-medicaux/blob/master/img/alert.png)
![add-analyse](https://github.com/Ammarke/analyses-medicaux/blob/master/img/addAnalyse.png)



