DECLARE
    v_id             NUMBER := 1;
    v_start_time     DATE   := TO_DATE('2024-12-29 09:00:00', 'YYYY-MM-DD HH24:MI:SS');
    v_end_time       DATE;
    v_creation_date  DATE   := SYSDATE;
    v_commit_counter NUMBER := 0;
BEGIN

    execute immediate 'truncate table AVAILABLE_SLOTS';

    FOR i IN 1..1000000
        LOOP
            v_end_time := v_start_time + INTERVAL '1' HOUR;

            INSERT INTO AVAILABLE_SLOTS (ID, START_TIME, END_TIME, IS_RESERVED, CREATION_DATE, CREATOR_USER_ID,LAST_UPDATE, UPDATER_USER_ID, VERSION)
            VALUES (v_id, v_start_time, v_end_time, 0, v_creation_date, 0, NULL, NULL, 0);

            v_id := v_id + 1;
            v_start_time := v_end_time;
            v_commit_counter := v_commit_counter + 1;

            IF v_commit_counter = 1000 THEN
                COMMIT;
                v_commit_counter := 0;
            END IF;
        END LOOP;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Inserted 1,000,000 records successfully.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
