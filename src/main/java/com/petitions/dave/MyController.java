package com.petitions.dave;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MyController {
    private static final Logger logger = LoggerFactory.getLogger(MyController.class);
    private List<User> userList = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Sample data
        userList.add(new User("John Doe", "john.doe@example.com"));
        userList.add(new User("Jane Smith", "jane.smith@example.com"));
        userList.add(new User("Bob Johnson", "bob.johnson@example.com"));
        userList.add(new User("Elis Clay", "e.clay@example.com"));
        userList.add(new User("kyle Reese", "kyle.Reese@example.com"));
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/submit")
    public String showSubmitForm(Model model) {
        model.addAttribute("user", new User());
        return "submit";
    }

    @PostMapping("/submit")
    public String submitForm(@ModelAttribute("user") User user) {
        userList.add(user);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("userList", userList);    
        logger.info("User List in Model: {}", userList);
        return "list";
    }
    

    @GetMapping("/search")
    public String showSearchForm(Model model) {
        logger.info("Showing Search Form");
        return "search";
    }

    @PostMapping("/search")
    public String search(@RequestParam String searchTerm, Model model) {
        String searchTermLowerCase = searchTerm.toLowerCase();
        List<User> searchResults = userList.stream()
                .filter(user ->
                        user.getName().toLowerCase().contains(searchTermLowerCase) ||
                        user.getEmail().toLowerCase().contains(searchTermLowerCase))
                .collect(Collectors.toList());
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("userList", searchResults);
        logger.info("Search Term: {}", searchTerm);
        logger.info("User List in Model: {}", searchResults);
        return "list";
    }
}