package homework.homework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String game;
    private int level;
    private int age;

    @ManyToOne
    @JoinColumn(name = "league_id",referencedColumnName = "id")
    private League league;

}
