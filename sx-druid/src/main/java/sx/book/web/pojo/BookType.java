package sx.book.web.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("booktype")
public class BookType extends Model<BookType> {
    //分类id
    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;
    //分类名
    private String typeName;
    //小说分类下对应的小说集合
    //private List<Books> booksList;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
