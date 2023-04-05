package site.onlineexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import site.onlineexam.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    
    Tag findByName(String name);
}
