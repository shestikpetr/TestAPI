CREATE TABLE users
(
    id          BIGSERIAL PRIMARY KEY,
    username    VARCHAR(255) NOT NULL UNIQUE,
    email       VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    system_role VARCHAR(50)  NOT NULL DEFAULT 'USER',
    avatar_url  VARCHAR(255)
);

CREATE TABLE groups
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    cover_url   VARCHAR(255)
);

CREATE TABLE group_memberships
(
    id       BIGSERIAL PRIMARY KEY,
    user_id  BIGINT      NOT NULL,
    group_id BIGINT      NOT NULL,
    role     VARCHAR(50) NOT NULL DEFAULT 'MEMBER',
    CONSTRAINT fk_membership_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_membership_group FOREIGN KEY (group_id) REFERENCES groups (id),
    CONSTRAINT uq_user_group UNIQUE (user_id, group_id)
);
