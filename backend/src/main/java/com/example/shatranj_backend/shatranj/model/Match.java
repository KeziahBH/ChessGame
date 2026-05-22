package com.example.shatranj_backend.shatranj.model;

import com.example.shatranj_backend.shatranj.model.DTO.Move;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import java.util.List;

@Getter
@Setter
public abstract class Match {
    private Long mid;
    private User playerWhite;
    private User playerBlack;
    private Board board;
    private List<Move> movesList;
    private ResponseEntity<?> startMatch;
}
