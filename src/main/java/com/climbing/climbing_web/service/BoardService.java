package com.climbing.climbing_web.service;

import com.climbing.climbing_web.dto.BoardDTO;
import com.climbing.climbing_web.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<BoardDTO> getList(){
        return boardRepository.getList();
    }
}
