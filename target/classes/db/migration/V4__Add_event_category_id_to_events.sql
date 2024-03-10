ALTER TABLE events
ADD COLUMN event_category_id INT,
ADD CONSTRAINT fk_event_category
    FOREIGN KEY (event_category_id)
    REFERENCES events_categories(entity_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE;
