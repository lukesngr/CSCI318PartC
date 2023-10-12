package csci318.demo.controller;

import csci318.demo.model.Contact;
import csci318.demo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    // Create a new contact
    @PostMapping("/")
    public Contact createContact(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    // Get all contacts
    @GetMapping("/")
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    // Get a specific contact by ID
    @GetMapping("/{id}")
    public Optional<Contact> getContactById(@PathVariable Long id) {
        return contactRepository.findById(id);
    }

    // Update a contact
    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact updatedContact) {
        updatedContact.setId(id);
        return contactRepository.save(updatedContact);
    }

    // Delete a contact
    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
    }
}
