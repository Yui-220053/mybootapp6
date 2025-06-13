package jp.te4a.spring.boot.myapp9.mybootapp9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("books")
public class BookController {
    @Autowired
    BookService bookService;

    @ModelAttribute
    BookForm setUpForm() {
        return new BookForm();
    }

    @GetMapping
    String list(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    @PostMapping(path = "create")
    String create(BookForm form, Model model) {
        bookService.save(form);
        return "redirect:/books";
    }

    @GetMapping("edit")
    public String editForm(@RequestParam Integer id, Model model) {
        BookForm bookForm = bookService.findOne(id);
        model.addAttribute("bookForm", bookForm);
        return "books/edit";
    }

    @PostMapping("edit")
    public String edit(BookForm form) {
        bookService.update(form);
        return "redirect:/books";
    }

    @PostMapping("delete")
    public String delete(@RequestParam Integer id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PostMapping(path = "edit", params = "goToTop")
    String goToTop() {
        return "redirect:/books";
    }
}
