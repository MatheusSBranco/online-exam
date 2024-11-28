package site.onlineexam.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import site.onlineexam.model.Tag;
import site.onlineexam.repository.TagRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TagServiceTest {

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagService tagService;

    private Tag tag;

    @BeforeEach
    void setUp() {
        tag = new Tag();
        tag.setId(1L);
        tag.setName("Science");
    }

    @Test
    void shouldAddTag() {
        tagService.addTag(tag);
        verify(tagRepository, times(1)).save(tag);
    }

    @Test
    void shouldReturnAllTags() {
        when(tagRepository.findAll()).thenReturn(Collections.singletonList(tag));

        List<Tag> retrievedTags = tagService.getAllTags();

        assertEquals(1, retrievedTags.size());
        assertEquals("Science", retrievedTags.get(0).getName());
    }

    @Test
    void shouldReturnTagById() {
        when(tagRepository.findById(anyLong())).thenReturn(Optional.of(tag));

        Tag retrievedTag = tagService.getTagById(1L);

        assertNotNull(retrievedTag);
        assertEquals("Science", retrievedTag.getName());
    }

    @Test
    void shouldThrowExceptionWhenTagNotFoundById() {
        when(tagRepository.findById(anyLong())).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            tagService.getTagById(1L);
        });

        assertEquals("Tag not found with id: 1", exception.getMessage());
    }

    @Test
    void shouldUpdateTag() {
        Tag updatedTag = new Tag();
        updatedTag.setName("Mathematics");

        when(tagRepository.findById(anyLong())).thenReturn(Optional.of(tag));

        tagService.updateTag(1L, updatedTag);

        verify(tagRepository, times(1)).save(tag);
        assertEquals("Mathematics", tag.getName());
    }

    @Test
    void shouldDeleteTag() {
        when(tagRepository.findById(anyLong())).thenReturn(Optional.of(tag));

        tagService.deleteTag(1L);

        verify(tagRepository, times(1)).delete(tag);
    }
}