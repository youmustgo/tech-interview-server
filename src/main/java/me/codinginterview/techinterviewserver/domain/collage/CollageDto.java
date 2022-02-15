package me.codinginterview.techinterviewserver.domain.collage;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CollageDto {
    String title;
    String body;
    Boolean opened;
    List<Long> postIds;
}
