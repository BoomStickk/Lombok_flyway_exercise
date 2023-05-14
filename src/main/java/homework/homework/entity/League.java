package homework.homework.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="leagues")
public class League {
    @Id
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "league")
    private List<Player> students;
}
