create procedure changeWFCAddress(
    p_id_wfc WFC.wfc_id%type,
    p_id_new_address ADDRESS.address_id%type
)
as
    p_address_exists int;
    p_address_count int;

    p_address_not_exists exception;
    p_address_already_used exception;
begin
    SELECT count(*)
    INTO p_address_exists
    FROM ADDRESS
    WHERE address_id = p_id_new_address;

    if (p_address_exists = 0) then
        raise p_address_not_exists;
    end if;

    SELECT count(*)
    INTO p_address_count
    FROM WFC
    WHERE address_id = p_id_new_address
    AND WFC.year > WFC.year - 10;

    if (p_address_count > 0) then
        raise p_address_already_used;
    end if;

    UPDATE WFC
    SET address_id = p_id_new_address
    WHERE wfc_id = p_id_wfc;

    DELETE
    FROM WFC_REPRE
    WHERE wfc_id = p_id_wfc;
    COMMIT;
EXCEPTION
    WHEN p_address_not_exists then
        print('Address does not exists.');
    WHEN p_address_already_used then
        print('Address already used in past 10 years.');
    WHEN others then
        print('Error' || SQLCODE || SQLERRM );
    ROLLBACK;
end;
/

