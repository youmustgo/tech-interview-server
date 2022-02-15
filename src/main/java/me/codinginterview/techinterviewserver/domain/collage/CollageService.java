package me.codinginterview.techinterviewserver.domain.collage;

import lombok.RequiredArgsConstructor;
import me.codinginterview.techinterviewserver.infra.entity.collage.Collage;
import me.codinginterview.techinterviewserver.infra.entity.collage.CollageRepository;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CollageService {

    private final CollageRepository collageRepository;

    public void createdCollage(CollageDto newCollageDto) {
        Long viewerId = Long.valueOf(1);
        Collage newCollage = Collage.builder()
                .title(newCollageDto.getTitle())
                .body(newCollageDto.getBody())
                .userId(viewerId)
                .opened(newCollageDto.getOpened())
                .postIds(newCollageDto.getPostIds()).build();
        collageRepository.save(newCollage);
    }

    public void deleteCollage(Long collageId) {
        Long viewerId = Long.valueOf(1);
        Collage collage = collageRepository.findById(collageId).orElseThrow(() -> new EntityNotFoundException("존재하지않는 콜라주입니다."));
        if(viewerId != collage.getOwner().getId()) {
            throw new IllegalArgumentException("해당 콜라주의 소유자가 아닙니다.");
        }
        collageRepository.delete(collage);
    }
}