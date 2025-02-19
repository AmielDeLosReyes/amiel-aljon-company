package amiel_aljon.auth_server.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import amiel_aljon.auth_server.model.dto.request.RegistrationRequest;
import amiel_aljon.auth_server.service.RegistrationService;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping( "/registration")
@AllArgsConstructor
@Slf4j
public class RegistrationController {

    private RegistrationService registrationService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register-user")
    public ResponseEntity<Map<String, String>> register(@RequestBody @Valid RegistrationRequest request) {
        log.info("Inside register()." + getClass().getSimpleName());
        String token = registrationService.register(request);

        // Create a map to hold the token in JSON format
        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/confirmation")
    public ResponseEntity<String> confirm(@RequestParam("token") String token) {
        return ResponseEntity.ok(registrationService.confirmToken(token));
    }
}
