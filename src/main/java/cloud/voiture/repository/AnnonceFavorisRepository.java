package cloud.voiture.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cloud.voiture.model.AnnonceFavoris;

@Repository
public interface AnnonceFavorisRepository extends MongoRepository<AnnonceFavoris, String>{
    public List<AnnonceFavoris> findByIdutilisateur(int idutilisateur);
}