package app.hdj.datepick.domain.service;

import app.hdj.datepick.domain.model.FeaturedDetail;
import app.hdj.datepick.domain.model.FeaturedMeta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeaturedService {
    public List<FeaturedMeta> findAll();

    public FeaturedDetail findByIdWithDetail(Long id);
}
