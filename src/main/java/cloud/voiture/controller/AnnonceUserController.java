package cloud.voiture.controller;

import cloud.voiture.service.AnnonceFavorisService;
import cloud.voiture.service.AnnonceService;
import cloud.voiture.model.Annonce;
import cloud.voiture.model.AnnonceFavoris;
import cloud.voiture.model.ResponseWrap;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/annonce")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AnnonceUserController {

    @Autowired
    private AnnonceService annonceService;

    @Autowired
    private AnnonceFavorisService annonceFavorisService;

    @GetMapping("mesannonces/{iduser}")
    public ResponseEntity<ResponseWrap> getMesAnnonces(@PathVariable int iduser) {
        try {
            List<Annonce> annonces = annonceService.getMesAnnonces(iduser);
            return ResponseEntity.ok(ResponseWrap.success(annonces));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseWrap.error("Erreur lors de la récupération des annonces : " + e.getMessage()));
        }
    }

    //tsy mety
    @PostMapping
    public ResponseEntity<Annonce> createAnnonce(@RequestBody Annonce nouvelleAnnonce) {
        Annonce savedAnnonce = annonceService.saveAnnonce(nouvelleAnnonce);
        return new ResponseEntity<>(savedAnnonce, HttpStatus.CREATED);
    }


    /*
     * ANNONCE FAVORIS
     * 
     */
   
    @GetMapping("mesannonces-favoris/{iduser}")
    public ResponseEntity<ResponseWrap> getMesAnnoncesFavoris(@PathVariable int iduser) {
        try {
            List<Annonce> annonces = annonceFavorisService.getAnnoncesFavoris(iduser);
            return ResponseEntity.ok(ResponseWrap.success(annonces));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseWrap.error("Erreur lors de la récupération des annonces : " + e.getMessage()));
        }
    }

    @PostMapping("/favoris")
    public ResponseEntity<ResponseWrap> saveAnnonceFavoris(@RequestBody AnnonceFavoris nouvelleAnnonceFavoris) {
        try {
            AnnonceFavoris savedAnnonceFavoris = annonceFavorisService.saveAnnonceFavoris(nouvelleAnnonceFavoris);
            return ResponseEntity.ok(ResponseWrap.success(savedAnnonceFavoris));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseWrap.error("Erreur lors de la sauvegarde de l'annonce favoris : " + e.getMessage()));
        }
    }

    @PostMapping("/vendre/{annonceId}/vendeur/{vendeurId}/acheteur/{acheteurId}")
    public ResponseEntity<ResponseWrap> changerStatusToSold(@PathVariable String annonceId,@PathVariable int vendeurId,@PathVariable int acheteurId) {
        try {
            Annonce vendu = annonceService.changeStatusToSold(vendeurId, acheteurId, annonceId);
            return ResponseEntity.ok(ResponseWrap.success(vendu));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseWrap.error("Erreur lors de la sauvegarde de l'annonce favoris : " + e.getMessage()));
        }
    }




}
