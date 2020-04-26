create FUNCTION repreTeammatesFunc(
   p_id_player Player.player_id%type
)
RETURN SYS_REFCURSOR
AS
my_cursor SYS_REFCURSOR;
p_year char(200) := to_char(sysdate, 'YYYY');
p_year_int number;
BEGIN
    p_year_int := to_number(p_year);
    OPEN my_cursor FOR
    with t as (
        select *
        from PLAYER
        where team_id = (
            select TEAM_ID
            from PLAYER
            where PLAYER_ID = p_id_player
        )
    )
    SELECT t.PLAYER_ID, FIRST_NAME, LAST_NAME, ASSISTS, GOALS, ADDRESS_ID, TEAM_ID, YEAR_BORN
    FROM t
    join REPRE_PLAYER rp on t.PLAYER_ID = rp.PLAYER_ID
    where rp.year = p_year_int;
    RETURN my_cursor;
END;


