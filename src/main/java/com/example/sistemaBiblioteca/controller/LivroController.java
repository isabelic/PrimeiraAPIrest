package com.example.sistemaBiblioteca.controller;

import com.example.sistemaBiblioteca.model.Livro;
import com.example.sistemaBiblioteca.model.Usuario;
import com.example.sistemaBiblioteca.service.LivroService;
import com.example.sistemaBiblioteca.service.UsuarioService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/livro")
public class LivroController {

        private final LivroService service;

        public LivroController(LivroService service) {
            this.service = service;
        }

        @PostMapping
        public ResponseEntity<Livro> criarLivro(
                @RequestBody Livro livro
        ){
            Livro newUser = new Livro();
            try{
                newUser = service.criarLivro(livro);
            }catch (SQLException e){
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }

        @GetMapping
        public ResponseEntity<List<Livro>> buscarTodosLivros(){
            List<Livro> livros =new ArrayList<>();

            try{
                livros = service.listarLivros();
            }catch (SQLException e){
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.OK).body(livros);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Livro> buscarLivroPorId(@PathVariable int id){
            Livro newLivro = new Livro();
            try{
                newLivro = service.listarLivroPorID(id);
            }catch (SQLException e){
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.OK).body(newLivro);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Livro>  atualizarLivro(@PathVariable int id, @RequestBody Livro livro){
            Livro newLivro = new Livro();
            try{
                newLivro = service.atualizarLivro(id, livro);
            }catch (SQLException e){
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.OK).body(newLivro);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletarLivro(@PathVariable int id){
            Livro newLivro = new Livro();
            try{
                newLivro = service.deletarLivro(id);
            }catch (SQLException e){
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }