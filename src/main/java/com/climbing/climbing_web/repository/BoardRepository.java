package com.climbing.climbing_web.repository;

import com.climbing.climbing_web.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final SqlSessionTemplate sql;

    // 커뮤니티 페이지 글 불러오기
    public List<BoardDTO> getList(){
        return sql.selectList("Board.getList");
    }

    public List<BoardDTO> getLatest5Posts(){
        return sql.selectList("Board.getLatest5Posts");
    }

    // 상세 페이지 불러오기
    public BoardDTO detail(Integer id) {
        return sql.selectOne("Board.detail", id);
    }

    // 글 저장
    public void save(BoardDTO boardDTO){
        sql.insert("Board.save", boardDTO);
    }
}
