package sx.book.web.mapper.master;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sx.book.web.pojo.BookType;
@Mapper
public interface BookTypeMasterMapper extends BaseMapper<BookType> {

    void insertBookType(@Param("bookType") BookType bookType);


}
