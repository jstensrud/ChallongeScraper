USE [_SmashDB]
GO

CREATE PROCEDURE [dbo].[getMatchesForTourney]
	(@TournamentID_1 [int])
AS

-- Make sure player and tourney are valid
IF @TournamentID_1 is NULL
	Return 1
IF NOT EXISTS (SELECT * FROM [Tournament] WHERE [Name] = @TournamentID_1)
	RETURN 2

-- Display
SELECT m.MatchID, m.WinnerTag, m.LoserTag, m.Score
	FROM Match m
	JOIN Tournament t ON t.TournamentID = @TournamentID_1
