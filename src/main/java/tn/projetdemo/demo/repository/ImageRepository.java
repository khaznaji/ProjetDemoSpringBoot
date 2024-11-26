package tn.projetdemo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.projetdemo.demo.entities.Comment;
import tn.projetdemo.demo.entities.Image;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {


    Optional<Image> findByIdUser(int idUser);

    void deleteByIdUser(int idUser);
}
