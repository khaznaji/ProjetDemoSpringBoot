package tn.projetdemo.demo.controllers;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.projetdemo.demo.entities.Image;
import tn.projetdemo.demo.repository.ImageRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping("image")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ImageController {

    @Autowired
    ImageRepository imageRepository;


    @GetMapping(path = { "/get/{idUser}" })
    public ResponseEntity<Image> getImageByIdUser(@PathVariable("idUser") int idUser) {
        final Optional<Image> retrievedImage = imageRepository.findByIdUser(idUser);
        if (retrievedImage.isPresent()) {
            Image img = retrievedImage.get();
            img.setPicByte(decompressBytes(img.getPicByte()));
            return ResponseEntity.ok(img);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{idUser}")
    @Transactional
    public ResponseEntity<String> deleteImageByIdUser(@PathVariable("idUser") int idUser) {
        try {
            imageRepository.deleteByIdUser(idUser);
            return ResponseEntity.ok("Image deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting image");
        }
    }

    @PutMapping("/update/{idUser}")
    @Transactional
    public ResponseEntity<Image> updateImage(
            @RequestParam("imageFile") MultipartFile file,
            @PathVariable("idUser") int idUser) {
        try {
            imageRepository.deleteByIdUser(idUser);

            Image img = new Image();
            img.setName(file.getOriginalFilename());
            img.setPicByte(compressBytes(file.getBytes()));
            img.setIdUser(idUser);
            imageRepository.save(img);

            return ResponseEntity.ok(img);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}
