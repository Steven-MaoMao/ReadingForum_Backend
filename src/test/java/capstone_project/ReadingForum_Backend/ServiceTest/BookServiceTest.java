package capstone_project.ReadingForum_Backend.ServiceTest;

import capstone_project.ReadingForum_Backend.Service.IBookCommentService;
import capstone_project.ReadingForum_Backend.Service.IBookRateService;
import capstone_project.ReadingForum_Backend.Service.IBookService;
import capstone_project.ReadingForum_Backend.Service.ITagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class BookServiceTest {
    @Autowired
    private IBookService bookService;
    @Autowired
    private IBookRateService bookRateService;
    @Autowired
    private IBookCommentService bookCommentService;
    @Autowired
    private ITagService tagService;

    @Test
    public void selectFavouriteByPageTest() throws IOException {
        int id = 7;
        int start = 1;

        System.out.println(bookService.selectFavouriteByPage(id, start));
    }

    @Test
    public void selectFavouriteNumTest() throws IOException {
        int id = 7;

        System.out.println(bookService.selectFavouriteNum(id));
    }

    @Test
    public void selectMyRate() throws IOException {
        System.out.println(bookRateService.selectRate(1, 7));
    }

    @Test
    public void selectCommentByPageTest() throws IOException {
        System.out.println(bookCommentService.selectCommentByPage(1, 0));
    }

    @Test
    public void searchBookTest() throws IOException {
        System.out.println(bookService.searchBook("01", 0));
    }

    @Test
    public void selectTagTest() throws IOException {
        System.out.println(tagService.selectAll());
    }

    @Test
    public void seletTopFiveTagTest() throws IOException {
        System.out.println(tagService.selectTopFive());
    }

    @Test
    public void recommendTest() throws IOException {
        System.out.println(bookService.selectTopFiveByTag(1));
    }
    @Test
    public void selectBookByTagPageTest() throws IOException {
        System.out.println(bookService.selectBookByTagPage(2, 0));
    }
}
