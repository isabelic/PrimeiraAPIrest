package com.example.sistemaBiblioteca.controller;

import com.example.sistemaBiblioteca.dto.livro.CriacaoLivroRequisicaoDto;
import com.example.sistemaBiblioteca.dto.livro.CriacaoLivroRespostaDto;
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
        public ResponseEntity<CriacaoLivroRespostaDto> criarLivro(
                @RequestBody CriacaoLivroRequisicaoDto requisicaoDto
                ){
            Livro newUser = new Livro();
            try{
                return  ResponseEntity.status(HttpStatus.CREATED)
                        .body(service.criarLivro(requisicaoDto));
            }catch (SQLException e){
                e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        }

        @GetMapping
        public ResponseEntity<List<CriacaoLivroRespostaDto>> buscarTodosLivros(){
            List<Livro> livros =new ArrayList<>();

            try{
                return ResponseEntity.status(HttpStatus.OK)
                        .body(service.listarLivros());
            }catch (SQLException e){
                e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        }

        @GetMapping("/{id}")
        public ResponseEntity<CriacaoLivroRespostaDto> buscarLivroPorId(@PathVariable int id){
            Livro newLivro = new Livro();
            try{
               return ResponseEntity.status(HttpStatus.OK)
                       .body(service.listarLivroPorID(id));
            }catch (SQLException e){
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        @PutMapping("/{id}")
        public ResponseEntity<CriacaoLivroRespostaDto>  atualizarLivro(@PathVariable int id, @RequestBody Livro livro){
            Livro newLivro = new Livro();
            try{
                return ResponseEntity.status(HttpStatus.OK)
                        .body(service.atualizarLivro(id, livro));
            }catch (SQLException e){
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build() ;
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<CriacaoLivroRespostaDto> deletarLivro(@PathVariable int id){
            Livro newLivro = new Livro();
            try{
              service.deletarLivro(id);
              return ResponseEntity.status(HttpStatus.NO_CONTENT)
                      .build();
            }catch (SQLException e){
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }