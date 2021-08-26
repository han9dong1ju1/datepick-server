package app.hdj.datepick.domain.service;

import app.hdj.datepick.domain.model.Featured;

import java.util.List;

public interface FeaturedService {
    List<Featured> getAllFeatured();

    Featured getFeatured(Long id);
}
