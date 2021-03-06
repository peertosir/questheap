CREATE TABLE app_user
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    login    VARCHAR(255),
    password VARCHAR(255),
    role_id  BIGINT,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_role
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_userrole PRIMARY KEY (id)
);

ALTER TABLE app_user
    ADD CONSTRAINT FK_USER_ON_ROLE FOREIGN KEY (role_id) REFERENCES user_role (id);

INSERT INTO user_role("name") VALUES ('ROLE_USER'), ('ROLE_ADMIN');