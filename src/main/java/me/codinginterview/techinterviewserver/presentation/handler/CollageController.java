package me.codinginterview.techinterviewserver.presentation.handler;

import lombok.RequiredArgsConstructor;
import me.codinginterview.techinterviewserver.domain.CollageService;
import me.codinginterview.techinterviewserver.dto.NewCollageDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/collages")
public class CollageController {

    private final CollageService collageService;

    @PostMapping
    void createCollage(@RequestBody NewCollageDto newCollageDto) {
        collageService.createdCollage(newCollageDto);
    }

    @DeleteMapping("/{collageId}")
    void deleteCollage(@PathVariable Long collageId) {
        collageService.deleteCollage(collageId);
    }
}
