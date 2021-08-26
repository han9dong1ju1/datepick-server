package app.hdj.datepick.domain.repository;

import app.hdj.datepick.domain.model.Featured;

import java.util.List;

public interface FeaturedRepository {

    List<Featured> findAll();

    Featured findById(Long id);
}
