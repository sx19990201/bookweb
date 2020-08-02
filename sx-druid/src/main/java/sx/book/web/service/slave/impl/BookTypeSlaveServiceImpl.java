package sx.book.web.service.slave.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sx.book.web.mapper.slave.BookTypeSlaveMapper;
import sx.book.web.pojo.BookType;
import sx.book.web.service.slave.BookTypeSlaveService;


import java.util.List;

@Service
@DS("slave")
public class BookTypeSlaveServiceImpl extends ServiceImpl<BookTypeSlaveMapper, BookType> implements BookTypeSlaveService {

    @Autowired
    private BookTypeSlaveMapper bookTypeSlaveMapper;

    @Override
    public List<BookType> list(Wrapper<BookType> queryWrapper) {
        return super.list(queryWrapper);
    }

    @Override
    public Integer selectIdByName(String typeName) {
        return bookTypeSlaveMapper.selectIdByName(typeName);
    }


}
