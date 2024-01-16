package cloud.voiture.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cloud.voiture.model.messagerie.Messagerie;
import cloud.voiture.model.request.MessageReq;
import cloud.voiture.model.response.ErrorRes;
import cloud.voiture.repository.MessagerieRepository;
import cloud.voiture.service.MessagerieService;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class MessagerieController {
    @Autowired
    MessagerieRepository messagerieRepository;
    @Autowired
    MessagerieService messagerieService;

    @GetMapping("/messageries")
    public ResponseEntity getMessageries() {
        try {
            System.out.println("All messages");
            return ResponseEntity.ok(messagerieRepository.findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/messageries/{id}")
    public Iterable<Messagerie> getMessageries(@PathVariable Integer id) {
        System.out.println("All messages for user");
        return messagerieService.getMessageries(id);
    }

    @PostMapping("/messageries")
    public ResponseEntity newMessagerie(@RequestBody MessageReq newMessage) {
        try {
            newMessage.setDateMessage(new Date());
            messagerieService.ajouterMessage(newMessage.getIdSender(),
            newMessage.getIdReceiver(),
            newMessage.getContenu(), newMessage.getDateMessage());
            return ResponseEntity.ok("Created");
        } catch (Exception e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
