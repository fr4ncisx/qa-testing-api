package github.francisx.simpleapi.infrastructure.security.controller;

import github.francisx.simpleapi.infrastructure.security.dto.response.AccessTokenResponse;
import github.francisx.simpleapi.infrastructure.security.dto.request.TokenRequest;
import github.francisx.simpleapi.infrastructure.security.service.ITokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Token Creation Controller",
        description = "Este controlador permite crear tokens(NO JWT) con roles aleatorios para acceder a los endpoints que lo requieran")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth/")
public class TokenController {
    private final ITokenService tokenService;

    @Operation(summary = "Create token with param",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Devuelve un JSON con el token",
                    content =
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccessTokenResponse.class))),
                    @ApiResponse(responseCode = "401",
                            description = "No autorizado por secret key incorrecta",
                            content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "400",
                            description = "Solicitud incorrecta",
                            content = @Content(schema = @Schema()))
            })
    @PostMapping("/token/param")
    public ResponseEntity<AccessTokenResponse> createToken(@RequestParam String secretKey){
        return ResponseEntity.ok(tokenService.createToken(secretKey));
    }

    @Operation(summary = "Create token with json body",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Devuelve un JSON con el token",
                            content =
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AccessTokenResponse.class))),
                    @ApiResponse(responseCode = "401",
                            description = "No autorizado por secret key incorrecta",
                            content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "400",
                            description = "Solicitud incorrecta",
                            content = @Content(schema = @Schema()))
            })
    @PostMapping("/create")
    public ResponseEntity<AccessTokenResponse> createTokenByBody(@RequestBody @Valid TokenRequest tokenRequest){
        return ResponseEntity.ok(tokenService.createToken(tokenRequest.getSecretKey()));
    }

}
