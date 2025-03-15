DELIMITER //

CREATE PROCEDURE insert_available_slots()
BEGIN
    DECLARE v_id INT DEFAULT 1;
    DECLARE v_start_time DATETIME DEFAULT '2024-12-29 09:00:00';
    DECLARE v_end_time DATETIME;
    DECLARE v_creation_date DATETIME DEFAULT CURRENT_TIMESTAMP;
    DECLARE v_commit_counter INT DEFAULT 0;

    #     TRUNCATE TABLE AVAILABLE_SLOTS;

    WHILE v_id <= 1000000 DO
        SET v_end_time = TIMESTAMPADD(HOUR, 1, v_start_time);
    INSERT INTO AVAILABLE_SLOTS (ID, START_TIME, END_TIME, IS_RESERVED, CREATION_DATE, CREATOR_USER_ID, LAST_UPDATE, UPDATER_USER_ID, VERSION)
    VALUES (v_id, v_start_time, v_end_time, 0, v_creation_date, 0, NULL, NULL, 0);
    SET v_id = v_id + 1;
    SET v_start_time = v_end_time;
    SET v_commit_counter = v_commit_counter + 1;

    IF v_commit_counter = 1000 THEN
        COMMIT;
        SET v_commit_counter = 0;
    END IF;
END WHILE;

COMMIT;
SELECT 'Inserted 1,000,000 records successfully.';
END //

DELIMITER ;

CALL insert_available_slots();
# drop procedure insert_available_slots;