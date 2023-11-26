package com.example.OceanNews.Repo;

import com.example.OceanNews.DTO.BaseEntity;
import com.example.OceanNews.Serivices.BaseEntityService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@Transactional
public abstract class Repo<E extends BaseEntity,ID extends Serializable>
        implements BaseEntityService<E, ID> {
    @Autowired
    IJpaRepository<E,ID> repo;
    @Override
    public E add(E e) {
        return repo.save(e);
    }

    @Override
    public String hardDelete(ID id) {
        E post=repo.findByPostID(id);
        assert post != null;
        repo.delete(post);
        return "Deleted successfully";
    }

    @Override
    public String softDelete(ID id) {
        E post=repo.findByPostID(id);
        assert post != null;
        post.setStatus(4L);
        repo.save(post);
        return "Deleted successfully";
    }

    @Override
    public E getById(ID id) {
        return repo.findByPostID(id);
    }

    @Override
    public Iterable<E> getAll() {
        return repo.findAll();
    }

    @Override
    public boolean update(ID id) {
        E existingPost= repo.findByPostID(id);
        assert existingPost != null;
        // check if ID is 0,1,2,3
        if ((Long)id >= 0 && (Long)id <= 3) {
            existingPost.setStatus((Long) id);
            repo.save(existingPost);
            return true;
        }
        return false;
    }

    @Override
    public boolean edit(ID id, E e) {
        E existingPost= repo.findByPostID(id);
        assert existingPost != null;
        existingPost.setTitle(e.getTitle());
        existingPost.setContent(e.getContent());
        existingPost.setCreator(e.getCreator());
        repo.save(existingPost);
        return false;
    }

    @Override
    public Boolean checkIdExists(ID id) {
        return repo.existsById(id);
    }

    @Override
    public E findId(ID id) {
        return repo.findByPostID(id);
    }

    @Override
    public String restore(ID id) {
        E post=repo.findByPostID(id);
        assert post != null;
        post.setStatus(1L);
        repo.save(post);
        return null;
    }
//    public E add(E entity){
//        return save(entity);
//    }
//    public E update(ID id,E entity){
//        if (!existsById(id)) {
//            throw new IllegalStateException(
//                    "category with id " + id + " does not exist"
//            );
//        }
//      return save(entity);
//    }
//    public List<E> getAll(){
//        return findAll();
//    }
//    public E findByIdOrThrow(ID id){
//        return findById(id).orElseThrow(() -> new IllegalStateException(
//                "category with id " + id + " does not exist"
//        ));
//    }
//
//    public void deleteOrThrow(ID id) {
//        if (!existsById(id)) {
//            throw new IllegalStateException(
//                    "category with id " + id + " does not exist"
//            );
//        }
//        deleteById(id);
//    }
}

