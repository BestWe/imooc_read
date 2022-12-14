package com.imooc.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.reader.entity.Book;
import com.imooc.reader.entity.Evaluation;
import com.imooc.reader.entity.MemberReadState;
import com.imooc.reader.mapper.BookMapper;
import com.imooc.reader.mapper.EvaluationMapper;
import com.imooc.reader.mapper.MemberReadStateMapper;
import com.imooc.reader.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("bookService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;
    @Resource
    private MemberReadStateMapper memberReadStateMapper;
    @Resource
    private EvaluationMapper evaluationMapper;
    @Override
    /**
     * 分页查询图书
     * @param categoryId 分类编号
     * @param order 排序方式
     * @param page 页号
     * @param rows 每页记录数
     * @return 分页对象
     */
    public IPage<Book> paging(Long categoryId, String order, Integer page, Integer rows) {
        Page<Book> bookPage = new Page<>(page, rows);
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if (categoryId!=null&&categoryId!=-1){
            queryWrapper.eq("category_id",categoryId);
        }
        if (order !=null){
            if (order.equals("quantity")){
                queryWrapper.orderByDesc("evaluation_quantity");
            }else if (order.equals("score")){
                queryWrapper.orderByDesc("evaluation_score");
            }
        }
        IPage<Book> bookIPage = bookMapper.selectPage(bookPage, queryWrapper);
        return bookIPage;
    }

    /**
     * @param bookId 图书编号
     * @return  图书对象
     */
    @Override
    public Book selectById(Long bookId) {
        Book book = bookMapper.selectById(bookId);
        return book;
    }

    /**
     * 更新图书评分/评价数量
     */
    @Override
    @Transactional
    public void updateEvaluation() {
        bookMapper.updateEvaluation();
    }

    /**
     * @param book
     * @return 图书对象
     */
    @Override
    @Transactional
    public Book createBook(Book book) {
        bookMapper.insert(book);
        return book;
    }

    /**
     * 更新图书
     *
     * @param book 新图书数据
     * @return 更新后的数据
     */
    @Override
    @Transactional
    public Book updateBook(Book book) {
        bookMapper.updateById(book);
        return book;
    }
    /**
     * 删除图书及相关数据
     *
     * @param bookId 图书编号
     */
    @Override
    @Transactional
    public void deleteBook(Long bookId) {
        bookMapper.deleteById(bookId);
        QueryWrapper<MemberReadState> memberReadStateMapperQueryWrapper = new QueryWrapper<>();
        memberReadStateMapperQueryWrapper.eq("book_id",bookId);
        memberReadStateMapper.delete(memberReadStateMapperQueryWrapper);
        QueryWrapper<Evaluation> evaluationQueryWrapper = new QueryWrapper<>();
        evaluationQueryWrapper.eq("book_id",bookId);
        evaluationMapper.delete(evaluationQueryWrapper);
    }
}
