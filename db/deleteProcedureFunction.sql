create
or replace procedure delete_data_procedure(u_id integer)
language 'plpgsql'
as $$
    BEGIN
            delete FROM products
            where id = u_id;
    END;
$$;

create
or replace function delete_data_function(u_id integer)
returns integer
language 'plpgsql'
as $$
    begin
        delete FROM products
        where id = u_id;
        return ROW_COUNT;
    end;
$$;