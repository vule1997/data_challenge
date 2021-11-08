package com.example.demo.match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Match SET id_home=?2, id_away=?3, start=?4 WHERE id_match=?1")
    void updateMatchStart(int id, int home, int away, int start);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Match SET finish=?2 WHERE id_match=?1")
    void updateMatchFinish(int id, int finish);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Match WHERE finish=0 OR start=0")
    void checkStartAndFinish();

    @Query("SELECT MIN(m.id_match) FROM Match m")
    int getMinId();

    @Query("SELECT MAX(m.id_match) FROM Match m")
    int getMaxId();
}
