create table Person(
	Id int IDENTITY(1,1) PRIMARY KEY,
    Name varchar(255),
	Description varchar(255)
)

create table Message(
	Id int IDENTITY(1,1) PRIMARY KEY,
	DirectId int FOREIGN KEY REFERENCES Person(Id),
	ReceiverId int FOREIGN KEY REFERENCES Person(Id),
	MessageText nvarchar(255)
)

create table Account(
	UserName nvarchar(255) PRIMARY KEY,
	Pass nvarchar(255),
	Email nvarchar(255),
	Phone int
)

drop table Message;
drop table Person;

insert into Person(Name)
values ('Server'),
('Client')

select * from Person;
select * from Message;

UPDATE Person SET Name = 'Name2' WHERE Id = 2 

INSERT INTO Message(DirectId,ReceiverId,MessageText)
VALUES (1, 2, 'MESS');

SELECT message.id, sendPerson.name as 'Sender Name', receiverPerson.name as 'Receiver Name', message.MessageText as 'Message Text'  FROM Message message 
JOIN Person receiverPerson ON message.ReceiverId = receiverPerson.id
JOIN Person sendPerson ON message.DirectId = sendPerson.id;
