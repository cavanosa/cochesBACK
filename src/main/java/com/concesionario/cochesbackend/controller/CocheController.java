package com.concesionario.cochesbackend.controller;

import com.concesionario.cochesbackend.criteria.CocheCriteria;
import com.concesionario.cochesbackend.dto.BusquedaDTO;
import com.concesionario.cochesbackend.enums.Color;
import com.concesionario.cochesbackend.model.Coche;
import com.concesionario.cochesbackend.service.CocheService;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.StringFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coche")
@CrossOrigin(origins = "http://localhost:4200")
public class CocheController {

    @Autowired
    CocheService cocheService;

    @PostMapping("/list")
    public ResponseEntity<List<Coche>> list(@RequestBody BusquedaDTO busquedaDTO){
        CocheCriteria cocheCriteria = createCriteria(busquedaDTO);
        List<Coche> list = cocheService.findByCriteria(cocheCriteria);
        return new ResponseEntity<List<Coche>>(list, HttpStatus.OK);
    }

    private CocheCriteria createCriteria(BusquedaDTO dto){
        CocheCriteria cocheCriteria = new CocheCriteria();
        if(dto!=null){
            if(!StringUtils.isBlank(dto.getMarca())){
                StringFilter filter = new StringFilter();
                filter.setEquals(dto.getMarca());
                cocheCriteria.setMarca(filter);
            }
            if(!StringUtils.isBlank(dto.getModelo())){
                StringFilter filter = new StringFilter();
                filter.setEquals(dto.getModelo());
                cocheCriteria.setModelo(filter);
            }
            if(!StringUtils.isBlank(dto.getVersion())){
                StringFilter filter = new StringFilter();
                filter.setContains(dto.getVersion());
                cocheCriteria.setVersion(filter);
            }
            if(!StringUtils.isBlank(dto.getCambio())){
                BooleanFilter filter = new BooleanFilter();
                switch (dto.getCambio()){
                    case "true":
                        filter.setEquals(true);
                        break;
                    case "false":
                        filter.setEquals(false);
                        break;
                        default:
                            filter.setEquals(false);
                            break;
                }
                cocheCriteria.setCambio(filter);
            }
            if(!StringUtils.isBlank(dto.getColor())){
                CocheCriteria.ColorFilter filter = new CocheCriteria.ColorFilter();
                String color = dto.getColor().toUpperCase();
                filter.setEquals(Color.valueOf(color));
                cocheCriteria.setColor(filter);
            }
            if(dto.getKmDesde()!=null || dto.getKmHasta()!=null){
                IntegerFilter filter = new IntegerFilter();
                if(dto.getKmDesde()!=null){
                    filter.setGreaterThanOrEqual(dto.getKmDesde());
                    cocheCriteria.setKm(filter);
                }
                if(dto.getKmHasta()!=null){
                    filter.setLessThanOrEqual(dto.getKmHasta());
                    cocheCriteria.setKm(filter);
                }
            }
        }
        return cocheCriteria;
    }
}
