package db;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "alfa_battle")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtmOffice {

    @Id
    @Column
    private Integer id;

    @Column
    private String title;

    @Column
    private Double lon;

    @Column
    private Double lat;

    @Column
    private String address;
}
