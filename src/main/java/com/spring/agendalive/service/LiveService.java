package com.spring.agendalive.service;

import com.spring.agendalive.entity.Live;
import com.spring.agendalive.repository.LiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LiveService {

    @Autowired
    LiveRepository liveRepository;

    public Page<Live> findAll(Pageable pageable, String flag){
        if(flag != null && flag.equals("next")){
            return liveRepository.findByLiveDateAfterOrderByLiveDateAsc(LocalDateTime.now(), pageable);
        }else if(flag != null && flag.equals("previous")){
            return liveRepository.findByLiveDateBeforeOrderByLiveDateDesc(LocalDateTime.now(), pageable);
        }else{
            return liveRepository.findAll(pageable);
        }
    }

    public Optional<Live> findById(Long id){
        return liveRepository.findById(id);
    }

    public Live save(Live liveDocument){
        return liveRepository.save(liveDocument);
    }

    public void delete(Live liveDocument){
        liveRepository.delete(liveDocument);
    }
}
