package cloud.voiture.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloud.voiture.model.Annonce;
import cloud.voiture.model.Historique;
import cloud.voiture.repository.AnnonceRepository;
import cloud.voiture.repository.HistoriqueRepository;

@Service
public class AnnonceService {

    @Autowired
    private AnnonceRepository annonceRepository;

    @Autowired
    private HistoriqueRepository historiqueRepository;

    /*
     * ADMIN METHODS
     */
    public List<Annonce> getAllAnnonce() {
        return annonceRepository.findAll();
    }

    public Optional<Annonce> getAnnonceById(String id) {
        return annonceRepository.findById(id);
    }

    public List<Annonce> getAnnonceNonValidee() {
        return annonceRepository.findByEtat(0);
    }

    public Annonce validerAnnonce(String id) throws Exception {
        Annonce annonce = annonceRepository.findById(id)
                .orElseThrow(() -> new Exception("id not found"));
        if (annonce.getEtat() != 0) {
            throw new Exception("annonce deja accepter ou supprimer");
        }
        annonce.setEtat(5);
        annonceRepository.save(annonce);

        return annonce;
    }

    public Annonce refuserAnnonce(String id) throws Exception {
        Annonce annonce = annonceRepository.findById(id)
                .orElseThrow(() -> new Exception("id not found"));
        if (annonce.getEtat() != 0) {
            throw new Exception("annonce deja refuser ou supprimer");
        }
        annonce.setEtat(-5);
        annonceRepository.save(annonce);

        return annonce;
    }

    /*
     * USER METHODS
     */

    // pour l'accueil
    public List<Annonce> getAnnoncesValidee() {
        return annonceRepository.findByEtat(5);
    }

    public List<Annonce> getMesAnnonces(int iduser) {
        return annonceRepository.findByUtilisateurId(iduser);
    }

    public Annonce saveAnnonce(Annonce nouvelleAnnonce) {
        return annonceRepository.save(nouvelleAnnonce);
    }

    public Annonce changeStatusToSold(int idvendeur,int idacheteur,String idannonce) throws Exception{

        Optional<Annonce> concerned = annonceRepository.findById(idannonce);

        if(concerned.isEmpty()){
            throw new Exception("annonce inexistante");
        }

        concerned.get().setEtat(10);
        annonceRepository.save(concerned.get());

        Historique historique = new Historique();
        historique.setIdacheteur(idacheteur);
        historique.setIdvendeur(idvendeur);
        historique.setIdannonce(idannonce);

        historiqueRepository.save(historique);
        return concerned.get();
    }

}
