package tn.projetdemo.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Getter
@Setter
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     String name;
     int  idUser;

    @Lob
    @Column(name = "picByte", columnDefinition = "LONGBLOB")

    byte[] picByte;


}
