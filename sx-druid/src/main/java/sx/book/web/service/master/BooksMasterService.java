package sx.book.web.service.master;

import com.baomidou.mybatisplus.extension.service.IService;
import sx.book.web.pojo.Books;

public interface BooksMasterService extends IService<Books> {

    void insertBooks(Books books);

}
