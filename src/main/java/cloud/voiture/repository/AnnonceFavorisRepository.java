package cloud.voiture.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import cloud.voiture.model.AnnonceFavoris;

public interface AnnonceFavorisRepository extends MongoRepository<AnnonceFavoris, String>{
    public List<AnnonceFavoris> findByIdutilisateur(int idutilisateur);
}