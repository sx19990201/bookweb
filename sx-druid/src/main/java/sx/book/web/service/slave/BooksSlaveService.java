package sx.book.web.service.slave;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import sx.book.web.pojo.Books;

import java.util.List;

public interface BooksSlaveService extends IService<Books> {

    @Override
    List<Books> list(Wrapper<Books> queryWrapper);

    //根据书名查id
    Integer selectIdByName(String bookName);
}
