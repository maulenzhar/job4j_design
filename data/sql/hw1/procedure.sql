create or replace procedure delete_data(u_id integer)
    language 'plpgsql'
as $$
BEGIN
delete from products where  id = u_id;
END;
$$;
call delete_data(6);

create or replace function f_delete_data(u_id integer)
    returns integer
    language 'plpgsql'
as
$$
declare
result integer;
begin
delete from products where  id = u_id;
select into result 1;
return result;
end;
$$;

select f_delete_data(7);