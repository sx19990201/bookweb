package sx.book.web.service.master.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sx.book.web.mapper.master.BooksMasterMapper;
import sx.book.web.pojo.Books;
import sx.book.web.service.master.BooksMasterService;



@Service
@DS("master")
public class BooksMasterServiceImpl extends ServiceImpl<BooksMasterMapper, Books> implements BooksMasterService {

    @Autowired
    private BooksMasterMapper booksMasterMapper;

    @Override
    public void insertBooks(Books books) {
        booksMasterMapper.insertBooks(books);
    }


}
