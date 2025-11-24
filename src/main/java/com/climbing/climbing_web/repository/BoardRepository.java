package com.climbing.climbing_web.repository;

import com.climbing.climbing_web.dto.BoardDTO;
import com.climbing.climbing_web.dto.CommentDTO;
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

    public List<BoardDTO> getMyPagePosts(String memberId){
        return sql.selectList("Board.getMyPagePosts", memberId);
    }

    // 상세 페이지 불러오기
    public BoardDTO detail(Integer id) {
        return sql.selectOne("Board.detail", id);
    }

    // 글 저장
    public void save(BoardDTO boardDTO){
        sql.insert("Board.save", boardDTO);
    }

    // 글 수정
    public void update(BoardDTO boardDTO){
        log.info("게시글 수정 요청{}", boardDTO.getPostid());
        sql.update("Board.update", boardDTO);
    }

    // 글 삭제
    public void delete(Integer id){
        log.info("게시글 삭제 요청{}", id);
        sql.delete("Board.delete", id);
    }

    // 댓글 추가
    public void saveComment(CommentDTO commentDTO){
        log.info("댓글 추가 요청");
        sql.insert("Board.saveComment", commentDTO);
    }

    public List<CommentDTO> getComment(int postId){
        log.info("댓글 불러오기 요청");
        return sql.selectList("Board.getComment", postId);
    }
}
