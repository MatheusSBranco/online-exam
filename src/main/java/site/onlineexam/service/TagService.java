package site.onlineexam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import site.onlineexam.model.Tag;
import site.onlineexam.repository.TagRepository;

@Service
public class TagService {
    
    private TagRepository tagRepository;

    public TagService(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    public void addTag(Tag tag) {
        tagRepository.save(tag);
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(Long tagId) {
        Optional<Tag> optionalTag = tagRepository.findById(tagId);
        if (optionalTag.isPresent()) {
            return optionalTag.get();
        } else {
            throw new EntityNotFoundException("Tag not found with id: " + tagId);
        }
    }

    public void updateTag(Long tagId, Tag updatedTag) {
        Tag tag = getTagById(tagId);
        tag.setName(updatedTag.getName());
        tagRepository.save(tag);
    }

    public void deleteTag(Long tagId) {
        Tag tag = getTagById(tagId);
        tagRepository.delete(tag);
    }
}
