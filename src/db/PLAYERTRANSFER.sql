create procedure playerTransfer(
    p_id_player Player.player_id%type,
    p_new_team_id Team.team_id%type
)
as
    p_old_team_id Team.team_id%type;
    p_old_league_id League.league_id%type;
    p_new_league_id League.league_id%type;

    p_same_team_exception exception;
begin
    select team_id
    into p_old_team_id
    from PLAYER
    where player_id = p_id_player;

    if (p_old_team_id = p_new_team_id) then
        raise p_same_team_exception;
    end if;

    select LEAGUE_ID
    into p_old_league_id
    from team
    where team_id = p_old_team_id;

    select LEAGUE_ID
    into p_new_league_id
    from team
    where team_id = p_new_team_id;

    if (p_old_league_id = p_new_league_id) then
        update PLAYER
        set TEAM_ID = p_new_team_id
        where player_id = p_id_player;
    else
        update PLAYER
        set
            TEAM_ID = p_new_team_id,
            GOALS = 0,
            ASSISTS = 0
        where player_id = p_id_player;
    end if;
    COMMIT;
Exception
    WHEN p_same_team_exception then
        print('The player is already in this team.');
    WHEN others then
        print('Error' || SQLCODE || SQLERRM );
    ROLLBACK;
end;

