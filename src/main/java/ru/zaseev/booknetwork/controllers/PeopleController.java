package ru.zaseev.booknetwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zaseev.booknetwork.models.Person;
import ru.zaseev.booknetwork.services.PeopleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("people", peopleService.index());
        return "people/people";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.read(id));
        model.addAttribute("bookOfPerson", peopleService.booksOfPerson(id));
        return "people/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.read(id));
        return "people/edit";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }

    @PostMapping("{id}/edit")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "/people/"+ id + "/edit";
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "/people/new";
        peopleService.save(person);
        return "redirect:/people";
    }

    @PostMapping("{id}/remove")
    public String remove(@PathVariable("id") int id ,@ModelAttribute("person") Person person){
        peopleService.delete(id);
        return "redirect:/people";
    }
}
