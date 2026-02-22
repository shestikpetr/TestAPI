ALTER TABLE group_memberships
    DROP CONSTRAINT fk_membership_user,
    DROP CONSTRAINT fk_membership_group;

ALTER TABLE group_memberships
    ADD CONSTRAINT fk_membership_user
        FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_membership_group FOREIGN KEY (group_id) REFERENCES groups (id) ON
        DELETE CASCADE;