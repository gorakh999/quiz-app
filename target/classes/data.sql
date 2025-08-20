
INSERT INTO question (id, text, correct_answer, category, difficulty) VALUES
  (1, 'What is the capital of France?', 'Paris', 'Geography', 'Easy'),
  (2, 'Which data structure uses LIFO?', 'Stack', 'Computer Science', 'Easy'),
  (3, '2 + 2 * 2 = ?', '6', 'Math', 'Easy');

INSERT INTO question_options (question_id, options) VALUES
  (1, 'Paris'), (1, 'London'), (1, 'Berlin'), (1, 'Madrid'),
  (2, 'Queue'), (2, 'Stack'), (2, 'Graph'), (2, 'Tree'),
  (3, '4'), (3, '6'), (3, '8'), (3, '10');
