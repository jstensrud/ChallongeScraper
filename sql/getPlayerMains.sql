USE [_SmashDB]
GO

CREATE PROCEDURE [dbo].[getPlayerMains]
	(@PlayerTag_1 varchar(30))

AS

IF NOT EXISTS(SELECT * FROM [Player] WHERE [Tag] = @PlayerTag_1)
	Return 1

SELECT m.PlayerTag, p.Crew, m.CharName, m.CharSkin, m.Game
	FROM Mains m
	JOIN Player p ON p.Tag = @PlayerTag_1