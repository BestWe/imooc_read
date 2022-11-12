package com.imooc.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.reader.entity.Book;
import com.imooc.reader.entity.Evaluation;
import com.imooc.reader.entity.Member;
import com.imooc.reader.mapper.BookMapper;
import com.imooc.reader.mapper.EvaluationMapper;
import com.imooc.reader.mapper.MemberMapper;
import com.imooc.reader.service.EvaluationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Service("evaluationService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class EvaluationServiceImpl implements EvaluationService {
    @Resource
    private EvaluationMapper evaluationMapper;
    @Resource
    private MemberMapper memberMapper;
    @Resource
    private BookMapper bookMapper;
    /**
     * 按图书编号查询有效短评
     *
     * @param bookId 图书编号
     * @return 评论列表
     */
    @Override
    public List<Evaluation> selectByBookId(Long bookId) {
        Book book = bookMapper.selectById(bookId);
        QueryWrapper<Evaluation> evaluationQueryWrapper = new QueryWrapper<>();
        evaluationQueryWrapper.eq("book_id",bookId);
        evaluationQueryWrapper.eq("state","enable");
        evaluationQueryWrapper.orderByDesc("create_time");
        List<Evaluation> evaluations = evaluationMapper.selectList(evaluationQueryWrapper);
        for (Evaluation evaluation : evaluations) {
            Member member = memberMapper.selectById(evaluation.getMemberId());
            evaluation.setMember(member);
            evaluation.setBook(book);
        }
        return evaluations;
    }

    @Override
    public IPage<Evaluation> queryAllComment(Integer page, Integer pageSize) {
        Page<Evaluation> evaluationPage = new Page<>(page,pageSize);
        IPage<Evaluation> list= evaluationMapper.list(evaluationPage);
        return list;
    }

    @Override
    public void changeState(Long evaluationId, String state,String reason) {
        QueryWrapper<Evaluation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("evaluation_id",evaluationId);
        Evaluation evaluation = evaluationMapper.selectOne(queryWrapper);
        evaluation.setState(state);
        evaluation.setDisableReason(reason);
        evaluation.setDisableTime(new Date());
        evaluationMapper.updateById(evaluation);
    }
}
