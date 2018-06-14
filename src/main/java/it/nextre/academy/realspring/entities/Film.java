package it.nextre.academy.realspring.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name="film")
public class Film {

    @Id    //chiave primaria della tabella
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idfilm")
    private long id;

    @NotNull(message = "Titolo non valido")
    @Column(length = 255)
    @Size(min = 3,max = 255, message = "Lunghezza titolo non valida")
    private String titolo;

    private String regista;

    @Column(length = 4)
    @Size(min = 1870,max = 2300, message = "Anno non valido")
    private int anno;

}
