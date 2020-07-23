package repository;

import entity.QueueLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QueueLogRepository extends JpaRepository<QueueLog, Integer> {

    /**
     * фильтрует по входящим параметрам
     *
     * @param idAtmOffice -   id банкомата
     * @param dayOfWeek   -   число, где понедельник это 1, а воскресенье 7, для use EXTRACT 7 -> 0
     * @param hourOfDay   -   число от 0 до 23
     * @return -   отфильтрованные список QueueLog
     */
    @Query(value = "SELECT * FROM queue_log AS q " +
            "WHERE q.branches_id = :idAtmOffice " +
            "  AND EXTRACT(DOW FROM q.data) = :dayOfWeek" +
            "  AND EXTRACT(HOUR FROM q.start_time_of_wait) = :hourOfDay",
            nativeQuery = true)
    List<QueueLog> getWorkLoadForPredict(int idAtmOffice, int dayOfWeek, int hourOfDay);

}
