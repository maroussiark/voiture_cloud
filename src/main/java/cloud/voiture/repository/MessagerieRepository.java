package cloud.voiture.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cloud.voiture.model.messagerie.Messagerie;

@Repository
public interface MessagerieRepository extends MongoRepository<Messagerie, String> {

}
