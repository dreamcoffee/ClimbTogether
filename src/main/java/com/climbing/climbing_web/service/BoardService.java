package com.climbing.climbing_web.service;

import com.climbing.climbing_web.dto.BoardDTO;
import com.climbing.climbing_web.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<BoardDTO> getList(){
        return boardRepository.getList();
    }

    public void savePost(BoardDTO boardDTO){
        log.info("[로그] BoardDTO : " + boardDTO);
        boardRepository.save(boardDTO);
    }
}
