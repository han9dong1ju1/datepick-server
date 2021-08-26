package app.hdj.datepick.controller;


import app.hdj.datepick.domain.service.FeaturedService;
import app.hdj.datepick.dto.featured.FeaturedDetailResponseDto;
import app.hdj.datepick.dto.featured.FeaturedMetaResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/featured")
public class FeaturedController {

    private final ModelMapper modelMapper;
    private final FeaturedService featuredService;

    @GetMapping("")
    public List<FeaturedMetaResponseDto> getAllFeatured(){
        return featuredService.getAllFeatured().stream()
                .map(featured -> modelMapper.map(featured, FeaturedMetaResponseDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{featuredId}")
    public FeaturedDetailResponseDto getFeaturedByIdWithDetail(@PathVariable Long featuredId){
        return modelMapper.map(featuredService.getFeatured(featuredId), FeaturedDetailResponseDto.class);
    }

}
