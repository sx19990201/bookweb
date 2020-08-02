package sx.book.web.service.master.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sx.book.web.mapper.master.BookTypeMasterMapper;
import sx.book.web.pojo.BookType;
import sx.book.web.service.master.BookTypeMasterService;



@Service
@DS("master")
public class BookTypeMasterServiceImpl extends ServiceImpl<BookTypeMasterMapper, BookType> implements BookTypeMasterService {

    @Autowired
    private BookTypeMasterMapper bookTypeMasterMapper;

    @Override
    public void insertBookType(BookType bookType) {
        bookTypeMasterMapper.insertBookType(bookType);
    }
}
