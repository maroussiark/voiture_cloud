package cloud.voiture.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cloud.voiture.model.ResponseWrap;
import cloud.voiture.model.Types;
import cloud.voiture.repository.TypesRepository;

@RestController
@RequestMapping("/admin/type")
public class TypesController {
    @Autowired
    private TypesRepository typesRepository;

    @GetMapping
    public ResponseWrap<List<Types>> getAllType() {
        return ResponseWrap.success(typesRepository.findByEtat(1));
    }

    @GetMapping("/{id}")
    public ResponseWrap<Types> getTypeById(@PathVariable int id) {
        return typesRepository.findById(id)
                .map(type -> ResponseWrap.success(type))
                .orElseGet(() -> ResponseWrap.error("id not found"));

    }

    @PostMapping
    public ResponseWrap<Types> addType(@RequestBody Types type) {
        return ResponseWrap.success(typesRepository.save(type));
    }

    @PutMapping("/{id}")
    public ResponseWrap<Types> updateType(@PathVariable int id, @RequestBody Types typeModif) {
        if (typesRepository.existsById(id)) {
            typeModif.setId(id);
            Types maj = typesRepository.save(typeModif);
            return ResponseWrap.success(maj);
        }
        return ResponseWrap.error("id not found");
    }

    @DeleteMapping("/{id}")
    public ResponseWrap<String> deleteType(@PathVariable int id) {
        Optional<Types> optionalType = typesRepository.findById(id);

        if (optionalType.isPresent()) {
            Types existingType = optionalType.get();
            existingType.setEtat(0);
            typesRepository.save(existingType);
            return ResponseWrap.success("deleted successfully");
        } else {
            return ResponseWrap.error("id not found");
        }
    }

}
