package com.jing.forum.service;

import com.jing.forum.dao.CommentMapper;
import com.jing.forum.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;

    public List<Comment> findCommentsByEntity(int entityType, int entityId, int offset, int limit) {
        return commentMapper.selectCommentsByEntity(entityType, entityId, offset, limit);
    }

    public int findCommentCOunt(int entityType, int entityId) {
        return commentMapper.selectCountByEntity(entityType, entityId);
    }


}
