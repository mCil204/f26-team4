package com.example.CleanSweep1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.CleanSweep1.dto.ProfileForm;
import com.example.CleanSweep1.entity.Customer;
import com.example.CleanSweep1.service.CustomerService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class CustomerUIController {

    private static final String SESSION_KEY = "customerId";

    private final CustomerService service;

    public CustomerUIController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/customers/new")
    public String registerForm(Model model) {
        return showForm(model, new ProfileForm(), new HashMap<>(),
                "/customers", "Create Your Profile", false);
    }

    @PostMapping("/customers")
    public String register(@Valid @ModelAttribute("form") ProfileForm form,
                           BindingResult result, Model model,
                           HttpSession session, RedirectAttributes ra) {
        Map<String, String> errors = fieldErrors(result);
        if (form.getPassword() == null || form.getPassword().isBlank()) {
            errors.put("password", "Password is required");
        } else if (!form.getPassword().equals(form.getConfirmPassword())) {
            errors.put("confirmPassword", "Passwords do not match");
        }
        if (!errors.isEmpty()) {
            return showForm(model, form, errors, "/customers", "Create Your Profile", false);
        }
        try {
            Customer saved = service.create(toCustomer(form));
            session.setAttribute(SESSION_KEY, saved.getCustomerId());
            ra.addFlashAttribute("message", "Welcome, " + saved.getFirstName() + "! Your profile was created.");
            return "redirect:/profile";
        } catch (ResponseStatusException e) {
            if (e.getStatusCode().value() == HttpStatus.CONFLICT.value()) {
                errors.put("email", e.getReason());
                return showForm(model, form, errors, "/customers", "Create Your Profile", false);
            }
            throw e;
        }
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model, RedirectAttributes ra) {
        Long id = (Long) session.getAttribute(SESSION_KEY);
        if (id == null) {
            return notSignedIn(ra);
        }
        model.addAttribute("customer", service.getById(id));
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String editForm(HttpSession session, Model model, RedirectAttributes ra) {
        Long id = (Long) session.getAttribute(SESSION_KEY);
        if (id == null) {
            return notSignedIn(ra);
        }
        Customer c = service.getById(id);
        ProfileForm form = new ProfileForm();
        form.setFirstName(c.getFirstName());
        form.setLastName(c.getLastName());
        form.setEmail(c.getEmail());
        form.setPhone(c.getPhone());
        form.setStreet(c.getStreet());
        form.setCity(c.getCity());
        form.setState(c.getState());
        form.setZip(c.getZip());
        return showForm(model, form, new HashMap<>(), "/profile/edit", "Edit Your Profile", true);
    }

    @PostMapping("/profile/edit")
    public String edit(@Valid @ModelAttribute("form") ProfileForm form,
                       BindingResult result, Model model,
                       HttpSession session, RedirectAttributes ra) {
        Long id = (Long) session.getAttribute(SESSION_KEY);
        if (id == null) {
            return notSignedIn(ra);
        }
        Map<String, String> errors = fieldErrors(result);
        boolean changingPassword = form.getPassword() != null && !form.getPassword().isBlank();
        if (changingPassword && !form.getPassword().equals(form.getConfirmPassword())) {
            errors.put("confirmPassword", "Passwords do not match");
        }
        if (!errors.isEmpty()) {
            return showForm(model, form, errors, "/profile/edit", "Edit Your Profile", true);
        }
        try {
            service.update(id, toCustomer(form));
            ra.addFlashAttribute("message", "Your profile was updated");
            return "redirect:/profile";
        } catch (ResponseStatusException e) {
            if (e.getStatusCode().value() == HttpStatus.CONFLICT.value()) {
                errors.put("email", e.getReason());
                return showForm(model, form, errors, "/profile/edit", "Edit Your Profile", true);
            }
            throw e;
        }
    }

@GetMapping("/")
public String home() {
    return "index";
}

@GetMapping("/login")
public String loginForm(HttpSession session) {
    if (session.getAttribute(SESSION_KEY) != null) {
        return "redirect:/profile";
    }
    return "login";
}

@PostMapping("/login")
public String login(@RequestParam String email, @RequestParam String password,
                    HttpSession session, Model model) {
    try {
        Customer customer = service.login(email.trim(), password);
        session.setAttribute(SESSION_KEY, customer.getCustomerId());
        return "redirect:/profile";
    } catch (ResponseStatusException e) {
        if (e.getStatusCode().value() == HttpStatus.UNAUTHORIZED.value()) {
            model.addAttribute("error", e.getReason());
            model.addAttribute("email", email);
            return "login";
        }
        throw e;
    }
}

@PostMapping("/logout")
public String logout(HttpSession session, RedirectAttributes ra) {
    session.removeAttribute(SESSION_KEY);
    ra.addFlashAttribute("message", "You have been logged out.");
    return "redirect:/login";
}

    private String showForm(Model model, ProfileForm form, Map<String, String> errors,
                            String action, String title, boolean isEdit) {
        model.addAttribute("form", form);
        model.addAttribute("errors", errors);
        model.addAttribute("formAction", action);
        model.addAttribute("formTitle", title);
        model.addAttribute("isEdit", isEdit);
        return "customer-form";
    }

    private Map<String, String> fieldErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(e -> errors.putIfAbsent(e.getField(), e.getDefaultMessage()));
        return errors;
    }

    private Customer toCustomer(ProfileForm form) {
        Customer c = new Customer();
        c.setFirstName(form.getFirstName());
        c.setLastName(form.getLastName());
        c.setEmail(form.getEmail());
        c.setPassword(form.getPassword());
        c.setPhone(form.getPhone());
        c.setStreet(form.getStreet());
        c.setCity(form.getCity());
        c.setState(form.getState());
        c.setZip(form.getZip());
        return c;
    }

    private String notSignedIn(RedirectAttributes ra) {
    ra.addFlashAttribute("error", "Please log in first.");
    return "redirect:/login";
    }

    @ExceptionHandler(ResponseStatusException.class)
    public String handleError(ResponseStatusException e, Model model, HttpServletResponse response) {
        response.setStatus(e.getStatusCode().value());
        model.addAttribute("errorMessage", e.getReason());
        return "error";
    }
}