package org.png.dto;

import lombok.Data;

@Data
public class CitizenDTO {
    Long id;
    String fullName;
    String address;
    String pinCode;
    String gender;
}
