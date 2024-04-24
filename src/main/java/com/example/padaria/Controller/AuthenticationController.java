package com.example.padaria.Controller;

import com.example.padaria.DTO.RequestDTO.AuthenticationDTO;
import com.example.padaria.DTO.RequestDTO.UsuarioDTO;
import com.example.padaria.DTO.Response.LoginResponseDTO;
import com.example.padaria.Repository.UsuarioRepository;
import com.example.padaria.model.Usuario;
import com.example.padaria.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService TokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data){
        System.out.println("merda");
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(),data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = TokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody UsuarioDTO data){
        if(usuarioRepository.findByEmail(data.email()) != null){
            return ResponseEntity.badRequest().build();
        } else {
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
            usuarioRepository.save(new Usuario(data.email(), encryptedPassword, data.nome()));
        }
        return ResponseEntity.ok().build();
    }

}
