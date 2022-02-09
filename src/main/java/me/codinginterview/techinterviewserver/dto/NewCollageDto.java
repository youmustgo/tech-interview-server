package me.codinginterview.techinterviewserver.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewCollageDto {
    String title;
    String body;
    Boolean opened;
    List<Long> postIds;
}
