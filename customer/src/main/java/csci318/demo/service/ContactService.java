package csci318.demo.service;

import csci318.demo.controller.dto.ContactDTO;
import csci318.demo.model.Contact;
import csci318.demo.repository.ContactRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public ContactDTO createContact(ContactDTO contactDTO) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactDTO, contact);
        Contact savedContact = contactRepository.save(contact);

        ContactDTO responseDTO = new ContactDTO();
        BeanUtils.copyProperties(savedContact, responseDTO);

        return responseDTO;
    }

    public List<ContactDTO> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts.stream()
                .map(contact -> {
                    ContactDTO dto = new ContactDTO();
                    BeanUtils.copyProperties(contact, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public ContactDTO getContactById(Long id) {
        Optional<Contact> contactOptional = contactRepository.findById(id);

        if (contactOptional.isPresent()) {
            ContactDTO responseDTO = new ContactDTO();
            BeanUtils.copyProperties(contactOptional.get(), responseDTO);
            return responseDTO;
        } else {
            return null; // You can return null or throw a custom exception here
        }
    }

    public ContactDTO updateContact(Long id, ContactDTO contactDTO) {
        Optional<Contact> contactOptional = contactRepository.findById(id);

        if (contactOptional.isPresent()) {
            Contact contact = contactOptional.get();
            BeanUtils.copyProperties(contactDTO, contact);
            Contact updatedContact = contactRepository.save(contact);

            ContactDTO responseDTO = new ContactDTO();
            BeanUtils.copyProperties(updatedContact, responseDTO);
            return responseDTO;
        } else {
            return null; // You can return null or throw a custom exception here
        }
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
