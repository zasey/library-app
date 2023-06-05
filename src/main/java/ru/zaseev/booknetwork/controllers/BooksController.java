package ru.zaseev.booknetwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zaseev.booknetwork.dao.BookDAO;
import ru.zaseev.booknetwork.dao.PersonDAO;
import ru.zaseev.booknetwork.models.Book;
import ru.zaseev.booknetwork.services.BooksService;
import ru.zaseev.booknetwork.services.PeopleService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    private BooksService booksService;
    private PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String index(@RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
                        @RequestParam(value = "sort_by_year", required = false) boolean sortByYear,
                        Model model){

        List<Book> books;
        if(sortByYear)
            if(page != null && booksPerPage != null)
                books = booksService.sortPageableIndex(page, booksPerPage);
            else
                books = booksService.sortIndex();
        else
            if(page != null && booksPerPage != null)
                books = booksService.pageableIndex(page, booksPerPage);
            else
                books = booksService.index();

        model.addAttribute("books", books);

        return "books/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("book", booksService.read(id));
        model.addAttribute("people", peopleService.index());
        model.addAttribute("personWhoTookTheBook", booksService.personWhoTookTheBook(id));
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book", booksService.read(id));
        return "books/edit";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @GetMapping("/search")
    public String searchPage(){
        return "books/search";
    }

    @PostMapping("/search")
    public String search(@ModelAttribute("search_request") String search_request, Model model){
        model.addAttribute("foundBook", booksService.findByTitleStartingWith(search_request));
        return "books/search";
    }

    @PostMapping("/{id}/select")
    public String select(@PathVariable("id") int bookId, @ModelAttribute("personId") int personId){
        booksService.setPersonId(bookId, personId);
        return "redirect:/books/" + bookId;
    }

    @PostMapping("/{id}/free")
    public String free(@PathVariable("id") int bookId){
        booksService.removePersonId(bookId);
        return "redirect:/books/" + bookId;
    }

    @PostMapping("{id}/edit")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "/books/edit";
        booksService.update(id, book);
        return "redirect:/books";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "/books/new";
        booksService.save(book);
        return "redirect:/books";
    }

    @PostMapping("{id}/remove")
    public String remove(@PathVariable("id") int id ,@ModelAttribute("book") Book book){
        booksService.delete(id);
        return "redirect:/books";
    }
}
