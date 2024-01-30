package com.prashantghule.springbootrestfulwebservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Schema(
        description = "UserDto Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    // user first name should not be null or empty
    @Schema(description = "User First Name")
    @NotEmpty(message = "user first name should not be null or empty")
    private String firstName;

    // user last name should not be null or empty
    @Schema(description = "User Last Name")
    @NotEmpty(message = "user last name should not be null or empty")
    private String lastName;

    // user email should not be null or empty
//    email should be valid address
    @Schema(description = "User Email")
    @NotEmpty(message = "user email should not be null or empty")
    @Email(message = "Email address should be valid")
    private String email;
}
