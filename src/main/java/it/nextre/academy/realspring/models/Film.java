package it.nextre.academy.realspring.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class Film {

    private long id;
    private String titolo;
    private String regista;
    private int anno;

}
