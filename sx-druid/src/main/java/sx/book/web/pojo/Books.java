package sx.book.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("books")
public class Books extends Model<Books> {
    /**
     * ybatis-plus的insert方法，在底层会默认生成一个Long类型的UUID，这就导致跟数据库里的id不一样发生错误
     * 所以要把这个默认自增的主键给禁了
     */
    //小说id
    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;
    //小说名
    private String bookName;
    //小说url
    private String bookURL;
    //作者
    private String author;
    //简介
    private String info;
    //分类id
    private Integer typeId;
    //图片
    private String imgURL;
    //小说对应的章节集合
    //private List<Chapter> chapterList;


    @Override
    protected Serializable pkVal() {
        return id;
    }
}
