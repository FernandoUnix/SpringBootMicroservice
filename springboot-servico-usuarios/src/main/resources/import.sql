INSERT INTO usuarios (username, password, enabled, nome , email) VALUES ('fernando', '123', true, 'Fernando','fernando@email.com')
INSERT INTO usuarios (username, password, enabled, nome , email) VALUES ('admin', '123', true, 'admin','admin@email.com')

INSERT INTO roles (nome) values ('ROLE_USER')
INSERT INTO roles (nome) values ('ROLE_ADMIN')

INSERT INTO usuarios_roles (usuario_id, role_id) values(1,1)
INSERT INTO usuarios_roles (usuario_id, role_id) values(2,2)
INSERT INTO usuarios_roles (usuario_id, role_id) values(2,1)