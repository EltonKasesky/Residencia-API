package org.serratec.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.serratec.backend.dto.FuncionarioResponseDTO;
import org.serratec.backend.entity.Funcionario;
import org.serratec.backend.entity.Usuario;
import org.serratec.backend.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;

    @Operation(summary = "Lista todos os funcionarios", description = "A resposta lista os dados dos funcionarios id, nome, data de nascimento e salario.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Funcionario.class), mediaType = "application/json") }, description = "Retorna todos os funcionarios"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") }
    )
    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> listar(){
        return ResponseEntity.ok().body(service.listar());
    }

    @Operation(summary = "Lista todos os funcionarios por paginas", description = "A resposta lista em paginas os dados dos funcionarios id, nome, data de nascimento e salario.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Funcionario.class), mediaType = "application/json") }, description = "Retorna todos os funcionarios em paginas"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") }
    )
    @GetMapping("/pagina")
    public Page<FuncionarioResponseDTO> listarPorPagina(@PageableDefault(size = 10, page = 1, sort = {"nome", "salario"}, direction = Sort.Direction.ASC) Pageable pageable){
        return service.listarPorPagina(pageable);
    }

    @Operation(summary = "Lista todos os funcionarios baseado em um filtro de salario", description = "A resposta lista funcionarios que estão no filtro de busca, e retorna os dados dos funcionarios id, nome, data de nascimento e salario.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Funcionario.class), mediaType = "application/json") }, description = "Retorna todos os funcionarios baseado em um filtro de salario"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") }
    )
    @GetMapping("/salario")
    public Page<FuncionarioResponseDTO> listarPorPaginaFaixaSalarial(
            @RequestParam(defaultValue = "1000") Double faixa1,
            @RequestParam(defaultValue = "2000") Double faixa2,
            @PageableDefault(size = 10, page = 1, sort = {"nome", "salario"}, direction = Sort.Direction.ASC) Pageable pageable
    ){
        return service.findBySalarioBetween(faixa1,faixa2,pageable);
    }

    @Operation(summary = "Lista todos os funcionarios baseado na busca por nome", description = "A resposta lista funcionarios que estão no filtro de busca, e retorna os dados dos funcionarios id, nome, data de nascimento e salario.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Funcionario.class), mediaType = "application/json") }, description = "Retorna todos os funcionarios baseado na busca por nome"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") }
    )
    @GetMapping("/nome")
    public Page<FuncionarioResponseDTO> buscarPorNome(@RequestParam(defaultValue = "") String busca, Pageable pageable){
        return service.findByNomeContainingIgnoreCase(busca,pageable);
    }
}
