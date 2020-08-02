package sx.book.web.service.slave;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import sx.book.web.pojo.BookType;

import java.util.List;

public interface BookTypeSlaveService extends IService<BookType> {

    @Override
    List<BookType> list(Wrapper<BookType> queryWrapper);

    //根据分类名查询id
    Integer selectIdByName(String typeName);
}
