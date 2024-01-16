package cloud.voiture.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cloud.voiture.model.Annonce;

@Repository
public interface AnnonceRepository extends MongoRepository<Annonce, String>{
    public List<Annonce> findByEtat(int etat);
    public List<Annonce> findByUtilisateurId(int id);
    public List<Annonce> findByIdIn(List<String> ids);
}
