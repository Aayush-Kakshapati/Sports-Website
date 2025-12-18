-- Create database
CREATE DATABASE IF NOT EXISTS sports_db;

USE sports_db;

-- Create sport table
CREATE TABLE IF NOT EXISTS sports (
    sport_id INT PRIMARY KEY AUTO_INCREMENT,
    sport_name VARCHAR(50) NOT NULL,
    description TEXT
);

-- Create sport details table
CREATE TABLE IF NOT EXISTS sport_details (
    detail_id INT PRIMARY KEY AUTO_INCREMENT,
    sport_id INT NOT NULL,
    rules TEXT,
    history TEXT,
    equipment TEXT,
    famous_players TEXT,
    FOREIGN KEY (sport_id) REFERENCES sports(sport_id)
);

-- Create admin table
CREATE TABLE IF NOT EXISTS admins (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP
);

-- Insert default admin user
INSERT INTO admins (username, password, email) 
VALUES ('admin', 'admin0', 'admin@sportsworld.com');

-- Insert sample sports data
INSERT INTO sports (sport_name, description) VALUES
('Football', 'Association football, commonly known as football or soccer, is a team sport played between two teams of 11 players.'),
('Basketball', 'Basketball is a team sport in which two teams, most commonly of five players each, opposing one another on a rectangular court.'),
('Tennis', 'Tennis is a racket sport that can be played individually against a single opponent or between two teams of two players each.'),
('Volleyball', 'Volleyball is a team sport in which two teams of six players are separated by a net. Each team tries to score points by grounding a ball on the other teams court.'),
('Cricket', 'Cricket is a bat-and-ball game played between two teams of eleven players on a field at the centre of which is a 22-yard pitch.'),
('Baseball', 'Baseball is a bat-and-ball game played between two opposing teams who take turns batting and fielding.'),
('Rugby', 'Rugby is a contact team sport which originated in England in the first half of the 19th century.'),
('Hockey', 'Hockey is a sport in which two teams play against each other by trying to maneuver a ball or a puck into the opponents goal using a hockey stick.'),

('Table Tennis', 'Table tennis, also known as ping-pong, is a sport in which two or four players hit a lightweight ball back and forth across a table using small rackets.'),
('Golf', 'Golf is a club-and-ball sport in which players use various clubs to hit balls into a series of holes on a course in as few strokes as possible.'),
('Swimming', 'Swimming is an individual or team racing sport that requires the use of ones entire body to move through water.'),

-- Insert sample sport details
INSERT INTO sport_details (sport_id, rules, history, equipment, famous_players) VALUES
(1, 'The game is played on a rectangular field with a goal at each end. The object is to score by getting the ball into the opposing goal. Each team has 11 players.', 'The modern game of football originated in England in the 19th century. The first set of rules was established in 1863.', 'Football, goal posts, corner flags, appropriate footwear.', 'Pele, Maradona, Messi, Ronaldo'),
(2, 'Two teams of five players each try to score by shooting a ball through a hoop elevated 10 feet above the ground.', 'Invented in 1891 by James Naismith in Springfield, Massachusetts.', 'Basketball, hoop, backboard, appropriate footwear.', 'Michael Jordan, LeBron James, Kobe Bryant'),
(3, 'Players use a racket to hit a ball over a net into the opponents court. Points are scored when the opponent fails to return the ball.', 'Modern tennis originated in Birmingham, England, in the late 19th century.', 'Tennis racket, tennis balls, net, appropriate footwear.', 'Roger Federer, Rafael Nadal, Serena Williams'),
(4, 'Teams of six try to ground the ball on the opponents court. Each team is allowed three touches before the ball must be sent over the net.', 'Invented in 1895 by William G. Morgan in Massachusetts, USA.', 'Volleyball, net, court, knee pads.', 'Karch Kiraly, Giba, Lang Ping'),
(5, 'Two teams of 11 players each. The batting team tries to score runs, while the bowling and fielding team tries to dismiss the batsmen.', 'Originated in England in the 16th century.', 'Bat, ball, wickets, protective gear.', 'Sachin Tendulkar, Don Bradman, Virat Kohli'),
(6, 'Teams alternate between batting and fielding. The batting team tries to score runs by hitting a pitched ball and running bases.', 'Originated in England and developed in the United States.', 'Bat, ball, gloves, bases.', 'Babe Ruth, Jackie Robinson, Ichiro Suzuki'),
(7, 'Two teams of 15 players each try to carry, pass, or kick the ball to the opponents end zone.', 'Originated at Rugby School, England, in the 19th century.', 'Rugby ball, mouthguard, cleats.', 'Jonah Lomu, Richie McCaw, Martin Johnson'),
(8, 'Players use sticks to hit a ball or puck into the opponents goal. Played on ice or field.', 'Field hockey dates back to ancient Egypt; ice hockey developed in Canada in the 19th century.', 'Stick, ball/puck, protective gear.', 'Wayne Gretzky, Dhyan Chand, Sidney Crosby'),
(9, 'Players hit a lightweight ball back and forth across a table using small rackets. Points are scored when the opponent fails to return the ball.', 'Originated in England in the 1880s.', 'Table, net, paddles, ball.', 'Ma Long, Jan-Ove Waldner, Ding Ning'),
(10, 'Players use clubs to hit balls into a series of holes on a course in as few strokes as possible.', 'Originated in Scotland in the 15th century.', 'Clubs, balls, tees, golf course.', 'Tiger Woods, Jack Nicklaus, Rory McIlroy'),
(11, 'Competitors race in water using various strokes such as freestyle, backstroke, breaststroke, and butterfly.', 'Swimming has been practiced since prehistoric times; first competitions in the 19th century.', 'Swimsuit, goggles, swim cap.', 'Michael Phelps, Katie Ledecky, Ian Thorpe'),

