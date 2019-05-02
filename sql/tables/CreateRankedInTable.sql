USE [SmashTournyDB]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE RankedIn (
	[PlayerTag] VARCHAR(30) FOREIGN KEY REFERENCES Player([Tag]) NOT NULL,
	[RankPeriod] VARCHAR(20) NOT NULL,
	[Game] VARCHAR(50) NOT NULL,
	[RankGroup] VARCHAR(50) NOT NULL,
	[Rank] VARCHAR(3) NOT NULL,
	FOREIGN KEY ([RankPeriod], [Game], [RankGroup]) REFERENCES Ranking([Period], [Game], [Group]),
	PRIMARY KEY (PlayerTag, RankPeriod, Game, RankGroup)
);
