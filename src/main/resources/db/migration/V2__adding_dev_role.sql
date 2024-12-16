-- Insert the DEVELOPER role
INSERT INTO role (name)
VALUES ('DEVELOPER')
ON CONFLICT (name) DO NOTHING;

-- Verify user existence and insert the role assignment
INSERT INTO user_roles (user_profile_id, role_id)
VALUES (
           (SELECT id FROM user_profile WHERE email = 'obeyblessing@gmail.com'),
           (SELECT id FROM role WHERE name = 'DEVELOPER')
       )
ON CONFLICT DO NOTHING;