insert into user (name, email, rank, about) values ('Magda G', 'magdag@gmail.com', 'MASTERCHEF', 'Know master chef from Poland');
insert into user (name, email, rank, about) values ('Oliver D', 'oli.ravioli@gmail.com', 'PRO', 'Just pro on the app');
insert into user (name, email, rank, about) values ('NewBlueShirtGUY', 'blueshirtguy@gmail.com', 'BEGINNER', 'Hi');

insert into content (title, type, creator_id, description)
values ('Cooked chicken', 'RECIPE', 1,'very good chicken');
insert into content (title, type, creator_id,  description)
values ('Peanut butter jelly', 'RECIPE', 2, 'fun recipe for fun');
insert into content (title, type, creator_id,  description)
values ('Fast chicken with tomatoes', 'CHALLENGE', 2, 'Fast recipe to cook with chicken and tomatoes - what is your idea?');
insert into content (title, type, creator_id,  description)
values ('Easy pasta', 'CHALLENGE', 3, 'very good chicken');
insert into content (title, type, creator_id,  description)
values ('Cheap asian food', 'CHALLENGE', 3, 'Cheap and easy to make asian food.');

insert into comment (text, creator_id, content_id) values ('What is that, stupid recipe!', 1, 2);
insert into comment (text, creator_id, content_id) values ('Great :p', 2, 1);
insert into comment (text, creator_id, content_id) values ('Amazing, thank you :)', 3, 1);
insert into comment (text, creator_id, content_id) values ('Yea, but it tastes good :p', 2, 2);
insert into comment (text, creator_id, content_id) values ('Check out my Cooked chicken recipe!', 1, 3);
insert into comment (text, creator_id, content_id) values ('Do you like carbonara?', 2, 4);
insert into comment (text, creator_id, content_id) values ('Check out my EGGSaclly pasta', 1, 4);

