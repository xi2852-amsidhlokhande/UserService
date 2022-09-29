package com.amsidh.mvc.model;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserInfo {
    private Integer id;
    private String firstName;
    private String lastName;
    private String city;
    private String address;
    private Date row_update_time;
    private String data;

}
