create procedure transferTeam(
    p_id_team Team.team_id%type,
    p_new_league_id League.league_id%type
)
as
    p_old_league_id League.league_id%type;
    p_rank Team.rank%type;
    p_same_league_exception exception;

    p_start int;
    p_end int;
begin
    select league_id, rank
    into p_old_league_id, p_rank
    from TEAM
    where team_id = p_id_team;

    if (p_old_league_id = p_new_league_id) then
        raise p_same_league_exception;
    end if;

    update TEAM
    set LEAGUE_ID = p_new_league_id
    where team_id = p_id_team;

    p_start := p_rank + 1;

    select count(*)
    into p_end
    from TEAM
    where LEAGUE_ID = p_old_league_id;

    FOR i IN p_start..p_end LOOP
        update team
        set rank = rank - 1
        where rank = i AND LEAGUE_ID = p_old_league_id;
    END LOOP;
    COMMIT;
Exception
    WHEN p_same_league_exception then
        print('The team is already in this league.');
    WHEN others then
        print('Error' || SQLCODE || SQLERRM );
    ROLLBACK;
end;

