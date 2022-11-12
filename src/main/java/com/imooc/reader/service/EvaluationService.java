package com.imooc.reader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Evaluation;

import java.util.List;

public interface EvaluationService {
    /**
     * 按图书编号查询有效短评
     * @param bookId 图书编号
     * @return 评论列表
     */
    public List<Evaluation> selectByBookId(Long bookId);

    /**
     * 查询所有图书评论
     * @return 返回所有图书评论列表
     */
    public IPage<Evaluation> queryAllComment(Integer page, Integer pageSize);

    /**
     * 修改图书评论状态
     * @param evaluationId 评论Id
     * @param state 评论状态
     */
    public void changeState(Long evaluationId,String state,String reason);
}
