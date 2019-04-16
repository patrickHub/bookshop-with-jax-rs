CREATE DATABASE IF NOT EXISTS bookshopWithJAXRSdb;
USE bookshopWithJAXRSdb;

DROP table books;

CREATE TABLE books(
    bookID INT AUTO_INCREMENT,
    bookTitle VARCHAR(50) NOT NULL,
    bookDescription VARCHAR (500),
    bookPrice float NOT NULL,
    bookImgPath VARCHAR (50) NOT NULL,
    bookLink VARCHAR(100) NOT NULL,
    bookPublishedDate DATE NOT NULL,
    
    CONSTRAINT Book_PK PRIMARY KEY (bookID)
   
);

CREATE TABLE authors(
	authorID INT AUTO_INCREMENT,
    authorFirstName VARCHAR(30) NOT NULL,
    authorLastName VARCHAR(30) NOT NULL,
    authorBirthdate DATE NOT NULL,
    authorBlogURL VARCHAR(50),
    
    CONSTRAINT Authors_PK PRIMARY KEY (authorID)
    
);

CREATE TABLE bookAuthor(
	bookAuthorID INT AUTO_INCREMENT,
    bookID INT,
    authorID INT,
    
    CONSTRAINT BookAuthor_PK  PRIMARY KEY (bookAuthorID),
    CONSTRAINT BookAuthor_Books_FK FOREIGN KEY (bookID) REFERENCES Books(bookID),
    CONSTRAINT BookAuthor_Authors_FK FOREIGN KEY (authorID) REFERENCES Authors(authorID)
);


 INSERT INTO Authors(authorFirstName, authorLastName, authorBirthdate, authorBlogURL) 
		VALUES('Captain S. P.', 'Meek', '1977/12/29', 'http://www.gutenberg.org');
 INSERT INTO Authors(authorFirstName, authorLastName, authorBirthdate, authorBlogURL) 
		VALUES('Sewell', 'Peaslee Wright', '1977/12/29', 'http://www.gutenberg.org');
INSERT INTO Authors(authorFirstName, authorLastName, authorBirthdate, authorBlogURL) 
		VALUES('Damon Francis', 'Knight', '1977/12/29', 'http://www.gutenberg.org');
INSERT INTO Authors(authorFirstName, authorLastName, authorBirthdate, authorBlogURL) 
		VALUES('Roger', 'Dee', '1977/12/29', 'http://www.gutenberg.org');
INSERT INTO Authors(authorFirstName, authorLastName, authorBirthdate, authorBlogURL) 
		VALUES('Gerald Allan', 'Sohl', '1977/12/29', 'http://www.gutenberg.org');
INSERT INTO Authors(authorFirstName, authorLastName, authorBirthdate, authorBlogURL) 
		VALUES('Jim', 'Meek', '1977/12/29', 'http://www.gutenberg.org');
INSERT INTO Authors(authorFirstName, authorLastName, authorBirthdate, authorBlogURL) 
		VALUES('Andrew', 'Fetler', '1977/12/29', 'http://www.gutenberg.org');
INSERT INTO Authors(authorFirstName, authorLastName, authorBirthdate, authorBlogURL) 
		VALUES('Fritz', 'Leiber', '1977/12/29', 'http://www.gutenberg.org');
INSERT INTO Authors(authorFirstName, authorLastName, authorBirthdate, authorBlogURL) 
		VALUES('Jean', 'Janis', '1977/12/29', 'http://www.gutenberg.org');
INSERT INTO Authors(authorFirstName, authorLastName, authorBirthdate, authorBlogURL) 
		VALUES('Johnny', 'Mayhem', '1977/12/29', 'http://www.gutenberg.org');
INSERT INTO Authors(authorFirstName, authorLastName, authorBirthdate, authorBlogURL) 
		VALUES('Arthur', 'Savage', '1977/12/29', 'http://www.gutenberg.org');
INSERT INTO Authors(authorFirstName, authorLastName, authorBirthdate, authorBlogURL) 
		VALUES('Robert', 'Young', '1977/12/29', 'http://www.gutenberg.org');
INSERT INTO Authors(authorFirstName, authorLastName, authorBirthdate, authorBlogURL) 
		VALUES('R', 'DeWitt Miller', '1977/12/29', 'http://www.gutenberg.org');
INSERT INTO Authors(authorFirstName, authorLastName, authorBirthdate, authorBlogURL) 
		VALUES('L. J.', 'Stecher JR.', '1977/12/29', 'http://www.gutenberg.org');
INSERT INTO Authors(authorFirstName, authorLastName, authorBirthdate, authorBlogURL) 
		VALUES('William', 'Stuart', '1977/12/29', 'http://www.gutenberg.org');
INSERT INTO Authors(authorFirstName, authorLastName, authorBirthdate, authorBlogURL) 
		VALUES('Murray', 'Leinster', '1977/12/29', 'http://www.gutenberg.org');
INSERT INTO Authors(authorFirstName, authorLastName, authorBirthdate, authorBlogURL) 
		VALUES('Edward', 'Ludwig', '1977/12/29', 'http://www.gutenberg.org');


INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)
	VALUES('The Thief of Time', 'HARVEY WINSTON, paying teller of the First National Bank of Chicago, stripped the band from a bundle of twenty dollar bills, counted out seventeen of them and added them to the pile on the counter before him.',
				20.00, 'the_thief_of_time.jpg', '1930/01/02', 'https://www.gutenberg.org/ebooks/28617');
INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)
	VALUES('Astounding Stories of Super Science', 'Commander John Hanson relates an interplanetary adventure illustrating the splendid Service spirit of the men of the Special Patrol.', 
				20.00, 'astounding_stories_of_super_science.jpg', '1931/01/04', 'https://www.gutenberg.org/ebooks/30177');
INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)
	VALUES('Special Delivery', 'All Len had to hear was the old gag: \"We have never lost a father yet.\" His child was not even born and it was thoroughly unbearable!',
				20.00, 'special_delivery.jpg', '1954/04/02', 'https://www.gutenberg.org/ebooks/32011');
INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)
	VALUES('Clean Break', 'A veteran veterinarian might have vamoosed—but Watts had to help any sick animal....!',
				20.00, 'clean_break.jpg', '1953/11/10', 'https://www.gutenberg.org/ebooks/32212');
INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)
	VALUES('he Seventh Order', 'A veteran veterinarian might have vamoosed—but Watts had to help any sick animal....!',
				20.00, 'the_seventh_order.jpg', '1952/03/16', 'https://www.gutenberg.org/ebooks/32327');
INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)
	VALUES('Pet Farm', 'The next worst thing to hell is being shanghaied into the Paradise of an alien planet!',
				20.00, 'pet_farm.jpg', '1954/02/23', 'https://www.gutenberg.org/ebooks/32344');
INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)
	VALUES('The Place Where Chicago Was', 'Well, they finally got rid of war. For the first time there was peace on Earth—since the only possible victims were the killers themselves!',
				20.00, 'the_place_where_chicago_was.jpg', '1962/02/04', 'https://www.gutenberg.org/ebooks/51832');
INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)
	VALUES('Cry Snooker', 'At the breakfast table next morning George gave her the diamond cocktail ring she had drooled over. Rosy gave him the self-winding time piece he had slobbered over in Cellinis window.', 
				20.00, 'cry_snooker.jpg', '1960/10/12', 'https://www.gutenberg.org/ebooks/51570');
INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)
	VALUES('Later Than You Think', 'It is much later. The question is ... how late?', 
				20.00, 'later_than_you_think.jpg', '1950/10/10', 'https://www.gutenberg.org/ebooks/50753');
INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)
	VALUES('Rough Translation', 'Do not be ashamed if you can not blikkel any more. It is because you could not help framishing.',
				20.00, 'later_than_you_think.jpg', '1956/06/05', 'https://www.gutenberg.org/ebooks/31980');
INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)
	VALUES('The Animated Pinup', 'To make it clear how normal everything was when the evening started out, I will let you in at the time Willy phoned me.',
				20.00, 'the_animated_pinup.jpg', '1953/07/15', 'https://www.gutenberg.org/ebooks/32345');
INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)
	VALUES('World Beyond Pluto', 'They loaded the over-age spaceship at night because Tritons one spaceport was too busy with the oreships from Neptune during the day to handle it.',
				20.00, 'the_animated_pinup.jpg', '1958/11/13', 'https://www.gutenberg.org/ebooks/32820');
INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)
	VALUES('The Butterfly Kiss', 'When Sykin Supcel was kidnaped, no one on Earth was less surprised than Dr. Horace Wilton, Chief Military Psychologist of the Solar Navy.',
				20.00, 'the_butterfly_kiss.jpg', '1953/12/25', 'https://www.gutenberg.org/ebooks/40991');
INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)
	VALUES('Sweet Tooth', 'The aliens were quite impressed by Earths technical marvels—they found them just delicious!',
				35.00, 'sweet_tooth.jpg', '1963/10/20', 'https://www.gutenberg.org/ebooks/50924');
INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)
	VALUES('Swenson, Dispatcher', 'There were no vacuums in Space Regulations, so Swenson—well, you might say he knew how to plot courses through sub-ether legality!',
				35.00, 'swenson_dispatcher.jpg', '1956/04/10', 'https://www.gutenberg.org/ebooks/51331');
INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)
	VALUES('Perfect Answer', 'Getting there may be half the fun ... but it is also all of a societys chance of survival!',
				35.00, 'perfect_answer.jpg', '1958/06/28', 'https://www.gutenberg.org/ebooks/51482');
INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)
	VALUES('Kreativity For Kats', 'They are the aliens among us—and their ways and wonders are stranger than extraterrestrials!', 
				50.00, 'kreativity_for_kats.jpg', '1961/04/28', 'https://www.gutenberg.org/ebooks/51493');
INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)
	VALUES('The Little Man Who Was not Quite', 'You could say Jonesy and/or I were not all there, but I do not see it that way. How much of Stanley was or was not there?',
				50.00, 'the_little_man.jpg', '1961/12/08', 'https://www.gutenberg.org/ebooks/51698');
INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)
	VALUES('Third Planet', 'The aliens had lost their lives to nuclear war—but their loss might be the salvation of Earth!',
				20.00, 'third_planet.jpg', '1963/04/08', 'https://www.gutenberg.org/ebooks/52574');
INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)
	VALUES('To Save Earth', 'The life of everyone on Earth depended on hich they had long ago lost!',
				20.00, 'to_save_earth.jpg', '1963/10/08', 'https://www.gutenberg.org/ebooks/53059');

			