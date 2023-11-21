//package com.example.OceanNews.Repo;
//
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public abstract class Repo<E,ID> implements IJpaRepository<E, ID> {
//    public E add(E entity){
//        return save(entity);
//    }
//    public E update(ID id,E entity){
//        if (!existsById(id)) {
//            throw new IllegalStateException(
//                    "category with id " + id + " does not exists"
//            );
//        }
//      return save(entity);
//    }
//    public List<E> getAll(){
//        return findAll();
//    }
//    public E findByIdOrThrow(ID id){
//        return findById(id).orElseThrow(() -> new IllegalStateException(
//                "category with id " + id + " does not exists"
//        ));
//    }
//
//    public void deleteOrThrow(ID id) {
//        if (!existsById(id)) {
//            throw new IllegalStateException(
//                    "category with id " + id + " does not exists"
//            );
//        }
//        deleteById(id);
//    }
//}
//
