package com.example.demo.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    public final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void addMatch(int match_id, int home, int away, int start) {
        if (!matchRepository.existsById(match_id)) {
            matchRepository.save(new Match(match_id, home, away, start));
        } else {
            matchRepository.updateMatchStart(match_id, home, away, start);
        }
    }

    public void addMatch(int match_id, int finish) {
        if (!matchRepository.existsById(match_id)) {
            matchRepository.save(new Match(match_id, finish));
        } else {
            matchRepository.updateMatchFinish(match_id, finish);
        }
    }

    public void checkStartAndFinish() {
        matchRepository.checkStartAndFinish();
    }

    public int getMinId() {
        return matchRepository.getMinId();
    }

    public int getMaxId() {
        return matchRepository.getMaxId();
    }

    public boolean isThere(int id) {
        return matchRepository.existsById(id);
    }

    public int getHome(int id) {
        return matchRepository.findById(id).get().getId_home();
    }

    public int getAway(int id) {
        return matchRepository.findById(id).get().getId_away();
    }

    public int getStart(int id) {
        return matchRepository.findById(id).get().getStart();
    }

    public int getEnd(int id) {
        return matchRepository.findById(id).get().getFinish();
    }
}
