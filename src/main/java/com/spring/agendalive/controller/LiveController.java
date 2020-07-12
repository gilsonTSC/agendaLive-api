package com.spring.agendalive.controller;

import com.spring.agendalive.entity.Live;
import com.spring.agendalive.service.LiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class LiveController {

    @Autowired
    LiveService liveService;

    @GetMapping("/lives")
    public ResponseEntity<Page<Live>> getAllLives(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                          @RequestParam(required = false) String flag){
        Page<Live> livePage = liveService.findAll(pageable, flag);
        if(livePage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<Page<Live>>(livePage, HttpStatus.OK);
        }
    }

    @GetMapping("/lives/{id}")
    public ResponseEntity<Live> getOneLive(@PathVariable(value="id") Long id){
        Optional<Live> liveO = liveService.findById(id);
        if(!liveO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<Live>(liveO.get(), HttpStatus.OK);
        }
    }

    @PostMapping("/lives")
    public ResponseEntity<Live> saveLive(@RequestBody @Valid Live live) {
        live.setRegistrationDate(LocalDateTime.now());
        return new ResponseEntity<Live>(liveService.save(live), HttpStatus.CREATED);
    }

    @DeleteMapping("/lives/{id}")
    public ResponseEntity<?> deleteLive(@PathVariable(value="id") Long id) {
        Optional<Live> liveO = liveService.findById(id);
        if(!liveO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            liveService.delete(liveO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/lives/{id}")
    public ResponseEntity<Live> updateLive(@PathVariable(value="id") Long id, @RequestBody @Valid Live live) {
        Optional<Live> liveO = liveService.findById(id);
        if(!liveO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            live.setId(liveO.get().getId());
            return new ResponseEntity<Live>(liveService.save(live), HttpStatus.OK);
        }
    }
}
