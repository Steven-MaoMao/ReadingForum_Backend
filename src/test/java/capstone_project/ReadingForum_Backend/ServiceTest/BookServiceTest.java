package capstone_project.ReadingForum_Backend.ServiceTest;

import capstone_project.ReadingForum_Backend.Service.IBookRateService;
import capstone_project.ReadingForum_Backend.Service.IBookService;
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
}
