package sx.book.web.service.slave.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sx.book.web.mapper.slave.BooksSlaveMapper;
import sx.book.web.pojo.Books;
import sx.book.web.service.slave.BooksSlaveService;

import java.util.List;

@Service
@DS("slave")
public class BooksSlaveServiceImpl extends ServiceImpl<BooksSlaveMapper,Books> implements BooksSlaveService {

    @Autowired
    private BooksSlaveMapper booksSlaveMapper;

    @Override
    public List<Books> list(Wrapper<Books> queryWrapper) {
        return super.list(queryWrapper);
    }

    @Override
    public Integer selectIdByName(String bookName) {
        return booksSlaveMapper.selectIdByName(bookName);
    }
}
