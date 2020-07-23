package entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Сущность описывающая ожидание клиента в конкретном офисе
 */
@Entity
@Table(name = "queue_log")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueueLog {

    @Id
    @Column
    private long id;

    @Column
    private int branchesId;

    @Column
    private LocalDate data;

    @Column
    private LocalTime startTimeOfWait;

    @Column
    private LocalTime endTimeOfWait;

    @Column
    private LocalTime endTimeOfService;
}
