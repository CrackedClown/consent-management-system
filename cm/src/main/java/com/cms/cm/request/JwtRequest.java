package com.cms.cm.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
