package sx.book.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sx.book.web.pojo.BookType;
import sx.book.web.service.master.BookTypeMasterService;
import sx.book.web.service.slave.BookTypeSlaveService;

import java.util.List;

@RestController
@RequestMapping("/api/bookType")
public class BookTypeController {

    @Autowired
    private BookTypeMasterService bookTypeMasterService;
    @Autowired
    private BookTypeSlaveService bookTypeSlaveService;

    /**
     * 查询全部分类
     * @return
     */
    @CrossOrigin    //该注解处理跨域请求
    @GetMapping()
    @RequestMapping("/getAll")
    public List<BookType> getAll(){
        return bookTypeSlaveService.list();
    }

}
