package com.example.demo.event;

import com.example.demo.goal.GoalService;
import com.example.demo.league.LeagueService;
import com.example.demo.match.MatchService;
import com.example.demo.team.Team;
import com.example.demo.team.TeamService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping(path="teams")
public class EventController {

    private final LeagueService leagueService;
    private final TeamService teamService;
    private final MatchService matchService;
    private final GoalService goalService;

    private final Scanner input;
    private String line = null;
    private JSONObject event_obj = null;
    private JSONObject data_obj = null;
    private String event_type = null;

    private int home;
    private int away;

    @Autowired
    public EventController(LeagueService leagueService, TeamService teamService, MatchService matchService, GoalService goalService) throws FileNotFoundException {
        this.leagueService = leagueService;
        this.teamService = teamService;
        this.matchService = matchService;
        this.goalService = goalService;
        this.input = new Scanner(new File("src/events.jsonl"));
    }

    @GetMapping
    public void storeDB() {
        parseData();
        cleanData();
        getResults();
    }

    @GetMapping(value = "{id}")
    public List<Team> getLeaderBoard(@PathVariable("id") int id_league) {
        return teamService.getLeaderBoard(id_league);
    }

    public void parseData() {
        while(input.hasNext()) {
            line = input.nextLine();
            System.out.println(line);
            event_obj = new JSONObject(line);
            event_type = event_obj.getString("event_type");
            data_obj = event_obj.getJSONObject("event_data");
            switch (event_type) {
                case "match_start":
                    leagueService.addLeague(data_obj.getInt("league_id"));
                    teamService.addTeam(data_obj.getInt("league_id"),data_obj.getString("home_club"));
                    teamService.addTeam(data_obj.getInt("league_id"),data_obj.getString("away_club"));
                    home = teamService.getIdByName(data_obj.getString("home_club"));
                    away = teamService.getIdByName(data_obj.getString("away_club"));
                    matchService.addMatch(data_obj.getInt("match_id"), home, away, event_obj.getInt("event_timestamp"));
                    break;
                case "match_end":
                    matchService.addMatch(data_obj.getInt("match_id"),event_obj.getInt("event_timestamp"));
                    break;
                case "goal":
                    goalService.addGoal(data_obj.getInt("match_id"), data_obj.getString("scoring_club"),
                            event_obj.getInt("event_timestamp"));
                    break;
                default:
            }
        }
        System.out.println("parsing done");
    }

    public void cleanData() {
        matchService.checkStartAndFinish();
        System.out.println("cleaning done");
    }

    public void getResults() {
        int min = matchService.getMinId();
        int max = matchService.getMaxId();
        int home_id;
        int away_id;
        int home_goals;
        int away_goals;
        int home_points;
        int away_points;
        int start;
        int end;

        for (int i = min; i <= max; i++) {
            if (matchService.isThere(i)) {
                home_id = matchService.getHome(i);
                away_id = matchService.getAway(i);
                start = matchService.getStart(i);
                end = matchService.getEnd(i);
                home_goals = goalService.getHomeGoals(i, start, end);
                away_goals = goalService.getAwayGoals(i, start, end);

                if (home_goals > away_goals) {
                    home_points = 3;
                    away_points = 0;
                } else if (home_goals < away_goals) {
                    home_points = 0;
                    away_points = 3;
                } else {
                    home_points = 1;
                    away_points = 1;
                }

                teamService.updateTeamResult(home_id, home_points, home_goals - away_goals);
                teamService.updateTeamResult(away_id, away_points, away_goals - home_goals);
            }
        }
        System.out.println("results done");
    }

}
