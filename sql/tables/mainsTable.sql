USE SmashTournyDB
GO

CREATE TABLE [dbo].[Mains](
	[PlayerTag]	varchar(30)	FOREIGN KEY REFERENCES Player([Tag]),
	CharName	varchar(40),
	Game		varchar(50),
	CharSkin	varchar(40),
	FOREIGN KEY ([CharName], [Game], [CharSkin]) REFERENCES
		Character([Name], [Game], [Skin]),
	PRIMARY KEY (PlayerTag, CharName, Game, CharSkin)
)

