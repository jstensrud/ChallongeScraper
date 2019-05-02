USE SmashTournyDB
GO

CREATE TABLE dbo.Character(
	Name	varchar(40),
	Game	varchar(50),
	Skin	varchar(40),
	PRIMARY KEY (Name, Game, Skin)
	)