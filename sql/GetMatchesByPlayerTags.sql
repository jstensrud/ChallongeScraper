USE [_SmashDB]
GO

CREATE PROCEDURE [dbo].[getMatchesForPlayers]
	(@PlayerTag_1 [varchar](30),
	@PlayerTag_2 [varchar](30))
AS

-- Make sure player exists
IF @PlayerTag_1 IS NULL or @PlayerTag_2 IS NULL
	Return 1
IF NOT EXISTS (SELECT * FROM [Player] WHERE [Tag] = @PlayerTag_1) OR NOT EXISTS (SELECT * FROM [Player] WHERE [Tag] = @PlayerTag_2)
	RETURN 2

SELECT x.WinnerTag, x.LoserTag, x.Score, t.[Name]
FROM (
	SELECT m.WinnerTag, m.LoserTag, m.Score, m.TournamentID
		FROM [Match] m
		WHERE m.WinnerTag = @PlayerTag_1 AND m.LoserTag = @PlayerTag_2
UNION ALL
	SELECT m.WinnerTag, m.LoserTag, m.Score
		FROM [Match] m
		WHERE m.WinnerTag = @PlayerTag_2 AND m.LoserTag = @PlayerTag_1
	) x
JOIN [Tournament] t ON t.TournamentID = x.TournamentID
