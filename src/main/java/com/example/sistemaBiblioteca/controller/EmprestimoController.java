package com.example.sistemaBiblioteca.controller;

import com.example.sistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRequisicaoDto;
import com.example.sistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRespostaDto;
import com.example.sistemaBiblioteca.dto.usuario.CriacaoUsuarioRequisicaoDto;
import com.example.sistemaBiblioteca.model.Emprestimo;
import com.example.sistemaBiblioteca.model.Usuario;
import com.example.sistemaBiblioteca.service.EmprestimoService;
import com.example.sistemaBiblioteca.service.UsuarioService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    private final EmprestimoService service;

    public EmprestimoController(EmprestimoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CriacaoEmprestimoRespostaDto> criarEmprestimo(
            @RequestBody CriacaoEmprestimoRequisicaoDto requisicaoDto
            ){
        Emprestimo newEmprestimo = new Emprestimo();
        try{
           return ResponseEntity.status(HttpStatus.CREATED)
                   .body(service.criarEmprestimo(requisicaoDto));
        }catch (SQLException e){
            e.printStackTrace();
        }
       return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> buscarTodosEmprestimos(){
        List<Emprestimo> emprestimos =new ArrayList<>();

        try{
            emprestimos = service.listarEmprestimo();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(emprestimos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarEmprestimoPorID(
            @PathVariable int id
    ){
        Emprestimo newEmprestimo = new Emprestimo();
        try{
            newEmprestimo = service.listarEmprestimoPorId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(newEmprestimo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprestimo> atualizarEmprestimo(@PathVariable int id, @RequestBody Emprestimo emprestimo){
        Emprestimo newEmprestimo = new Emprestimo();
        try{
            newEmprestimo = service.atualizarEmprestimo(id, emprestimo);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(newEmprestimo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmprestimo(@PathVariable int id){
        Emprestimo newEmprestimo = new Emprestimo();
        try{
            newEmprestimo = service.deletarEmprestimo(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
