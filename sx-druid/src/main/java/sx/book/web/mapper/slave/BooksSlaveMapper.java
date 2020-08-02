package sx.book.web.mapper.slave;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sx.book.web.pojo.Books;


import java.util.List;

@Mapper
public interface BooksSlaveMapper extends BaseMapper<Books> {

    List<Books> selectByBooksWrapper(@Param(Constants.WRAPPER) Wrapper<Books> booksWrapper);

    //根据书名查id
    Integer selectIdByName(@Param("bookName") String bookName);

}
