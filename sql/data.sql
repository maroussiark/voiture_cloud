INSERT INTO pays VALUES
(DEFAULT,'France', 1),
(DEFAULT,'Allemagne', 1),
(DEFAULT,'Espagne', 1),
(DEFAULT,'Italie', 1),
(DEFAULT,'Royaume-Uni', 1),
(DEFAULT,'Portugal', 1),
(DEFAULT,'Belgique', 1),
(DEFAULT,'Pays-Bas', 1),
(DEFAULT,'Suède', 1),
(DEFAULT,'Norvège', 1),
(DEFAULT,'Danemark', 1),
(DEFAULT,'Finlande', 1),
(DEFAULT,'Autriche', 1),
(DEFAULT,'Suisse', 1),
(DEFAULT,'Irlande', 1);

INSERT INTO categorie VALUES
(DEFAULT,'SUV',1),
(DEFAULT,'sportive',1);

INSERT INTO utilisateur VALUES
(DEFAULT,'admin','admin','admin@gmail.com','now()','1234',1,1),
(DEFAULT,'user','user','user@gmail.com','now()','1234',0,1);


CREATE TABLE favoris(
    id SERIAL PRIMARY KEY,
    idUtilisateur INTEGER,
    idAnnonce VARCHAR(255),
    FOREIGN KEY(idUtilisateur) REFERENCES utilisateur(id)    
);