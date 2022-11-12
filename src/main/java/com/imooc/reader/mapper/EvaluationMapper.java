package com.imooc.reader.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Evaluation;

import java.util.List;

public interface EvaluationMapper extends BaseMapper<Evaluation> {
    IPage<Evaluation> list(IPage<Evaluation> page);

    void changeState(Long evaluationId, String state, String reason);
}
