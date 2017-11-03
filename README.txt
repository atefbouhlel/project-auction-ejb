
Pour utiliser le système des enchères, il y a deux façons :
• Démo automatique : lancer le client AutomatedDemoClient qui fait tous les opérations
possibles
• Mode Interactif : lancer un client AdministrationClient (pseudo : admin) et faire les
opérations possibles et plusieurs AuctionClient pour tester le reste des opérations

Méthode 1 (Démo automatique)
Pour lancer la démo il faut exécuter plusieurs commandes, après la décompression du projet
zippé :
• cd /project-auction
• mvn clean install
• asadmin start-domain domain1 ; asadmin start-database ; asadmin deploy project-auction-
bean/target/entity-bean.jar
• (cd project-auction-client/ ; java -classpath $CLASSPATH :../project-auction-bean/target/entity-
bean.jar :target/project-auction-client-4.0-SNAPSHOT.jar entreprise/demoClient/Automated
DemoClient)


Méthode 2 (Mode Interactif)

Pour lancer le mode interactif, il faut lancer le AdministrationClient dans un terminal et
taper "admin" comme pseudo d’authentification et lancer plusieurs auctions en s’authentifiant
avec les utilisateurs créés dans le premier terminal.
Une fois connecté, laissez-vous guidée par le menu.
Sur le premier terminal il faut lancer :
• cd /project-auction
• mvn clean install
• asadmin start-domain domain1 ; asadmin start-database ; asadmin deploy project-auction-
bean/target/entity-bean.jar
• (cd project-auction-client/ ; java -classpath $CLASSPATH :../project-auction-bean/target/entity-
bean.jar :target/project-auction-client-4.0-SNAPSHOT.jar entreprise/adminClient/
AdministrationClient)
Et pour chaque AuctionClient il faut lancer cette commande sur un terminal :
• (cd project-auction-client/ ; java -classpath $CLASSPATH :../project-auction-bean/target/entity-
bean.jar :target/project-auction-client-4.0-SNAPSHOT.jar entreprise/auctionClient/AuctionClient)
