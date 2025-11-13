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

    // 커뮤니티 페이지 글 목록 불러오기
    public List<BoardDTO> getList(){
        return boardRepository.getList();
    }

    // 게시글 상세보기
    public BoardDTO detail(Integer id) {
        return boardRepository.detail(id);
    }

    // 게시글 저장
    public void savePost(BoardDTO boardDTO){
        log.info("[로그] BoardDTO : " + boardDTO);
        boardRepository.save(boardDTO);
    }
}
