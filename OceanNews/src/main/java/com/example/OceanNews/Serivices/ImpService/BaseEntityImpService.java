//package com.example.OceanNews.Serivices.ImpService;
//
//import com.example.OceanNews.DTO.BaseEntity;
//import com.example.OceanNews.Repo.IJpaRepository;
//import com.example.OceanNews.Serivices.BaseEntityService;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.Serializable;
//
//@Service
//@Transactional
//public abstract class BaseEntityImpService<E extends BaseEntity,ID extends Serializable>
//        implements BaseEntityService<E,ID> {
//    @Autowired
//    IJpaRepository<E,ID> iJpaRepository;
//    @Override
//    public E add(E baseEntity) {
//        return iJpaRepository.save(baseEntity);
//    }
//
//    @Override
//    public String hardDelete(ID serializable) {
//        return null;
//    }
//
//    @Override
//    public String softDelete(ID serializable) {
//        return null;
//    }
//
//    @Override
//    public E getById(ID serializable) {
//        return null;
//    }
//
//    @Override
//    public Iterable<E> getAll() {
//        return null;
//    }
//
//    @Override
//    public boolean update(ID serializable) {
//        return true;
//    }
//
//    @Override
//    public boolean edit(ID serializable, E baseEntity) {
//        return true;
//    }
//
//    @Override
//    public Boolean checkIdExists(ID serializable) {
//        return null;
//    }
//
//    @Override
//    public E findId(ID serializable) {
//        return null;
//    }
//
//    @Override
//    public String restore(ID serializable) {
//        return null;
//    }
//}
