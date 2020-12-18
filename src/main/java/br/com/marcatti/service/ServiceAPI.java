package br.com.marcatti.service;

import br.com.marcatti.dto.Dto;
import br.com.marcatti.model.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.UUID;

@Slf4j
@Service
public class ServiceAPI {

    private LinkedHashMap<UUID,Model> modelList;
    public  ServiceAPI(){
        this.modelList = new LinkedHashMap<>();
    }

    private Model builder(UUID uuid, Dto dto){
        return  Model.builder().id(uuid).nome(dto.getNome()).build();
    }

    public void save(Dto dto){
      UUID uuid = UUID.randomUUID();
      this.save(uuid, builder(uuid,dto));
    }

    public void save(UUID uuid, Model model){
        log.debug("post UUID {} Model {}", uuid , model);
        this.modelList.put(uuid, model);
    }
    public Collection<Model> findAll() {
        return this.modelList.values();
    }

    public Model findById(UUID id) {
        return this.modelList.get(id);
    }

    public void deleteById(UUID id) { this.modelList.remove(id); }

    public void update(UUID id, Dto dto) {
        Model m = this.modelList.get(id);
        if(m == null){
           this.save(dto);
        }else {
            this.deleteById(id);
            this.save(id,builder(id, dto));
        }
    }
}
