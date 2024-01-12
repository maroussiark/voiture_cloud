package cloud.voiture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cloud.voiture.model.Commission;
import cloud.voiture.model.ResponseWrap;
import cloud.voiture.repository.CommissionRepository;

@RestController
@RequestMapping("/admin/commission")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CommissionController {

    @Autowired
    CommissionRepository commissionRepository;

    @GetMapping("/")
    public ResponseEntity<ResponseWrap<List<Commission>>> getAllCarburant() {
        return new ResponseEntity<>(ResponseWrap.success(commissionRepository.findAll()),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseWrap<Commission> getCarburantById(@PathVariable long id) {
        return commissionRepository.findById(id).map(carburant -> ResponseWrap.success(carburant))
                .orElseGet(() -> ResponseWrap.error("Commission non trouvee"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommission(@PathVariable long id) {
        commissionRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @PostMapping("/") 
    public Commission createCommission(@RequestBody Commission commission) {
        return commissionRepository.save(commission);
    }

    @PutMapping("/{id}")
    public ResponseWrap<Commission> updateCommission(@RequestBody Commission commission, @PathVariable int id)
            throws Exception {
        if (commissionRepository.existsById((long) id)) {
            commission.setId((long) id);
            return ResponseWrap.success(commissionRepository.saveAndFlush(commission));
        }else {
            return ResponseWrap.error("Le type de commission n'existe pas");
        }
    }

}
