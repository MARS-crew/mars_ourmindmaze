package mars.ourmindmaze.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars.ourmindmaze.common.dto.SwaggerConfig;
import mars.ourmindmaze.dto.user.RequestTokenDto;
import mars.ourmindmaze.dto.user.RequestUserLoginDto;
import mars.ourmindmaze.dto.user.RequestUserSaveDto;
import mars.ourmindmaze.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth API", description = "인증 API")
public class AuthController {
    private final UserService userService;

    @Operation(summary = "Save User", description = "유저 생성하기")
    @ApiResponse(responseCode = "400", description = "Parameter type is incorrect")
    @ApiResponse(responseCode = "401", description = "Bad Credentials, JWT token expires")
    @ApiResponse(responseCode = "401", description = "Access denied")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody RequestUserSaveDto dto) {
        return userService.save(dto);
    }

    @Operation(summary = "Login User", description = "유저 로그인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sign in success", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.LOGIN_SUCCESS))),
            @ApiResponse(responseCode = "401", description = SwaggerConfig.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(name = SwaggerConfig.UNAUTHORIZED_ERROR, value = SwaggerConfig.TOKEN_EXPIRED_REPONSE)})),
            @ApiResponse(responseCode = "403", description = SwaggerConfig.FORBIDDEN_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.UNAUTHORIZED_ACCESS_DENIED_RESPONSE))),
            @ApiResponse(responseCode = "500", description = SwaggerConfig.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RequestUserLoginDto dto) {
        return userService.login(dto);
    }

    @Operation(summary = "Get Token", description = "Access Token 재발급")
    @ApiResponse(responseCode = "400", description = "Parameter type is incorrect")
    @ApiResponse(responseCode = "401", description = "Bad Credentials, JWT token expires")
    @ApiResponse(responseCode = "401", description = "Access denied")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @PostMapping("/token")
    public ResponseEntity<?> token(@RequestBody RequestTokenDto dto) {
        return userService.getTokenByRefreshToken(dto);
    }
}
