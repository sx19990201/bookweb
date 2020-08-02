package sx.book.web.mapper.slave;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sx.book.web.pojo.BookType;


import java.util.List;

@Mapper
public interface BookTypeSlaveMapper extends BaseMapper<BookType> {

    List<BookType> selectByBookTypeWrapper(@Param(Constants.WRAPPER) Wrapper<BookType> bookTypeWrapper);

    //根据分类名查询id
    Integer selectIdByName(@Param("typeName") String typeName);


}
