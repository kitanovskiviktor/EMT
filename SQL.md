CREATE MATERIALIZED VIEW IF NOT EXISTS books_by_author AS
SELECT a.id AS author_id, a.name, a.surname, COUNT(b.id) AS books_count
FROM author a
LEFT JOIN book b ON a.id = b.author_id
GROUP BY a.id, a.name, a.surname;

CREATE OR REPLACE FUNCTION refresh_books_by_author()
RETURNS trigger AS $$
BEGIN
REFRESH MATERIALIZED VIEW books_by_author;
RETURN NULL;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS refresh_books_by_author_trigger ON book;

CREATE TRIGGER refresh_books_by_author_trigger
AFTER INSERT OR UPDATE OR DELETE ON book
FOR EACH STATEMENT
EXECUTE FUNCTION refresh_books_by_author();

-----------------------------------------------------------------------------

CREATE MATERIALIZED VIEW authors_by_country AS
SELECT c.id AS country_id, c.name AS country_name, COUNT(a.id) AS authors_count
FROM country c
LEFT JOIN author a ON c.id = a.country_id
GROUP BY c.id, c.name;

-----------------------------------------------------------------------------