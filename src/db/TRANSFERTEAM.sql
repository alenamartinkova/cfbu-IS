create function teamTransferFunc(
    p_id_team Team.team_id%type,
    p_new_league_id League.league_id%type
)
return boolean
as
    p_old_league_id League.league_id%type;
    p_old_rank Team.rank%type;
    p_new_rank Team.rank%type;
    p_same_league_exception exception;

    p_start int;
    p_end int;
begin
    select league_id, rank
    into p_old_league_id, p_old_rank
    from TEAM
    where team_id = p_id_team;

    if (p_old_league_id = p_new_league_id) then
        raise p_same_league_exception;
    end if;

    SELECT MAX(RANK)
    into p_new_rank
    from TEAM
    WHERE LEAGUE_ID = p_new_league_id;

     if (p_new_rank is null) then
        p_new_rank := 1;
    else
        p_new_rank := p_new_rank + 1;
    end if;

    select count(*)
    into p_end
    from TEAM
    where LEAGUE_ID = p_old_league_id;

    update TEAM
    set LEAGUE_ID = p_new_league_id,
        RANK = p_new_rank
    where team_id = p_id_team;

    p_start := p_old_rank + 1;

    FOR i IN p_start..p_end LOOP
        update team
        set rank = rank - 1
        where rank = i AND LEAGUE_ID = p_old_league_id;
    END LOOP;
    COMMIT;
    return true;
Exception
    WHEN p_same_league_exception then
        print('The team is already in this league.');
        ROLLBACK;
        return false;
    WHEN others then
        print('Error' || SQLCODE || SQLERRM );
        ROLLBACK;
        return false;
end;
