CREATE TABLE IF NOT EXISTS notes (
    title varchar(50) NOT NULL,
    note varchar(500) NOT NULL
);
INSERT INTO notes (title, note) VALUES
  ('First note', 'Привет, Дневник'),
  ('New note', 'Каждый день новая запись');