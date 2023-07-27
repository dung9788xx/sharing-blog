package com.nhom2.sharingblog.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateProfileDTO {
    @NotEmpty(message = "Name is required")
    private String name;
    @NotNull(message = "Gender is required") @Min(0) @Max(1)
    private Integer gender;
    @NotEmpty(message = "Address is required")
    private String address;
}