package com.example.OceanNews.Repo.RepoImplementation;

import com.example.OceanNews.Repo.IJpaRepository;
import com.example.OceanNews.Repo.IRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RepoImp<E,ID> implements IRepo<E,ID> {
    private final IJpaRepository<E, ID> jpaRepository;
   // private final  JpaRepository<E, ID> jpaRepository;
    @Autowired
    public RepoImp(IJpaRepository<E, ID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public E add(E entity) {
        return jpaRepository.save(entity);
    }

    @Override
    public void delete(ID id) {
        if (!jpaRepository.existsById(id)) {
            throw new IllegalStateException(
                    "category with id " + id + " does not exists"
            );
        }
        jpaRepository.deleteById(id);
    }

    @Override
    public E update(E entity, ID id) {
        if (!jpaRepository.existsById(id)) {
            throw new IllegalStateException(
                    "category with id " + id + " does not exists"
            );
        }
        return jpaRepository.save(entity);
    }

    @Override
    public Iterable<E> getAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<E> GetById(ID id) {
        return jpaRepository.findById(id);
    }
}
