package sx.book.web.mapper.master;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sx.book.web.pojo.Books;


@Mapper
public interface BooksMasterMapper extends BaseMapper<Books> {

    void insertBooks(@Param("books") Books books);




}
