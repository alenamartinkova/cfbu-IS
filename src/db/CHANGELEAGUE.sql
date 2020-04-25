create procedure changeLeague(
    p_name League.name%type,
    p_division League.division%type,
    p_old_league_id League.league_id%type
)
as
    p_new_id League.league_id%type;
begin
    SELECT max(LEAGUE_ID) + 1
    into p_new_id
    from LEAGUE;

    INSERT INTO LEAGUE
    VALUES (p_new_id, p_division, p_name);

    UPDATE TEAM
    set LEAGUE_ID = p_new_id
    where LEAGUE_ID = p_old_league_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        print('Error' || SQLCODE || SQLERRM );
    ROLLBACK;
end;


