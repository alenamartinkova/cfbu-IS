create procedure repreTeammates(
    p_id_player Player.player_id%type
)
as
    p_curr_year char := to_char(sysdate, 'YYYY');

begin
    with t as (
        select *
        from PLAYER
        where team_id = (
            select TEAM_ID
            from PLAYER
            where PLAYER_ID = p_id_player
        )
    )
    select *
    from t
    join REPRE_PLAYER rp on t.PLAYER_ID = rp.PLAYER_ID
    where rp.year = p_curr_year;
end;


