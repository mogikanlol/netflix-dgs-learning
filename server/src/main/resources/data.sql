INSERT INTO server_user (id, name)
VALUES
('440f122a-544f-458b-9061-c026f8a92840', 'Test User');

INSERT INTO post (id, title, date, user_id)
VALUES
('b2fff139-ddcd-4338-b9d6-882158acdc6f', 'Post #1', now(), '440f122a-544f-458b-9061-c026f8a92840'),
('293b8569-afa8-42c2-90f1-0cf5e36c8e83', 'Post #2', now(), '440f122a-544f-458b-9061-c026f8a92840');