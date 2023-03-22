package capstone_project.ReadingForum_Backend.Controller;

import capstone_project.ReadingForum_Backend.Service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService bookService;
}
