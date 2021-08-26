package app.hdj.datepick.domain.service;

import app.hdj.datepick.domain.model.Featured;
import app.hdj.datepick.domain.repository.FeaturedRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FeaturedServiceImp implements FeaturedService {

    private final FeaturedRepository featuredRepository;

    public List<Featured> getAllFeatured() {
        return featuredRepository.findAll();
    }

    @Override
    public Featured getFeatured(Long id) {
        return featuredRepository.findById(id);
    }
}
