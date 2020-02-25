-- TABLE users
CREATE TABLE users
(
    id       BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    position VARCHAR(255) NOT NULL,
    age      INT          NOT NULL,
    email    VARCHAR(255) NOT NULL
)
# самый быстрый движок для БД InnoDB
    ENGINE = InnoDB;
# Table: roles
CREATE TABLE roles
(
    id   BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
) ENGINE = InnoDB;

# Table for mapping users and roles: user_roles
# Внешние ключи позволяют установить связи между таблицами. Внешний ключ устанавливается для столбцов
# из зависимой, подчиненной таблицы, и указывает на один из столбцов из главной таблицы.
# Для создания ограничения внешнего ключа после FOREIGN KEY указывается столбец таблицы, который
# будет представляет внешний ключ. А после ключевого слова REFERENCES указывается имя связанной
# таблицы, а затем в скобках имя связанного столбца, на который будет указывать внешний ключ
CREATE TABLE users_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id),
    UNIQUE (user_id, role_id)
) ENGINE = InnoDB;

-- Inset data
INSERT INTO users VALUES (1, "shurik", "12345", "father", 35, "1@tut.by");
INSERT INTO users VALUES (2, "yulia", "123", "mather", 33, "2@tut.by");

INSERT INTO roles
VALUES (1, "ADMIN");
INSERT INTO roles
VALUES (2, "USER");

INSERT INTO users_roles
VALUES (1, 1);
