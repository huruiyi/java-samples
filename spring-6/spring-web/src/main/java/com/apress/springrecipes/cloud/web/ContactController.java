package com.apress.springrecipes.cloud.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apress.springrecipes.cloud.ContactRepository;
import com.apress.springrecipes.cloud.model.Contact;

@Controller
@RequestMapping("/contact")
public class ContactController {

	private final ContactRepository contactRepository;

	@Autowired
	public ContactController(@Qualifier("mapBasedContactRepository") ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@Autowired
	ContactRepository contactRepository0;

	@Autowired
	@Resource(name = "jdbcContactRepository")
	ContactRepository contactRepository1;

	@Autowired
	@Qualifier("mapBasedContactRepository")
	ContactRepository contactRepository2;

	// 必须结合@Autowired一起使用
	@Qualifier("mapBasedContactRepository")
	ContactRepository contactRepository3;

	@GetMapping
	public String list(Model model) {
		List<Contact> list0 = contactRepository0.findAll();
		List<Contact> list1 = contactRepository1.findAll();
		List<Contact> list2 = contactRepository2.findAll();

		System.out.printf("list0 size:%d\n", list0.size());
		System.out.printf("list1 size:%d\n", list1.size());
		System.out.printf("list2 size:%d\n", list2.size());
		model.addAttribute("contacts", contactRepository.findAll());
		return "list";
	}

	@GetMapping("/new")
	public String newContact(Model model) {
		model.addAttribute(new Contact());
		return "contact";
	}

	@PostMapping("/new")
	public String newContact(@ModelAttribute Contact contact) {
		contactRepository.save(contact);
		return "redirect:/contact";
	}

}
