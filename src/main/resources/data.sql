TRUNCATE TABLE posts;

INSERT INTO posts (title, content, author)
VALUES
    ('Первый пост', 'Это содержимое первого поста', 'admin'),
    ('Второй пост', 'Второй пост', 'user'),
    ('Третий пост', 'Три', 'user');
