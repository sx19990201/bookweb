package sx.book.web.service.master;

import com.baomidou.mybatisplus.extension.service.IService;
import sx.book.web.pojo.BookType;


public interface BookTypeMasterService extends IService<BookType> {

    void insertBookType(BookType bookType);
}
