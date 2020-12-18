package br.com.marcatti.controller;

import br.com.marcatti.dto.Dto;
import br.com.marcatti.model.Model;
import br.com.marcatti.service.ServiceAPI;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1")
public class Controller {

    @Autowired
    ServiceAPI service;

    @PostMapping()
    @ApiOperation(value = "Serviço para incluir novos registros",
            notes = "Servico exemplo para incluir novos registros")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody Dto dto){
        log.debug("Controller::v1:: dto {}" , dto );
        service.save(dto);
    }

    @GetMapping
    @ApiOperation(value = "Serviço para listar todos os registros",
            response = Model.class,
            responseContainer = "List",
            notes = "Servico exemplo para listar todos os registros")
    public ResponseEntity<Collection<Model>> all() {
        log.debug("Controller::v1::find");
        Collection<Model> lst = service.findAll();
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    @ApiOperation(value = "Serviço de buscar por uuid",
            response = Model.class,
            notes = "Servico exemplo para buscar registros por uuid")
    public ResponseEntity<Model> findById(
            @ApiParam(value = "uuid do registro para busca", required = true , example = "3fa85f64-5717-4562-b3fc-2c963f66afa")
            @PathVariable("uuid") UUID uuid){
        log.debug("Controller::findById::v1 uuid {}" , uuid );
        Model m = service.findById(uuid);
        if (m == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    @ApiOperation(value = "Serviço de remover por uuid",
            response = Model.class,
            notes = "Servico exemplo para buscar registros por uuid")
    public ResponseEntity<Model> deleteById(
            @ApiParam(value = "uuid excluir", required = true , example = "3fa85f64-5717-4562-b3fc-2c963f66afa")
            @PathVariable("uuid") UUID uuid){
        log.debug("Controller::deleteById::v1 uuid {}" , uuid );
        service.deleteById(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void put(
            @ApiParam(value = "Id para update", required = true , example = "3fa85f64-5717-4562-b3fc-2c963f66afa")
            @PathVariable("uuid") UUID uuid, @RequestBody Dto dto){
        log.debug("Controller::put::v1 pathvariable {} objeto {}", uuid , dto  );
        service.update(uuid, dto);
    }





}
//    @GetMapping("/{id}")
//    @ApiOperation(value = "Serviço de buscar",
//            response = Model.class,
//            notes = "Servico exemplo para buscar registros por ID")
//    public ResponseEntity<Model> findById(
//            @ApiParam(value = "Id do registro para busca", required = true , example = "123")
//            @PathVariable("id") long id) throws Exception{
//        log.debug("Controller::findById::v1 id {}" , id );
//        Model m = service.findById(id);
//        return new ResponseEntity<Model>(m, HttpStatus.OK);
//    }
//
//    @GetMapping("/{sort}/{order}/{page}/{size}")
//    @ApiOperation(value = "Serviço de buscar",
//            response = Model.class,
//            notes = "Servico exemplo para buscar registros por ID")
//    public ResponseEntity<Page<Entidade>> findAll(
//            @ApiParam(value = "Parametro de Direcao", required = true )
//            @PathVariable("sort") String sort,
//            @ApiParam(value = "Parametro de Ordenaçao", required = true )
//            @PathVariable("order") String order,
//            @ApiParam(value = "Pagina", required = true )
//            @PathVariable("page") int page,
//            @ApiParam(value = "Tamanho", required = true )
//            @PathVariable("size") int size
//    ) throws Exception{
//        log.debug("Controller::findAll::v1 sort {} order {} page {} size {}" , sort,order, page , size);
//        Page<Entidade> e = service.findAll(sort,order, page, size );
//        return new ResponseEntity<Page<Entidade>>(e, HttpStatus.OK);
//    }
//
//    @PostMapping("")
//    public ResponseEntity<?> save(@Valid @RequestBody Dto dto) throws Exception{
//        log.debug("Controller::save::v1 objeto {}" , dto );
//        service.post(dto);
//        return new ResponseEntity<Model>(HttpStatus.OK);
//    }
//
//    @PutMapping("/{pathvariable}")
//    @ResponseStatus(HttpStatus.OK)
//    public void put(
//            @ApiParam(value = "Id do registro para busca", required = true , example = "123")
//            @PathVariable("pathvariable") long pathvariable, @RequestBody Dto dto) throws Exception{
//        log.debug("Controller::put::v1 pathvariable {} objeto {}", pathvariable , dto  );
//        dto.setId(pathvariable);
//        service.update(dto);
//    }
//
//    @DeleteMapping("/{pathvariable}")
//    @ResponseStatus(HttpStatus.OK)
//    public void delete(
//            @ApiParam(value = "Id do registro para busca", required = true , example = "123")
//            @PathVariable("pathvariable") Long id) throws Exception{
//        log.debug("Controller::delete::v1 pathvariable {} objeto {}", id);
//        service.delete(id);
//    }