package com.concesionario.cochesbackend.service;

import com.concesionario.cochesbackend.criteria.CocheCriteria;
import com.concesionario.cochesbackend.model.Coche;
import com.concesionario.cochesbackend.model.Coche_;
import com.concesionario.cochesbackend.model.Marca_;
import com.concesionario.cochesbackend.model.Modelo_;
import com.concesionario.cochesbackend.repository.CocheRepository;
import io.github.jhipster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CocheService extends QueryService<Coche> {

    @Autowired
    CocheRepository cocheRepository;

    public Page<Coche> findByCriteria(CocheCriteria cocheCriteria, Pageable pageable){
        final Specification<Coche> specification = createSpecification(cocheCriteria);
        Page<Coche> coches = cocheRepository.findAll(specification, pageable);
        return coches;
    }

    private Specification<Coche> createSpecification(CocheCriteria criteria){
        Specification<Coche> specification = Specification.where(null);
        if(criteria != null){
            if(criteria.getVersion()!=null){
                specification =
                        specification.and(buildStringSpecification(criteria.getVersion(), Coche_.version));
            }
            if(criteria.getColor()!=null){
                specification =
                        specification.and(buildSpecification(criteria.getColor(), Coche_.color));
            }
            if(criteria.getKm()!=null){
                specification =
                        specification.and(buildRangeSpecification(criteria.getKm(), Coche_.km));
            }
            if(criteria.getCambio()!=null){
                specification =
                        specification.and(buildSpecification(criteria.getCambio(), Coche_.cambio));
            }
            if(criteria.getModelo()!=null){
                specification =
                        specification
                                .and(buildReferringEntitySpecification(criteria.getModelo(), Coche_.modelo, Modelo_.nombre));
            }
            /*if(criteria.getModelo()!=null){
                specification =
                        specification.and(buildSpecification(criteria.getModelo(),
                                root -> root.join(Coche_.modelo, JoinType.LEFT).get(Modelo_.nombre)));
            }*/
            if(criteria.getMarca()!=null){
                specification =
                        specification.and(buildSpecification(criteria.getMarca(),
                                root->root.join(Coche_.modelo, JoinType.LEFT)
                                        .join(Modelo_.marca, JoinType.LEFT).get(Marca_.nombre)));
            }
        }
        return specification;
    }
}
