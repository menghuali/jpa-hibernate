-- Courses
INSERT INTO COURSE(ID, FULLNAME, CREATED_TIME, LAST_UPDATED_TIME)
VALUES(
        COURSE_ID_SEQ.NEXTVAL,
        'Become A Spider in 50 Steps',
        SYSDATE(),
        SYSDATE()
    );
INSERT INTO COURSE(ID, FULLNAME, CREATED_TIME, LAST_UPDATED_TIME)
VALUES(
        COURSE_ID_SEQ.NEXTVAL,
        'Become A Bat in 100 Steps',
        SYSDATE(),
        SYSDATE()
    );
INSERT INTO COURSE(ID, FULLNAME, CREATED_TIME, LAST_UPDATED_TIME)
VALUES(
        COURSE_ID_SEQ.NEXTVAL,
        'Become A Superman in 200 Steps',
        SYSDATE(),
        SYSDATE()
    );
INSERT INTO COURSE(ID, FULLNAME, CREATED_TIME, LAST_UPDATED_TIME)
VALUES(
        2000,
        'Become A superhero in X Steps',
        SYSDATE(),
        SYSDATE()
    );
-- Passports
INSERT INTO PASSPORT(ID, PASSPORT_NUM)
VALUES(PASSPORT_ID_SEQ.NEXTVAL, 'MARVEL_1');
INSERT INTO PASSPORT(ID, PASSPORT_NUM)
VALUES(PASSPORT_ID_SEQ.NEXTVAL, 'DC_1');
INSERT INTO PASSPORT(ID, PASSPORT_NUM)
VALUES(PASSPORT_ID_SEQ.NEXTVAL, 'DC_2');
-- Students
-- Students data must be created after Passport data in order to follow the foreign key restrictions
INSERT INTO STUDENT(ID, FULLNAME, PASSPORT_ID, STREET, CITY)
VALUES(STUDENT_ID_SEQ.NEXTVAL, 'Peter Parker', 1000, '20 Ingram St.', 'New York');
INSERT INTO STUDENT(ID, FULLNAME, PASSPORT_ID, STREET, CITY)
VALUES(STUDENT_ID_SEQ.NEXTVAL, 'Bruce Wayne', 1001, '1007 Mountain Drive', 'Gotham');
INSERT INTO STUDENT(ID, FULLNAME, PASSPORT_ID, STREET, CITY)
VALUES(STUDENT_ID_SEQ.NEXTVAL, 'Kal-EI', 1002, '1938 Sullivan Place', 'Metropolis');
-- Reviews
INSERT INTO REVIEW(ID, RATING, REVIEW_DESC, COURSE_ID)
VALUES(REVIEW_ID_SEQ.NEXTVAL, '4', 'Good!', 1000);
INSERT INTO REVIEW(ID, RATING, REVIEW_DESC, COURSE_ID)
VALUES(REVIEW_ID_SEQ.NEXTVAL, '5', 'Excellent!', 1001);
INSERT INTO REVIEW(ID, RATING, REVIEW_DESC, COURSE_ID)
VALUES(REVIEW_ID_SEQ.NEXTVAL, '4', 'Good!', 1001);
INSERT INTO REVIEW(ID, RATING, REVIEW_DESC, COURSE_ID)
VALUES(REVIEW_ID_SEQ.NEXTVAL, '3', 'OK...', 1000);
-- Student/Course Relationships
INSERT INTO STUDENT_COURSE(STUDENT_ID, COURSE_ID)
VALUES(1000, 1000);
INSERT INTO STUDENT_COURSE(STUDENT_ID, COURSE_ID)
VALUES(1000, 1001);
INSERT INTO STUDENT_COURSE(STUDENT_ID, COURSE_ID)
VALUES(1000, 1002);
INSERT INTO STUDENT_COURSE(STUDENT_ID, COURSE_ID)
VALUES(1001, 1001);
INSERT INTO STUDENT_COURSE(STUDENT_ID, COURSE_ID)
VALUES(1002, 1002);