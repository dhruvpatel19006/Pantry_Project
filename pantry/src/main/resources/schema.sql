CREATE TABLE IF NOT EXISTS user_items (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    expiration_date TEXT NOT NULL,
    quantity INTEGER DEFAULT 1,
    created_at TEXT DEFAULT (date('now'))
);