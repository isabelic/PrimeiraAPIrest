package com.example.sistemaBiblioteca.controller;

import com.example.sistemaBiblioteca.dto.usuario.CriacaoUsuarioRequisicaoDto;
import com.example.sistemaBiblioteca.dto.usuario.CriacaoUsuarioRespostaDto;
import com.example.sistemaBiblioteca.model.Usuario;
import com.example.sistemaBiblioteca.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CriacaoUsuarioRespostaDto> criarUsuario(
            @RequestBody CriacaoUsuarioRequisicaoDto requisicaoUsuario
            ){
        Usuario newUser = new Usuario();
        try{
           return ResponseEntity.status(HttpStatus.CREATED)
                   .body(service.criarUsuario(requisicaoUsuario));
        }catch (SQLException e){
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping
    public ResponseEntity<List<CriacaoUsuarioRespostaDto>> buscarTodosUsuarios(){
        List<Usuario> usuarios =new ArrayList<>();

        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.listarUsuarios());
        }catch (SQLException e){
            e.printStackTrace();
        }
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriacaoUsuarioRespostaDto> buscarUsuarioPorID(@PathVariable int id){
        Usuario newUsuario = new Usuario();
        try{
           return ResponseEntity.status(HttpStatus.OK)
                   .body(service.listarUsuarioPorId(id));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(
            @PathVariable int id,
            @RequestBody Usuario usuario
    ){
        Usuario newUsuario = new Usuario();
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.atualizarUsuario(id,usuario));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(
            @PathVariable int id
        ){
        Usuario newUsuario = new Usuario();
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
